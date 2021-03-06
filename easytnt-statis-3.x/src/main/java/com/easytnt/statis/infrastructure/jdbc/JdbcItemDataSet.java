package com.easytnt.statis.infrastructure.jdbc;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.statis.domain.mark.MarkItemDataSet;
import com.easytnt.statis.domain.mark.MarkScore;
import com.easytnt.statis.domain.mark.ScoreMode;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class JdbcItemDataSet implements MarkItemDataSet {
    private static Logger logger = LoggerFactory.getLogger(JdbcItemDataSet.class);

    private int start = 0;

    private int size = 2000;

    private Date startTime;

    private Date endTime;

    private String markItemId;

    private JdbcTemplate jdbc;

    private boolean hasNext = true;

    private List<TeamAndMembers> teams = Lists.newArrayList();

    private Double error = -1d;
    
    private Integer errors =0;

    private int timesRequired = 1;

    public JdbcItemDataSet(JdbcTemplate jdbc,String markItemId){
        this(jdbc,markItemId,null, null);
    }

    public JdbcItemDataSet(JdbcTemplate jdbc,String markItemId,Date startTime,Date endTime) {
        this(jdbc,markItemId,0,2000,startTime,endTime);
    }

    public JdbcItemDataSet(JdbcTemplate jdbc,String markItemId,int start,int size,Date startTime,Date endTime) {
        if( startTime == null ){
            startTime = DateUtilWrapper.toDate("2000-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
        }
        if( endTime == null ){
            endTime = DateUtilWrapper.now();
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.jdbc = jdbc;
        this.markItemId = markItemId;
        this.start = start >= 0?start:0;
        this.size = size>=1?size:2000;
        dataInit();
    }

    private void dataInit(){
        getError();

        getTimesRequired();
        getTeamsAndMembers();
        getMarkers();
        
        getErrors();
    }

    private void getError(){
        String sql = "select error from ps_mark_item_score where mark_item_id=? and is_del = 0 and parent_score_id IS NULL ";
        this.error = jdbc.queryForObject(sql,(rs, rowNum) ->rs.getDouble("error"),this.markItemId);
    }
    
    private void getErrors(){
    	String sql = "SELECT count(1) as errors FROM ps_scoring_mark a WHERE a.unabled = 1 AND a.mark_item_id = ? AND a.is_del = 0 ";
        this.errors = jdbc.queryForObject(sql,(rs, rowNum) ->rs.getInt("errors"),this.markItemId);
    }
    
    private void getTimesRequired(){
        String sql = "select required_times from ps_mark_item where mark_item_id=?";
        this.timesRequired = jdbc.queryForObject(sql,(rs, rowNum) -> rs.getInt("required_times"),this.markItemId);
    }

    private void getTeamsAndMembers(){
        String sql = "select a.team_id,a.parent_team_id from ps_mark_team a where a.item_id=? and a.is_del=0";
        jdbc.query(sql,(rs)->{addTeam(rs);},this.markItemId);
    }

    private void getMarkers(){
        String sql = "select b.marker_id,a.team_id from ps_mark_team_member a " +
                "inner join ps_marker b on b.marker_id = a.marker_id where b.mark_item_id=? and b.is_del=0 ";
        jdbc.query(sql,(rs)->{addTeamMember(rs);},this.markItemId);
    }

    private void addTeam(ResultSet rs) throws SQLException{
        addTeam(rs.getString("team_id"), rs.getString("parent_team_id"));
    }

    private void addTeamMember(ResultSet rs) throws SQLException{
        for(TeamAndMembers tam:this.teams){
            tam.addMember(rs.getString("team_id"),rs.getString("marker_id"));
        }
    }

    @Override
    public Collection<MarkScore> next() {

        if(!this.hasNext())
            return null;

        //TODO (a)->{}
        List<MarkScore> markScores = buildMarkScore();
        if(markScores.size() > 0){
            this.start = this.start + this.size;
        }else{
            this.hasNext = false;
        }
        return markScores;
    }

    public boolean hasNext(){
        return this.hasNext;
    }

    private List<MarkScore> buildMarkScore() {
        Object[] args = new Object[]{this.markItemId,this.startTime,this.endTime,this.start,this.size};

        logger.debug("Loading markerscores by {}", Arrays.toString(args));

        ArrayList<MarkScore> markScores = Lists.newArrayList();
        String sql = "select a.marker_id,a.fetch_times,a.fetch_time, a.mark_type," +
                "a.submit_time,a.score,b.score as final_socre,b.times, a.unabled,a.submit_times " +
                "from ps_scoring_mark_handler a inner join ps_scoring_mark b on a.mark_item_id=b.mark_item_id " +
                "and a.mark_id = b.mark_id  and b.mark_item_id=? and a.fetch_time>=? and a.fetch_time<=? " +
                "and b.is_del=0 and a.mark_type!=1 and a.is_del=0 limit ?,?";
        
        jdbc.query(sql,rs ->{
        	//判断是否是问题卷
        	int unabled = rs.getInt("unabled");
        	double score = rs.getDouble("score");
        	if(unabled == 0){
        		score = -1d;
        	}
        	
            markScores.add(new MarkScore.Builder()
            		.mode(ScoreMode.wayOf(rs.getInt("mark_type")))
                    .error(this.error)
                    .submitTime(rs.getTimestamp("submit_time"))
                    .fetchTime(rs.getTimestamp("fetch_time"))
                    .finalScore(rs.getDouble("final_socre"))
                    .score(rs.getInt("score"))
                    .curTimes(rs.getInt("submit_times"))
                    .totalTimes(rs.getInt("times"))
                    .tartgetId(this.markItemId)
                    .timesRequired(this.timesRequired)
                    .build());
            String markerId = rs.getString("marker_id");
            List<TeamAndMembers> teams = getMarkerOf(markerId);
            for(TeamAndMembers tam:teams){
                markScores.add(new MarkScore.Builder()
                		.mode(ScoreMode.wayOf(rs.getInt("mark_type")))
                        .error(this.error)
                        .submitTime(rs.getTimestamp("submit_time"))
                        .fetchTime(rs.getTimestamp("fetch_time"))
                        .finalScore(rs.getDouble("final_socre"))
                        .score(rs.getInt("score"))
                        .curTimes(rs.getInt("submit_times"))
                        .totalTimes(rs.getInt("times"))
                        .tartgetId(tam.teamId)
                        .timesRequired(this.timesRequired)
                        .build());
            }
            markScores.add(new MarkScore.Builder()
            		.mode(ScoreMode.wayOf(rs.getInt("mark_type")))
                    .error(this.error)
                    .submitTime(rs.getTimestamp("submit_time"))
                    .fetchTime(rs.getTimestamp("fetch_time"))
                    .finalScore(rs.getDouble("final_socre"))
                    .score(rs.getInt("score"))
                    .curTimes(rs.getInt("submit_times"))
                    .totalTimes(rs.getInt("times"))
                    .tartgetId(rs.getString("marker_id"))
                    .timesRequired(this.timesRequired)
                    .build());
        },args);
        return markScores;
    }

    private List<TeamAndMembers> getMarkerOf(String markerId) {
        for(TeamAndMembers tam:this.teams){
            List<TeamAndMembers> teams = tam.getMarkerTeams(markerId);
            if(teams.size()>0)
                return teams;
        }
        return Lists.newArrayList();
    }

    private void addTeam(String teamId,String parentTeamId){
        if(parentTeamId == null){
            this.teams.add(new TeamAndMembers(teamId));
            return;
        }

        for(TeamAndMembers tam:this.teams){
            boolean added = tam.add(teamId,parentTeamId,1);
            if(added){
                break;
            }
        }
    }

    class TeamAndMembers{
        private String teamId;

        private int level = 1;

        private List<TeamAndMembers> children;

        private List<String> markerIds;

        private TeamAndMembers(String teamId) {
            this.teamId = teamId;
            this.children = Lists.newArrayList();
            this.markerIds = Lists.newArrayList();
        }

        private List<TeamAndMembers> getMarkerTeams(String markerId){
            ArrayList<TeamAndMembers> teams = Lists.newArrayList();
            if (this.isMemberOf(markerId)) {
                teams.add(this);
                for(TeamAndMembers tam:this.children){
                    List<TeamAndMembers> childenTam = tam.getMarkerTeams(markerId);
                    teams.addAll(childenTam);
                }
            }
            Collections.sort(teams,Comparator.comparingInt(TeamAndMembers::getLevel));
            return teams;
        }

        private int getLevel(){
            return this.level;
        }

        private boolean isMemberOf(String markerId) {
            if(this.markerIds.contains(markerId)){
                return true;
            }
            for(TeamAndMembers tam:this.children){
                boolean b = tam.isMemberOf(markerId);
                if(b){
                    return true;
                }
            }

            return false;
        }

        private boolean add(String childTeamId,String parentTeamId,int level){
            if(this.teamId.equals(parentTeamId)){
                this.addChild(childTeamId);
                return true;
            }
            if(this.hasChildren()){
                for(TeamAndMembers tam:this.children){
                    boolean b = tam.add(childTeamId,parentTeamId,level+1);
                    if(b){
                        return b;
                    }
                }
            }
            return false;
        }

        private boolean hasChildren(){
            return this.children.size() > 0 ;
        }

        private void addChild(String teamId){
            this.children.add(new TeamAndMembers(teamId));
        }

        private void addMember(String teamId,String markerId){
            if(this.teamId.equals(teamId)){
                this.markerIds.add(markerId);
            }else{
                for(TeamAndMembers childTeam:this.children){
                    childTeam.addMember(teamId,markerId);
                }
            }
        }
    }
}
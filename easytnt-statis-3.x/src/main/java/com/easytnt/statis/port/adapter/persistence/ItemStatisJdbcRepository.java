package com.easytnt.statis.port.adapter.persistence;

import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.share.domain.id.mark.MarkerId;
import com.easytnt.share.domain.id.mark.MarkerTeamId;
import com.easytnt.share.domain.id.subject.SubjectId;
import com.easytnt.statis.domain.mark.*;
import com.google.common.collect.Lists;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ItemStatisJdbcRepository implements ItemStatisRepository {
    private JdbcTemplate jdbcTemplate;

    public ItemStatisJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ItemStatis> newItemStatisFor(MarkItemId itemId) {
        String sql = "select a.name,a.score,a.required_times,b.error from ps_mark_item a " +
                "inner join ps_mark_item_score b on b.mark_item_id =a.mark_item_id  " +
                "where a.purpose=1 and a.is_del = 0 and b.parent_score_id is null and a.mark_item_id = ? ";

        ItemStatis itemStatis = jdbcTemplate.queryForObject(sql,(rs,index)->
                new MarkItemStatis(itemId, rs.getString("name"),
                        rs.getInt("required_times"), rs.getDouble("score"),
                        rs.getDouble("error")),itemId.id());

        List<ItemStatis> itemStatises = Lists.newArrayList();
        itemStatises.add(itemStatis);

        String itemName = itemStatis.getItemName();
        int timesRequire = itemStatis.getTimesRequired();
        double fullScore = itemStatis.getFullScore();
        double error = itemStatis.getError();

        List<MarkTeam> teams = Lists.newArrayList();
        getItemTeams(itemId,1,teams);

        for(MarkTeam team:teams){
            itemStatises.add(new TeamItemStatis(team.teamId,team.name,itemId,itemName,
                    timesRequire,fullScore,error,team.planned));
            List<Marker> markers = getTeamMarker(team);
            addMarker(markers,itemStatises,itemId,itemName,timesRequire,fullScore,error);
        }

        List<Marker> markers = getItemMarker(itemId);
        addMarker(markers,itemStatises,itemId,itemName,timesRequire,fullScore,error);

        return itemStatises;
    }

    private void addMarker(List<Marker> markers,List<ItemStatis> itemStatises,MarkItemId itemId,
                         String itemName,int timesRequire,double fullScore,double error){
        for(Marker marker:markers){
            itemStatises.add(new MarkerItemStatis(marker.markerId,marker.name,itemId,
                    itemName,timesRequire,fullScore,error,marker.planned));
        }
    }

    private List<Marker> getItemMarker(MarkItemId itemId) {
        String sql = "select m.marker_id,m.name,m.planned from ps_marker m left join (" +
                "select b.marker_id from ps_mark_team_member a " +
                "inner join ps_marker b on b.marker_id = a.marker_id " +
                "where a.mark_item_id=? and b.is_del=0 and b.is_del=0 " +
                ") t on m.marker_id = t.marker_id where m.mark_item_id =? and m.is_del=0 and t.marker_id is null";

        List<Marker> marker =  jdbcTemplate.query(sql,
                (rs, rowNum) -> new Marker(new MarkerId(rs.getString("marker_id")),
                        rs.getString("name"),rs.getInt("planned")),
                itemId.id(),itemId.id());
        return marker;
    }

    private List<Marker> getTeamMarker(MarkTeam team) {
        String sql = "select b.marker_id,b.name,b.planned from ps_mark_team_member a " +
                "inner join ps_marker b on b.marker_id = a.marker_id " +
                "where a.team_id=? and b.is_del=0 and b.is_del=0 ";
        List<Marker> marker =  jdbcTemplate.query(sql,
                (rs, rowNum) -> new Marker(new MarkerId(rs.getString("marker_id")),
                        rs.getString("name"),rs.getInt("planned")),
                team.teamId.id());
        return marker;
    }

    private void getItemTeams(MarkItemId itemId,int level,List<MarkTeam> result){
        List<MarkTeam> thisTeams = findTeamOf(itemId,level);
        if(thisTeams != null){
            for(MarkTeam team:thisTeams){
                result.add(team);
                getItemTeams(itemId,level+1,result);
            }

        }
    }

    private List<MarkTeam> findTeamOf(MarkItemId itemId, int level){
        String sql = "select a.team_id,a.name,a.planned from ps_mark_team a where a.mark_item_id=? and a.is_del=0 and a.level=?";
        List<MarkTeam> teamIds =  jdbcTemplate.query(sql,
                (rs, rowNum) -> new MarkTeam(new MarkerTeamId(rs.getString("team_id")),
                        rs.getString("name"),rs.getInt("planned")),
                itemId.id(),level);
        return teamIds;
    }

    @Override
    public List<MarkItemId> findMarkItemIdOf(SubjectId subjectId) {
        String sql = "select mark_item_id from ps_mark_item where purpose=1 and is_del = 0 and subject_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new MarkItemId(rs.getString("mark_item_id")),subjectId.id());
    }

    class MarkTeam{
        private MarkerTeamId teamId;

        private String name;

        private int planned;

        MarkTeam(MarkerTeamId teamId,String name,int planned){
            this.teamId = teamId;
            this.name = name;
            this.planned = planned;
        }

    }

    class  Marker{
        private MarkerId markerId;

        private String name;

        private int planned;

        public Marker(MarkerId markerId, String name,int planned) {
            this.markerId = markerId;
            this.name = name;
            this.planned = planned;
        }
    }
}
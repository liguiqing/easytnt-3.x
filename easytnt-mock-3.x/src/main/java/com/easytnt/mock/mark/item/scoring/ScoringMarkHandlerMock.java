package com.easytnt.mock.mark.item.scoring;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.commons.util.ArraysUtilWraper;
import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.commons.util.NumberUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.Repeator;
import com.easytnt.mock.mark.item.MarkItemMock;
import com.easytnt.mock.mark.item.MarkItemScoreMock;
import com.easytnt.mock.mark.team.MarkerMock;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class ScoringMarkHandlerMock extends AbstractMock {
    private Object[] scoreingMarkIds;

    private int size = 0;

    private double finishedRate = 0.9;

    private double unableRate = 0.05;

    private Inner inner;

    @Override
    public String table() {
        return "ps_scoring_mark_handler";
    }

    @Override
    public String getIdField() {
        return "handler_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        mock();
        switch(key){
            case "handler_id": return ids();
            case "mark_id": return inner.realMarkIds;
            case "mark_item_id": return inner.markItemIds;
            case "marker_id": return inner.markerIds;
            case "mark_type": return this.repeator.repeatOf(this.size(),2);
            case "fetch_times": return inner.fetchTimeses;
            case "fetch_time": return inner.fetchTimes;
            case "valid": return this.repeator.repeatOf(this.size(),1);
            case "submit_time": return inner.submitTimes;
            case "submit_times": return inner.submitTimeses;
            case "score": return inner.scores;
            case "scores": return inner.scoreses;
            case "unabled": return inner.unableds;
            case "unabled_catagory": return inner.unabledCatagory;
            case "marker_seq": return this.repeator.repeatOf(this.size(),1);
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    protected Object[] deleteIds(){
        return this.getValues("mark_item_id");
    }

    protected String deleteIdField(){
        return "mark_item_id";
    }

    protected void correct(JdbcTemplate jdbc){
        String sql1 = "update ps_scoring_mark set score = ? ,times = ? where mark_id=?";
        ArrayList<Object[]> args1 = Lists.newArrayList();
        for(Scoreable scoreable:inner.scoreables) {
            args1.add(new Object[]{scoreable.score,scoreable.times,scoreable.markId});
        }
        jdbc.batchUpdate(sql1, args1);

        String sql2 = "update ps_scoring_mark a inner join ps_scoring_mark_handler b on a.mark_id=b.mark_id set a.unabled=b.unabled where b.unabled=1";
        jdbc.update(sql2);
    }

    private void mock(){
        if(inner == null){
            inner = new Inner();
            inner.mock();
            this.size = inner.size;
        }
    }

    @Override
    public int size(){
        mock();
        return this.size;
    }

    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds("SMH","",suffixes);
        return ids;
    }

    @Override
    public int order() {
        return 61;
    }

    private class Inner{
        private Repeator repeator;

        private ScoringMarkMock mark;

        private MarkerMock maker;

        private MarkItemMock markItem;

        private MarkItemScoreMock markItemScore;

        private int length = 0;

        private int size = 0;

        private String[] markIds;

        private String[] realMarkIds;

        private Integer[] times;

        private String[] markItemIds;

        private String[] markerIds;

        private Double[] scores;

        private String[] scoreses;

        private Date[] fetchTimes;

        private Integer[] fetchTimeses;

        private Date[] submitTimes;

        private Integer[] submitTimeses;

        private Integer[] unableds;

        private Integer[] unabledCatagory;

        private List<Scoreable> scoreables = Lists.newArrayList();

        private Inner(){
            this.mark = getOtherMock(ScoringMarkMock.class);
            this.maker = getOtherMock(MarkerMock.class);
            this.repeator = SpringContextUtil.getBean(Repeator.class);
            this.markItem = getOtherMock(MarkItemMock.class);
            this.markItemScore = getOtherMock(MarkItemScoreMock.class);

            Object[] allMarkIds = this.mark.getValues("mark_id");
            int max = Double.valueOf(Math.ceil(allMarkIds.length * finishedRate)).intValue();
            List<Object> toList = Arrays.stream(allMarkIds).collect(Collectors.toList());
            Collections.shuffle(toList);

            String[] newAllMarkIds = toList.toArray(new String[]{});
            this.markIds = this.repeator.repeatOfGroupOfEachMaxLt(1, 1, max, newAllMarkIds);
            this.length = this.markIds.length;
        }


        private void mock(){
            ArrayList<String> alMarkId = Lists.newArrayList();
            ArrayList<Integer> alTimes = Lists.newArrayList();
            ArrayList<String> alMarkItemId = Lists.newArrayList();
            ArrayList<String> alMarkerId = Lists.newArrayList();
            ArrayList<Double> alScore = Lists.newArrayList();
            ArrayList<String> alScores = Lists.newArrayList();
            ArrayList<Date> alFetchTime = Lists.newArrayList();
            ArrayList<Integer> alFetchTimes = Lists.newArrayList();
            ArrayList<Date> alSubmitTime = Lists.newArrayList();
            ArrayList<Integer> alSubmitTimes = Lists.newArrayList();
            ArrayList<Integer> alUnableds = Lists.newArrayList();
            ArrayList<Integer> alUnabledCatagory = Lists.newArrayList();

            int[] unableRandom = new int[this.length];
            int unableCount = Double.valueOf(unableRate * this.length).intValue();
            for(int i=0;i<unableCount;){
                int r = NumberUtilWrapper.randomBetween(0,this.length-1);
                if(unableRandom[r] == 0) {
                    unableRandom[r] = 1;
                    i++;
                }
            }

            for(int i = 0;i<this.length;i++){
                this.size++;
                alMarkId.add(this.markIds[i]);
                alTimes.add(1);

                String markItemId = (String) this.mark.getValueForMarkId(this.markIds[i],"mark_item_id");
                alMarkItemId.add(markItemId);

                String[] markerIds = this.maker.markerIdsOf(markItemId);
                int[] ii = new int[markerIds.length];
                random(0,markerIds.length,ii);
                alMarkerId.add(markerIds[ii[0]]);

                Date fetchTime = DateUtilWrapper.now();
                alFetchTime.add(fetchTime);
                alFetchTimes.add(1);

                int dr = NumberUtilWrapper.randomBetween(10,20);
                alSubmitTime.add(DateUtilWrapper.addSecondTo(fetchTime, dr));
                alSubmitTimes.add(1);

                int u = unableRandom[i];
                Double[] scoreLimit = this.markItemScore.getMarkItemScoreLimit(markItemId);
                double score;
                if(u == 0) {
                    score = getScore(scoreLimit);
                    alScore.add(score);
                    alScores.add(score + "");
                    alUnableds.add(0);
                    alUnabledCatagory.add(null);
                }else{
                    alScore.add(null);
                    alScores.add(null);
                    alUnableds.add(1);
                    alUnabledCatagory.add(1);
                    continue;
                }

                Integer required = (Integer) this.mark.getValueForMarkId(this.markIds[i],"required");
                if(required.compareTo(3) == 0){
                    int random = NumberUtilWrapper.randomBetween(1, 10);
                    if(random >= 3){
                        this.size++;
                        alMarkId.add(this.markIds[i]);
                        alTimes.add(2);
                        alMarkItemId.add(markItemId);
                        alMarkerId.add(markerIds[ii[1]]);
                        double score2 = getScore(scoreLimit);
                        alScore.add(score2);
                        alScores.add(score2 + "");
                        fetchTime = DateUtilWrapper.now();
                        alFetchTime.add(fetchTime);
                        alFetchTimes.add(2);
                        dr = NumberUtilWrapper.randomBetween(10,20);
                        alSubmitTime.add(DateUtilWrapper.addSecondTo(fetchTime, dr));
                        alSubmitTimes.add(2);
                        alUnableds.add(0);
                        alUnabledCatagory.add(null);

                        Double error = this.markItemScore.getError(markItemId);
                        if(error.compareTo(Math.abs(score-score2))< 0){
                            scoreables.add(new Scoreable(this.markIds[i],(score+score2)/2,2));
                        }

                    }
                }else{
                    scoreables.add(new Scoreable(this.markIds[i],score,1));
                }
            }
            this.realMarkIds = alMarkId.toArray(new String[]{});
            this.times = alTimes.toArray(new Integer[]{});
            this.markItemIds = alMarkItemId.toArray(new String[]{});
            this.markerIds = alMarkerId.toArray(new String[]{});
            this.scores = alScore.toArray(new Double[]{});
            this.scoreses = alScores.toArray(new String[]{});
            this.fetchTimes = alFetchTime.toArray(new Date[]{});
            this.fetchTimeses = alFetchTimes.toArray(new Integer[]{});
            this.submitTimes = alSubmitTime.toArray(new Date[]{});
            this.submitTimeses = alSubmitTimes.toArray(new Integer[]{});
            this.unableds = alUnableds.toArray(new Integer[]{});
            this.unabledCatagory = alUnabledCatagory.toArray(new Integer[]{});
        }

        private double getScore(Double[] scores){
            int length = scores.length;
            int i = NumberUtilWrapper.randomBetween(0,length-1);
            return scores[i];
        }

        private void random(int x,int y,int[] rs){
            int v = NumberUtilWrapper.randomBetween(x,y-1);

            int length = rs.length;
            for(int i=1;i<length;i++){
                if(v == rs[i]){
                    random(x,y-1,rs);
                }
            }
            for(int i=1;i<length;i++){
                if(rs[i] == 0) {
                    rs[i] = v;
                    break;
                }
            }
        }
    }

    private class Scoreable{
        private String markId;

        private Double score;

        private int times;

        public Scoreable(String markId, Double score, int times) {
            this.markId = markId;
            this.score = score;
            this.times = times;
        }

        public String toString(){
            return this.markId+"-"+times+"-"+score;
        }
    }
}
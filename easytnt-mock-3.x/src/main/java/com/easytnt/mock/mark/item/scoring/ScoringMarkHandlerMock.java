package com.easytnt.mock.mark.item.scoring;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.commons.util.ArraysUtilWraper;
import com.easytnt.commons.util.NumberUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.Repeator;
import com.easytnt.mock.mark.item.MarkItemMock;
import com.easytnt.mock.mark.item.MarkItemScoreMock;
import com.easytnt.mock.mark.team.MarkerMock;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ScoringMarkHandlerMock extends AbstractMock {
    private Object[] scoreingMarkIds;

    private int size = 0;

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
        switch(key){
            case "handler_id": return new Object[]{};
            case "mark_id": return new Object[]{};
            case "mark_item_id": return new Object[]{};
            case "marker_id": return new Object[]{};
            case "mark_type": return this.repeator.repeatOf(this.size(),"Formal");
            case "fetch_times": return new Object[]{};
            case "fetch_time": return new Object[]{};
            case "valid": return this.repeator.repeatOf(this.size(),1);
            case "submit_time": return new Object[]{};
            case "submit_times": return new Object[]{};
            case "score": return new Object[]{};
            case "scores": return new Object[]{};
            case "unabled": return this.repeator.repeatOfMixedRandom(this.size(),1,0.1,1);
            case "unabled_catagory": return new Object[]{};
            case "marker_seq": return new Object[]{};
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    private int countSize(){
        ScoringMarkMock mark = getOtherMock(ScoringMarkMock.class);
        if(this.scoreingMarkIds == null) {

            Object[] allMarkIds = mark.getValues("mark_id");
            int max = Double.valueOf(Math.ceil(allMarkIds.length * 0.9)).intValue();
            List<Object> toList = Arrays.stream(allMarkIds).collect(Collectors.toList());
            Collections.shuffle(toList);
            allMarkIds = toList.toArray(new Object[]{});
            this.scoreingMarkIds = this.repeator.repeatOfGroupOfEachMaxLt(1, 1, max, allMarkIds);
        }
        int size = 0;
        int length = this.scoreingMarkIds.length;
        for(int i=0;i<length;i++){
            Object markId = this.scoreingMarkIds[i];
            Object markItemId = mark.getValueForMarkId((String) markId, "mark_item_id");
        }
        return size;
    }

    @Override
    public int size(){
        if(this.size == 0)
            this.size = countSize();
        return this.size;
    }

    @Override
    public String[] ids() {
        return new String[0];
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

        private String[] markIds;

        private String[] markItemId;

        private Integer[] fetchTimes;

        private Inner(){
            this.mark = getOtherMock(ScoringMarkMock.class);
            this.maker = getOtherMock(MarkerMock.class);
            this.repeator = SpringContextUtil.getBean(Repeator.class);
            this.markItem = getOtherMock(MarkItemMock.class);
            this.markItemScore = getOtherMock(MarkItemScoreMock.class);

            Object[] allMarkIds = this.mark.getValues("mark_id");
            int max = Double.valueOf(Math.ceil(allMarkIds.length * 0.9)).intValue();
            List<Object> toList = Arrays.stream(allMarkIds).collect(Collectors.toList());
            Collections.shuffle(toList);

            String[] newAllMarkIds = toList.toArray(new String[]{});
            this.markIds = this.repeator.repeatOfGroupOfEachMaxLt(1, 1, max, newAllMarkIds);
            this.length = this.markIds.length;

        }


        private void markItemId(){
            this.markItemId = new String[this.length];
            setValues(this.markItemId,"mark_item_id");
        }

        private void fetchTimes(){
            ArrayList<Integer> alTimes = Lists.newArrayList();
            ArrayList<String> alMarkItemId = Lists.newArrayList();
            ArrayList<String> alMarkerId = Lists.newArrayList();
            ArrayList<Double> alScore = Lists.newArrayList();
            ArrayList<String> alScores = Lists.newArrayList();
            for(int i = 0;i<this.length;i++){
                Integer required = (Integer) this.mark.getValueForMarkId("required",this.markIds[i]);
                alTimes.add(1);
                String markItemId = (String) this.mark.getValueForMarkId("mark_item_id",this.markIds[i]);
                Double[] scoreLimit = this.markItemScore.getMarkItemScoreLimit(markItemId);


                alMarkItemId.add(markItemId);
                String[] markerIds = this.maker.markerIdsOf(markItemId);
                int[] ii = new int[markerIds.length];
                random(0,markerIds.length,ii);
                alMarkerId.add(markerIds[ii[0]]);
                double score = getScore(scoreLimit);
                alScore.add(score);
                alScores.add(score + "");
                if(required.compareTo(3) == 0){
                    int random = NumberUtilWrapper.randomBetween(1, 10);
                    if(random >= 3){
                        alTimes.add(2);
                        alMarkItemId.add(markItemId);
                        alMarkerId.add(markerIds[ii[1]]);
                        score = getScore(scoreLimit);
                        alScore.add(score);
                        alScores.add(score + "");
                    }
                }
            }
        }

        private double getScore(Double[] scores){
            int length = scores.length;
            int i = NumberUtilWrapper.randomBetween(0,length);
            return scores[i];
        }

        private void random(int x,int y,int[] rs){
            int v = NumberUtilWrapper.randomBetween(x,y);

            int length = rs.length;
            for(int i=1;i<length;i++){
                if(v == rs[i]){
                    random(x,y,rs);
                }
            }
            for(int i=1;i<length;i++){
                if(rs[i] == 0) {
                    rs[i] = v;
                    break;
                }
            }
        }

        private void setValues(Object[] values,String key){
            for(int i = 0;i<this.length;i++){
                values[i] = this.mark.getValueForMarkId(key,this.markIds[i]);
            }
        }
    }
}
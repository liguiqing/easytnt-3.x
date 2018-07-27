package com.easytnt.statis.domain.task;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.util.CollectionsUtilWrapper;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.statis.domain.mark.MarkItemDataSet;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.MarkScore;
import com.easytnt.statis.domain.mark.StatisIndex;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 统计任务
 *
 * @author Liguiqing
 * @since V3.0
 */

public class StatisTask {
    private StatisTaskId taskId;

    private MarkItemId markItemId;

    private AtomicBoolean running = new AtomicBoolean(false);

    private MarkItemDataSet dataSet;

    private List<ItemStatis> statises;

    private StatisIndex index;

    private boolean finished = false; //是否已经计算完成

    private StatisTask(){

    }

    public void start(){
        if(this.isRunning()){
           return ;
        }
        this.finished = false;
        this.statis();
    }

    public void restart(){
        this.start();
    }

    public void pause(){
        this.running.set(false);
    }

    public void shutdown(){
        this.running.set(false);
        this.clear();
    }

    private void addAll(List<ItemStatis> itemStatis) {
        if(CollectionsUtilWrapper.hasElements(itemStatis)){
            for(ItemStatis is:itemStatis){
                add(is);
            }
        }
    }

    private void add(ItemStatis statis){
        if(statis.supports(this.markItemId) && !statises.contains(statis)){
            this.statises.add(statis);
        }
    }

    private void clear(){
        this.statises.clear();
    }

    private boolean isRunning(){
        return !this.running.compareAndSet(false,true);
    }

    private boolean isPauseOrShutdown(){
        return !this.isRunning();
    }

    private void statis(){
        if(this.dataSet == null) {
            return;
        }

        if(this.isPauseOrShutdown()){
            return ;
        }

        Collection<MarkScore> scores = this.dataSet.next();
        if(CollectionsUtilWrapper.isNullOrEmpty(scores)){
            this.finished = true;
            for(ItemStatis aStatis:this.statises){
                index.statis(aStatis);
            }
            return;
        }

        for(MarkScore score:scores){
            for(ItemStatis statis:this.statises){
                statis.getAndIncrement(score);
            }
        }

        if(this.running.get()){
            this.statis();
        }
    }

    public boolean isFinished() {
        return finished;
    }

    /**
     * 读取统计结果，如果统计未完成，返回null
     * 调用时，请先调用<code>isFinished</code>
     * @return
     */
    public List<ItemStatis> getStatises() {
        if(this.isFinished())
            return statises;
        return null;
    }

    public MarkItemId markItemId() {
        return markItemId;
    }

    public StatisTaskId taskId() {
        return taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisTask task = (StatisTask) o;
        return Objects.equal(markItemId, task.markItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(markItemId);
    }

    public static class Builder{
        private  StatisTask task;

        public Builder(StatisTaskId taskId,MarkItemId markItemId){
            this.task = new StatisTask();
            this.task.taskId = taskId;
            this.task.markItemId = markItemId;
            this.task.statises = Lists.newArrayList();
        }

        public Builder useDataSet(MarkItemDataSet dataSet){
            this.task.dataSet = dataSet;
            return this;
        }

        public Builder statisFor(ItemStatis statis){
            this.task.add(statis);
            return this;
        }

        public Builder statisFor(List<ItemStatis> itemStatises){
            this.task.addAll(itemStatises);
            return this;
        }

        public Builder withIndex(StatisIndex index){
            this.task.index = index;
            return this;
        }

        public StatisTask build(){
            AssertionConcerns.assertArgumentNotNull(this.task.markItemId,"评题标识不能为空");
            AssertionConcerns.assertArgumentNotNull(this.task.dataSet,"评题数据不能为空");
            AssertionConcerns.assertArgumentTrue(this.task.statises.size() > 0,"统计目标不能为空");
            return this.task;
        }
    }
}
package com.easytnt.statis.domain.task;

import com.easytnt.commons.util.CollectionsUtilWrapper;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.statis.domain.mark.ItemDataSet;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.MarkScore;
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
    private MarkItemId markItemId;

    private AtomicBoolean running = new AtomicBoolean(false);

    private ItemDataSet dataSet;

    private List<ItemStatis> statises;

    private boolean finished = false; //是否已经计算完成

    public StatisTask(MarkItemId markItemId,ItemDataSet dataSet,List<ItemStatis> itemStatis) {
        this.markItemId = markItemId;
        this.dataSet = dataSet;
        this.statises = Lists.newArrayList();
        this.add(itemStatis);
    }


    private void add(List<ItemStatis> itemStatis) {
        if(CollectionsUtilWrapper.hasElements(itemStatis)){
            for(ItemStatis is:itemStatis){
                if(is.supports(this.markItemId) && !statises.contains(is)){
                    this.statises.add(is);
                }
            }
        }
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

    private void clear(){
        this.dataSet.clear();
        this.statises.clear();
    }

    private boolean isRunning(){
        return !this.running.compareAndSet(false,true);
    }

    private boolean isPauseOrShutdown(){
        return !this.isRunning();
    }

    private void statis(){
        if(this.isPauseOrShutdown()){
            return ;
        }

        Collection<MarkScore> scores = this.dataSet.next();
        if(scores == null){
            this.finished = true;
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
}
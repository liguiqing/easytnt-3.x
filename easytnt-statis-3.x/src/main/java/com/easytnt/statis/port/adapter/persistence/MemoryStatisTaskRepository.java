package com.easytnt.statis.port.adapter.persistence;

import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.statis.application.data.StatisQueryParamter;
import com.easytnt.statis.domain.task.StatisTask;
import com.easytnt.statis.domain.task.StatisTaskRepository;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * StatisTaskRepository内存实现
 *
 * @author Liguiqing
 * @since V3.0
 */

public class MemoryStatisTaskRepository implements StatisTaskRepository {


    private Map<String,StatisTask> tasks =  Maps.newConcurrentMap();

    @Override
    public StatisTask loadOf(MarkItemId itemId,StatisQueryParamter paramter) {
        return this.tasks.get(paramter.keyOf(itemId));
    }

    @Override
    public void save(StatisTask task,StatisQueryParamter paramter) {
        this.tasks.put(paramter.keyOf(task.markItemId()), task);
    }

    @Override
    public void remove(StatisTask task,StatisQueryParamter paramter) {
        task.shutdown();
        this.tasks.remove(paramter.keyOf(task.markItemId()));
    }
}
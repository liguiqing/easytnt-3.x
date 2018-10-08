package com.easytnt.statis.domain.task;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface StatisTaskRepository {
    StatisTask loadOf(StatisTaskId taskId);

    void save(StatisTask task);
}
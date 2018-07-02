package com.easytnt.statis.domain.task;

import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.statis.application.data.StatisQueryParamter;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface StatisTaskRepository {
    StatisTask loadOf(MarkItemId itemId,StatisQueryParamter paramter);

    void save(StatisTask task,StatisQueryParamter paramter);

    void remove(StatisTask task,StatisQueryParamter paramter);
}
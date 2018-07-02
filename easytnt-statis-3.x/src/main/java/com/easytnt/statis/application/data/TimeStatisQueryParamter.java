package com.easytnt.statis.application.data;

import com.easytnt.share.domain.id.mark.MarkItemId;

import java.util.Date;

/**
 * 起始时间统计参数
 *
 * @author Liguiqing
 * @since V3.0
 */

public class TimeStatisQueryParamter implements  StatisQueryParamter {

    private Date startTime;

    private Date endTime;


    public TimeStatisQueryParamter(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String keyOf(MarkItemId itemId) {
        long s = startTime == null?0l:startTime.getTime();
        long e = endTime == null?0l:endTime.getTime();
        return (itemId.id()+s+e).hashCode()+"";
    }

    public Date getStartTime() {
        return startTime;
    }

    public TimeStatisQueryParamter setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public TimeStatisQueryParamter setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }
}
package com.easytnt.statis.domain.task;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.share.domain.id.mark.MarkItemId;

import java.util.Date;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StatisTastKeyService {

    public StatisTaskId gen(MarkItemId markItemId, Date startTime, Date endTime){
        String id = (markItemId.id() + DateUtilWrapper.toString(startTime, "yyyy-MM-dd HH:mm:ss")
                + DateUtilWrapper.toString(endTime, "yyyy-MM-dd HH:mm:ss")).hashCode() + "";
        return new StatisTaskId("StatisTaskId-"+id);
    }
}
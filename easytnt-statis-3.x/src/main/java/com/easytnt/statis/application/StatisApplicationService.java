package com.easytnt.statis.application;

import com.easytnt.commons.util.CollectionsUtilWrapper;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.share.domain.id.subject.SubjectId;
import com.easytnt.statis.domain.mark.*;
import com.easytnt.statis.domain.mark.index.StatisFactory;
import com.easytnt.statis.domain.task.StatisTask;
import com.easytnt.statis.domain.task.StatisTaskId;
import com.easytnt.statis.domain.task.StatisTaskRepository;
import com.easytnt.statis.domain.task.StatisTastKeyService;
import com.easytnt.statis.infrastructure.DefaultDataSetFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StatisApplicationService {
    private static Logger logger = LoggerFactory.getLogger(StatisApplicationService.class);

    private StatisTaskRepository taskRepository;

    private ItemStatisRepository itemStatisRepository;

    private ItemDataSetFactory dataSetFactory;

    public StatisApplicationService(StatisTaskRepository taskRepository,
                                    ItemStatisRepository itemStatisRepository) {
        this(taskRepository,itemStatisRepository,null);
    }

    public StatisApplicationService(StatisTaskRepository taskRepository,
                                    ItemStatisRepository itemStatisRepository,
                                    ItemDataSetFactory dataSetFactory) {
        this.taskRepository = taskRepository;
        this.itemStatisRepository = itemStatisRepository;
        this.dataSetFactory = dataSetFactory == null ?new DefaultDataSetFactory():dataSetFactory;
    }

    public void statisForItem(String markItemId,Date startTime, Date endTime, DataSetFilter filter){
        this.statisForItem(new MarkItemId(markItemId),startTime,endTime,filter);
    }


    private void statisForItem(MarkItemId markItemId,Date startTime, Date endTime, DataSetFilter filter){
        logger.debug("Statis Of Item :{} is starting",markItemId);

        StatisTaskId taskId = new StatisTastKeyService().gen(markItemId,startTime,endTime);
        StatisTask task = taskRepository.loadOf(taskId);
        if(task != null)
            return;

        List<ItemStatis> itemStatis = itemStatisRepository.newItemStatisFor(markItemId);
        itemStatis.removeIf(statis -> filter.containsOf(statis.getId()));
        StatisIndex index = StatisFactory.getDefaultsStatis();
        ItemDataSet dataSet = dataSetFactory.newDataSetOf(markItemId,startTime,endTime);
        task = new StatisTask.Builder(taskId,new MarkItemId()).useDataSet(dataSet)
                .statisFor(itemStatis).withIndex(index).build();
        taskRepository.save(task);
        task.start();
    }

    public void statisForSubject(String subjectId, Date startTime, Date endTime, DataSetFilter filter){
        logger.debug("Statis Of Subject :{} is starting",subjectId);

        List<MarkItemId> itemIds = itemStatisRepository.findMarkItemIdOf(new SubjectId(subjectId))
                .stream().filter(s-> filter.containsOf(s)).collect(Collectors.toList());

        if(CollectionsUtilWrapper.hasElements(itemIds)){
            for (MarkItemId itemId:itemIds){
                this.statisForItem(itemId,startTime,endTime,filter);
            }
        }
    }
}
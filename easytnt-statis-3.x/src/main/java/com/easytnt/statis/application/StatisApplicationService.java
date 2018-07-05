package com.easytnt.statis.application;

import com.easytnt.commons.util.CollectionsUtilWrapper;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.share.domain.id.subject.SubjectId;
import com.easytnt.statis.application.data.StatisQueryParamter;
import com.easytnt.statis.domain.mark.ItemDataSet;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.ItemStatisRepository;
import com.easytnt.statis.domain.mark.StatisIndex;
import com.easytnt.statis.domain.mark.index.StatisFactory;
import com.easytnt.statis.domain.task.StatisTask;
import com.easytnt.statis.domain.task.StatisTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StatisApplicationService {
    private static Logger logger = LoggerFactory.getLogger(StatisApplicationService.class);

    private StatisTaskRepository taskRepository;

    private ItemStatisRepository itemStatisRepository;

    private ItemDataSet dataSet;

    public StatisApplicationService(StatisTaskRepository taskRepository,
                                    ItemStatisRepository itemStatisRepository, ItemDataSet dataSet) {
        this.taskRepository = taskRepository;
        this.itemStatisRepository = itemStatisRepository;
        this.dataSet = dataSet;
    }

    public void statisForItem(String markItemId,StatisQueryParamter paramter){
        this.statisForItem(new MarkItemId(markItemId),paramter);
    }


    private void statisForItem(MarkItemId markItemId,StatisQueryParamter paramter){
        logger.debug("Statis Of Item :{} is starting",markItemId);
        StatisTask task = taskRepository.loadOf(markItemId,paramter);
        if(task == null) {
            List<ItemStatis> itemStatis = itemStatisRepository.newItemStatisFor(markItemId);
            StatisIndex index = StatisFactory.getDefaultsStatis();
            task = new StatisTask.Builder(new MarkItemId()).useDataSet(dataSet).statisFor(itemStatis).withIndex(index).build();
            taskRepository.save(task,paramter);
        }

        task.start();
    }

    public void statisForSubject(String subjectId, StatisQueryParamter paramter){
        logger.debug("Statis Of Subject :{} is starting",subjectId);
        List<MarkItemId> itemIds = itemStatisRepository.findMarkItemIdOf(new SubjectId(subjectId));
        if(CollectionsUtilWrapper.hasElements(itemIds)){
            for (MarkItemId itemId:itemIds){
                this.statisForItem(itemId,paramter);
            }
        }
    }
}
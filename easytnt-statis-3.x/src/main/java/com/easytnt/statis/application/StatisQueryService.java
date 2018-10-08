package com.easytnt.statis.application;

import com.easytnt.commons.util.CollectionsUtilWrapper;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.share.domain.id.subject.SubjectId;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.ItemStatisRepository;
import com.easytnt.statis.domain.task.StatisTask;
import com.easytnt.statis.domain.task.StatisTaskId;
import com.easytnt.statis.domain.task.StatisTaskRepository;
import com.easytnt.statis.domain.task.StatisTastKeyService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 统计查询服务
 *
 * @author Liguiqing
 * @since V3.0
 */

public class StatisQueryService {
    private static Logger logger = LoggerFactory.getLogger(StatisQueryService.class);

    private StatisTaskRepository taskRepository;

    private ItemStatisRepository itemStatisRepository;

    public StatisQueryService(StatisTaskRepository taskRepository, ItemStatisRepository itemStatisRepository) {
        this.taskRepository = taskRepository;
        this.itemStatisRepository = itemStatisRepository;
    }

    public List<ItemStatis> queryStatisForItem(String markItemId, Date startTime, Date endTime){
        logger.debug("Query Statis Of MarkItem :{} i",markItemId);

        StatisTaskId taskId = new StatisTastKeyService().gen(new MarkItemId(markItemId),startTime,endTime);
        StatisTask task = taskRepository.loadOf(taskId);
        if(task.isFinished())
            return task.getStatises();

        logger.info("Statis Of MarkItem :{} is running,please try again later",markItemId);
        return Lists.newArrayList();
    }

    public List<ItemStatis> queryStatisForSubject(String subjectId, Date startTime, Date endTime){
        logger.debug("Query Statis Of Subject :{} i",subjectId);
        List<MarkItemId> itemIds = itemStatisRepository.findMarkItemIdOf(new SubjectId(subjectId));
        ArrayList<ItemStatis> statises = Lists.newArrayList();
        if(CollectionsUtilWrapper.hasElements(itemIds)){
            for (MarkItemId itemId:itemIds){
                List<ItemStatis> anItemStatis = this.queryStatisForItem(itemId.id(),startTime,endTime);
                statises.addAll(anItemStatis);
            }
        }
        return statises;
    }
    public List<ItemStatis> queryStatisForMarkItem(String markItemId, Date startTime, Date endTime){
    	logger.debug("Query Statis Of Subject :{} i",markItemId);
    	ArrayList<ItemStatis> statises = Lists.newArrayList();
		List<ItemStatis> anItemStatis = this.queryStatisForItem(markItemId,startTime,endTime);
		statises.addAll(anItemStatis);
    	return statises;
    }
}
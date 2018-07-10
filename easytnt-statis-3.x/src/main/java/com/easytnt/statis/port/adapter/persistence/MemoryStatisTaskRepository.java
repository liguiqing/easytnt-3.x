package com.easytnt.statis.port.adapter.persistence;

import com.easytnt.statis.domain.task.StatisTask;
import com.easytnt.statis.domain.task.StatisTaskId;
import com.easytnt.statis.domain.task.StatisTaskRepository;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * StatisTaskRepository内存实现
 *
 * @author Liguiqing
 * @since V3.0
 */

public class MemoryStatisTaskRepository implements StatisTaskRepository {
    private static Logger logger = LoggerFactory.getLogger(MemoryStatisTaskRepository.class);

    private Cache<StatisTaskId,StatisTask> runningTask;

    public MemoryStatisTaskRepository(){
        this(0, 0);
    }

    /**
     * @param maxSize　最大统计任务数
     * @param expire　任务驻留时长（秒）
     */
    public MemoryStatisTaskRepository(int maxSize,int expire) {
        if(maxSize <= 0)
            maxSize = 1000;
        if(expire <= 0)
            expire = 30;

        this.runningTask = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterAccess(expire, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public StatisTask loadOf(StatisTaskId taskId) {
        try {
            return this.runningTask.get(taskId, () -> {return null;});
        }catch (Exception e){
            logger.info("Statis Task of {} not found",taskId);
        }
        return null;
    }

    @Override
    public void save(StatisTask task) {
        this.runningTask.put(task.taskId(), task);
    }

}
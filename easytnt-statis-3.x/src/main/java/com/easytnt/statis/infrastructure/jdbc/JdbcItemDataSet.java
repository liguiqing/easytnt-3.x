package com.easytnt.statis.infrastructure.jdbc;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.statis.domain.mark.DataSetFilter;
import com.easytnt.statis.domain.mark.ItemDataSet;
import com.easytnt.statis.domain.mark.MarkScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class JdbcItemDataSet implements ItemDataSet {
    private static Logger logger = LoggerFactory.getLogger(JdbcItemDataSet.class);

    private int start = 0;

    private int size = 2000;

    private Date startTime;

    private Date endTime;

    private String markItemId;

    private JdbcTemplate jdbc;

    private boolean hasNext = true;

    //todo
    private String sql = "select 1 from dual limit ?,?";

    public JdbcItemDataSet(JdbcTemplate jdbc,String markItemId){
        this(jdbc,markItemId,null, null);
    }

    public JdbcItemDataSet(JdbcTemplate jdbc,String markItemId,Date startTime,Date endTime) {
        this.jdbc = jdbc;
        this.markItemId = markItemId;
        this.startTime = startTime == null ? DateUtilWrapper.toDate("2000-01-01 00:00:00","yyyy-MM-dd HH:mm:ss"):startTime;
        this.startTime = endTime == null ? DateUtilWrapper.now():endTime;
    }


    public JdbcItemDataSet(JdbcTemplate jdbc,String markItemId,int start,int size,Date startTime,Date endTime) {
        this(jdbc,markItemId,startTime,endTime);
        this.start = start >= 0?start:0;
        this.size = size>=1?size:2000;
    }

    @Override
    public Collection<MarkScore> next() {
        Object[] args = new Object[]{this.markItemId,this.startTime,this.endTime,this.start,this.size};
        logger.debug("Loading markerscores by {}", Arrays.toString(args));

        if(!this.hasNext)
            return null;

        //TODO (a)->{}
        List<MarkScore> markScores = jdbc.query(sql,(rs, rowNum) -> new MarkScore.Builder().build(),args);
        if(markScores != null){
            this.start = this.start + this.size;
        }else{
            this.hasNext = false;
        }
        return markScores;
    }


    public void setSql(String sql) {
        this.sql = sql;
    }
}
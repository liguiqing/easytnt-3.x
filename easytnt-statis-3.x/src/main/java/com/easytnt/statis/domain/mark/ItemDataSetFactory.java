package com.easytnt.statis.domain.mark;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.statis.infrastructure.jdbc.JdbcItemDataSet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface ItemDataSetFactory {

    default MarkItemDataSet newDataSetOf (MarkItemId markItemId, Date startTime, Date endTime){
        JdbcTemplate jdbc = SpringContextUtil.getBean(JdbcTemplate.class);
        AssertionConcerns.assertArgumentNotNull(jdbc,"无效的数据源");
        return new JdbcItemDataSet(jdbc,markItemId.id(),startTime,endTime);
    }

}
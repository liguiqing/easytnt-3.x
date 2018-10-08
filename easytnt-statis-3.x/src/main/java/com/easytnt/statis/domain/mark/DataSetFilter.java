package com.easytnt.statis.domain.mark;

import com.easytnt.commons.domain.Identity;

/**
 * 分析数据集合过滤器
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface DataSetFilter<ID extends Identity> {

    boolean containsOf(ID id);
}
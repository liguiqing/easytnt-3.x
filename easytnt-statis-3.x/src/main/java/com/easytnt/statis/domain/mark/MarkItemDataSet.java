package com.easytnt.statis.domain.mark;

import java.util.Collection;

/**
 * 评题数据集
 *
 * @author Liguiqing
 * @since V3.0
 */
public interface MarkItemDataSet {

    Collection<MarkScore> next();

    boolean hasNext();

}
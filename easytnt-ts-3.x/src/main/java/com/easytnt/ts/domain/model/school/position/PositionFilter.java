package com.easytnt.ts.domain.model.school.position;

/**
 * 教职工的职务过滤器
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface PositionFilter {

    boolean isSatisfied(Position position);
}
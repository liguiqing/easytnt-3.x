/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.ts.domain.model.school.common.Period;

/**
 * 职位扮演
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface Act {

    Position actTo(Staff staff, Period period);
}
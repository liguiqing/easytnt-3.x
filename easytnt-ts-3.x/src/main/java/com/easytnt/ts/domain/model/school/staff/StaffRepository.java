/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.commons.domain.EntityRepository;
import com.easytnt.ts.domain.model.school.SchoolId;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface StaffRepository  {

    void save(Staff staff);

    void delete(Staff staff);

    /**
     * 读取在职状态的教职工
     *
     * @param identity
     * @param schoolId
     * @return
     */
    Staff loadOfOn(String identity,StaffPost post,SchoolId schoolId);
}
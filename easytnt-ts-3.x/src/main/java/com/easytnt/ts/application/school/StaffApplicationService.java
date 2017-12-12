/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.application.school;

import com.easytnt.ts.domain.model.school.staff.Staff;
import com.easytnt.ts.domain.model.school.staff.StaffId;
import com.easytnt.ts.domain.model.school.staff.StaffRepository;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StaffApplicationService {

    private StaffRepository staffRepository;

    public void rename(String staffId,String newName){
        Staff staff = staffRepository.loadOfId(new StaffId(staffId));
        staff.rename(newName);
        staffRepository.save(staff);
    }
}
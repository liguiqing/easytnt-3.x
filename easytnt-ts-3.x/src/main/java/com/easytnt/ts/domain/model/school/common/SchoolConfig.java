/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.common;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.IdentifiedValueObject;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 学校配置项目
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SchoolConfig extends IdentifiedValueObject {
    private SchoolId schoolId;

    private Configuation configuation;

    public SchoolConfig(SchoolId schoolId, Configuation configuation) {
        AssertionConcerns.assertArgumentNotNull(schoolId,"请提供学校");
        AssertionConcerns.assertArgumentNotNull(configuation,"请提供学校配置");
        this.schoolId = schoolId;
        this.configuation = configuation;
    }

    public SchoolId getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(SchoolId schoolId) {
        this.schoolId = schoolId;
    }

    public Configuation getConfiguation() {
        return configuation;
    }

    public void setConfiguation(Configuation configuation) {
        this.configuation = configuation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolConfig that = (SchoolConfig) o;
        return Objects.equal(schoolId, that.schoolId) &&
                Objects.equal(configuation.getName(), that.configuation.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(schoolId, configuation.getName());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("schoolId", schoolId)
                .add("configuation", configuation)
                .toString();
    }

    protected SchoolConfig(){}
}
package com.easytnt.scan.domain.batch;

import com.easytnt.share.domain.id.exam.ExamId;
import com.easytnt.share.domain.id.subject.SubjectId; /**
 * 批次扫描时，实际扫描量与计划扫描量关系控制策略
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface BatchScanActualExpectedAllowedStrategy {

    /**
     * 是否允许实际数量超过扫描数量
     * 默认允许
     * @param examId
     * @param subjectId
     * @return
     */
    default boolean allowed(ExamId examId, SubjectId subjectId){
        return Boolean.TRUE;
    }
}
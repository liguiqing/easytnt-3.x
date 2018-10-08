package com.easytnt.statis.domain.mark;

import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.share.domain.id.subject.SubjectId;

import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface ItemStatisRepository {

    List<ItemStatis> newItemStatisFor(MarkItemId itemId);

    List<MarkItemId>  findMarkItemIdOf(SubjectId subjectId);
}
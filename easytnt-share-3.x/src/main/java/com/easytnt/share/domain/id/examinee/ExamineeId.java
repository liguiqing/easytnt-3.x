package com.easytnt.share.domain.id.examinee;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

import static com.easytnt.share.domain.id.IdPrefixes.ExamineeIdPrefix;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ExamineeId extends AbstractId {

    public ExamineeId(String anId) {
        super(anId);
    }

    public ExamineeId() {
        super(Identities.genIdNone(ExamineeIdPrefix));
    }
}
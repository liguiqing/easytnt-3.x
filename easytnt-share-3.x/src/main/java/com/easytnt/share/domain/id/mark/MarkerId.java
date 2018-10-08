package com.easytnt.share.domain.id.mark;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

import static com.easytnt.share.domain.id.IdPrefixes.MarkerIdPrefix;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class MarkerId extends AbstractId {

    public MarkerId(String anId) {
        super(anId);
    }

    public MarkerId() {
        super(Identities.genIdNone(MarkerIdPrefix));
    }
}
package com.easytnt.share.domain.id.mark;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;
import static com.easytnt.share.domain.id.IdPrefixes.MarkerTeamIdPrefix;
/**
 * @author Liguiqing
 * @since V3.0
 */

public class MarkerTeamId extends AbstractId {

    public MarkerTeamId(String anId) {
        super(anId);
    }

    public MarkerTeamId() {
        super(Identities.genIdNone(MarkerTeamIdPrefix));
    }
}
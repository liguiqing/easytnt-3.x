package com.easytnt.share.domain.id;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

import static com.easytnt.share.domain.id.IdPrefixes.PersonIdPrefix;

/**
 * 个人唯一标识
 *
 * @author Liguiqing
 * @since V3.0
 */

public class PersonId extends AbstractId {


    public PersonId(String anId) {
        super(anId);
    }

    public PersonId() {
        super(Identities.genIdNone(PersonIdPrefix));
    }

}
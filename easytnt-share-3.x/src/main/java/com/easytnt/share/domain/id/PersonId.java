package com.easytnt.share.domain.id;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

/**
 * 个人唯一标识
 *
 * @author Liguiqing
 * @since V3.0
 */

public class PersonId extends AbstractId {

    private static String prefix  = "PER";

    public PersonId(String anId) {
        super(anId);
    }

    public PersonId() {
        super(Identities.genIdDNone(prefix));
    }

    public static String prefix(){
        return prefix;
    }
}
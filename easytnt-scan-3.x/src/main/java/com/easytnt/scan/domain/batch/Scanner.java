package com.easytnt.scan.domain.batch;

import com.easytnt.commons.domain.ValueObject;
import com.easytnt.share.domain.id.PersonId;

/**
 * 扫描员
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Scanner extends ValueObject {
    private PersonId personId;

    private String name;

}
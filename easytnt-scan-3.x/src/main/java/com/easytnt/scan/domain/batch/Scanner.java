package com.easytnt.scan.domain.batch;

import com.easytnt.commons.domain.ValueObject;
import com.easytnt.share.domain.id.PersonId;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 扫描员
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Scanner extends ValueObject {
    private PersonId personId;

    private String name;

    public Scanner(PersonId personId, String name) {
        this.personId = personId;
        this.name = name;
    }

    public PersonId personId() {
        return personId;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("personId", personId)
                .add("name", name)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scanner scanner = (Scanner) o;
        return Objects.equal(personId, scanner.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(personId);
    }

    //Only 4 persistence
    protected  Scanner(){}
}
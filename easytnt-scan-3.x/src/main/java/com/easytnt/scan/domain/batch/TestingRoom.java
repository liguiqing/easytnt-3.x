package com.easytnt.scan.domain.batch;

import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 考场
 *
 * @author Liguiqing
 * @since V3.0
 */

public class TestingRoom extends ValueObject {

    private String point; //考点

    private String room;

    public TestingRoom(String point, String room) {
        this.point = point;
        this.room = room;
    }

    public String point() {
        return point;
    }

    public String room() {
        return room;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("point", point)
                .add("room", room)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestingRoom that = (TestingRoom) o;
        return Objects.equal(point, that.point) &&
                Objects.equal(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(point, room);
    }
}
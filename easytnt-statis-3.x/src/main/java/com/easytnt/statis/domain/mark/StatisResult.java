package com.easytnt.statis.domain.mark;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StatisResult {
    private  Number value;

    private  String percent;

    private double rate;

    private String name;

    public StatisResult( String name,Number value, double rate,String percent) {
        this.value = value;
        this.percent = percent;
        this.rate = rate;
        this.name = name;
    }

    public Number getValue(){
        return this.value;
    }

    public double getRate(){
        return this.rate;
    }

    public String getPercent(){
        return this.percent;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisResult that = (StatisResult) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .add("percent", percent)
                .add("rate", rate)
                .add("name", name)
                .toString();
    }
}
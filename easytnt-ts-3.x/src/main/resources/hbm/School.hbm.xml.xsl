<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping default-access="field" default-cascade="all" p>
    <class name="com.easytnt.ts.domain.model.school.School" table="ts_school" lazy="true">
        <id name="id" type="long" column="id" unsaved-value="-1">
            <generator class="native"/>
        </id>
        <property name="name" column="name" type="java.lang.String"/>
        <property name="name" column="name" type="java.lang.String"/>

        <component name="tenantId" class="com.easytnt.ts.domain.model.school.TenantId">
            <property name="tenantId" column="tenantId" type="java.lang.String"/>
        </component>
        <component name="schoolId" class="com.easytnt.ts.domain.model.school.SchoolId">
            <property name="schoolId" column="schoolId" type="java.lang.String"/>
        </component>
        <property name="name" column="name" type="java.lang.String"/>
        <property name="alias" column="alias" type="java.lang.String"/>
        <property name="type" column="type" >
            <type name="org.hibernate.type.EnumType">
                <param name="enumClass">com.easytnt.ts.domain.model.school.SchoolType</param>
                <param name="usedName">true</param>
            </type>
        </property>
    </class>
</hibernate-mapping>
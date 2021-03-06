package com.easytnt.commons.util;

import java.util.Collection;

/**
 * 集合处理包装器
 *
 * @author Liguiqing
 * @since V3.0
 */

public class CollectionsUtilWrapper {

    public static <E> boolean isNullOrEmpty(Collection<E> collection){
        return (collection == null) || collection.size() == 0;
    }

    public static <E> boolean hasElements(Collection<E> collection){
        return (collection != null) && collection.size() > 0;
    }
}
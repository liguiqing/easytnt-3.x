package com.easytnt.statis.domain.mark;

import java.util.Map;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface DataSetFilterBuilder {

    /**
     * filterMap keys:
     *  markItemIds
     *  teamIds
     *  scores
     * @param filterMap
     * @return
     */
     DataSetFilter concreate(Map<String, String[]> filterMap);
}
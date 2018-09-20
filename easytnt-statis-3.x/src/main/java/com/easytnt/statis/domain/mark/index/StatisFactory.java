package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.StatisIndex;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StatisFactory {

    private static StatisIndex defaults = new FinishedNoErrorsStatis()
            .append(new AvgScoreStatis()).append(new AvgSpeedStatis()).append(new SdScoreStatis());

    public static  StatisIndex getDefaultsStatis(){
        return defaults;
    }

}
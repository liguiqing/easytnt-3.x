package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.StatisIndex;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StatisFactory {

    private static StatisIndex defaults = new FinishedNoErrorsStatis()
            .append(new AvgScoreStatis()).append(new AvgSpeedStatis()).append(new SdScoreStatis())
            .append(new AvgScoreNoneZeroStatis()).append(new CurTimesTotalStatis(1)).append(new CurTimesTotalStatis(2));

    public static  StatisIndex getDefaultsStatis(){
        return defaults;
    }

}
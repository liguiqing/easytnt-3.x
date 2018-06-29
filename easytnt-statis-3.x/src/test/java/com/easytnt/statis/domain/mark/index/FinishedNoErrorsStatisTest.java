package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.symbol.NoneDataSlashSymbol;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class FinishedNoErrorsStatisTest {

    @Test
    public void testStatis()throws Exception{
        FinishedNoErrorsStatis statis = new FinishedNoErrorsStatis(null);
        ItemStatis itemStatis = mock(ItemStatis.class);
        when(itemStatis.getValids()).thenReturn(100);
        when(itemStatis.hasTotalRequired()).thenReturn(false);
        statis.computer(itemStatis);
        assertEquals(statis.getValue(),100);
        assertEquals(statis.getRate(),-1d,0d);
        assertEquals(statis.getPercent(),new NoneDataSlashSymbol().getSymbol());

        when(itemStatis.hasTotalRequired()).thenReturn(true);
        when(itemStatis.getTotalRequired()).thenReturn(100);
        statis.computer(itemStatis);
        assertEquals(statis.getValue(),100);
        assertEquals(statis.getRate(),1d,0d);
        assertEquals(statis.getPercent(),"100.00%");

        when(itemStatis.getTotalRequired()).thenReturn(130);
        statis.computer(itemStatis);
        assertEquals(statis.getValue(),100);
        assertEquals(statis.getRate(),1d,0.7692307692307693d);
        assertEquals("76.92%",statis.getPercent());

    }
}
package com.easytnt.scan.domain.batch;

import com.easytnt.share.domain.id.exam.ExamId;
import com.easytnt.share.domain.id.scan.BatchScanId;
import com.easytnt.share.domain.id.subject.AnswerSheetId;
import com.easytnt.share.domain.id.subject.SubjectId;
import com.google.common.collect.Lists;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * @author Liguiqing
 * @since V3.0
 */
@RunWith(PowerMockRunner.class)
public class BatchScanTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testBatchScan()throws Exception {
        BatchScan batchScan = new BatchScan(new TestingRoom("point","room"),new ExamId(),
                new SubjectId(),new AnswerSheetId(),new Scanner(),"name","fileName",100,100);
        assertTrue(batchScan.isScanOfRoom());
        assertEquals(batchScan.actual(),100);
        assertEquals(batchScan.expected(),100);
        assertTrue(batchScan.batchScanId().id().startsWith(BatchScanId.prefix()));

        batchScan = new BatchScan(new TestingRoom("point","room"),new ExamId(),
                new SubjectId(),new AnswerSheetId(),new Scanner(),"name","fileName",100);
        assertTrue(batchScan.isScanOfRoom());
        assertEquals(batchScan.actual(),100);
        assertEquals(batchScan.expected(),-1);
        assertTrue(batchScan.batchScanId().id().startsWith(BatchScanId.prefix()));

        batchScan = new BatchScan(new ExamId(), new SubjectId(),new AnswerSheetId(),new Scanner(),
                "name","fileName",100);
        assertTrue(!batchScan.isScanOfRoom());
        assertEquals(batchScan.actual(),100);
        assertEquals(batchScan.expected(),-1);
        assertTrue(batchScan.batchScanId().id().startsWith(BatchScanId.prefix()));

        batchScan = new BatchScan(new ExamId(), new SubjectId(),new AnswerSheetId(),new Scanner(),
                "name","fileName",100);
        assertTrue(!batchScan.isScanOfRoom());
        assertEquals(batchScan.actual(),100);
        assertEquals(batchScan.expected(),-1);
        assertTrue(batchScan.batchScanId().id().startsWith(BatchScanId.prefix()));

        batchScan = new BatchScan(new ExamId(), new SubjectId(),new AnswerSheetId(),new Scanner(),
                "name","fileName",100,100);
        assertTrue(!batchScan.isScanOfRoom());
        assertEquals(batchScan.actual(),100);
        assertEquals(batchScan.expected(),100);
        assertTrue(batchScan.batchScanId().id().startsWith(BatchScanId.prefix()));
    }

    @Test
    public void testSubmitting()throws Exception{
        BatchScan batchScan = new BatchScan(new TestingRoom("point","room"),new ExamId(),
                new SubjectId(),new AnswerSheetId(),new Scanner(),"name","fileName",100,100);
        batchScan.submitting();
        assertNotNull(batchScan.submittingTime());
        assertNull(batchScan.submittedTime());
        assertEquals(batchScan.sizeOfSubmitted(),0);

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("扫描批次未完成上传，不能结束怀疑处理" );
        batchScan.doubtsDone();
    }

    @Test
    public void testSubmitSheet()throws Exception{
        ExamId examId = new ExamId();
        SubjectId subjectId = new SubjectId();
        BatchScan batchScan = new BatchScan(new TestingRoom("point","room"),examId,subjectId,
                new AnswerSheetId(),new Scanner(),"name","fileName",3,5);
        batchScan.submitting();

        SheetScan sheet1 = mock(SheetScan.class);
        when(sheet1.sameBatchOf(batchScan)).thenReturn(Boolean.TRUE);
        batchScan.submitSheet(sheet1);

        assertTrue(batchScan.isSubmitting());
        assertNull(batchScan.submittedTime());
        assertEquals(batchScan.sizeOfSubmitted(),1);

        SheetScan sheet2 = mock(SheetScan.class);
        when(sheet2.sameBatchOf(batchScan)).thenReturn(Boolean.TRUE);
        batchScan.submitSheet(sheet2);

        SheetScan sheet3 = mock(SheetScan.class);
        when(sheet3.sameBatchOf(batchScan)).thenReturn(Boolean.TRUE);
        batchScan.submitSheet(sheet3);

        SheetScan sheet4 = mock(SheetScan.class);
        when(sheet4.sameBatchOf(batchScan)).thenReturn(Boolean.TRUE);
        batchScan.submitSheet(sheet4);

        SheetScan sheet5 = mock(SheetScan.class);
        when(sheet5.sameBatchOf(batchScan)).thenReturn(Boolean.TRUE);
        batchScan.submitSheet(sheet5);

        assertEquals(batchScan.sizeOfSubmitted(),5);

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("提交的答题卡不能超过实际扫描量" );
        SheetScan sheet6 = mock(SheetScan.class);
        when(sheet6.sameBatchOf(batchScan)).thenReturn(Boolean.TRUE);
        batchScan.submitSheet(sheet6);

    }


    @Test
    public void testSubmitSheets()throws Exception{
        ExamId examId = new ExamId();
        SubjectId subjectId = new SubjectId();
        BatchScan batchScan = new BatchScan(new TestingRoom("point","room"),examId,subjectId,
                new AnswerSheetId(),new Scanner(),"name","fileName",3,5);
        batchScan.submitting();
        SheetParser parser = mock(SheetParser.class);
        List<SheetScan> sheetScans = Lists.newArrayList();
        for(int i=0;i<5;i++){
            SheetScan sheet1 = mock(SheetScan.class);
            when(sheet1.sameBatchOf(batchScan)).thenReturn(Boolean.TRUE);
            sheetScans.add(sheet1);
        }
        String s = "";
        when(parser.parse(s, batchScan)).thenReturn(sheetScans);
        batchScan.submitSheets(s,parser);

        assertTrue(batchScan.isSubmitting());
        assertNull(batchScan.submittedTime());
        assertEquals(batchScan.sizeOfSubmitted(),5);

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("提交的答题卡不能超过实际扫描量" );
        SheetScan sheet6 = mock(SheetScan.class);
        when(sheet6.sameBatchOf(batchScan)).thenReturn(Boolean.TRUE);
        batchScan.submitSheet(sheet6);

    }
}
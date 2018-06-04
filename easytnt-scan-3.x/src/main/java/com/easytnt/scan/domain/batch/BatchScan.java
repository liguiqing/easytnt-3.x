package com.easytnt.scan.domain.batch;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.DomainEventPublisher;
import com.easytnt.commons.domain.Entity;
import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.share.domain.id.exam.ExamId;
import com.easytnt.share.domain.id.scan.BatchScanId;
import com.easytnt.share.domain.id.subject.AnswerSheetId;
import com.easytnt.share.domain.id.subject.SubjectId;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * 批次扫描
 *
 * @author Liguiqing
 * @since V3.0
 */

public class BatchScan extends Entity {
    private BatchScanId batchScanId;

    private ExamId examId;

    private SubjectId subjectId;

    private AnswerSheetId answerSheetId;

    private Scanner scanner;

    private String name;

    private String fileName;

    private TestingRoom room; //考场扫描

    private int expected = -1; //计划扫描人数,-1表示无计划扫描数

    private int actual; //实际扫描数

    private BatchScanDoubts scanDoubts;//扫描怀疑

    private Date submittingTime; //开始提交时间

    private Date  submittedTime; //提交完成时间

    private List<SheetScan> sheets;

    /**
     * 不按考场扫描，也无计划扫描数
     *
     * @param examId 考试唯一标识
     * @param subjectId 考试科目唯一标识
     * @param answerSheetId 考试科目用答题卡唯一标识
     * @param scanner 扫描人
     * @param name 扫描批次名称
     * @param fileName 扫描批次文件名
     * @param actual 实际扫描数量
     */
    public BatchScan(ExamId examId, SubjectId subjectId, AnswerSheetId answerSheetId,
                     Scanner scanner, String name, String fileName, int actual) {
        this(null,examId,subjectId,answerSheetId,scanner,name,fileName,-1,actual);
    }


    /**
     * 不按考场扫描，有计划扫描数
     *
     * @param examId 考试唯一标识
     * @param subjectId 考试科目唯一标识
     * @param answerSheetId 考试科目用答题卡唯一标识
     * @param scanner 扫描人
     * @param name 扫描批次名称
     * @param fileName 扫描批次文件名
     * @param expected 计划扫描量
     * @param actual 实际扫描数量
     */
    public BatchScan(ExamId examId, SubjectId subjectId, AnswerSheetId answerSheetId,
                     Scanner scanner, String name, String fileName, int expected, int actual) {
        this(null,examId,subjectId,answerSheetId,scanner,name,fileName,expected,actual);
    }


    /**
     * 按考场扫描不限数量
     *
     * @param examId 考试唯一标识
     * @param subjectId 考试科目唯一标识
     * @param answerSheetId 考试科目用答题卡唯一标识
     * @param scanner 扫描人
     * @param name 扫描批次名称
     * @param fileName 扫描批次文件名
     * @param actual 实际扫描数量
     */
    public BatchScan(TestingRoom room,ExamId examId, SubjectId subjectId, AnswerSheetId answerSheetId,
                     Scanner scanner, String name, String fileName, int actual) {
        this(room,examId,subjectId,answerSheetId,scanner,name,fileName,-1,actual);
    }

    /**
     * 按考场扫描，有计划扫描数量
     *
     * @param examId 考试唯一标识
     * @param subjectId 考试科目唯一标识
     * @param answerSheetId 考试科目用答题卡唯一标识
     * @param scanner 扫描人
     * @param name 扫描批次名称
     * @param fileName 扫描批次文件名
     * @param expected 计划扫描量
     * @param actual 实际扫描数量
     */
    public BatchScan(TestingRoom room, ExamId examId, SubjectId subjectId, AnswerSheetId answerSheetId,
                     Scanner scanner, String name, String fileName, int expected, int actual) {
        AssertionConcerns.assertArgumentNotNull(examId,"无效的考试标识");
        AssertionConcerns.assertArgumentNotNull(subjectId,"无效的科目标识");
        AssertionConcerns.assertArgumentNotNull(answerSheetId,"无效的答题卡标识");
        AssertionConcerns.assertArgumentNotNull(scanner,"无效的扫描人");
        AssertionConcerns.assertArgumentTrue(actual >= 0,"无效的扫描数");

        this.room = room;
        this.examId = examId;
        this.subjectId = subjectId;
        this.answerSheetId = answerSheetId;
        this.scanner = scanner;
        this.name = name;
        this.fileName = fileName;
        this.expected = expected;
        this.actual = actual;

        this.batchScanId = new BatchScanId();
        this.sheets = Lists.newArrayList();

    }

    /**
     * 开始本批次扫描文件上传
     */
    public void submitting(){
        AssertionConcerns.assertArgumentNull(this.submittingTime,"扫描批次已开始上传！");

        this.submittingTime = DateUtilWrapper.now();
        DomainEventPublisher.instance().publish(new BatchSubmitting(this.batchScanId));
    }

    /**
     * 完成本批次扫描件提交，接收到所有扫描识别数据时视为上传完成
     *
     */
    public void submitted(){
        AssertionConcerns.assertArgumentNotNull(this.submittingTime,"扫描批次未开始提交！");
        AssertionConcerns.assertArgumentNull(this.submittedTime,"扫描批次已完成提交！");

        this.submittedTime = DateUtilWrapper.now();
        this.countDoubts();
        DomainEventPublisher.instance().publish(new BatchSubmitted(this.batchScanId));
    }

    /**
     * 批量提交答题卡扫描件
     *
     * @param sheets 某种格式的答题卡扫描数据，如JSON,XML等
     * @param parser 数据解析器
     */
    public void submitSheets(String sheets,SheetParser parser){
        List<SheetScan> sheetScans = parser.parse(sheets,this);
        this.checkCanUpload();
        AssertionConcerns.assertArgumentTrue(sheetScans.size()+this.sizeOfSubmitted() <= this.actual,"提交的答题卡不能超过实际扫描量");
        this.sheets = sheetScans;
    }

    /**
     * 上传一份答题卡扫描件
     *
     * @param sheet
     */
    public void submitSheet(SheetScan sheet){
        this.checkCanUpload();
        AssertionConcerns.assertStateTrue(this.sizeOfSubmitted()+1 <= this.actual,"提交的答题卡不能超过实际扫描量");
        AssertionConcerns.assertStateTrue(sheet.sameBatchOf(this),"不能提交非本批次扫描的答题卡");
        this.sheets.add(sheet);
    }

    private void checkCanUpload(){
        if(!this.isSubmitting())
            this.submitting();

        AssertionConcerns.assertStateTrue(this.actual >= this.sheets.size(),"提交数量不能超过实际扫描数量!");
    }

    /**
     * 计算怀疑数量
     */
    private void countDoubts(){
        int examNumberDoubts = 0;
        int kgDoubts = 0;
        int zgOptionalDoubts = 0;
        boolean examNumberDoubtsDone = Boolean.FALSE;
        for(SheetScan sheet:this.sheets){
            if(sheet.examNumberDoubt())
                examNumberDoubts ++;

            if(sheet.kgDoubt())
                kgDoubts ++;

            if(sheet.zgOptionalDoubt())
                zgOptionalDoubts ++;

            if(sheet.examNumberDoubtDone())
                examNumberDoubtsDone = Boolean.TRUE;
        }
        this.scanDoubts = new BatchScanDoubts(examNumberDoubts,kgDoubts,zgOptionalDoubts,examNumberDoubtsDone);
    }

    /**
     * 完成本批次异常处理
     *
     */
    public void doubtsDone(){
        AssertionConcerns.assertStateTrue(this.submittedTime != null,"扫描批次未完成上传，不能结束怀疑处理！");

        DomainEventPublisher.instance().publish(new BatchDoubtsDone(this.batchScanId));
    }

    /**
     * 是否开始上传
     * @return
     */
    public boolean isSubmitting(){
        return this.submittingTime != null;
    }

    /**
     * 是否已经完成上传
     *
     * @return
     */
    public boolean isSubmitted(){
        return this.submittedTime != null;
    }

    /**
     * 已经完成的上传数量
     *
     * @return
     */
    public int sizeOfSubmitted(){
        return this.sheets.size();
    }

    /**
     * 是否按考场扫描
     *
     * @return
     */
    public boolean isScanOfRoom(){
        return this.room != null;
    }

    public BatchScanId batchScanId() {
        return batchScanId;
    }

    public ExamId examId() {
        return examId;
    }

    public SubjectId subjectId() {
        return subjectId;
    }

    public AnswerSheetId answerSheetId() {
        return answerSheetId;
    }

    public Scanner scanner() {
        return scanner;
    }

    public String name() {
        return name;
    }

    public String fileName() {
        return fileName;
    }

    public TestingRoom room() {
        return room;
    }

    public int expected() {
        return expected;
    }

    public int actual() {
        return actual;
    }

    public BatchScanDoubts scanDoubts() {
        return scanDoubts;
    }

    public Date submittingTime() {
        return submittingTime;
    }

    public Date submittedTime() {
        return submittedTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("scanner", scanner)
                .add("name", name)
                .add("fileName", fileName)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchScan batchScan = (BatchScan) o;
        return Objects.equal(fileName, batchScan.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fileName);
    }

    //Only 4 persistence
    protected BatchScan(){}
}
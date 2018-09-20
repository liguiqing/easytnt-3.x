package com.easytnt.statis.port.adapter.http

import com.easytnt.commons.util.DateUtilWrapper
import com.easytnt.share.domain.id.exam.ExamId
import com.easytnt.share.domain.id.mark.MarkItemId
import com.easytnt.share.domain.id.subject.SubjectId
import com.easytnt.statis.application.StatisApplicationService
import com.easytnt.statis.application.StatisQueryService
import com.easytnt.statis.port.adapter.http.controller.MarkStatisController
import com.ez.test.controller.AbstractControllerTest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.ContextHierarchy

import java.util.Date

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 */
@ContextHierarchy(ContextConfiguration(classes = arrayOf(MarkStatisController::class)),
        ContextConfiguration(locations = arrayOf("classpath:servlet-context.xml")))
class MarkStatisControllerTest : AbstractControllerTest() {
    @Autowired
    @InjectMocks
    private val controller: MarkStatisController? = null

    @Mock
    private val appService: StatisApplicationService? = null

    @Mock
    private val statisQueryService: StatisQueryService? = null

    @Test
    @Throws(Exception::class)
    fun testOnSubjectStatis() {
        assertNotNull(controller)

        val examId = ExamId()
        val subjectId = SubjectId()
        val startTime = DateUtilWrapper.now()
        val endTime = DateUtilWrapper.addSecondTo(startTime, 30)
        val st = DateUtilWrapper.toString(startTime, "yyyy-MM-dd HH:mm:ss")
        val et = DateUtilWrapper.toString(endTime, "yyyy-MM-dd HH:mm:ss")

        //doNothing().when(appService).statisForSubject(anyString(),startTime,endTime,any(DataSetFilter.class));
        this.mvc.perform(get("/mark/statis/subject/" + subjectId.id())
                .param("startTime", st).param("endTime", et)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.success", `is`(java.lang.Boolean.TRUE)))
    }

    @Test
    @Throws(Exception::class)
    fun testOnMarkItemStatis() {
        assertNotNull(controller)

        val itemId = MarkItemId()
        val subjectId = SubjectId()
        val startTime = DateUtilWrapper.now()
        val endTime = DateUtilWrapper.addSecondTo(startTime, 30)
        val st = DateUtilWrapper.toString(startTime, "yyyy-MM-dd HH:mm:ss")
        val et = DateUtilWrapper.toString(endTime, "yyyy-MM-dd HH:mm:ss")

        this.mvc.perform(get("/mark/statis/subject/item/" + subjectId.id() + "/" + itemId.id())
                .param("startTime", st).param("endTime", et)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.success", `is`(java.lang.Boolean.TRUE)))
    }



}
package com.easytnt.statis.port.adapter.http.controller;

import com.easytnt.commons.port.adaptor.http.web.ModelAndViewFactory;
import com.easytnt.statis.application.StatisApplicationService;
import com.easytnt.statis.application.StatisQueryService;
import com.easytnt.statis.domain.mark.DataSetFilter;
import com.easytnt.statis.domain.mark.DataSetFilterBuilder;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.infrastructure.DefaultDataSetFilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 评卷统计控制器
 *
 * 统计数据过滤的参数：
 * markItemIds:[]
 * teamIds:[]
 * scores:[]
 * 以上参数有数据执行过滤
 *
 * @author Liguiqing
 * @since V3.0
 */

@Controller
@RequestMapping(value = "/mark/statis")
public class MarkStatisController {
    private static Logger logger = LoggerFactory.getLogger(MarkStatisController.class);

    @Autowired(required=false)
    private StatisApplicationService statisAppService;

    @Autowired(required=false)
    private StatisQueryService statisQueryService;

    @Autowired(required=false)
    private DataSetFilterBuilder dataSetFilterBuilder;


    @RequestMapping(value = "/subject/{subjectId}",method=RequestMethod.GET)
    public ModelAndView onSubjectStatis(@PathVariable String subjectId,
                                        @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date startTime,
                                        @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date endTime,
                                        HttpServletRequest request)throws Exception{
        logger.debug("GET URL /statis/exam/subject/{} ",subjectId);
        DataSetFilter filter = createFilter(request);
        statisAppService.statisForSubject(subjectId,startTime,endTime,filter);
        List<ItemStatis> statises = statisQueryService.queryStatisForSubject(subjectId,startTime,endTime);
        return ModelAndViewFactory.newModelAndViewFor().with("statises",statises).build();
    }

    @RequestMapping(value = "/subject/item/{subjectId}/{itemId}",method=RequestMethod.GET)
    public ModelAndView onMarkItemStatis(@PathVariable String itemId, @PathVariable String subjectId,
                                        @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date startTime,
                                        @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date endTime,
                                        HttpServletRequest request)throws Exception{
        logger.debug("GET URL /statis/exam/subject/item/{}/{} ",subjectId,itemId);
        DataSetFilter filter = createFilter(request);
        statisAppService.statisForItem(itemId,startTime,endTime,filter);
        List<ItemStatis> statises = statisQueryService.queryStatisForSubject(itemId,startTime,endTime);
        return ModelAndViewFactory.newModelAndViewFor().with("statises",statises).build();
    }

    private DataSetFilter createFilter(HttpServletRequest request){
        Map<String,String[]> filterParams = request.getParameterMap();
        if(dataSetFilterBuilder == null)
            dataSetFilterBuilder = DefaultDataSetFilterBuilder.getInstance();
        return dataSetFilterBuilder.concreate(filterParams);
    }
}
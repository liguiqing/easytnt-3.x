package com.easytnt.commons.spring;

import com.easytnt.commons.lang.Throwables;
import com.easytnt.commons.port.adaptor.http.web.ModelAndViewFactory;
import com.easytnt.commons.port.adaptor.http.web.Responser;
import com.easytnt.commons.util.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class SpringMvcExceptionResolver extends SimpleMappingExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(SpringMvcExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                              Exception ex) {
        logger.error("{}", ex);

        String viewName = determineViewName(ex, request);
        if (ServletUtil.isAjaxRequest(request)) {
            Integer statusCode = determineStatusCode(request, viewName);

            if (statusCode != null) {
                applyStatusCodeIfPossible(request, response, statusCode);
            } else if (ex instanceof SQLException)
                request.setAttribute("message", "服务器异常");
            else
                request.setAttribute("message", "很抱歉，系统在处理您的请求产生未知错误，请联系管理员");

            Responser rs = new Responser.Builder().failure().code("900101")
                    .msg(parseExceptionMessage(request,ex)).create();
            return ModelAndViewFactory.newModelAndViewFor(rs).with("message", parseExceptionMessage(request,ex)).build();
        } else {
            Responser rs = new Responser.Builder().failure().code("900101")
                    .msg(parseExceptionMessage(request,ex)).create();
            return ModelAndViewFactory.newModelAndViewFor(viewName,rs).with("message", parseExceptionMessage(request,ex)).build();
        }
    }

    private String parseExceptionMessage(HttpServletRequest request,Exception ex) {
        if(ex instanceof SQLException) {
            if(ServletUtil.isHttpMethod(ServletUtil.HttpMethod.POST, request))
                return "数据更新异常";

            if(ServletUtil.isHttpMethod(ServletUtil.HttpMethod.PUT, request))
                return "数据更新异常";

            if(ServletUtil.isHttpMethod(ServletUtil.HttpMethod.DELETE, request))
                return "数据删除异常";

            if(ServletUtil.isHttpMethod(ServletUtil.HttpMethod.GET, request))
                return "数据查询异常";
        }
        return Throwables.toString(ex);
    }
}
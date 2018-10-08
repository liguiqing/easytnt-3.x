/** 
 * Project Name:easytnt-commons 
 * File Name:ServletUtil.java 
 * Package Name:com.easytnt.commons.controller 
 * Date:2016年3月25日下午3:02:44 
 * Copyright (c) 2016, easytnt All Rights Reserved. 
 * 
 */
package com.easytnt.commons.util;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Preconditions;

/**
 * ClassName: ServletUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年3月25日 下午3:02:44 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class ServletUtil {
	
	public static enum HttpMethod{
		GET("post"),POST("post"),PUT("put"),DELETE("delete");
		
		private String method = "";
		
		private HttpMethod(String method) {
			this.method = method;
		}
		
		public Boolean sameAs(String method) {
			return this.method.equalsIgnoreCase(method);
		}
		
		public String getMethod() {
			return this.method;
		}
		
		public static HttpMethod lookup(String method) {
			EnumSet<HttpMethod> methods = EnumSet.allOf(HttpMethod.class);
			for(HttpMethod m:methods) {
				if(m.sameAs(method))
					return m;
			}
			return null;
		}
	}
	
	public static Map<String, String> requestParamsToMap(ServletRequest request) {
		HashMap<String, String> requestParamsMap = new HashMap<>();
		Map<String, String[]> tmpMap = request.getParameterMap();
		for (String key : tmpMap.keySet()) {
			String[] values = tmpMap.get(key);
			if (values == null) {
				values = new String[] { "" };
			}
			requestParamsMap.put(key, values[0]);
		}
		return requestParamsMap;
	}
	
	public static Boolean isAjaxRequest(HttpServletRequest request) {
		String accept = request.getHeader("accept") ;
		//JQuery ajax
		if(accept!=null &&accept.indexOf("application/json") > -1)
			return Boolean.TRUE;
		
		//ajax文件上传
		if(request.getHeader("Content-Type") != null && request.getHeader("Content-Type").indexOf("multipart/form-data") > -1) {
			return Boolean.TRUE;
		}
		//移动端
		return  (request.getHeader("X-Requested-With") != null  
				    && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}
	
	public static String getRequestMethod(HttpServletRequest request) {
		return request.getMethod();
	}
	
	public static Boolean isHttpMethod(HttpMethod method,HttpServletRequest request) {
		return method.sameAs(getRequestMethod(request));
	}
	
	public static Boolean isHttpMethod(String method,HttpServletRequest request) {
		HttpMethod httpMethod = HttpMethod.lookup(method);
		if(httpMethod == null)
			return Boolean.FALSE;
		return isHttpMethod(httpMethod,request);
	}
	
	public static <T> T getSessionValue(HttpServletRequest request,String sessionName,Class<T> cls,T defaultValue) {
		HttpSession session = request.getSession();
		Object v = session.getAttribute(sessionName);
		if(v == null)
			return defaultValue;
		Preconditions.checkArgument((v.getClass().isAssignableFrom(cls)), "Session {} value typeif {} ,require type is {}",sessionName,v.getClass(),cls);
		return (T)v;
	}
	
	public static <T> T getParameter(HttpServletRequest request,String parameterName,Class<T> cls,T defaultValue){
		String value = request.getParameter(parameterName);
		if(value==null){
			return defaultValue;
		}
		if(Integer.class.isAssignableFrom(cls)){
			return (T)Integer.valueOf(value);
		}
		return (T)value;
	}
	
	public static ResponseEntity<byte[]> download(HttpServletRequest request,OutputStream fos,String fileName) throws Exception{
		//String fileName = file.getName();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                + URLEncoder.encode(fileName, "utf-8") + "\"");
        byte[] bytes = new byte[1024];
        fos.write(bytes);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
	}
}

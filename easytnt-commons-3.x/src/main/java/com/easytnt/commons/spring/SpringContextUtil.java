/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */
package com.easytnt.commons.spring;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.easytnt.commons.lang.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ClassName: SpringContextUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年3月25日 下午2:35:28 <br/>
 * 
 * @author 刘海林
 * @version
 * @since JDK 1.7+
 */
public class SpringContextUtil implements ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	/**
	 * 从spring context读取bean
	 * @param beanId 待读取bean的id
	 * @param <T>
	 * @return 无定义返回 null
	 */
	public static <T> T getBean(String beanId) {
		try {
			return (T) applicationContext.getBean(beanId);
		}catch (Exception e){
			logger.info(Throwables.toString(e));
		}

		return null;
	}

	/**
	 * 从spring context读取bean
	 *
	 * @param clazz 待读取bean的类型
	 * @param <T>
	 * @return 无定义返回 null
	 */
	public static <T> T getBean(Class clazz) {
		try {
			return (T) applicationContext.getBean(clazz);
		}catch (Exception e){
			logger.info(Throwables.toString(e));
		}
		return null;
	}

	public static void registerBean(String beanId,String beanClassName,Map<String,Object> pvs) {
		BeanDefinition bdef = new GenericBeanDefinition();
		bdef.setBeanClassName(beanClassName);
		if(pvs != null) {
			for (String p : pvs.keySet()) {
				bdef.getPropertyValues().add(p, pvs.get(p));
			}
		}
		DefaultListableBeanFactory fty = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
		fty.registerBeanDefinition(beanId, bdef);
	}

	public static <T> T[] getBeans(Class<T> type){
		Map<String,T> subBeans =  applicationContext.getBeansOfType(type);
		T[] ts = (T[]) Array.newInstance(type, subBeans.size());
		if(ts.length == 0)
			return ts;
		return subBeans.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()).toArray(ts);
	}
}
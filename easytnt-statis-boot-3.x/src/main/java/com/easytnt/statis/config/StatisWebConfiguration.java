package com.easytnt.statis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.commons.spring.SpringMvcExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Slf4j
@Configuration
@EnableAspectJAutoProxy
@EnableCaching
@ComponentScan(basePackages = { "com.easytnt.**.config" })
@ComponentScan(basePackages = { "com.easytnt.**.controller" })
@PropertySource("classpath:/META-INF/spring/bootConfig.properties")
public class StatisWebConfiguration extends WebMvcConfigurationSupport {

	@Bean
	public SpringContextUtil contextUtil(ApplicationContext applicationContext) {
		SpringContextUtil springContextUtil = new SpringContextUtil();
		springContextUtil.setApplicationContext(applicationContext);
		return springContextUtil;
	}

	@Bean(name = "cacheManager")
	public CacheManager cacheManager() {
		log.debug("Create Cache ");
		CompositeCacheManager cacheManager = new CompositeCacheManager();
		EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(ehCacheCacheManager().getObject());
		cacheManager.setCacheManagers(Arrays.asList(ehCacheCacheManager));
		cacheManager.setFallbackToNoOpCache(true);
		return cacheManager;
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}

	@Bean("dataSource")
	public DataSource dataSource(@Value("${jdbc.jndi.name:testJndiDs}") String jdbcJndiName,
			@Value("${jdbc.url}") String url, @Value("${jdbc.username}") String username,
			@Value("${jdbc.password}") String password) throws SQLException {

		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		try {
			log.debug("Get DataSource from jndi {}", jdbcJndiName);
			DataSource dataSource = dsLookup.getDataSource("java:comp/env/jdbc/" + jdbcJndiName);
			return dataSource;
		} catch (Exception e) {
			log.debug("DataSource not found with jndi {}", jdbcJndiName);
		}

		log.debug("Create DataSource {} {} {}", url, username, password);
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setInitialSize(10);
		druidDataSource.setMinIdle(1);
		druidDataSource.setMaxActive(20);
		druidDataSource.setMaxWait(60000);
		druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
		druidDataSource.setMinEvictableIdleTimeMillis(30000);
		druidDataSource.setValidationQuery("SELECT 'X'");
		druidDataSource.setTestWhileIdle(true);
		druidDataSource.setTestOnBorrow(false);
		druidDataSource.setTestOnReturn(false);
		druidDataSource.setPoolPreparedStatements(true);
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(100);
		druidDataSource.setFilters("stat");
		return druidDataSource;
	}

	@Bean("jdbcTemplate")
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		return transactionManager;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		log.debug("Configure Message Converters");
		super.configureMessageConverters(converters);
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteEnumUsingToString);
		FastJsonHttpMessageConverter c1 = new FastJsonHttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
		supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
		supportedMediaTypes.add(MediaType.APPLICATION_PDF);
		supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XML);
		supportedMediaTypes.add(MediaType.IMAGE_GIF);
		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
		supportedMediaTypes.add(MediaType.IMAGE_PNG);
		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		supportedMediaTypes.add(MediaType.TEXT_XML);
		c1.setSupportedMediaTypes(supportedMediaTypes);
		c1.setFastJsonConfig(fastJsonConfig);
		converters.add(c1);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.debug("Configure Resourece Handlers");
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/resources/");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		// TODO添加自定义的拦截器
		super.addInterceptors(registry);
	}

	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		super.addCorsMappings(registry);
	}

	@Bean(name = "viewResolver")
	@Primary
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		List<View> views = new ArrayList<View>();
		views.add(fastJsonJsonView());

		// ArrayList<ViewResolver> viewResolvers = new ArrayList<>();
		// viewResolvers.add(freeMarkerViewResolver);

		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		// viewResolver.setViewResolvers(viewResolvers);
		viewResolver.setDefaultViews(views);

		return viewResolver;
	}

	@Bean
	public FastJsonJsonView fastJsonJsonView() {

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteEnumUsingToString);
		fastJsonConfig.setDateFormat("yyyy-MM-dd");
		FastJsonJsonView view = new FastJsonJsonView();
		view.setFastJsonConfig(fastJsonConfig);
		return view;
	}

	@Bean("exceptionResolver")
	public SpringMvcExceptionResolver exceptionResolver() {
		SpringMvcExceptionResolver exceptionResolver = new SpringMvcExceptionResolver();
		exceptionResolver.setDefaultErrorView("/404");
		Properties mappings = new Properties();
		mappings.setProperty("java.lang.Exception", "505");
		exceptionResolver.setExceptionMappings(mappings);
		Properties statusCodes = new Properties();
		statusCodes.setProperty("/500", "500");
		statusCodes.setProperty("/401", "401");
		statusCodes.setProperty("/403", "403");
		statusCodes.setProperty("/404", "404");
		statusCodes.setProperty("/405", "405");
		exceptionResolver.setStatusCodes(statusCodes);
		return exceptionResolver;
	}

}
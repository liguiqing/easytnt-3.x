package com.easytnt.statis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class})
public class StatisApplication  extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception{
        log.debug("Easytnt marking statis starting ...");
        SpringApplication app = new SpringApplication(StatisApplication.class);
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        log.debug("Add Configurations ");
        application.sources(StatisApplication.class);
        return super.configure(application);
    }

}
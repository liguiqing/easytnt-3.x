package com.easytnt.statis.config;

import com.easytnt.statis.application.StatisApplicationService;
import com.easytnt.statis.application.StatisQueryService;
import com.easytnt.statis.domain.mark.ItemStatisRepository;
import com.easytnt.statis.domain.task.StatisTaskRepository;
import com.easytnt.statis.infrastructure.DefaultContextBuilder;
import com.easytnt.statis.port.adapter.persistence.ItemStatisJdbcRepository;
import com.easytnt.statis.port.adapter.persistence.MemoryStatisTaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Liguiqing
 * @since V3.0
 */

@Configuration
@PropertySource("classpath:/META-INF/spring/statisConfig.properties")
public class StatisConfiguration {

    @Bean
    public DefaultContextBuilder defaultContextBuilder(){
        DefaultContextBuilder defaultContextBuilder = new DefaultContextBuilder();
        defaultContextBuilder.installDefaltContext();
        return defaultContextBuilder;
    }

    @Bean
    public ItemStatisRepository itemStatisJdbcRepository(JdbcTemplate jdbcTemplate){
        ItemStatisJdbcRepository itemStatisJdbcRepository = new ItemStatisJdbcRepository(jdbcTemplate);
        return itemStatisJdbcRepository;
    }

    @Bean
    public StatisTaskRepository memoryStatisTaskRepository(@Value("${markitem.statis.memoryimpl.maxsize:1000}") int maxsize,
                                                                 @Value("${markitem.statis.memoryimpl.expire:30}") int expire){
        return new MemoryStatisTaskRepository(maxsize,expire);
    }

    @Bean StatisQueryService statisQueryService(StatisTaskRepository statisTaskRepository,
                                                ItemStatisRepository itemStatisRepository){
        return new StatisQueryService(statisTaskRepository,itemStatisRepository);
    }

    @Bean
    public StatisApplicationService statisApplicationService(StatisTaskRepository statisTaskRepository,
                                                             ItemStatisRepository itemStatisRepository){
        return new StatisApplicationService(statisTaskRepository,itemStatisRepository);
    }

}
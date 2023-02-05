package com.kk.picturequizapi.global.event;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventsBeanConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public InitializingBean eventInitializer(){
        return () -> Events.setPublisher(applicationContext);
    }
}

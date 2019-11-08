package edu.gyc.mp2.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import javax.xml.ws.handler.LogicalHandler;

@Configuration
public class MybatisConfiguration {
    /* 3.1.1need
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

     */

    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor=new PerformanceInterceptor();
       // performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}

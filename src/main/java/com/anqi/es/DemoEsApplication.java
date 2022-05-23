package com.anqi.es;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author anqi
 */
@SpringBootApplication(scanBasePackages = "com.anqi.es")
@MapperScan(value = "com.anqi.es.mapper")
public class DemoEsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoEsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoEsApplication.class, args);
    }

}

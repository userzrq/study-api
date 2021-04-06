package com.userzrq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.userzrq.rockymobi.noval.sys.dao")
public class ZrqApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZrqApiApplication.class, args);
    }

    @PostConstruct
    void setDefaultTimeZone(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}

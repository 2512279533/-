package com.guigu.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.guigu")
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.guigu.staservice.mapper")
public class staApplication {

    public static void main(String[] args) {
        SpringApplication.run(staApplication.class,args);
    }
}

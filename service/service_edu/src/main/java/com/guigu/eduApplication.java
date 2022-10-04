package com.guigu;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.guigu"})
@MapperScan("com.guigu.edu.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class eduApplication {
    public static void main(String[] args) {
        SpringApplication.run(eduApplication.class,args);
    }
}

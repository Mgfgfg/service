package com.yh.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.persistence.EntityResult;

@SpringBootApplication
//扫描实体类
@EntityScan("com.tx.framework.domain.cms")
//扫描接口
@ComponentScan(basePackages={"com.lxw.api"})
//扫描本项目下的所有类
@ComponentScan(basePackages={"com.yh.manage"})
public class ManageCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class,args);
    }
}

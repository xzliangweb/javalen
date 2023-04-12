package com.example.itheima_reggie_take;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement // 开启事务的支持
public class ItheimaReggieTakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItheimaReggieTakeApplication.class, args);
    }

}

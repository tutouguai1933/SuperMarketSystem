package com.djy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.djy.mapper")
public class SupermarketSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupermarketSystemApplication.class, args);
    }

}

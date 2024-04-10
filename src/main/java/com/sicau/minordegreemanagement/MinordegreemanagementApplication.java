package com.sicau.minordegreemanagement;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sicau.*.*.mapper")
@Slf4j
public class MinordegreemanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinordegreemanagementApplication.class, args);
        log.info("are you ok?");
    }

}

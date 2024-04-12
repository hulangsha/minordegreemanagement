package com.sicau.minordegreemanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MinordegreemanagementApplicationTests {

    @Test
    void contextLoads() {
        // 获取当前的日期和时间
        LocalDateTime currentTime = LocalDateTime.now();

        // 输出当前的日期和时间
        System.out.println("当前时间: " + currentTime);
    }

}

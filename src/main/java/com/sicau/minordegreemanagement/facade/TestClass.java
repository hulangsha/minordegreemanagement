package com.sicau.minordegreemanagement.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestClass {
    @GetMapping("/login")
    public String login() {
        return "ok";
    }
}

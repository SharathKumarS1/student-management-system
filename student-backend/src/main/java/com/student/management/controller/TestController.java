package com.student.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/version-test")
    public String versionTest() {
        return "VERSION-999";
    }
}
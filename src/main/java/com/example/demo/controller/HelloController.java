package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    /**
     * http://localhost:19222/hello
     */
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}

package com.easytnt.statis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController 
@RequestMapping("/hello")
@Slf4j
public class DemoController {
	
    @RequestMapping("/demo")
    @ResponseBody
    public String index() {
    	log.debug("aaaaaa Hello World ");
        return "Hello World";
    }

}

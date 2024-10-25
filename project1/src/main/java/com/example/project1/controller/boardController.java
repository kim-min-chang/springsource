package com.example.project1.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping("/board")
public class boardController {

    @GetMapping("/list")
    public void getlist() {
        log.info("list 요청");
    }

    @GetMapping("/read")
    public void getread() {
        log.info("read 요청");
    }

}

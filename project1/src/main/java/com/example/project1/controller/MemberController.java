package com.example.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.DTO.LoginDto;
import com.example.project1.DTO.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public void getlogin(LoginDto loginDto) {
        log.info("login 페이지 요청");
    }

    // @PostMapping("/login")
    // public void postlogin(HttpServletRequest request) {
    // log.info("login 요청 - 사용자 입력값 요청");

    // String userid = request.getParameter("userid");
    // String password = request.getParameter("password");

    // log.info("userid : {} password {}", userid, password);
    // }
    @PostMapping("/login")
    public String postlogin(@Valid LoginDto loginDto, BindingResult result) {
        log.info("login 요청 - 사용자 입력값 요청");
        log.info("userid : {} password {}", loginDto.getUserid(), loginDto.getPassword());

        if (result.hasErrors()) {
            return "/member/login";
        }

        return "index.html";
    }

    // http://localhost:8080/

    @GetMapping("/register")
    public void getregister(MemberDto memberDto) {
        log.info("register");
    }

    @PostMapping("/register")
    public String postregister(@Valid MemberDto memberDto, BindingResult result) {
        log.info("회원가입요청 :{} ", memberDto);

        if (result.hasErrors()) {
            return "/member/register";
        }

        return "redirect:/member/login"; // redirect : 경로
    }

    @GetMapping("/path1")
    public String method1() {

        return "login";
    }

    @PostMapping("/path1")
    public void method2() {
        // 1. 입력값 가져오기
        // 2. service 호출 후 결과받기
        // 3. model.addAttribute()
        // 4. 페이지 이동
    }

}

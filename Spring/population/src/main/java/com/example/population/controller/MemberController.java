package com.example.population.controller;

import com.example.population.domain.dto.MemberDto;
import com.example.population.domain.Shape;
import com.example.population.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    // login
    @GetMapping("/loginForm")
    public String loginForm() {
        return "members/loginForm";
    }

    // login error
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model)
    {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "members/loginForm";
    }

    // signup
    @GetMapping("/signupForm")
    public String signupForm(Model model) {
        model.addAttribute("member",new MemberDto());
        return "members/signupForm";
    }

    @PostMapping("/member/signup")
    public String signup(MemberDto memberDto) {
        memberService.signUp(memberDto);

        return "redirect:/loginForm";
    }

}

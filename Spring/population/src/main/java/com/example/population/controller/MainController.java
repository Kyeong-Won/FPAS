package com.example.population.controller;

import com.example.population.repository.BoardRepository;
import com.example.population.repository.MemberRepository;
import com.example.population.repository.ShapeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ShapeRepository shapeRepository;

    @GetMapping("/")
    public String main(Model model) {
        long memberCount = memberRepository.count();
        long boardCount = boardRepository.count();
        long shapeCount = shapeRepository.count();
        model.addAttribute("memberCount", memberCount);
        model.addAttribute("boardCount", boardCount);
        model.addAttribute("shapeCount", shapeCount);
        return "home/index";
    }

    @GetMapping("/index")
    public String index() {
        return "home/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        long memberCount = memberRepository.count();
        long boardCount = boardRepository.count();
        long shapeCount = shapeRepository.count();
        model.addAttribute("memberCount", memberCount);
        model.addAttribute("boardCount", boardCount);
        model.addAttribute("shapeCount", shapeCount);
        return "home/about";
    }

    @GetMapping("/blog")
    public String blog() {
        return "home/blog";
    }

    @GetMapping("/blog-details")
    public String blog_details() {
        return "home/blog-details";
    }

    @GetMapping("/contact")
    public String contact() {
        return "home/contact";
    }

    @GetMapping("/hosting")
    public String hosting() {
        return "home/hosting";
    }

    @GetMapping("/pricing")
    public String pricing() {
        return "home/pricing";
    }
}

package com.example.population.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {

    @GetMapping("/")
    public String main() {
        return "home/index";
    }

    @GetMapping("/index")
    public String index() {
        return "home/index";
    }

    @GetMapping("/about")
    public String about() {
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

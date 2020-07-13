package com.packtpub.book.ch02.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(Model model, Principal principal) {
        System.out.println("endpoint '/' -no login");
        if(principal != null)
            model.addAttribute("msg", "Welcome " + principal.getName());
        return "home";
    }


    @GetMapping("/user")
    public String user(Model model, Principal principal){

        System.out.println("endpoint '/user' ");
        if(principal != null)
            model.addAttribute("msg", "Welcome " + principal.getName());
        return "user";
    }

    @GetMapping("/admin") // admin , admin@password
    public String admin(Model model, Principal principal){
        System.out.println("endpoint '/admin' ");
        if(principal != null)
            model.addAttribute("msg", "Welcome " + principal.getName());
        return "home";
    }
}
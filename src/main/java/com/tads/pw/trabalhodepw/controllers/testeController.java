package com.tads.pw.trabalhodepw.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testeController {

    @GetMapping("/")
    public String testando(){
        return "testando";
    }
}

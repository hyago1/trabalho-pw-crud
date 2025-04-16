package com.tads.pw.trabalhodepw.controllers;

import com.tads.pw.trabalhodepw.entity.produto;
import com.tads.pw.trabalhodepw.service.usuarioService;
import com.tads.pw.trabalhodepw.service.produtoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class LoginController {

    ArrayList<produto> produtos = new ArrayList<>();

    @Autowired
    usuarioService usuarioService;

    @Autowired
    produtoService produtoService;

    @RequestMapping(value ="/login",method= RequestMethod.POST)
    public void getLoginData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (usuarioService.validarLogin(username,password)){

            var dispatcher = request.getRequestDispatcher("/dashboard");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("localhost:8080/userInvalid.html");
        }

    }





}
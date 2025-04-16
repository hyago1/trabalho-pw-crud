package com.tads.pw.trabalhodepw.controllers;


import com.tads.pw.trabalhodepw.entity.cliente;
import com.tads.pw.trabalhodepw.entity.produto;
import com.tads.pw.trabalhodepw.service.usuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class registerController {



    @Autowired
    usuarioService usuarioService;




    @RequestMapping(value ="/register",method= RequestMethod.POST)
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        cliente cliente = new cliente(username,email, password);
        System.out.println(username);

        if (usuarioService.verificarEmail(email)){

            PrintWriter writer = response.getWriter();
            writer.println("<h1>Usuario ja existe</h1>");

        }else {

            usuarioService.save(cliente);
        }

    }


}

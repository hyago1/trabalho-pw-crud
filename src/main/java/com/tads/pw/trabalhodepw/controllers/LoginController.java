package com.tads.pw.trabalhodepw.controllers;

import com.tads.pw.trabalhodepw.entity.carrinho;
import com.tads.pw.trabalhodepw.entity.cliente;
import com.tads.pw.trabalhodepw.entity.logista;
import com.tads.pw.trabalhodepw.entity.produto;
import com.tads.pw.trabalhodepw.service.clienteService;
import com.tads.pw.trabalhodepw.service.logistaService;
import com.tads.pw.trabalhodepw.service.produtoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class LoginController {

    ArrayList<produto> produtos = new ArrayList<>();

    @Autowired
    clienteService clienteService;

    @Autowired
    logistaService logistaService;

    @Autowired
    produtoService produtoService;




    @RequestMapping(value ="/login",method= RequestMethod.POST)
    public void getLoginData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!clienteService.validarLogin(email,password).isEmpty() ){
            cliente cliente = clienteService.validarLogin(email, password).getFirst();
            HttpSession session = request.getSession();


            // Limpa qualquer carrinho anterior
            session.removeAttribute("carrinho");
            produtos.clear();
            carrinho carrinho = new carrinho((ArrayList<produto>) produtos);
            // Cria um novo carrinho vazio para este cliente
            session.setAttribute("carrinho", carrinho);

            // Armazena o cliente
            session.setAttribute("cliente", cliente);
            response.sendRedirect("/dashboard");

        } else if (!logistaService.validarLogin(email,password).isEmpty() ){
            System.out.println("Logissss");
            logista logista = logistaService.validarLogin(email, password).getFirst();

            HttpSession session = request.getSession();
            session.setAttribute("logista", logista); //
            response.sendRedirect("/logistaDashboard");
        }else {
            response.sendRedirect("/userInvalid.html");
        }

    }





}
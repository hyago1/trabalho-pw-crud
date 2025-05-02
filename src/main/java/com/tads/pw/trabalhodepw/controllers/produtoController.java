package com.tads.pw.trabalhodepw.controllers;

import com.tads.pw.trabalhodepw.entity.produto;
import com.tads.pw.trabalhodepw.service.produtoService;
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
public class produtoController {

    @Autowired
    produtoService produtoService;

    @RequestMapping(value = "/cadastrarProduto",method = RequestMethod.POST)
    public void cadastrarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String nome = request.getParameter("nome");
        int valor = Integer.parseInt(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");
        int estoque = Integer.parseInt(request.getParameter("estoque"));
        produto produto = new produto(nome, descricao,valor, estoque);

        PrintWriter writer = response.getWriter();

        if (produtoService.save(produto) == 1) {
            writer.println("<script>alert('Produto cadastrado com sucesso!')</script>");
            response.sendRedirect("/logistaDashboard");
        }{
            writer.println("<script>alert('Erro ao cadastrar!')</script>");
        }





    }


}

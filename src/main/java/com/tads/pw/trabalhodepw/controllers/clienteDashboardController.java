package com.tads.pw.trabalhodepw.controllers;


import com.tads.pw.trabalhodepw.entity.cliente;
import com.tads.pw.trabalhodepw.entity.logista;
import com.tads.pw.trabalhodepw.entity.produto;
import com.tads.pw.trabalhodepw.service.produtoService;
import com.tads.pw.trabalhodepw.service.clienteService;
import jakarta.servlet.RequestDispatcher;
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
import java.util.ArrayList;

@Controller
public class clienteDashboardController {


    ArrayList<produto> produtos = new ArrayList<>();
    ArrayList<cliente> cliente = new ArrayList<>();

    @Autowired
    clienteService clienteService;

    @Autowired
    produtoService produtoService;

    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public void getLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        Cookie c = new Cookie("login", "dw");
        c.setMaxAge(60*10);
        produtos.clear();
        HttpSession session = request.getSession();
        cliente log = (cliente) session.getAttribute("cliente");
        String username = log.getNome();


        produtoService.findAll().forEach(produto -> {produtos.add(produto);});

        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='pt-br'>");
        writer.println("<head>");
        writer.println("<meta charset=\"UTF-8\">");
        writer.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        writer.println("<title>Dashboard</title>");
        writer.println("<link rel='stylesheet' href='styles/dashboard.css'>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Ola, " + username + "</h1>");
        writer.println("<h3>Produtos:</h3>");
        writer.println("<table>");
        writer.println("<thead>");
        writer.println("<tr>");
        writer.println("<th>Nome</th>");
        writer.println("<th>Descrição</th>");
        writer.println("<th>Preço</th>");
        writer.println("<th>Quantidade</th>");
        writer.println("<th>Ação</th>");
        writer.println("</tr>");
        writer.println("</thead>");
        writer.println("<tbody>");

        for (produto produto : produtos) {
            writer.println("<tr>");
            writer.println("<td>" + produto.getNome() + "</td>");
            writer.println("<td>" + produto.getDescricao() + "</td>");
            writer.println("<td>" + produto.getPreco() + "</td>");
            writer.println("<td>" + produto.getEstoque() + "</td>");
            if (produto.getEstoque() > 0) {
                writer.println("<td><a href='/addProdutoNoCarrinho?id="+produto.getId()+"'>Adicionar</a></td>");

            } else {
                writer.println("<td>Esgotado</td>");
            }
            writer.println("</tr>");
        }

        writer.println("</tbody>");
        writer.println("</table>");
        writer.println("<br>");
        writer.println("<a href='/index.html'>Sair</a>");
        writer.println("<a href='/carrinho'>Ver carrinho</a>");
        writer.println("</body>");
        writer.println("</html>");


    }


}

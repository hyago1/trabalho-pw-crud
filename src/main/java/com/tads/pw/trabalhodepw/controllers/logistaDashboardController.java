package com.tads.pw.trabalhodepw.controllers;

import com.tads.pw.trabalhodepw.entity.cliente;
import com.tads.pw.trabalhodepw.entity.logista;
import com.tads.pw.trabalhodepw.entity.produto;
import com.tads.pw.trabalhodepw.service.clienteService;
import com.tads.pw.trabalhodepw.service.logistaService;
import com.tads.pw.trabalhodepw.service.produtoService;
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
public class logistaDashboardController {


    ArrayList<produto> produtos = new ArrayList<>();
    ArrayList<logista> logistas = new ArrayList<logista>();

    @Autowired
    logistaService logistaService;

    @Autowired
    produtoService produtoService;


    @RequestMapping(value="/logistaDashboard", method = RequestMethod.GET)
    public void getLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        logista log = (logista) session.getAttribute("logista");
        String username = log.getNome();
        System.out.println(username);
        produtos.clear();
        produtoService.findAll().forEach(produto -> {produtos.add(produto);});
        PrintWriter writer = response.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='pt-br'>");
        writer.println("<head>");
        writer.println("<meta charset='UTF-8'>");
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
        writer.println("<th>Estoque</th>");
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
            writer.println("<td><a href='/excluirProduto?id="+produto.getId()+"'>Excluir</a></td>");
            writer.println("</tr>");
        }

        writer.println("</tbody>");
        writer.println("</table>");
        writer.println("<br>");
        writer.println("<a href='cadastrarProduto.html'>Cadastrar Produto</a>");
        writer.println("<a href='/index.html'>Sair</a>");
        writer.println("</body>");
        writer.println("</html>");




    }
}

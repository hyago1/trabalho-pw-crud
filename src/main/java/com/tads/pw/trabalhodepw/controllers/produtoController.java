package com.tads.pw.trabalhodepw.controllers;

import com.tads.pw.trabalhodepw.entity.carrinho;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class produtoController {
    List<produto> produtos = new ArrayList<produto>();
    @Autowired
    produtoService produtoService;


    carrinho carrinho;
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


    @RequestMapping(value = "/addProduto",method = RequestMethod.GET)
    public void addProdutoNoCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        carrinho = new carrinho((ArrayList<produto>) produtos);
        int id = Integer.parseInt(request.getParameter("id"));
        carrinho.addProduto(produtoService.findById(id).getFirst());

        PrintWriter writer = response.getWriter();
        produtos.forEach(produto -> {System.out.println(produto);});
        response.sendRedirect("/dashboard");
    }
    @RequestMapping(value = "/removeProduto",method = RequestMethod.GET)
    public void removeProdutoNoCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        carrinho = new carrinho((ArrayList<produto>) produtos);
        int id = Integer.parseInt(request.getParameter("id"));
        carrinho.removeProduto(id);
        produtos.forEach(produto -> {System.out.println(produto);});
        response.sendRedirect("/dashboard");
    }


    @RequestMapping(value = "/carrinho",method = RequestMethod.GET)
    public void verCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

        writer.println("<h3>Carrinho:</h3>");
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
    if (carrinho != null) {
        for (produto produto : carrinho.getProdutos()) {

            writer.println("<tr>");
            writer.println("<td>" + produto.getNome() + "</td>");
            writer.println("<td>" + produto.getDescricao() + "</td>");
            writer.println("<td>" + produto.getPreco() + "</td>");
            writer.println("<td>" + produto.getEstoque() + "</td>");
            writer.println("<td><a href='/removeProduto?id="+produto.getId()+"'>Remover</a></td>");
            writer.println("</tr>");
     }
    }

        writer.println("</tbody>");
        writer.println("</table>");
        writer.println("<br>");
        writer.println("<a href='/dashboard'>Voltar</a>");
        writer.println("</body>");
        writer.println("</html>");


    }






}

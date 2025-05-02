package com.tads.pw.trabalhodepw.controllers;

import com.tads.pw.trabalhodepw.entity.carrinho;
import com.tads.pw.trabalhodepw.entity.cliente;
import com.tads.pw.trabalhodepw.entity.produto;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class produtoController {
    List<produto> produtos = new ArrayList<produto>();
    List<produto> produtosC = new ArrayList<produto>();
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

    @RequestMapping(value = "/excluirProduto",method = RequestMethod.GET)
    public void excluirProduto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        produtoService.removeProdutoById(id);
        response.sendRedirect("/logistaDashboard");
    }


    @RequestMapping(value = "/addProdutoNoCarrinho",method = RequestMethod.GET)
    public void addProdutoNoCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {



        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("cliente") == null) {
            response.sendRedirect("/login");
            return;
        }


        carrinho car = (carrinho) session.getAttribute("carrinho");
        int id = Integer.parseInt(request.getParameter("id"));
        car.addProduto(produtoService.findById(id).getFirst());
        session.setAttribute("carrinho", car);
        response.sendRedirect("/dashboard");
    }
    @RequestMapping(value = "/removeProdutoDoCarrinho",method = RequestMethod.GET)
    public void removeProdutoNoCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        carrinho car = (carrinho) session.getAttribute("carrinho");
        int id = Integer.parseInt(request.getParameter("id"));
        car.removeProduto(id);
        session.setAttribute("carrinho", car);
        response.sendRedirect("/dashboard");

    }


    @RequestMapping(value = "/carrinho",method = RequestMethod.GET)
    public void verCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        carrinho car = (carrinho) session.getAttribute("carrinho");

        int valorTotal=0;

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
    if (car != null) {
        for (produto produto : car.getProdutos()) {

            writer.println("<tr>");
            writer.println("<td>" + produto.getNome() + "</td>");
            writer.println("<td>" + produto.getDescricao() + "</td>");
            writer.println("<td>" + produto.getPreco() + "</td>");
            writer.println("<td>" + produto.getEstoque() + "</td>");
            writer.println("<td><a href='/removeProdutoDoCarrinho?id="+produto.getId()+"'>Remover</a></td>");
            writer.println("</tr>");
            valorTotal+=produto.getPreco();
     }
    }

        writer.println("</tbody>");
        writer.println("</table>");
        writer.println("<br>");
        writer.println("<a href='/dashboard'>Voltar</a>");
        writer.println("<td><a href='/finalizar'>Finalizar Compra</a></td>");
        writer.println("<span style='text-align:right;'> Valor Total: "+ (double) valorTotal +"</span>");
        writer.println("</body>");
        writer.println("</html>");


    }

    @RequestMapping(value = "/finalizar",method = RequestMethod.GET)
    public void finalizarCompra(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        carrinho car = (carrinho) session.getAttribute("carrinho");
        car.getProdutos().forEach(produto -> {
            produtoService.update(produto);
            System.out.println("Comprando = " + produto.getId());
        });
        produtosC.clear();
        carrinho carrinho = new carrinho((ArrayList<produto>) produtosC);
        session.setAttribute("carrinho", carrinho);
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('Compra finalizada!!'</script>");
        response.sendRedirect("/dashboard?sucesso=Compra+finalizada");
    }





}

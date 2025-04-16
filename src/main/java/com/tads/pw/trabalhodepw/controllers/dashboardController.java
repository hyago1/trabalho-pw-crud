package com.tads.pw.trabalhodepw.controllers;


import com.tads.pw.trabalhodepw.entity.produto;
import com.tads.pw.trabalhodepw.service.produtoService;
import com.tads.pw.trabalhodepw.service.usuarioService;
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
public class dashboardController {


    ArrayList<produto> produtos = new ArrayList<>();

    @Autowired
    usuarioService usuarioService;

    @Autowired
    produtoService produtoService;

    @RequestMapping(value="/dashboard", method = RequestMethod.POST)
    public void getLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie c = new Cookie("horario_login", LocalDateTime.now().toString());
        c.setMaxAge(60*10);
        produtos.clear();
        produtoService.findAll().forEach(produto -> {produtos.add(produto);});


        produtos.forEach(produto -> {System.out.println(produto);});

        HttpSession session = request.getSession();
        session.setAttribute("logado", true);
        response.addCookie(c);


        PrintWriter writer = response.getWriter();
        writer.println("<html>" +
                "<style>" +
                "table, th, tr,td, thead  {border:1px solid}" +
                "</style>"+
                "<body>");
        writer.println("<h1> ola usuario</h1>");
        writer.println("<h3> Produtos: </h3>" +
               "<table style='border:1px solid'>\n" +
                "    <thead>\n" +
                "        <tr>\n" +
                "            <th>Nome</th>\n" +
                "            <th>Descrição</th>\n" +
                "            <th>Preço</th>\n" +
                "            <th>Estoque</th>\n" +
                "            <th>Carrinho</th>\n" +
                "        </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n" );

        for (produto produto : produtos) {
            writer.println( "<tr>\n");
            writer.println(" <td>"+produto.getNome()+"</td>\n");
            writer.println(" <td>"+produto.getDescricao()+"</td>\n");
            writer.println(" <td>"+produto.getPreco()+"</td>\n");
            writer.println(" <td>"+produto.getEstoque()+"</td>\n");

        }
        writer.println("  </tbody></table><br>");

        writer.println("<a href='/index.html'>sair</a>");

        writer.println("  </body> </html>");





    }





}

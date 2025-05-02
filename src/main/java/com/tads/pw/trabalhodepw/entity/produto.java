package com.tads.pw.trabalhodepw.entity;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class produto {
    

    int id;
    int preco;
    String nome;
    String descricao;
    int estoque;


    public produto(String nome, String descricao , int preco, int estoque   )
    {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;


    }


    public produto() {

    }
}
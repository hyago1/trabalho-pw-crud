package com.tads.pw.trabalhodepw.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int preco;
    String nome;
    String descricao;
    int estoque;


    public produto(String nome, String descricao    )
    {
        this.nome = nome;
        this.descricao = descricao;

    }


    public produto() {

    }
}
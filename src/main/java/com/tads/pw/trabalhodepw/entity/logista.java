package com.tads.pw.trabalhodepw.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class logista {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private String senha;
}

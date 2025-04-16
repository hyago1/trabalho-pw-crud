package com.tads.pw.trabalhodepw.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "cliente")
@Table(name = "cliente")
@Getter
@Setter
public class cliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        private String email;
        private String senha;

        public cliente( String nome, String email, String senha) {
                this.nome = nome;
                this.email = email;
                this.senha = senha;
        }

        public cliente() {}

}



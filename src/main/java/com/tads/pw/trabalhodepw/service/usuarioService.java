package com.tads.pw.trabalhodepw.service;


import com.tads.pw.trabalhodepw.entity.cliente;
import com.tads.pw.trabalhodepw.entity.produto;
import com.tads.pw.trabalhodepw.repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class usuarioService {

    @Autowired
    private usuarioRepository usuarioRepository;

    public List<cliente> findAll() {
        return usuarioRepository.findAll();
    }

    public cliente findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public boolean validarLogin(String email, String senha) {

        System.out.println("Verificando login.... ");
        System.out.println(usuarioRepository.findByEmailAndSenha(email, senha).isPresent());

        return usuarioRepository.findByEmailAndSenha(email, senha).isPresent();
    }

    public boolean verificarEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    public void delete(cliente cliente) {
        usuarioRepository.delete(cliente);
    }

    public void save(cliente cliente) {
        usuarioRepository.save(cliente);
    }

    public cliente update(cliente cliente) {
        return usuarioRepository.save(cliente);
    }
}

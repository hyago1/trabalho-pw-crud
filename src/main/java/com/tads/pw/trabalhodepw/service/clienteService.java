package com.tads.pw.trabalhodepw.service;


import com.tads.pw.trabalhodepw.entity.cliente;
import com.tads.pw.trabalhodepw.repository.clienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class clienteService {

    @Autowired
    private clienteRepository clienteRepository;
//
//    public List<cliente> findAll() {
//        return usuarioRepository.findAll();
//    }

//    //public cliente findById(Long id) {
//        return usuarioRepository.findById(id).get();
//    }

    public List<cliente> validarLogin(String email, String senha) {

        System.out.println("Verificando login.... ");
        System.out.println("email: "+email + "senha: "+ senha);
        return clienteRepository.findEmailESenha(email, senha);
    }

    public List<cliente> verificarEmail(String email) {
        System.out.println("Verificando login.... ");
        System.out.println( clienteRepository.findEmail(email));
        return clienteRepository.findEmail(email);
    }


    public List<cliente> retornarUsernameByEmail(String email) {
        System.out.println("Verificando login.... ");
        System.out.println( clienteRepository.findEmail(email));
        return  clienteRepository.findEmail(email);
    }

//
//    public void delete(cliente cliente) {
//        usuarioRepository.delete(cliente);
//    }
//
    public void save(cliente cliente) {
        clienteRepository.save(cliente);
    }
//
//    public cliente update(cliente cliente) {
//        return usuarioRepository.save(cliente);
//    }
}

package com.tads.pw.trabalhodepw.service;

import com.tads.pw.trabalhodepw.entity.cliente;
import com.tads.pw.trabalhodepw.entity.logista;
import com.tads.pw.trabalhodepw.repository.logistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class logistaService {

     logistaRepository logistaRepository = new logistaRepository();


    public List<logista> validarLogin(String email, String senha) {

        System.out.println("Verificando login.... se é logista");
        return logistaRepository.findEmailESenha(email, senha);
    }

    public List<logista> verificarEmail(String email) {
        System.out.println("Verificando login.... ");
        System.out.println( logistaRepository.findEmail(email));
        return logistaRepository.findEmail(email);
    }


    public List<logista> retornarUsernameByEmail(String email) {
        System.out.println("Verificando login.... ");
        System.out.println( logistaRepository.findEmail(email));
        return  logistaRepository.findEmail(email);
    }

    //
//    public void delete(cliente cliente) {
//        usuarioRepository.delete(cliente);
//    }
//
    public void save(logista logista) {
        logistaRepository.save(logista);
    }


}

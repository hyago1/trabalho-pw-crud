package com.tads.pw.trabalhodepw.repository;

import com.tads.pw.trabalhodepw.entity.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usuarioRepository extends JpaRepository<cliente, Long> {
    Optional<cliente> findByEmailAndSenha(String email, String senha);
    Optional<cliente> findByEmail(String email);

}

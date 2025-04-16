package com.tads.pw.trabalhodepw.repository;

import com.tads.pw.trabalhodepw.entity.produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface produtoRepository extends JpaRepository<produto, Long> {
}

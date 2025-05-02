package com.tads.pw.trabalhodepw.service;


import com.tads.pw.trabalhodepw.entity.produto;
import com.tads.pw.trabalhodepw.repository.produtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class produtoService {

    @Autowired
    private  produtoRepository produtoRepository;

    public int save(produto produto) {
       return produtoRepository.save(produto);
    }

    public List<produto> findById(Integer id) {
        return produtoRepository.findById(id);
    }

    public List<produto> findAll() {
        return produtoRepository.findAll();
    }
}

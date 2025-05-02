package com.tads.pw.trabalhodepw.entity;
import com.tads.pw.trabalhodepw.entity.produto;

import java.util.ArrayList;



public class carrinho {

    public carrinho(ArrayList<produto> produtos) {
        super();
        this.produtos = produtos;
    }

    ArrayList<produto> produtos;
    public ArrayList<produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(ArrayList<produto> produtos) {
        this.produtos = produtos;
    }
    public produto getProduto (int id){
        produto mp = null;
        for (produto p : produtos){
            if (p.getId() == id){
                return p;
            }
        }
        return mp;
    }
    public void removeProduto (int id){
        produto p = getProduto(id);
        produtos.remove(p);
    }
    public void addProduto (produto p){produtos.add(p);
    }
}
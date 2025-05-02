package com.tads.pw.trabalhodepw.repository;

import com.tads.pw.trabalhodepw.entity.produto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Repository
public class produtoRepository  {
    public List<produto> findAll() { // listAll (if the database is huge, consider the use of pagination)
        List<produto> produtos = new ArrayList<produto>();
        String sql = "select * from Produtos";
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; // stores the query result

        try {
            conn =dbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            // iterates the resultSet and stores in the object the column values from the
            // database
            while (resultSet.next()) {
                produto produto = new produto();
                produto.setId(resultSet.getInt("id")); // "id" is the column at postgres
                produto.setNome(resultSet.getString("nome")); // "nome" is the column at postgres
                produto.setPreco(resultSet.getInt("preco")); // "preco" is the column at postgres
                produto.setDescricao(resultSet.getString("descricao")); // "descricao" is the column at postgres
                produto.setEstoque(resultSet.getInt("estoque"));
                produtos.add(produto); // add the object filled with database data to products list
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return produtos;
    }


    public int save(produto produto) {
        String sql = "insert into produtos (preco, nome, descricao, estoque)" + " values (?, ?, ?,?)";
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;

        try {
            conn = dbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, produto.getPreco());
            preparedStatement.setString(2, produto.getNome());
            preparedStatement.setString(3, produto.getDescricao());
            preparedStatement.setInt(4, produto.getEstoque());

            preparedStatement.execute(); //it is not a query. It is an insert command

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
                return 1;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    public List<produto> findById(int id) { // listAll (if the database is huge, consider the use of pagination)
        List<produto> produtos = new ArrayList<produto>();
        String sql = "select * from produtos where id = ?";
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; // stores the query result

        try {
            conn =dbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produto produto = new produto();
                produto.setId(resultSet.getInt("id")); // "id" is the column at postgres
                produto.setNome(resultSet.getString("nome")); // "nome" is the column at postgres
                produto.setPreco(resultSet.getInt("preco")); // "preco" is the column at postgres
                produto.setDescricao(resultSet.getString("descricao")); // "descricao" is the column at postgres
                produto.setEstoque(resultSet.getInt("estoque"));
                produtos.add(produto); // add the object filled with database data to products list
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return produtos;
    }

    public int deleteById(int id) {
        String sql = "delete from produtos where id = ?";
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;

        try {
            conn = dbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            preparedStatement.execute(); //it is not a query. It is an insert command

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
                return 1;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int update(produto produto) {
        String sql = "update produtos set estoque = estoque - ? where id = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = dbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, produto.getId());


            preparedStatement.execute(); //it is not a query. It is an insert command

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
                return 1;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return 0;

    }
}






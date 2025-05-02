package com.tads.pw.trabalhodepw.repository;


import com.tads.pw.trabalhodepw.entity.logista;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class logistaRepository {
    public List<logista> findEmailESenha(String email, String senha) { // listAll (if the database is huge, consider the use of pagination)
        List<logista> logistas = new ArrayList<logista>();
        String sql = "select * from logistas where email = ? and senha = ?";
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; // stores the query result


        try {
            conn =dbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            // sending the parameter to sql execution
            preparedStatement.setString(1, email); // id is an object, not primitive (intValue required)
            preparedStatement.setString(2, senha); // id is an object, not primitive (intValue required)
            resultSet = preparedStatement.executeQuery();
            // iterates the resultSet and stores in the object the column values from the
            // database
            while (resultSet.next()) {
                logista logista = new logista();
                logista.setId(resultSet.getLong("id")); // "id" is the column at postgres
                logista.setNome(resultSet.getString("nome")); // "nome" is the column at postgres
                logista.setEmail(resultSet.getString("email")); // "nome" is the column at postgres
                logista.setSenha(resultSet.getString("senha")); // "senha" is the column at postgres
                logistas.add(logista); // add the object filled with database data to products list
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
        return logistas;
    }
    public List<logista> findEmail(String email) {
        List<logista> logistas = new ArrayList<>();
        String sql = "select * from logistas where email = ?";
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; // stores the query result


        try {
            conn =dbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            // sending the parameter to sql execution
            preparedStatement.setString(1, email); // id is an object, not primitive (intValue required)
            resultSet = preparedStatement.executeQuery();
            // iterates the resultSet and stores in the object the column values from the
            // database
            while (resultSet.next()) {
                logista logista = new logista();
                logista.setId(resultSet.getLong("id")); // "id" is the column at postgres
                logista.setNome(resultSet.getString("nome")); // "id" is the column at postgres
                logista.setEmail(resultSet.getString("email")); // "id" is the column at postgres


                logistas.add(logista); // add the object filled with database data to products list
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
        return logistas;
    }


    public int save(logista logista) {
        String sql = "insert into clientes (nome, email, senha)" + " values (?, ?, ?)";
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;

        try {
            conn = dbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, logista.getNome());
            preparedStatement.setString(2, logista.getEmail());
            preparedStatement.setString(3, logista.getSenha());

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

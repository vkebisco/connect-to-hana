package com.connect.dao;


import com.connect.domain.User;
import com.connect.factories.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Queries {

    /**
     * faz um select e retorna todos os usuários
     * @return lista de todos os usuários na base
     * @throws SQLException
     */
    public List<User> getAllUsers() throws SQLException {
        // Criar um statement
        Statement stmt = ConnectionFactory.getConnection().createStatement();

        // Executar a query
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");

        //Criar um lista vazia de usuários
        List<User> userList = new ArrayList<>();

        // Iterar pelos resultados
        while (rs.next()) {
            // Adicionar os resultados a lista
            userList.add(new User(rs.getInt("id"), rs.getString("name")));
        }

        return userList;
    }

    /**
     * inserer um User no HANA
     * <p>
     * !!!IMPORTANTE!!!
     * Ao executar um SQL com parâmetros, sempre usar o PreparedStatement e setar
     * os parâmetros programaticamente, para se proteger contra ataques de SQL Injection
     * para mais informações, ver:
     * https://www.w3schools.com/sql/sql_injection.asp
     * @param user
     * @return o numero de linhas alteradas
     * @throws SQLException
     */
    public int insert(User user) throws SQLException {
        // Query de insert customizado para o preparedStatement
        String query = "INSERT INTO user (id, name) VALUES (?, ?);";

        // Preparar Statement
        PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(query);

        // Setar parâmetros
        statement.setInt(1, user.getId());
        statement.setString(2, user.getName());

        // Execute SQL query
        return statement.executeUpdate();
    }

    /**
     * inserer um User no HANA
     * <p>
     * !!!IMPORTANTE!!!
     * Ao executar um SQL com parâmetros, sempre usar o PreparedStatement e setar
     * os parâmetros programaticamente, para se proteger contra ataques de SQL Injection
     * para mais informações, ver:
     * https://www.w3schools.com/sql/sql_injection.asp
     * @param user
     * @return o numero de linhas alteradas
     * @throws SQLException
     */
    public int update(User user) throws SQLException {
        // Query de update customizado para o preparedStatement
        String query = "UPDATE user SET name = ? WHERE id = ?";

        // Preparar Statement
        PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(query);

        // Setar parâmetros
        statement.setString(1, user.getName());
        statement.setInt(2, user.getId());

        // Execute SQL query
        return statement.executeUpdate();
    }

    /**
     * inserer um User no HANA
     * <p>
     * !!!IMPORTANTE!!!
     * Ao executar um SQL com parâmetros, sempre usar o PreparedStatement e setar
     * os parâmetros programaticamente, para se proteger contra ataques de SQL Injection
     * para mais informações, ver:
     * https://www.w3schools.com/sql/sql_injection.asp
     * @param id
     * @return numero de linhas alteradas
     * @throws SQLException
     */
    public int delete(int id) throws SQLException {
        // Query de delete customizado para o preparedStatement
        String query = "DELETE FROM user WHERE id = ?";

        // Preparar Statement
        PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(query);

        // Setar parâmetros
        statement.setInt(1, id);

        // Execute SQL query
        return statement.executeUpdate();
    }

}

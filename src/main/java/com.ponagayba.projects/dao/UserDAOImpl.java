package com.ponagayba.projects.dao;

import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

    public UserDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User findById(int id) throws SQLException {
        return null;
    }

    @Override
    public User getUser(String username, String password) throws SQLException {
        String query =
                "SELECT id, token, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE username=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        User result = null;
        if (resultSet.next()) {
            result = new User();
            result.setId(resultSet.getInt("id"));
            result.setToken(resultSet.getString("token"));
            result.setUsername(username);
            result.setPassword(password);
        }
        return result;
    }

    @Override
    public boolean userExists(String username) throws SQLException {
        String query =
                "SELECT id FROM test_yourself.user " +
                "WHERE username=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    @Override
    public void create(User user) throws SQLException {
        String query =
                "INSERT INTO test_yourself.user(username, password) " +
                "VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.execute();
    }

    @Override
    public void addRole(int userId, Role role) throws SQLException {
        String query =
                "INSERT INTO test_yourself.user_to_role(user_id, role_id) " +
                "VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, role.getId());
        preparedStatement.execute();
    }
}

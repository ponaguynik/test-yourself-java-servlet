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
        String query =
                "SELECT username, password, token, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    id,
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("token"),
                    resultSet.getInt("last_result"),
                    resultSet.getInt("best_result")
            );
        }
        return user;
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
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    username,
                    password,
                    resultSet.getString("token"),
                    resultSet.getInt("last_result"),
                    resultSet.getInt("best_result")
            );
        }
        return user;
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

    @Override
    public void updateToken(int userId, String token) throws SQLException {
        String query =
                "UPDATE test_yourself.user " +
                "SET token=? " +
                "WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, token);
        preparedStatement.setInt(2, userId);
        preparedStatement.execute();
    }

    @Override
    public User findByToken(String token) throws SQLException {
        String query =
                "SELECT id, username, password, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE token=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, token);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    token,
                    resultSet.getInt("last_result"),
                    resultSet.getInt("best_result")
            );
        }
        return user;
    }

    @Override
    public void removeToken(String token) throws SQLException {
        String query =
                "UPDATE test_yourself.user " +
                "SET token=NULL " +
                "WHERE token=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, token);
        preparedStatement.execute();
    }

    @Override
    public void updateResults(User user) throws SQLException {
        String query =
                "UPDATE test_yourself.user " +
                "SET last_result=?, best_result=? " +
                "WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, user.getLastResult());
        preparedStatement.setInt(2, user.getLastResult());
        preparedStatement.setInt(3, user.getBestResult());
        preparedStatement.executeUpdate();
    }
}

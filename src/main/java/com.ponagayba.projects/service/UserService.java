package com.ponagayba.projects.service;

import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.TestResult;

import java.sql.SQLException;

public interface UserService {

    User findById(int id) throws SQLException;

    User getUser(String username, String password) throws SQLException;

    boolean userExists(String username) throws SQLException;

    void addNewUser(User user) throws SQLException;

    void updateToken(int userId, String token) throws SQLException;

    User findByToken(String token) throws SQLException;

    void removeToken(String token) throws SQLException;

    void updateResults(User user, TestResult testResult) throws SQLException;
}

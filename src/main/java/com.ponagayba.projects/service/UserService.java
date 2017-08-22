package com.ponagayba.projects.service;

import com.ponagayba.projects.model.User;

import java.sql.SQLException;

public interface UserService {

    User findById(int id) throws SQLException;

    User getUser(String username, String password) throws SQLException;

    boolean userExists(String username) throws SQLException;

    void addNewUser(User user) throws SQLException;
}

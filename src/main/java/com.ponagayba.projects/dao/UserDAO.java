package com.ponagayba.projects.dao;

import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;

import java.sql.SQLException;

public interface UserDAO {

    User getUser(String username, String password) throws SQLException;

    User findById(int id) throws SQLException;

    boolean userExists(String username) throws SQLException;

    void create(User user) throws SQLException;

    void addRole(int userId, Role role) throws SQLException;
}

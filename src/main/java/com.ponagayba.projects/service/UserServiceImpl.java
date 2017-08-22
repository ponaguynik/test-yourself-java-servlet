package com.ponagayba.projects.service;

import com.ponagayba.projects.dao.RoleDAO;
import com.ponagayba.projects.dao.UserDAO;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private RoleDAO roleDAO;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public User findById(int id) throws SQLException {
        return userDAO.findById(id);
    }

    @Override
    public User getUser(String username, String password) throws SQLException {
        return userDAO.getUser(username, password);
    }

    @Override
    public boolean userExists(String username) throws SQLException {
        return userDAO.userExists(username);
    }

    @Override
    public void addNewUser(User user) throws SQLException {
        userDAO.create(user);
        User userDB = userDAO.getUser(user.getUsername(), user.getPassword());
        for (Role role : user.getRoles()) {
            userDAO.addRole(userDB.getId(), role);
        }
    }
}

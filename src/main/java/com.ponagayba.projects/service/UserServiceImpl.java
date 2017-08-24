package com.ponagayba.projects.service;

import com.ponagayba.projects.dao.RoleDAO;
import com.ponagayba.projects.dao.UserDAO;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public User findById(int id) throws SQLException {
        User result = userDAO.findById(id);
        if (result != null) {
            result.setRoles(roleDAO.getUserRoles(result.getId()));
        }
        return result;
    }

    @Override
    public User getUser(String username, String password) throws SQLException {
        User result = userDAO.getUser(username, password);
        if (result != null) {
            result.setRoles(roleDAO.getUserRoles(result.getId()));
        }
        return result;
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

    @Override
    public void updateToken(int userId, String token) throws SQLException {
        userDAO.updateToken(userId, token);
    }

    @Override
    public User findByToken(String token) throws SQLException {
        User result = userDAO.findByToken(token);
        if (result != null) {
            result.setRoles(roleDAO.getUserRoles(result.getId()));
        }
        return result;
    }

    @Override
    public void removeToken(String token) throws SQLException {
        userDAO.removeToken(token);
    }
}

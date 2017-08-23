package com.ponagayba.projects.factory;

import com.ponagayba.projects.controller.*;
import com.ponagayba.projects.dao.RoleDAO;
import com.ponagayba.projects.dao.RoleDAOImpl;
import com.ponagayba.projects.dao.UserDAO;
import com.ponagayba.projects.dao.UserDAOImpl;
import com.ponagayba.projects.service.RoleService;
import com.ponagayba.projects.service.RoleServiceImpl;
import com.ponagayba.projects.service.UserService;
import com.ponagayba.projects.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class Factory {

    public static Controller getHomePageController() {
        return new HomePageController();
    }

    public static Controller getSignUpPageController() {
        return new SignUpPageController();
    }

    public static Controller getSignUpController() {
        return new SignUpController();
    }

    public static Controller getLoginPageController() {
        return new LoginPageController();
    }

    public static Controller getLoginController() {
        return new LoginController();
    }

    public static Controller getLogoutController() {
        return new LogoutController();
    }

    public static UserService getUserService() {
        return new UserServiceImpl(getUserDAO(), getRoleDAO());
    }

    public static RoleService getRoleService() {
        return new RoleServiceImpl(getRoleDAO());
    }


    private static RoleDAO getRoleDAO() {
        return new RoleDAOImpl(getConnection());
    }

    private static UserDAO getUserDAO() {
        return new UserDAOImpl(getConnection());
    }

    private static Connection getConnection() {
        Connection connection;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (Exception e) {
            throw new RuntimeException("Driver has not been initialized");
        }
        return connection;
    }
}

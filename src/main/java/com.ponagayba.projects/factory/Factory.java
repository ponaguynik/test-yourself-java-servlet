package com.ponagayba.projects.factory;

import com.ponagayba.projects.controller.*;
import com.ponagayba.projects.controller.test.*;
import com.ponagayba.projects.controller.user.*;
import com.ponagayba.projects.dao.*;
import com.ponagayba.projects.service.*;

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

    public static Controller getTestPageController() {
        return new TestPageController();
    }

    public static Controller getTestController() {
        return new TestController();
    }

    public static Controller getFinishTestController() {
        return new FinishTestController();
    }

    public static Controller getCancelAnswerTestController() {
        return new CancelAnswerTestController();
    }

    public static Controller getAnswerTestController() {
        return new AnswerTestController();
    }

    public static Controller getResultsPageController() {
        return new ResultsPageController();
    }


    public static UserService getUserService() {
        return new UserServiceImpl(getUserDAO(), getRoleDAO());
    }

    public static RoleService getRoleService() {
        return new RoleServiceImpl(getRoleDAO());
    }

    public static QuestionService getQuestionService() {
        return new QuestionServiceImpl(getQuestionDAO());
    }

    public static TestService getTestService() {
        return new TestServiceImpl();
    }

    public static TestResultService getTestResultService() {
        return new TestResultServiceImpl(getTestResultDAO());
    }


    private static RoleDAO getRoleDAO() {
        return new RoleDAOImpl(getConnection());
    }

    private static UserDAO getUserDAO() {
        return new UserDAOImpl(getConnection());
    }

    private static QuestionDAO getQuestionDAO() {
        return new QuestionDAOImpl(getConnection());
    }

    private static TestResultDAO getTestResultDAO() {
        return new TestResultDAOImpl(getConnection());
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

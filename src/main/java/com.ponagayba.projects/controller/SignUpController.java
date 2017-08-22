package com.ponagayba.projects.controller;

import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SignUpController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");

        String validationResult = validate(username, password, confPassword);
        if (validationResult == null) {
            result.setRedirect(true);
            addNewUser(username, password);
            result.setView("login");
            result.addAttribute("message", "User has been successfully created!");
        } else {
            result.setView("signUp");
            result.addAttribute("error", validationResult);
        }
        return result;
    }

    private String validate(String username, String password, String confPassword) throws SQLException {
        String result = null;
        if (Factory.getUserService().userExists(username)) {
            result = "Username with such username already exists.";
        } else if (!password.equals(confPassword)) {
            result = "Password does not match the confirm password.";
        }
        return result;
    }

    private void addNewUser(String username, String password) throws SQLException {
        User newUser = new User(username, password);
        List<Role> roles = new ArrayList<>();
        roles.add(Factory.getRoleService().findByName("user"));
        newUser.setRoles(roles);
        Factory.getUserService().addNewUser(newUser);
    }
}

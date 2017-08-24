package com.ponagayba.projects.controller;

import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class HomePageController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("home");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("TOKEN")) {
                    User user = Factory.getUserService().findByToken(cookie.getValue());
                    if (user != null) {
                        result.setAttribute("user", user);
                    }
                }
            }
        }
        return result;
    }
}

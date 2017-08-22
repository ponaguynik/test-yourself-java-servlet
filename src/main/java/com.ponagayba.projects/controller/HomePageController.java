package com.ponagayba.projects.controller;

import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HomePageController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException {
        return new ModelAndView("home");
    }
}

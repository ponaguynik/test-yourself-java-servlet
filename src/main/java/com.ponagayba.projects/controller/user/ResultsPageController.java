package com.ponagayba.projects.controller.user;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.TestResult;
import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ResultsPageController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("results");
        User user = (User) request.getAttribute("user");
        List<TestResult> results = Factory.getTestResultService().getUserResults(user.getId());
        Collections.reverse(results);
        result.setAttribute("results", results);
        return result;
    }
}

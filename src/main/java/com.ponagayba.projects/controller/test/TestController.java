package com.ponagayba.projects.controller.test;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.service.QuestionService;
import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TestController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("test");
        if (request.getSession().getAttribute("questions") == null) {
            prepareTest(result);
        }
        setCurrentQuestion(request, result);
        return result;
    }

    private void prepareTest(ModelAndView mv) throws SQLException {
        QuestionService questionService = Factory.getQuestionService();
        List<Question> questions = questionService.getAll();
        List<Question> randomQuestions = questionService.getRandomQuestions(questions, 10);
        mv.setSessionAttribute("questions", randomQuestions);
        mv.setSessionAttribute("startTime", System.nanoTime());
    }

    private int getQuestionNum(HttpServletRequest request) {
        int qnNum;
        try {
            qnNum = Integer.parseInt(request.getParameter("qnNum"));
        } catch (NumberFormatException e) {
            try {
                qnNum = (int) request.getAttribute("qnNum");
            } catch (NullPointerException e1) {
                qnNum = 1;
            }
        }
        return qnNum;
    }

    private void setCurrentQuestion(HttpServletRequest request, ModelAndView mv) {
        int qnNum = getQuestionNum(request);

        HttpSession session = request.getSession();
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        if (questions == null) {
            questions = (List<Question>) mv.getSessionAttribute("questions");
        }
        for (Question question : questions) {
            if (question.getNum() == qnNum) {
                question.setActive(true);
            } else {
                question.setActive(false);
            }
        }

        //Set current question to the session scope.
        mv.setSessionAttribute("currentQn", questions.get(qnNum-1));
    }
}

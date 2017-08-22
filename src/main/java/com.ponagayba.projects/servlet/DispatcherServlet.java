package com.ponagayba.projects.servlet;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.exception.PageNotFoundException;
import com.ponagayba.projects.factory.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private static final String PREFIX = "/WEB-INF/views/";
    private static final String SUFFIX = ".jsp";

    private Map<String, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        controllerMap.put("GET/pages/", Factory.getHomePageController());
        controllerMap.put("GET/pages/home", Factory.getHomePageController());
        controllerMap.put("GET/pages/signup", Factory.getSignUpPageController());
        controllerMap.put("POST/pages/signup", Factory.getSignUpController());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Controller controller = getController(request);
            ModelAndView modelAndView = controller.process(request);
            render(modelAndView, request, response);
        } catch (PageNotFoundException e) {
            response.sendError(404);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    private Controller getController(HttpServletRequest request) {
        String methodUri = request.getMethod() + request.getRequestURI().split(";")[0];
        Controller result = controllerMap.get(methodUri);
        if (result == null) {
            throw new PageNotFoundException();
        }
        return result;
    }

    private void render(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (modelAndView.getCookies() != null) {
            for (Cookie cookie : modelAndView.getCookies()) {
                response.addCookie(cookie);
            }
        }
        if (modelAndView.isRedirect()) {
            response.sendRedirect(modelAndView.getView());
        } else {
            for (Map.Entry<String, Object> entry : modelAndView.getAttributes().entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            request.getRequestDispatcher(PREFIX + modelAndView.getView() + SUFFIX).forward(request, response);
        }
    }
}

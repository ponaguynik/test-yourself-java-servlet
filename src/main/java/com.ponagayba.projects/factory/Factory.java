package com.ponagayba.projects.factory;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.controller.HomePageController;

public class Factory {

    public static Controller getHomePageController() {
        return new HomePageController();
    }
}

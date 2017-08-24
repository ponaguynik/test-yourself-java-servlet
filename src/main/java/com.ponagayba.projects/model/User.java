package com.ponagayba.projects.model;

import java.util.List;

public class User {

    private Integer id;
    private String username;
    private String password;
    private String token;
    private Integer lastResult;
    private Integer bestResult;
    private List<Role> roles;
    private boolean admin;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, String token, Integer lastResult, Integer bestResult) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
        this.lastResult = lastResult;
        this.bestResult = bestResult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    public int getBestResult() {
        return bestResult;
    }

    public void setBestResult(int bestResult) {
        this.bestResult = bestResult;
    }

    public void setLastResult(Integer lastResult) {
        this.lastResult = lastResult;
    }

    public void setBestResult(Integer bestResult) {
        this.bestResult = bestResult;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setRoles(List<Role> roles) {
        admin = roles.contains(new Role(0, "admin"));
        this.roles = roles;
    }
}

package com.ponagayba.projects.service;

import com.ponagayba.projects.dao.RoleDAO;
import com.ponagayba.projects.model.Role;

import java.sql.SQLException;

public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public Role findByName(String name) throws SQLException {
        return roleDAO.findByName(name);
    }
}

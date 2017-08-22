package com.ponagayba.projects.service;

import com.ponagayba.projects.model.Role;

import java.sql.SQLException;

public interface RoleService {

    Role findByName(String name) throws SQLException;
}

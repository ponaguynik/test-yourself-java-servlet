package com.ponagayba.projects.service.user;

import com.ponagayba.projects.model.Role;

import java.sql.SQLException;

public interface RoleService {

    Role findByName(String name) throws SQLException;
}

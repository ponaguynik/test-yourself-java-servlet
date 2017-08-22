package com.ponagayba.projects.dao;


import com.ponagayba.projects.model.Role;

import java.sql.SQLException;

public interface RoleDAO {

    Role findByName(String name) throws SQLException;
}

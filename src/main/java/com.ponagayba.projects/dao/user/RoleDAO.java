package com.ponagayba.projects.dao.user;


import com.ponagayba.projects.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {

    Role findByName(String name) throws SQLException;

    List<Role> getUserRoles(int userId) throws SQLException;
}

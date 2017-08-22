package com.ponagayba.projects.dao;

import com.ponagayba.projects.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOImpl extends AbstractDAO implements RoleDAO {

    public RoleDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role findByName(String name) throws SQLException {
        String query =
                "SELECT id, name " +
                "FROM test_yourself.role " +
                "WHERE name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        }
        return null;
    }
}

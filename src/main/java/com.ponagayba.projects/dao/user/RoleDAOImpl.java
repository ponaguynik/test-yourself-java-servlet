package com.ponagayba.projects.dao.user;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Role> getUserRoles(int userId) throws SQLException {
        String query =
                "SELECT r.id, r.name " +
                "FROM test_yourself.user u " +
                "JOIN test_yourself.user_to_role ur ON ur.user_id=u.id " +
                "JOIN test_yourself.role r ON r.id=ur.role_id " +
                "WHERE u.id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Role> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            ));
        }
        return result;
    }
}

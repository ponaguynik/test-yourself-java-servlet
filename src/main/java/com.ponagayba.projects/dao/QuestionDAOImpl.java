package com.ponagayba.projects.dao;

import com.ponagayba.projects.model.test.Question;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl extends AbstractDAO implements QuestionDAO {

    public QuestionDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Question> getAll() throws SQLException {
        String query =
                "SELECT id, question, code, choice, choice_type, answer " +
                "FROM test_yourself.question;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Question> result = new ArrayList<>();
        while (resultSet.next()) {
            Question question = new Question(
                    resultSet.getInt("id"),
                    resultSet.getString("question"),
                    resultSet.getString("code"),
                    resultSet.getString("choice").split("&"),
                    resultSet.getString("choice_type"),
                    resultSet.getString("answer").split("&")
            );
            result.add(question);
        }
        return result;
    }
}

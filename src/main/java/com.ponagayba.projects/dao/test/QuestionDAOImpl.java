package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.test.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDAOImpl extends AbstractDAO implements QuestionDAO {

    public QuestionDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Question> getAll() throws SQLException {
        String query =
                "SELECT id, question, code, options, option_type, answer " +
                "FROM test_yourself.question;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Question> result = new ArrayList<>();
        while (resultSet.next()) {
            Question question = new Question();
            question.setId(resultSet.getInt("id"));
            question.setQuestion(resultSet.getString("question"));
            question.setCode(resultSet.getString("code"));
            question.setOptions(Arrays.asList(resultSet.getString("options").split("&")));
            question.setOptionType(resultSet.getString("option_type"));
            question.setCorrectAnswers(Arrays.asList(resultSet.getString("answer").split("&")));
            result.add(question);
        }
        return result;
    }

    @Override
    public void addQuestion(Question question) throws SQLException {
        String options = toStoringForm(question.getOptions());
        String answer = toStoringForm(question.getCorrectAnswers());
        String query =
                "INSERT INTO test_yourself.question(question, code, options, option_type, answer)" +
                "VALUES(?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, question.getQuestion());
        preparedStatement.setString(2, question.getCode());
        preparedStatement.setString(3, options);
        preparedStatement.setString(4, question.getOptionType());
        preparedStatement.setString(5, answer);
        preparedStatement.execute();
    }

    private String toStoringForm(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(item).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

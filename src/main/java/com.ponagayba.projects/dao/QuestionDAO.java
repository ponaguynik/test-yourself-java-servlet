package com.ponagayba.projects.dao;

import com.ponagayba.projects.model.test.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {

    List<Question> getAll() throws SQLException;
}

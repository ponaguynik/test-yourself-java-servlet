package com.ponagayba.projects.service;

import com.ponagayba.projects.model.test.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionService {

    List<Question> getAll() throws SQLException;

    List<Question> getRandomQuestions(List<Question> questions, int num);
}

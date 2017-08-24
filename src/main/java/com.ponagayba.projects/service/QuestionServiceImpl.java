package com.ponagayba.projects.service;

import com.ponagayba.projects.dao.QuestionDAO;
import com.ponagayba.projects.model.test.Question;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;

    public QuestionServiceImpl(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    public List<Question> getAll() throws SQLException {
        return questionDAO.getAll();
    }

    @Override
    public List<Question> getRandomQuestions(List<Question> questions, int num) {
        List<Question> copy = new ArrayList<>(questions);
        List<Question> result = new ArrayList<>();
        for (int i = 0, random; i < num; i++) {
            if (copy.isEmpty()) {
                break;
            }
            random = ThreadLocalRandom.current().nextInt(0, copy.size());
            Question qsn = copy.get(random);
            qsn.setNum(i + 1);
            if (qsn.getNum() == 1) {
                qsn.setActive(true);
            }
            result.add(qsn);
            copy.remove(random);
        }
        return result;
    }
}

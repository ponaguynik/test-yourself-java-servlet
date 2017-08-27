package com.ponagayba.projects.dao;

import com.ponagayba.projects.model.test.TestResult;

import java.sql.SQLException;
import java.util.List;

public interface TestResultDAO {

    void addTestResult(TestResult testResult) throws SQLException;

    List<TestResult> getUserResults(Integer userId) throws SQLException;
}

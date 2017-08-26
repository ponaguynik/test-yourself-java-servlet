package com.ponagayba.projects.service;

import com.ponagayba.projects.model.test.TestResult;

import java.sql.SQLException;

public interface TestResultService {

    void addTestResult(TestResult testResult) throws SQLException;
}

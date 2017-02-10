package com.fakecompany.applicant.approver.models.tests;

/**
 * Created by coatsmi on 2/9/17.
 */
public interface StandardizedTestResult {
    String getScore();
    String getTestType();
    Boolean isScorePassing();
}

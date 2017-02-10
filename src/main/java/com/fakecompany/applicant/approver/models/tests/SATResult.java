package com.fakecompany.applicant.approver.models.tests;

import com.fakecompany.applicant.approver.rules.EvaluationStandards;

/**
 * Created by coatsmi on 2/9/17.
 */
public class SATResult implements StandardizedTestResult {
    private static final String TEST_TYPE = "SAT";

    private Integer score;

    public SATResult(Integer score) {
        this.score = score;
    }

    public String getScore() {
        return score.toString();
    }

    public String getTestType() {
        return TEST_TYPE;
    }

    public Boolean isScorePassing() {
        return score >= EvaluationStandards.ACCEPTED_SAT_SCORE;
    }

}

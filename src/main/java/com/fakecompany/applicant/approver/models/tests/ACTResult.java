package com.fakecompany.applicant.approver.models.tests;

import com.fakecompany.applicant.approver.rules.EvaluationStandards;

/**
 * Created by coatsmi on 2/9/17.
 */
public class ACTResult implements StandardizedTestResult {
    private static final String TEST_TYPE = "ACT";

    private Integer score;

    public ACTResult(Integer score) {
        this.score = score;
    }

    public String getScore() {
        return score.toString();
    }

    public String getTestType() {
        return TEST_TYPE;
    }

    public Boolean isScorePassing() {
        return score >= EvaluationStandards.ACCEPTED_ACT_SCORE;
    }
}

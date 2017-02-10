package com.fakecompany.applicant.approver.rules;

import com.fakecompany.applicant.approver.models.ApplicantProfile;
import com.fakecompany.applicant.approver.models.ApplicantResult;
import com.fakecompany.applicant.approver.models.ResultType;
import com.fakecompany.applicant.approver.models.tests.StandardizedTestResult;

import java.util.List;

/**
 * Created by coatsmi on 2/9/17.
 */
public class InstantAcceptUtil {
    public static ApplicantResult evaluate(ApplicantProfile applicant) {
        ApplicantResult result = new ApplicantResult();
        if (passAgeStateRequirement(applicant.getAge(), applicant.getStateResdidence())
                && passGPARequirement(applicant.getGpa(), applicant.getGpaScale())
                && passStandardizedTestRequirement(applicant.getTestScores())) {
            result.setResultType(ResultType.INSTANT_ACCEPTED);
        }
        return result;
    }

    static boolean passAgeStateRequirement(Integer age, String stateResdidence) {
        if (EvaluationStandards.IN_STATE.equals(stateResdidence)) {
            return (EvaluationStandards.MIN_AGE_IN_STATE.compareTo(age) <= 0) && (EvaluationStandards.MAX_AGE_IN_STATE.compareTo(age) > 0);
        }

        return EvaluationStandards.MIN_AGE_ANY_STATE < age;
    }

    static boolean passGPARequirement(Double gpa, Double gpaScale) {
        return ((gpa/gpaScale) * 100) >= EvaluationStandards.ACCEPT_GPA;
    }

    static boolean passStandardizedTestRequirement(List<StandardizedTestResult> testScores) {
        boolean result = false;
        for (StandardizedTestResult testScore : testScores) {
            result = result || testScore.isScorePassing();
        }
        return result;
    }
}

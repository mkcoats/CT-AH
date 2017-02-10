package com.fakecompany.applicant.approver;

import com.fakecompany.applicant.approver.models.ApplicantProfile;
import com.fakecompany.applicant.approver.models.ApplicantResult;
import com.fakecompany.applicant.approver.models.ResultType;
import com.fakecompany.applicant.approver.rules.InstantAcceptUtil;
import com.fakecompany.applicant.approver.rules.InstantRejectUtil;

/**
 * Created by coatsmi on 2/9/17.
 */
public class ApplicantApprover {

    public static ApplicantResult evaluate(ApplicantProfile applicant) {
        ApplicantResult result = InstantRejectUtil.evaluate(applicant);

        if (ResultType.INSTANT_REJECTED.equals(result.getResultType())) {
            return result;
        }

        result = InstantAcceptUtil.evaluate(applicant);

        return result;
    }
}

package com.fakecompany.applicant.approver;

import com.fakecompany.applicant.approver.models.ApplicantProfile;
import com.fakecompany.applicant.approver.models.ResultType;
import com.fakecompany.applicant.approver.models.tests.ACTResult;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by coatsmi on 2/9/17.
 */
public class ApplicantApproverTest {

    @Test
    public void testEvaluate() throws Exception {
        ApplicantProfile profile = new ApplicantProfile("Foo", "Bar", 25, "TX", 3.5, 4.0);
        profile.addTestScore(new ACTResult(15));

        assertEquals(ResultType.FURTHER_REVIEW, ApplicantApprover.evaluate(profile).getResultType());

    }

}
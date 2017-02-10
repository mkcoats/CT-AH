package com.fakecompany.applicant.approver.rules;

import com.fakecompany.applicant.approver.models.ApplicantProfile;
import com.fakecompany.applicant.approver.models.ApplicantResult;
import com.fakecompany.applicant.approver.models.Felony;
import com.fakecompany.applicant.approver.models.ResultType;
import com.fakecompany.applicant.approver.models.tests.ACTResult;
import com.fakecompany.applicant.approver.models.tests.SATResult;
import com.fakecompany.applicant.approver.models.tests.StandardizedTestResult;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by coatsmi on 2/9/17.
 */
public class InstantAcceptUtilTest {

    @Test
    public void testEvaluate() throws Exception {
        ApplicantProfile profile = new ApplicantProfile("Foo", "Bar", 25, "CA", 3.8, 4.0);
        profile.addTestScore(new ACTResult(28));
        profile.addFelony(new Felony(DateTime.now().minusYears(10).toDate(), "Crime"));

        ApplicantResult actual = InstantAcceptUtil.evaluate(profile);

        assertEquals(ResultType.INSTANT_ACCEPTED, actual.getResultType());
        assertEquals(0, actual.getMessages().size());
    }
    @Test
    public void testPassAgeStateRequirement() throws Exception {
        String state = "CA";
        Integer age = 23;
        assertTrue(InstantAcceptUtil.passAgeStateRequirement(age, state));
    }

    @Test
    public void testPassAgeStateRequirementOver80() throws Exception {
        String state = "TX";
        Integer age = 81;
        assertTrue(InstantAcceptUtil.passAgeStateRequirement(age, state));
    }

    @Test
    public void testPassGPARequirementPass() throws Exception {
        Double gpa = 3.6;
        Double gpaScale = 4.0;
        assertTrue(InstantAcceptUtil.passGPARequirement(gpa, gpaScale));
    }

    @Test
    public void testPassGPARequirementFail() throws Exception {
        Double gpa = 4.4;
        Double gpaScale = 5.0;
        assertFalse(InstantAcceptUtil.passGPARequirement(gpa, gpaScale));
    }

    @Test
    public void testPassStandardizedTestRequirementSAT() throws Exception {

        List<StandardizedTestResult> testScores = new ArrayList<StandardizedTestResult>();
        testScores.add(new SATResult(1921));
        assertTrue(InstantAcceptUtil.passStandardizedTestRequirement(testScores));
    }

    @Test
    public void testPassStandardizedTestRequirementACT() throws Exception {
        List<StandardizedTestResult> testScores = new ArrayList<StandardizedTestResult>();
        testScores.add(new ACTResult(28));
        assertTrue(InstantAcceptUtil.passStandardizedTestRequirement(testScores));
    }

    @Test
    public void testPassStandardizedTestRequirementSATPassAndACTFail() throws Exception {
        List<StandardizedTestResult> testScores = new ArrayList<StandardizedTestResult>();
        testScores.add(new SATResult(1921));
        testScores.add(new ACTResult(1));
        assertTrue(InstantAcceptUtil.passStandardizedTestRequirement(testScores));
    }

    @Test
    public void testPassStandardizedTestRequirementSATFailAndACTPass() throws Exception {
        List<StandardizedTestResult> testScores = new ArrayList<StandardizedTestResult>();
        testScores.add(new SATResult(1));
        testScores.add(new ACTResult(28));
        assertTrue(InstantAcceptUtil.passStandardizedTestRequirement(testScores));
    }

    @Test
    public void testPassStandardizedTestRequirementSATFailAndACTFail() throws Exception {
        List<StandardizedTestResult> testScores = new ArrayList<StandardizedTestResult>();
        testScores.add(new SATResult(1));
        testScores.add(new ACTResult(1));
        assertFalse(InstantAcceptUtil.passStandardizedTestRequirement(testScores));
    }
}
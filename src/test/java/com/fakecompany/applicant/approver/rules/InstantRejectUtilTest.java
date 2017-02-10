package com.fakecompany.applicant.approver.rules;

import com.fakecompany.applicant.approver.models.ApplicantProfile;
import com.fakecompany.applicant.approver.models.ApplicantResult;
import com.fakecompany.applicant.approver.models.Felony;
import com.fakecompany.applicant.approver.models.ResultType;
import com.fakecompany.applicant.approver.models.tests.ACTResult;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by coatsmi on 2/9/17.
 */
public class InstantRejectUtilTest {

    @Test
    public void testEvaluate() throws Exception {
        ApplicantProfile profile = new ApplicantProfile("foo", "BAR", -25, "TX", 1.0, 4.0);
        profile.addTestScore(new ACTResult(15));
        profile.addFelony(new Felony(DateTime.now().minusYears(3).toDate(), "Crime"));

        ApplicantResult actual = InstantRejectUtil.evaluate(profile);

        assertEquals(ResultType.INSTANT_REJECTED, actual.getResultType());
        assertEquals(4, actual.getMessages().size());
    }

    @Test
    public void testPassFelonyCriteriaWithOldFelony() throws Exception {

        DateTime date = DateTime.now().minusYears(6);
        Felony felony1 = new Felony(date.toDate(), "Crime");
        List<Felony> feloniesList = new ArrayList<Felony>();
        feloniesList.add(felony1);

        assertTrue(InstantRejectUtil.passFelonyCriteria(feloniesList));

    }

    @Test
    public void testPassFelonyCriteriaWithRecentFelony() throws Exception {

        DateTime date = DateTime.now().minusYears(3);
        Felony felony1 = new Felony(date.toDate(), "Crime");
        List<Felony> feloniesList = new ArrayList<Felony>();
        feloniesList.add(felony1);

        assertFalse(InstantRejectUtil.passFelonyCriteria(feloniesList));

    }

    @Test
    public void testPassGPACriteria() throws Exception {

        Double gpa = 2.8;
        Double gpaScale = 4.0;
        assertFalse(InstantRejectUtil.passGPACriteria(gpa, gpaScale));
    }

    @Test
    public void testPassNameFormatPass() throws Exception {

        assertTrue(InstantRejectUtil.passNameFormat("Foobar"));
    }

    @Test
    public void testPassNameFormatFailWhiteSpace() throws Exception {

        assertFalse(InstantRejectUtil.passNameFormat("Foo bar"));
    }

    @Test
    public void testPassNameFormatFailMuliCaps() throws Exception {

        assertFalse(InstantRejectUtil.passNameFormat("FooBar"));
    }

    @Test
    public void testPassNameFormatFailLowerCase() throws Exception {

        assertFalse(InstantRejectUtil.passNameFormat("foobar"));
    }
}
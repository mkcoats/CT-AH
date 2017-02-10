package com.fakecompany.applicant.approver.rules;

import com.fakecompany.applicant.approver.models.ApplicantProfile;
import com.fakecompany.applicant.approver.models.ApplicantResult;
import com.fakecompany.applicant.approver.models.Felony;
import com.fakecompany.applicant.approver.models.ResultType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by coatsmi on 2/9/17.
 */
public class InstantRejectUtil {

    public static ApplicantResult evaluate(ApplicantProfile applicant) {

        ApplicantResult result = new ApplicantResult();

        if (!passFelonyCriteria(applicant.getFelonies())) {
            result.setResultType(ResultType.INSTANT_REJECTED);
            result.addMessage("Rejected Reason: Applicant has Felony charge within last " + EvaluationStandards.FELONY_EXPUNGE_DATE + " years.");
        }

        if (applicant.getAge() < 0) {
            result.setResultType(ResultType.INSTANT_REJECTED);
            result.addMessage("Rejected Reason: Applicant listed age as " + applicant.getAge() + ". Can't have negative years.");
        }

        if (!passGPACriteria(applicant.getGpa(), applicant.getGpaScale())) {
            result.setResultType(ResultType.INSTANT_REJECTED);
            result.addMessage("Rejected Reason: Applicant GPA below " + EvaluationStandards.REJECT_GPA + "% " + applicant.getGpa() + "/" + applicant.getGpaScale());
        }

        if (!passNameFormat(applicant.getFirstName()) || !passNameFormat(applicant.getLastName())) {
            result.setResultType(ResultType.INSTANT_REJECTED);
            result.addMessage("Rejected Reason: Applicant did not format name correctly");
        }
        return result;
    }

    static boolean passFelonyCriteria(List<Felony> felonies) {
        boolean result = true;
        for (Felony felony : felonies) {
            DateTime fiveYearsAgo = new DateTime().minusYears(EvaluationStandards.FELONY_EXPUNGE_DATE);
            result = result && fiveYearsAgo.toDate().compareTo(felony.getDateCommitted()) > 0;

        }
        return result;
    }

    static boolean passGPACriteria(Double gpa, Double gpaScale) {

        return ((gpa/gpaScale) * 100) > EvaluationStandards.REJECT_GPA;
    }

    static boolean passNameFormat(String name) {
        Pattern pattern = Pattern.compile(EvaluationStandards.NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }


}

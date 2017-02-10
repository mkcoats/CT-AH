package com.fakecompany.applicant.approver.rules;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * Created by coatsmi on 2/9/17.
 */
public class EvaluationStandards {
    public static final String IN_STATE = "CA";
    public static final Integer MAX_AGE_IN_STATE = 26;
    public static final Integer MIN_AGE_IN_STATE = 17;

    public static final Integer MIN_AGE_ANY_STATE = 80;

    public static final Integer ACCEPTED_ACT_SCORE = 27;
    public static final Integer ACCEPTED_SAT_SCORE = 1920;

    public static final String NAME_REGEX = "[A-Z][a-z]*";

    public static final Integer FELONY_EXPUNGE_DATE = 5;

    public static final Integer ACCEPT_GPA = 90;

    public static final Integer REJECT_GPA = 70;
}

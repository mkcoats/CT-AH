package com.fakecompany.applicant.approver.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coatsmi on 2/9/17.
 */
public class ApplicantResult {
    private ResultType resultType = ResultType.FURTHER_REVIEW;
    private List<String> messages;

    public ApplicantResult() {
        this.resultType = ResultType.FURTHER_REVIEW;
        this.messages = new ArrayList<String>();
    }

    public ApplicantResult(ResultType resultType, String message) {
        this.resultType = resultType;
        this.messages = new ArrayList<String>();
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}

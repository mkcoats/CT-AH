package com.fakecompany.applicant.approver.models;

import java.util.Date;

/**
 * Created by coatsmi on 2/9/17.
 */
public class Felony {

    public Date dateCommitted;
    public String message;

    public Felony(Date date, String description) {
        dateCommitted = date;
        message = description;
    }

    public Date getDateCommitted() {
        return dateCommitted;
    }

    public void setDateCommitted(Date dateCommitted) {
        this.dateCommitted = dateCommitted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

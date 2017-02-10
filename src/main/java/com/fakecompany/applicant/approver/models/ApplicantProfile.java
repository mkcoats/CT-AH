package com.fakecompany.applicant.approver.models;

import com.fakecompany.applicant.approver.models.tests.StandardizedTestResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coatsmi on 2/9/17.
 */
public class ApplicantProfile {
    String firstName;
    String lastName;
    Integer age;
    String stateResdidence;
    Double gpa;
    Double gpaScale;
    List<StandardizedTestResult> testScores;
    List<Felony> felonies;

    public ApplicantProfile(String firstName, String lastName, Integer age, String state, Double gpa, Double gpaScale) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.stateResdidence = state;
        this.gpa = gpa;
        this.gpaScale = gpaScale;
        this.testScores = new ArrayList<StandardizedTestResult>();
        this.felonies = new ArrayList<Felony>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStateResdidence() {
        return stateResdidence;
    }

    public void setStateResdidence(String stateResdidence) {
        this.stateResdidence = stateResdidence;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public Double getGpaScale() {
        return gpaScale;
    }

    public void setGpaScale(Double gpaScale) {
        this.gpaScale = gpaScale;
    }

    public List<StandardizedTestResult> getTestScores() {
        return testScores;
    }

    public void setTestScores(List<StandardizedTestResult> testScores) {
        this.testScores = testScores;
    }

    public void addTestScore(StandardizedTestResult testScore) {
        this.testScores.add(testScore);
    }

    public List<Felony> getFelonies() {
        return felonies;
    }

    public void setFelonies(List<Felony> felonies) {
        this.felonies = felonies;
    }

    public void addFelony(Felony felony) {
        this.felonies.add(felony);
    }
}

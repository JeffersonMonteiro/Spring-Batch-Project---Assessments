package com.example.demo.Entity;

import javax.persistence.*;

@Entity
public class Activity {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int activityId;

    private String code;
    private int dateCode;
    private String slum;

//    @ManyToOne
//    private Volunteer volunteer;

    public Activity() {
    }

    public Activity(int activityId, String code, int dateCode, String slum) {
        this.activityId = activityId;
        this.code = code;
        this.dateCode = dateCode;
        this.slum = slum;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDateCode() {
        return dateCode;
    }

    public void setDateCode(int dateCode) {
        this.dateCode = dateCode;
    }

    public String getSlum() {
        return slum;
    }

    public void setSlum(String slum) {
        this.slum = slum;
    }

//    public Volunteer getVolunteer() {
//        return volunteer;
//    }
//
//    public void setVolunteer(Volunteer volunteer) {
//        this.volunteer = volunteer;
//    }
}

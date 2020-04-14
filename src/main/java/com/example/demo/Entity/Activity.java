package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Activity {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "ACTIVITY_ID", unique = true, nullable = false)
    private int id;

    private String code;
    private int dateCode;
    private String slum;

    @JsonBackReference
    @ManyToOne
    private Volunteer volunteer;

    public Activity() {
    }

    public Activity(int id, String code, int dateCode, String slum) {
        this.id = id;
        this.code = code;
        this.dateCode = dateCode;
        this.slum = slum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
}

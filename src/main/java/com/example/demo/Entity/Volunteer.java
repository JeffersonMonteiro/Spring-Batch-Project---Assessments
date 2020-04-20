package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.aspectj.bridge.IMessage;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    private String name;
    private int age;
    private int amntBuilding;
    private int amntSurvey;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="ID")
    private List<Activity> activityList;

    public Volunteer() {
    }

    public Volunteer(String name, int age, int amntBuilding, int amntSurvey) {
        this.name = name;
        this.age = age;
        this.amntBuilding = amntBuilding;
        this.amntSurvey = amntSurvey;
    }

    public Volunteer(int id, String name, int age, int amntBuilding, int amntSurvey) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.amntBuilding = amntBuilding;
        this.amntSurvey = amntSurvey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAmntBuilding() {
        return amntBuilding;
    }

    public void setAmntBuilding(int amntBuilding) {
        this.amntBuilding = amntBuilding;
    }

    public int getAmntSurvey() {
        return amntSurvey;
    }

    public void setAmntSurvey(int amntSurvey) {
        this.amntSurvey = amntSurvey;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }
}

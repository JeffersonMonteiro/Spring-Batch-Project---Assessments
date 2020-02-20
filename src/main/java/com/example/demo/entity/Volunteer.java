package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int amntBuilding;
    private int amntSurvey;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "volunteer")
    private Set<Event> events;


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

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}

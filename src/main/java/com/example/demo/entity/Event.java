package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "tbEvents")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private ActivityType type;
    private String dateCode;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunt_id")
    private Volunteer volunteer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
}

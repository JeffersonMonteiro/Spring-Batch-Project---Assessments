package com.project.spring_rest_project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;

    private String nameEvent;

    private  String localEvent;

    private String dateEvent;

    public Event() { }

    public Event(long eventId, String nameEvent, String localEvent, String dateEvent) {
        this.eventId = eventId;
        this.nameEvent = nameEvent;
        this.localEvent = localEvent;
        this.dateEvent = dateEvent;
    }

    public long getCode() {
        return eventId;
    }

    public void setCode(long code) {
        this.eventId = code;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getLocalEvent() {
        return localEvent;
    }

    public void setLocalEvent(String localEvent) {
        this.localEvent = localEvent;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

}

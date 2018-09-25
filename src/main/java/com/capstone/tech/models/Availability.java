package com.capstone.tech.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String day;

    @Column
    private String fromTime;

    @Column
    private String toTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "availability")
    private List<UserAvailability> userAvailability;

    public Availability() {}

    public Availability(long id, String day, String fromTime, String toTime, List<UserAvailability> userAvailability) {
        this.id = id;
        this.day = day;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.userAvailability = userAvailability;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public List<UserAvailability> getUserAvailability() {
        return userAvailability;
    }

    public void setUserAvailability(List<UserAvailability> userAvailability) {
        this.userAvailability = userAvailability;
    }
}

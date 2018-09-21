package com.capstone.tech.models;

import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue
    private long id;

    @Column()
    private String day_of_week;

    @Column()
    private String time_start;

    @Column()
    private String time_end;

    // relationships

    @OneToOne
    private User user;

    // constructors/getters/setters

    public Availability() { }

    public Availability(String day_of_week, String time_start, String time_end, User user) {
        this.day_of_week = day_of_week;
        this.time_start = time_start;
        this.time_end = time_end;
        this.user = user;
    }

    public Availability(long id, String day_of_week, String time_start, String time_end, User user) {
        this.id = id;
        this.day_of_week = day_of_week;
        this.time_start = time_start;
        this.time_end = time_end;
        this.user = user;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

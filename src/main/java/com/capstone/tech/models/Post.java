package com.capstone.tech.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private String body;


    // Relationships
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public Post(){

    }

// this is the constructor to create
    public Post(String title, String date, String latitude, String longitude, String body, User user) {
        this.title = title;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.body = body;
        this.user = user;
    }

// this is the constructor to display
    public Post(long id, String title, String date, String latitude, String longitude, String body, User user) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.body = body;
        this.user = user;
}



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

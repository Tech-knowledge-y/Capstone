package com.capstone.tech.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_details")
public class UserDetail {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50)
    private String fName;

    @Column(nullable = false, length = 50)
    private String lName;

    @Column
    private String birthdate;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String state;

    @Column(length = 20)
    private String zipCode;

    @Column
    private String bio;


    // Relationships

    @OneToOne
    private User user;


    // Constructors, Getters, and Setters

    public UserDetail() {

    }

    public UserDetail(String fName, String lName, String birthdate, String city, String state, String zipCode, String bio, User user) {
        this.fName = fName;
        this.lName = lName;
        this.birthdate = birthdate;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.bio = bio;
        this.user = user;
    }

    public UserDetail(long id, String fName, String lName, String birthdate, String city, String state, String zipCode, String bio, User user) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.birthdate = birthdate;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.bio = bio;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

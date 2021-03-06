package com.capstone.tech.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String status;

    // Relationships

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    @OneToOne(mappedBy = "user")
    private UserDetail userDetail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comments> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserLanguages> userLanguages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserAvailability> userAvailability;


    // Constructors / Getters / Setters

    public User() { }

    public User(String username, String email, String password, String gender, String birthdate, String status, List<Post> posts, UserDetail userDetail, List<Comments> comments, List<UserLanguages> userLanguages, List<UserAvailability> userAvailability) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.posts = posts;
        this.userDetail = userDetail;
        this.comments = comments;
        this.userLanguages = userLanguages;
        this.userAvailability = userAvailability;
    }

    public User(long id, String username, String email, String password, String gender, String status, List<Post> posts, UserDetail userDetail, List<Comments> comments, List<UserLanguages> userLanguages, List<UserAvailability> userAvailability) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.posts = posts;
        this.userDetail = userDetail;
        this.comments = comments;
        this.userLanguages = userLanguages;
        this.userAvailability = userAvailability;
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        username = copy.username;
        email = copy.email;
        password = copy.password;
        status = copy.status;
        gender = copy.gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<UserLanguages> getUserLanguages() {
        return userLanguages;
    }

    public void setUserLanguages(List<UserLanguages> userLanguages) {
        this.userLanguages = userLanguages;
    }

    public List<UserAvailability> getUserAvailability() {
        return userAvailability;
    }

    public void setUserAvailability(List<UserAvailability> userAvailability) {
        this.userAvailability = userAvailability;
    }
}
package com.example.firebaseaauthentication.firebaseauthentication;

/**
 * Created by amanj on 7/21/2017.
 */

public class MyTasks {

    String title;
    String description;
    String id;


    public MyTasks(String title, String description, String id) {
        this.title = title;
        this.description = description;
        this.id = id;

    }

    public MyTasks() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
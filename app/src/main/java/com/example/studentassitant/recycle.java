package com.example.studentassitant;

import android.telecom.Call;

import db.additionalNotes;
import db.courses;
import db.details;

public class recycle {
    private String name;
    private String type;
    private String date;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public recycle(int id, String name, String type, String date) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.id = id;
    }

    public void setDeets(details deets)
    {
        this.name = deets.getTitle();
        this.type = deets.getPQA();
        this.date = deets.getDod();
    }

    public void setCourses(courses deets)
    {
        this.name = deets.getCourseName();
        this.type = "Course";
        this.date = deets.getDod();
    }

    public void setAdditionalNotes(additionalNotes deets)
    {
        this.name = deets.getTitle();
        this.type = "Additional Notes";
        this.date = deets.getDod();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

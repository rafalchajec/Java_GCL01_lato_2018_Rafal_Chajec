package com.example.project9;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.Serializable;
import java.util.Date;

public class Picture implements Serializable{

    private int ID;
    private String name;
    private String resolution;
    private long size;
    private Date date;

    Picture()
    {

    }

    public Picture(int ID, String name, String resolution, long size, Date date) {
        super();
        this.ID = ID;
        this.name = name;
        this.resolution = resolution;
        this.size = size;
        this.date = date;
    }

    public int getID() {

        return ID;
    }

    public void setID(int ID) {

        this.ID = ID;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @JsonSerialize(using=DateSerializer.class)
    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {

        this.resolution = resolution;
    }

    public long getSize() {

        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}

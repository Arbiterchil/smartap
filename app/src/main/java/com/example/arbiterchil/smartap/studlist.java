package com.example.arbiterchil.smartap;

public class studlist {
    String fullname,id,status,arc;
    public  studlist(){

    }

    public studlist(String fullname, String id, String status, String arc) {
        this.fullname = fullname;
        this.id = id;
        this.status = status;
        this.arc = arc;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArc() {
        return arc;
    }

    public void setArc(String arc) {
        this.arc = arc;
    }
}

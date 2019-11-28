package com.example.arbiterchil.smartap;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class members {
    private String fullname,status,uid;
    int absents,lates;
    public Map<String , Object> valvee = new HashMap<>();
    public members(){ }


    public members(String fullname, String status, String uid, int absents, int lates) {
        this.fullname = fullname;
        this.status = status;
        this.uid = uid;
        this.absents = absents;
        this.lates = lates;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getAbsents() {
        return absents;
    }

    public void setAbsents(int absents) {
        this.absents = absents;
    }

    public int getLates() {
        return lates;
    }

    public void setLates(int lates) {
        this.lates = lates;
    }

    @Exclude
    public Map<String,Object> valvees(){

        Map<String , Object> valvee = new HashMap<>();
        valvee.put("FullName", getFullname());
        valvee.put("Absents", getAbsents());
        valvee.put("Lates",getLates());
        valvee.put("Stats", getStatus());
        valvee.put("Uid",getUid());
        return valvee;
    }
}

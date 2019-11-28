package com.example.arbiterchil.smartap;

public class memberlist {

    private String fullname,absents,lates,status,uid,key,url,stature,trickart;

    public memberlist(){

    }

    public memberlist(String fullname, String absents, String lates, String status, String uid, String key, String url, String stature, String trickart) {
        this.fullname = fullname;
        this.absents = absents;
        this.lates = lates;
        this.status = status;
        this.uid = uid;
        this.key = key;
        this.url = url;
        this.stature = stature;
        this.trickart = trickart;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAbsents() {
        return absents;
    }

    public void setAbsents(String absents) {
        this.absents = absents;
    }

    public String getLates() {
        return lates;
    }

    public void setLates(String lates) {
        this.lates = lates;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStature() {
        return stature;
    }

    public void setStature(String stature) {
        this.stature = stature;
    }

    public String getTrickart() {
        return trickart;
    }

    public void setTrickart(String trickart) {
        this.trickart = trickart;
    }
}

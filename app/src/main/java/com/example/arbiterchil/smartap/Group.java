package com.example.arbiterchil.smartap;


import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Group {

    public String groupname, groupass, gcid, uid, url;


    public Group() {

    }

    public Group(String groupname, String groupass, String gcid, String uid, String url) {
        this.groupname = groupname;
        this.groupass = groupass;
        this.gcid = gcid;
        this.uid = uid;
        this.url = url;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupass() {
        return groupass;
    }

    public void setGroupass(String groupass) {
        this.groupass = groupass;
    }

    public String getGcid() {
        return gcid;
    }

    public void setGcid(String gcid) {
        this.gcid = gcid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
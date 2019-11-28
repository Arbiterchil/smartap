package com.example.arbiterchil.smartap;

public class getLa {

    String latetimes,date,fullname;

    public getLa(){

    }

    public getLa(String latetimes, String date, String fullname) {
        this.latetimes = latetimes;
        this.date = date;
        this.fullname = fullname;
    }

    public String getLatetimes() {
        return latetimes;
    }

    public String getDate() {
        return date;
    }

    public String getFullname() {
        return fullname;
    }
}

package com.example.arbiterchil.smartap;

public class getAb {

    String absenttimes,date,fullname;

    public getAb(){

    }

    public getAb(String absenttimes, String date, String fullname) {
        this.absenttimes = absenttimes;
        this.date = date;
        this.fullname = fullname;
    }

    public String getAbsenttimes() {
        return absenttimes;
    }

    public String getDate() {
        return date;
    }

    public String getFullname() {
        return fullname;
    }
}

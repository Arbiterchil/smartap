package com.example.arbiterchil.smartap;

public class smartdab {
    public String email, password,fullname,idnumber, status,url;

public  smartdab(){

}

    public smartdab(String email, String password, String fullname, String idnumber, String status, String url) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.idnumber = idnumber;
        this.status = status;
        this.url = url;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.example.arbiterchil.smartap;

public class post {

    String fullname,type,total,remark,url;

    public post(){

    }

    public post(String fullname, String type, String total, String remark, String url) {
        this.fullname = fullname;
        this.type = type;
        this.total = total;
        this.remark = remark;
        this.url = url;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.example.universityapp;

public class User {
    private String fn, sn, em, studentId;

    public User(String fn, String sn,String em, String studentId)
    {
        this.fn = fn;
        this.sn = sn;
        this.em = em;
        this.studentId = studentId;

    }

    public User()
    {

    }

    public String getFn()
    {
        return fn;
    }

    public void setFn(String fn)
    {
        this.fn = fn;
    }

    public String getSn()
    {
        return sn;
    }

    public void setSn(String sn)
    {
        this.sn = sn;
    }

    public String getEm() {return em;    }

    public void setEm(String em) {  this.em = em;    }

    public String getStudentId() {return studentId;}

    public void setStudentId(String studentId) {this.studentId = studentId;}
}

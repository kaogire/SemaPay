package com.ogresolutions.imali.semapay.helper;

/**
 * Created by Njuguna on 02/07/2016.
 */
public class Client {
    protected  String clFname, clLname,clCountry,clEmail,clPass;
    protected double clTel;

    public Client(){}

    public Client(String fname, String  lname,double tel, String  email, String  country, String  pass)
    {
        this.clCountry = country;
        this.clEmail = email;
        this.clFname = fname;
        this.clLname = lname;
        this.clTel = tel;
        this.clPass = pass;
    }

    public double getTel() {
        return clTel;
    }

    public String getEmail() {
        return clEmail;
    }

    public String getCountry() {
        return clCountry;
    }

    public String getFname() {
        return clFname;
    }

    public String getLname() {
        return clLname;
    }

    public String getPass() {
        return clPass;
    }

    public void setCountry(String country) {
        this.clCountry = clCountry;
    }

    public void setEmail(String email) {
        this.clEmail = clEmail;
    }

    public void setFname(String fname) {
        this.clFname = clFname;
    }

    public void setLname(String lname) {
        this.clLname = clLname;
    }

    public void setPass(String pass) {
        this.clPass = clPass;
    }

    public void setTel(double tel) {
        this.clTel = clTel;
    }
}

package com.ogresolutions.imali.semapay.helper;

/**
 * Created by Njuguna on 02/07/2016.
 */
public class Contact {
     String conFname, conLname, conCountry, conEmail, conCard, conTel;

    public Contact(){}

    public Contact(String fname, String lname,String tel,String cardNo, String email, String country)
    {
        this.conCard = cardNo;
        this.conCountry = country;
        this.conFname = fname;
        this.conLname = lname;
        this.conTel = tel;
        this.conEmail = email;
    }

    public String getCard() {
        return conCard;
    }

    public String getTel() {
        return conTel;
    }

    public String getCountry() {
        return conCountry;
    }

    public String getFname() {
        return conFname;
    }

    public String getLname() {
        return conLname;
    }

    public void setCard(String conCard) {
        this.conCard = conCard;
    }

    public void setCountry(String conCountry) {
        this.conCountry = conCountry;
    }

    public void setFname(String conFname) {
        this.conFname = conFname;
    }

    public void setLname(String conLname) {
        this.conLname = conLname;
    }

    public void setTel(String conTel) {
        this.conTel = conTel;
    }

    public void setEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public String getEmail() {
        return conEmail;
    }
}


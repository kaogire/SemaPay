package com.ogresolutions.imali.semapay.helper;

/**
 * Created by Njuguna on 02/06/2016.
 */
public class Card {
    protected String card_number;
    protected String card_type;
    protected String card_security;
    protected String card_name;

    public Card(){}

    public Card(String cardName,String cardType, String cardNumber, String cardSecurity)
    {
        this.card_name = cardName;
        this.card_number = cardNumber;
        this.card_type = cardType;
        this.card_security = cardSecurity;
    }

    public String getNumber()
    {
        return card_number;
    }

    public void setNumber(String cardNumber)
    {
        this.card_number = cardNumber;
    }

    public String getName() {
        return card_name;
    }

    public void setName(String card_name) {
        this.card_name = card_name;
    }

    public String getSecurity() {
        return card_security;
    }

    public void setASecurity(String card_security) {
        this.card_security = card_security;
    }

    public String getType() {
        return card_type;
    }

    public void setType(String card_type) {
        this.card_type = card_type;
    }
}

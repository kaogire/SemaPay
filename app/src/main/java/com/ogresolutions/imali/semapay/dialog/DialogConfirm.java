package com.ogresolutions.imali.semapay.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class DialogConfirm extends AppCompatActivity {
    String theUrl = MainActivity.request.myUrl;
    JSONObject theJson = MainActivity.request.myjson;
    String myConfirm;
    StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm);
        TextView myTv = (TextView)findViewById(R.id.tvMine);

        if(theUrl.equalsIgnoreCase(MainActivity.request.URL_BUY_GOODS)){
            myConfirm = buyGoods()+" "+myCard();
        }
        else if (theUrl.equalsIgnoreCase(MainActivity.request.URL_OTHERS)){
            myConfirm = others()+" "+ myCard();
        }else if(theUrl.equalsIgnoreCase(MainActivity.request.URL_POWER)){
            myConfirm = power()+" "+myCard();
        }else if(theUrl.equalsIgnoreCase(MainActivity.request.URL_POWER_TOKEN)){
            myConfirm = token()+" "+myCard();
        }else if(theUrl.equalsIgnoreCase(MainActivity.request.URL_SCHOOL_FEES)){
            myConfirm = schoolFees()+" "+myCard();
        }else if (theUrl.equalsIgnoreCase(MainActivity.request.URL_SEND_CASH)){
            myConfirm = sendCash()+myCard()+myContact();
            Log.i("joe", myConfirm);
        }else if (theUrl.equalsIgnoreCase(MainActivity.request.URL_WATER)){
            myConfirm = water()+" "+myCard();
        }else if (theUrl.equalsIgnoreCase(MainActivity.request.URL_CREATE_ACCOUNT)){
            myConfirm = myAccount();
        }
        myTv.setText(myConfirm);
        Button btnOk = (Button)findViewById(R.id.btnConfirmOk);
        Button btnCancel = (Button)findViewById(R.id.btnConfirmCancel);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("joe", myConfirm);
                Log.i("joe", "send data");
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.request.clearAll();
                onBackPressed();
            }
        });

    }
    public String sendCash(){
        String sendMulla = "";
        try {
            JSONObject myJson = theJson.getJSONObject("sendOne");
            sendMulla = "Send "+myJson.getString("amount")+" to "+
                    myJson.getString("ToCountry")+" from "
                    +myJson.getString("FromCountry")+" through "+
                    myJson.getString("delivery")+".";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMulla;
    }
    public String buyGoods(){
        String buyGoods = "";
        try {
            JSONObject myJson = theJson.getJSONObject("Buy_goods");
            buyGoods = "Buy goods worth "+myJson.getString("amount")+" from "+
                    myJson.getString("shop")+" with till number"+
                    myJson.getString("tillNo")+".";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return buyGoods;
    }
    public String schoolFees(){
        String myString = "";
        try {
            JSONObject myJson = theJson.getJSONObject("schoolFees");
            myString = "Pay "+myJson.getString("amount")+" to "+
                    myJson.getString("SchName")+" account number"+
                    myJson.getString("AcctNo")+" for "+
                    myJson.getString("StdName")+" in "+
                    myJson.getString("Level")+".";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myString;
    }
    public String water(){
        String myString = "";
        try {
            JSONObject myJson = theJson.getJSONObject("water");
            myString = "Pay "+myJson.getString("waterAmount")+" to "+
                    myJson.getString("waterCompany")+" for account name: "+
                    myJson.getString("waterAccName")+" account number "+
                    myJson.getString("waterAccNo")+" with meter number"+
                    myJson.getString("waterMeter")+".";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myString;
    }
    public String power(){
        String myString = "";
        try {
            JSONObject myJson = theJson.getJSONObject("powerNormal");
            myString = "Pay "+myJson.getString("amount")+" for account number "+
                    myJson.getString("account")+" and meter number"+
                    myJson.getString("meter")+".";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myString;
    }
    public String others(){
        String myString = "";
        try {
            JSONObject myJson = theJson.getJSONObject("others");
            myString = "Pay"+myJson.getString("Amount")+"to account name"+
                    myJson.getString("AccName")+" with account number "+
                    myJson.getString("AccNo")+" and pay bill number "+
                    myJson.getString("Paybill")+" for "+
                    myJson.getString("Purpose")+".";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myString;
    }
    public String token(){
        String myString = "";
        try {
            JSONObject myJson = theJson.getJSONObject("tokens");
            myString = "Buy electricity tokens worth "+myJson.getString("amount")+" for meter number "+
                    myJson.getString("meter")+".";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myString;
    }
    public String myCard(){
        String myString = "";
        try {
            JSONObject myJson = theJson.getJSONObject("card");
            myString = "Using card number "+myJson.getString("number")+".";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myString;
    }
    public String myContact(){
        String myString = "";
        try {
            JSONObject myJson = theJson.getJSONObject("contact");
            myString = "To "+myJson.getString("fname")+" "+
                    myJson.getString("lname")+" tel Number"+
                    myJson.getString("tel")+" card number"+
                    myJson.getString("card")+" email "+
                    myJson.getString("email")+" in "+
                    myJson.getString("country")+".";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myString;
    }
    public String myAccount(){
        String myString = "";
        try {
            JSONObject myJson = theJson.getJSONObject("account");
            myString = "Name "+myJson.getString("name")+" date of birth "+
                    myJson.getString("DOB")+" id number "+
                    myJson.getString("id")+" tel number"+
                    myJson.getString("tel")+" email "+
                    myJson.getString("email")+" occupation "+
                    myJson.getString("occupation")+" KRA PIN"+
                    myJson.getString("kra pin")+" address"+
                    myJson.getString("PO Box")+" postal code"+
                    myJson.getString("P Code")+".";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myString;
    }
}
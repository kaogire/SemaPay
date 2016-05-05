package com.ogresolutions.imali.semapay.helper;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Njuguna on 02/04/2016.
 */
public class Request {
    public JSONObject myjson = new JSONObject();
    public String myUrl = " ";
    public final String URL_SEND_CASH = "sendcsh url";
    public final String URL_SCHOOL_FEES = "schoolfees url";
    public final String URL_BUY_GOODS = "buy goods url";
    public final String URL_POWER = "power url";
    public final String URL_POWER_TOKEN = "token url";
    public final String URL_OTHERS = "OTHERS url";
    public final String URL_WATER = "water url";
    public final String URL_CREATE_ACCOUNT = "createAccount";

    public Request() {
    }

    public void buyGoods(JSONObject myjson) {

        try {
            this.myjson.put("Buy_goods", myjson);
            Log.i("joe", this.myjson.toString(2));
            myUrl = URL_BUY_GOODS;
            Log.i("joe", this.myUrl);
        } catch (JSONException e) {
            Log.i("joe", "error Json");
            e.printStackTrace();
        }

    }
    public void powerTokens(JSONObject myjson) {
        try {
            this.myjson.put("tokens", myjson);
            Log.i("joe", this.myjson.toString(2));
            myUrl = URL_POWER_TOKEN;
            Log.i("joe", this.myUrl);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("joe", "error Json");
        }
    }
    public void payWater(JSONObject myjson) {
        try {
            this.myjson.put("water", this.myjson.toString(2));
            Log.i("joe", this.myjson.toString(2));
            myUrl = URL_WATER;
            Log.i("joe", this.myUrl);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("joe", "error Json");
        }
    }
    public void powerNormal(JSONObject myjson){
        try {
            this.myjson.put("powerNormal", myjson);
            Log.i("joe",this.myjson.toString(2));
            myUrl = URL_POWER;
            Log.i("joe",this.myUrl);
        } catch (JSONException e) {
            Log.i("joe", "error Json");
            e.printStackTrace();
        }
    }
    public void schoolFees(JSONObject myjson){
        try {
            this.myjson.put("schoolFees", myjson);
            Log.i("joe",this.myjson.toString(2));
            myUrl = URL_SCHOOL_FEES;
            Log.i("joe",this.myUrl);
        } catch (JSONException e) {
            Log.i("joe", "error Json");
            e.printStackTrace();
        }
    }
    public void othersBill(JSONObject myjson){
        try {
            this.myjson.put("others", myjson);
            Log.i("joe",this.myjson.toString(2));
            myUrl = URL_OTHERS;
            Log.i("joe",this.myUrl);
        } catch (JSONException e) {
            Log.i("joe", "error Json");
            e.printStackTrace();
        }
    }
    public void addCard(JSONObject myjson){
        try {
            this.myjson.put("card", myjson);
            Log.i("joe", this.myjson.toString(2));
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void addContact(JSONObject myJson){
        try {
            this.myjson.put("contact", myJson);
            Log.i("joe",this.myjson.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void sendOne(JSONObject myJson){
        try {
            this.myjson.put("sendOne", myJson);
            Log.i("joe", this.myjson.toString(2));
            myUrl = URL_SEND_CASH;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void addAccount(JSONObject myJson){
        try {
            this.myjson.put("account", myJson);
            Log.i("joe",this.myjson.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void clearAll(){
        this.myjson = new JSONObject();
        this.myUrl = "";
        try {
            Log.i("joe",myjson.toString(2)+myUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void createGeneral(JSONObject theJson, String theUrl){
        try {
            this.myjson.put("account", theJson);
            Log.i("joe", this.myjson.toString(2));
            myUrl = theUrl;
            Log.i("joe", this.myUrl);
        } catch (JSONException e) {
            Log.i("joe", "error Json");
            e.printStackTrace();
        }
    }
}

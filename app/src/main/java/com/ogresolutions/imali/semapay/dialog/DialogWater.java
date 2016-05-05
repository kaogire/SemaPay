package com.ogresolutions.imali.semapay.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Njuguna on 02/19/2016.
 */
public class DialogWater extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_water);
        final EditText edtMeterNo = (EditText)findViewById(R.id.edtWaterMeter);
        final EditText edtAccNo = (EditText)findViewById(R.id.edtWaterAccountNumber);
        final EditText edtAccName = (EditText)findViewById(R.id.edtWaterAccountName);
        final EditText edtAmount = (EditText)findViewById(R.id.edtWaterAmount);
        final EditText edtCompany = (EditText)findViewById(R.id.edtWaterCompany);
        Button btnPay = (Button)findViewById(R.id.btnWaterpay);
        Button btnCancel = (Button)findViewById(R.id.btnWaterCancel);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtMeterNo.getText())||
                        TextUtils.isEmpty(edtAccName.getText())||
                        TextUtils.isEmpty(edtAccNo.getText())||
                        TextUtils.isEmpty(edtAmount.getText())||
                        TextUtils.isEmpty(edtCompany.getText())){
                    Toast.makeText(DialogWater.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        Log.i("joe","try");
                        JSONObject myJson = new JSONObject();
                        myJson.put("waterAccName",edtAccName.getText());
                        myJson.put("waterAccNo",edtAccNo.getText());
                        myJson.put("waterMeter",edtMeterNo.getText());
                        myJson.put("waterAmount",edtAmount.getText());
                        myJson.put("waterCompany",edtCompany.getText());
                        Log.i("joe", myJson.toString(2));
                        MainActivity.request.payWater(myJson);
                        Intent addCard = new Intent(DialogWater.this, CardRegister.class);
                        startActivity(addCard);
                    } catch (JSONException e) {
                        Log.i("joe","try_error");
                        e.printStackTrace();
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

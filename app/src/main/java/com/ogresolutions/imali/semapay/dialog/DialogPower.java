package com.ogresolutions.imali.semapay.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
public class DialogPower extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_power);
        final EditText edtMeter = (EditText)findViewById(R.id.edtPowerMeter);
        final EditText edtAmount = (EditText)findViewById(R.id.edtPowerAmount);
        final EditText edtAcct = (EditText)findViewById(R.id.edtPowerAcct);
        Button btnPay = (Button)findViewById(R.id.btnPowerPay);
        Button btnCancel = (Button)findViewById(R.id.btnPowerCancel);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtMeter.getText())||
                        TextUtils.isEmpty(edtAcct.getText())||
                        TextUtils.isEmpty(edtAmount.getText())){
                    Toast.makeText(DialogPower.this, "please fill all the fields",Toast.LENGTH_LONG).show();
                }
                else{
                    JSONObject myJson = new JSONObject();
                    try{
                        myJson.put("meter", edtMeter.getText().toString());
                        myJson.put("account", edtAcct.getText().toString());
                        myJson.put("amount", edtAmount.getText().toString());
                        MainActivity.request.powerNormal(myJson);
                        Intent addCard = new Intent(DialogPower.this, CardRegister.class);
                        startActivity(addCard);
                        onBackPressed();
                    }
                    catch (JSONException e){
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

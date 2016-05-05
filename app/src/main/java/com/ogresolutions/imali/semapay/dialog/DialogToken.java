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
public class DialogToken extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_power_token);
        final EditText edtMeter = (EditText)findViewById(R.id.edtTokenMeter);
        final EditText edtAmount = (EditText)findViewById(R.id.edtTokenAmount);
        Button btnBuy = (Button)findViewById(R.id.btnTokenBuy);
        Button btnCancel = (Button)findViewById(R.id.btnTokenCancel);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtMeter.getText())||
                        TextUtils.isEmpty(edtAmount.getText())) {
                    Toast.makeText(DialogToken.this, "Please Enter all fields",Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject myJson = new JSONObject();
                        Log.i("joe", "try");
                        myJson.put("amount", Double.parseDouble(edtAmount.getText().toString()));
                        myJson.put("meter", Double.parseDouble(edtMeter.getText().toString()));
                        MainActivity.request.powerTokens(myJson);
                        Intent addCard = new Intent(DialogToken.this, CardRegister.class);
                        startActivity(addCard);
                    } catch (JSONException e) {
                        Log.i("joe","the Json Failure");
                        e.printStackTrace();
                    }
                    onBackPressed();
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

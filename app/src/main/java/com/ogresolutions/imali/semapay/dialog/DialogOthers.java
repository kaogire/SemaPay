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
 * Created by Njuguna on 02/18/2016.
 */
public class DialogOthers extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_others);
        final EditText edtAccName = (EditText)findViewById(R.id.edtOtherAccountName);
        final EditText edtAccNo = (EditText)findViewById(R.id.edtOtherAccountNumber);
        final EditText edtAmount = (EditText)findViewById(R.id.edtOtherAmount);
        final EditText edtPaybil = (EditText)findViewById(R.id.edtOtherPaybillNumber);
        final EditText edtPurpose = (EditText)findViewById(R.id.edtOtherPurpose);
        Button btnSend = (Button)findViewById(R.id.btnOtherPay);
        Button btncancel = (Button)findViewById(R.id.btnOtherCancel);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtAccName.getText())||
                        TextUtils.isEmpty(edtAccNo.getText())||
                        TextUtils.isEmpty(edtAmount.getText())||
                        TextUtils.isEmpty(edtPaybil.getText())||
                        TextUtils.isEmpty(edtPurpose.getText())) {
                    Toast.makeText(DialogOthers.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
                else{
                    Log.i("joe", "not null");
                    JSONObject myJson = new JSONObject();
                    try {
                        myJson.put("AccName", edtAccName.getText().toString());
                        myJson.put("AccNo", edtAccNo.getText().toString());
                        myJson.put("Amount", edtAmount.getText().toString());
                        myJson.put("Paybill", edtPaybil.getText().toString());
                        myJson.put("Purpose", edtPurpose.getText().toString());
                        MainActivity.request.othersBill(myJson);
                        Intent addCard = new Intent(DialogOthers.this, CardRegister.class);
                        startActivity(addCard);
                        Log.i("joe", myJson.toString(2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent createAccountIntent = new Intent(DialogOthers.this,DialogCard.class);
                    startActivity(createAccountIntent);
                    onBackPressed();
                }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

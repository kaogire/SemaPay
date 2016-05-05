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
public class DialogSchool extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_school_fees);
        final EditText edtSchName = (EditText)findViewById(R.id.edtSchName);
        final EditText edtSchAccNo = (EditText)findViewById(R.id.edtSchAcctNo);
        final EditText edtSchAmount = (EditText)findViewById(R.id.edtSchAmount);
        final EditText edtSchLevel = (EditText)findViewById(R.id.edtSchLevel);
        final EditText edtSchStdName = (EditText)findViewById(R.id.edtSchStdName);
        Button btnPay = (Button)findViewById(R.id.btnSchPay);
        Button btnCancel = (Button)findViewById(R.id.btnSchCancel);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtSchAccNo.getText())||
                        TextUtils.isEmpty(edtSchName.getText())||
                        TextUtils.isEmpty(edtSchAmount.getText())||
                        TextUtils.isEmpty(edtSchStdName.getText())||
                        TextUtils.isEmpty(edtSchLevel.getText())) {
                    Toast.makeText(DialogSchool.this , "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Log.i("joe", "try");
                        JSONObject myJson = new JSONObject();
                        myJson.put("AcctNo", edtSchAccNo.getText().toString());
                        myJson.put("SchName", edtSchName.getText().toString());
                        myJson.put("StdName", edtSchStdName.getText().toString());
                        myJson.put("Level", edtSchLevel.getText().toString());
                        myJson.put("amount", Double.parseDouble(edtSchAmount.getText().toString()));
                        MainActivity.request.schoolFees(myJson);
                        Intent addCard = new Intent(DialogSchool.this, CardRegister.class);
                        startActivity(addCard);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.i("joe","catch");
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

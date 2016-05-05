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
import com.ogresolutions.imali.semapay.helper.MyWatcher;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Njuguna on 02/06/2016.
 */
public class CardRegister extends AppCompatActivity{
    String cardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_card_layout);

        Button btnOk = (Button) findViewById(R.id.btnCardRegisterOk);
        Button btnCancel = (Button) findViewById(R.id.btnCardRegisterCancel);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtCardNo = (EditText) findViewById(R.id.edtCardNumber);
                EditText edtCardSec = (EditText) findViewById(R.id.edtCardSecurity);
                EditText edtCardSec2 = (EditText) findViewById(R.id.edtCardSecurity2);
                new MyWatcher(edtCardNo, "10000000000000" , "9999999999999999");
                if(TextUtils.isEmpty(edtCardNo.getText())||
                        TextUtils.isEmpty(edtCardSec.getText())||
                        TextUtils.isEmpty(edtCardSec2.getText())){
                    Toast.makeText(CardRegister.this, "Please fill all the fields",Toast.LENGTH_LONG).show();
                }else if (edtCardSec.getText().toString().equals(edtCardSec2.getText().toString())) {
                    try {
                        JSONObject cardJson = new JSONObject();
                        cardJson.put("number",edtCardNo.getText().toString());
                        cardJson.put("security",edtCardSec.getText().toString());
                        Log.i("joe", "cardJson");
                        MainActivity.request.addCard(cardJson);
                        Intent callConfirm = new Intent(CardRegister.this, DialogConfirm.class);
                        startActivity(callConfirm);
                        onBackPressed();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else{
                    Log.i("joe", "error sec");
                    edtCardSec2.setError("The Card Security does not match");
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

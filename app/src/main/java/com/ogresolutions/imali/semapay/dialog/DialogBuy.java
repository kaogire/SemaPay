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
public class DialogBuy extends AppCompatActivity {
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.dialog_buy_goods);
        final EditText shopName = (EditText)findViewById(R.id.edtShopName);
        final EditText shopAmount = (EditText)findViewById(R.id.edtShopAmount);
        final EditText shopTillNumber = (EditText)findViewById(R.id.edtShopTillNumber);
        Button btnBuy = (Button)findViewById(R.id.btnBuyBuy);
        Button btnCancel = (Button)findViewById(R.id.btnBuyCancel);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(shopName.getText())||
                        TextUtils.isEmpty(shopAmount.getText())||
                        TextUtils.isEmpty(shopTillNumber.getText())){
                    Toast.makeText(DialogBuy.this, "Please fill all fields.", Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        Log.i("joe", "try");
                        JSONObject data = new JSONObject();
                        data.put("shop", shopName.getText().toString());
                        data.put("tillNo",shopTillNumber.getText().toString());
                        data.put("amount", Double.parseDouble(shopAmount.getText().toString()));
                        MainActivity.request.buyGoods(data);
                        Intent addCard = new Intent(DialogBuy.this, CardRegister.class);
                        startActivity(addCard);
                    } catch (JSONException e) {
                        Log.i("joe","catch");
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

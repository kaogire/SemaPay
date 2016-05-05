package com.ogresolutions.imali.semapay.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.activity.MainActivity;
import com.ogresolutions.imali.semapay.helper.Contact;
import com.ogresolutions.imali.semapay.helper.MySql;
import com.ogresolutions.imali.semapay.helper.RecyclerClickListener;
import com.ogresolutions.imali.semapay.model.ContactRecycler;

import org.json.JSONException;
import org.json.JSONObject;

public class DialogContact extends AppCompatActivity {
    Contact myContact = new Contact();
    ContactRecycler adapter;
    int selectedPosition;
    MySql db = new MySql(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_contact);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rvCont);
        adapter = new ContactRecycler(db.getAllContacts());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        rv.addOnItemTouchListener(new RecyclerClickListener(this, rv, new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView tvcard = (TextView) view.findViewById(R.id.tvConCard);
                String cardNo = tvcard.getText().toString();
                if (view.isActivated()) {
                    view.setActivated(false);
                    selectedPosition = -1;
                } else {
                    view.setActivated(true);
                    selectedPosition = position;
                }
                if (selectedPosition == -1) {
                    Log.i("joe", "is inactive");
                } else {
                    try {
                        myContact = db.getContact(Double.parseDouble(cardNo));
                        JSONObject conJson = new JSONObject();
                        conJson.put("fname",myContact.getFname());
                        conJson.put("lname",myContact.getLname());
                        conJson.put("tel",myContact.getTel());
                        conJson.put("card",myContact.getCard());
                        conJson.put("email",myContact.getEmail());
                        conJson.put("country",myContact.getCountry());
                        MainActivity.request.addContact(conJson);
                        Intent addCard = new Intent(DialogContact.this, CardRegister.class);
                        startActivity(addCard);
                        Log.i("joe", "got card");
                        onBackPressed();
                    } catch (JSONException e) {
                        Log.i("joe", "error getting Card");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        }) {

        });
        Button btnCancel = (Button)findViewById(R.id.btnConCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}

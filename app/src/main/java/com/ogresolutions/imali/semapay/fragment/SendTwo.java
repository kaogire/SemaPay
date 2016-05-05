package com.ogresolutions.imali.semapay.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.dialog.CardRegister;
import com.ogresolutions.imali.semapay.activity.MainActivity;
import com.ogresolutions.imali.semapay.dialog.DialogContact;
import com.ogresolutions.imali.semapay.helper.Contact;
import com.ogresolutions.imali.semapay.helper.MySql;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendTwo extends Fragment {
    MySql db = new MySql(getActivity());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_send_two, container, false);
        final EditText edtFname = (EditText)v.findViewById(R.id.edtSendFname);
        final EditText edtLname = (EditText)v.findViewById(R.id.edtSendLname);
        final EditText edtemail = (EditText)v.findViewById(R.id.edtSendEmail);
        final EditText edtTel = (EditText)v.findViewById(R.id.edtSendTel);
        final EditText edtCard = (EditText)v.findViewById(R.id.edtSendCardNo);
        Button btnSelect = (Button)v.findViewById(R.id.btnSendChoose);
        Button btnNextTwo = (Button)v.findViewById(R.id.btnSendTwo);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccountIntent = new Intent(getActivity(), DialogContact.class);
                startActivity(createAccountIntent);
                ((MainActivity)getActivity()).goToFragment(new Launcher(),"Home");
            }
        });
        btnNextTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject myJson = new JSONObject();
                    JSONObject myJson2 = MainActivity.request.myjson.getJSONObject("sendOne");
                    String country = myJson2.getString("ToCountry");
                    myJson.put("fname",edtFname.getText().toString());
                    myJson.put("lname",edtLname.getText().toString());
                    myJson.put("email",edtemail.getText().toString());
                    myJson.put("tel", edtTel.getText().toString());
                    myJson.put("card", edtCard.getText().toString());
                    myJson.put("country", country);
                    Contact myContact = new Contact(edtFname.getText().toString(),
                            edtLname.getText().toString(),
                            edtTel.getText().toString(),
                            edtemail.getText().toString(),
                            edtCard.getText().toString(),country);
//                    db.addContact(myContact);
                    Log.i("joe",myContact.getFname()+" "+myContact.getLname()+" "+myContact.getTel()+" "+myContact.getCard()+" "+myContact.getCountry()+" "+myContact.getEmail());
                    MainActivity.request.addContact(myJson);
                    Intent addCard = new Intent(getActivity(), CardRegister.class);
                    startActivity(addCard);
                    Log.i("joe","contact created" );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        return v;
    }

}
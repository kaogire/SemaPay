package com.ogresolutions.imali.semapay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ogresolutions.imali.semapay.helper.Contact;
import com.ogresolutions.imali.semapay.helper.MySql;
import com.ogresolutions.imali.semapay.R;

/**
 * Created by Njuguna on 02/11/2016.
 */
public class AddContact extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_contact,container, false);
        Button btnAdd = (Button)v.findViewById(R.id.btnConAdd);
        final EditText edtFname = (EditText)v.findViewById(R.id.edtConFname);
        final EditText edtLname = (EditText)v.findViewById(R.id.edtConLname);
        final EditText edtTel = (EditText)v.findViewById(R.id.edtConTel);
        final EditText edtEmail = (EditText)v.findViewById(R.id.edtConEmail);
        final EditText edtCountry = (EditText)v.findViewById(R.id.edtConCountry);
        final EditText edtCard = (EditText)v.findViewById(R.id.edtConCard);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = edtFname.getText().toString();
                String lname= edtLname.getText().toString();
                String email= edtEmail.getText().toString();
                String country= edtCountry.getText().toString();
                String tel= edtTel.getText().toString();
                String card= edtCard.getText().toString();
                Contact contact = new Contact(fname,lname,tel,card,email,country);
                Log.i("joe", "contact activity");
                MySql db = new MySql(getContext());
                db.addContact(contact);
                Log.i("joe", "contact activity");
            }
        });
        return v;
    }
}

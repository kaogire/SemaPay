package com.ogresolutions.imali.semapay.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SendOne extends Fragment {
    String myCountry;
    String myDelivery = "Card";
    JSONObject myjson = new JSONObject();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_send_one, container, false);
        final EditText edtFrom = (EditText) v.findViewById(R.id.edtSendFrom);
        final EditText edtAmount = (EditText) v.findViewById(R.id.edtSendAmount);
        Spinner spn = (Spinner) v.findViewById(R.id.spnSendCountry);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.coutries));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(aa);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        myCountry = "Kenya";
                        break;
                    case 1:
                        myCountry = "Uganda";
                        break;
                    case 2:
                        myCountry = "Tanzania";
                        break;
                    case 3:
                        myCountry = "South Sudan";
                        break;
                    default:
                        myCountry = "";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        RadioGroup rg = (RadioGroup) v.findViewById(R.id.rgSend);
        final RadioButton rbnMpesa = (RadioButton)v.findViewById(R.id.rbnSendMpesa);
        final Button next = (Button) v.findViewById(R.id.btnSendOne);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbnSendCard:
                        myDelivery = "Card";
                        break;
                    case R.id.rbnSendMpesa:
                        if (!myCountry.equalsIgnoreCase("Kenya")) {
                            rbnMpesa.setError("Sorry, this service is only available in kenya");
                            Toast.makeText(getActivity().getBaseContext(), "Sorry, this service is only available in kenya", Toast.LENGTH_LONG).show();
                            next.setActivated(false);
                        } else {
                            myDelivery = "Mpesa";
                            next.setActivated(true);
                        }
                        break;
                    default:
                        myDelivery = "Card";
                        break;
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myjson.put("FromCountry", edtFrom.getText().toString());
                    myjson.put("ToCountry", myCountry);
                    myjson.put("amount", edtAmount.getText().toString());
                    myjson.put("delivery", myDelivery);
                    MainActivity.request.sendOne(myjson);
                    Log.i("joe", myjson.toString(2));
                    Log.i("joe", "done entry send 0ne");
                    ((MainActivity) getActivity()).goToFragment(new SendTwo(), "Receiver Details");
                } catch (JSONException e) {
                    Log.i("joe", "error entry send 0ne");
                    e.printStackTrace();
                }
            }
        });
        return v;
    }
}

package com.ogresolutions.imali.semapay.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.dialog.DialogBuy;
import com.ogresolutions.imali.semapay.dialog.DialogOthers;
import com.ogresolutions.imali.semapay.dialog.DialogPower;
import com.ogresolutions.imali.semapay.dialog.DialogSchool;
import com.ogresolutions.imali.semapay.dialog.DialogToken;
import com.ogresolutions.imali.semapay.dialog.DialogWater;

/**
 * Created by Njuguna on 01/31/2016.
 */
public class BillsHome extends Fragment {

    ListAdapter adapter;
    ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bills_home, container, false);
        String[] listMenu = getResources().getStringArray(R.array.bills_home);
        lv = (ListView) rootView.findViewById(R.id.lvBill);
        ArrayAdapter<String> adb = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listMenu);
        lv.setAdapter(adb);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent createAccountIntent5 = new Intent(getContext(),DialogSchool.class);
                        startActivity(createAccountIntent5);
                        break;
                    case 1:
                        Intent createAccountIntent1 = new Intent(getContext(),DialogPower.class);
                        startActivity(createAccountIntent1);
                        break;
                    case 3:
                        Intent createAccountIntent2 = new Intent(getContext(),DialogWater.class);
                        startActivity(createAccountIntent2);
                        break;
                    case 4:
                        Intent createAccountIntent3 = new Intent(getContext(),DialogBuy.class);
                        startActivity(createAccountIntent3);
                        break;
                    case 5:
                        Intent createAccountIntent = new Intent(getContext(),DialogOthers.class);
                        startActivity(createAccountIntent);
                        break;
                    case 2:
                        Intent createAccountIntent4 = new Intent(getContext(),DialogToken.class);
                        startActivity(createAccountIntent4);
                        break;

                }
            }
        });

        return rootView;
    }
}

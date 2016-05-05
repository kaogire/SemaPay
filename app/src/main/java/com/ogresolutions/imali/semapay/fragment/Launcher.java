package com.ogresolutions.imali.semapay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ogresolutions.imali.semapay.activity.MainActivity;
import com.ogresolutions.imali.semapay.R;

/**
 * Created by Njuguna on 02/06/2016.
 */
public class Launcher extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button btnPay = (Button)v.findViewById(R.id.btnHomePay);
        Button btnSend = (Button)v.findViewById(R.id.btnHomeSend);
        btnPay.setOnClickListener(this);
        btnSend.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager mFragmentManager;
        mFragmentManager = getFragmentManager();
        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch(v.getId())
        {
            case R.id.btnHomeSend:
                Log.i("joe","send");
                ((MainActivity)getActivity()).goToFragment(new SendOne(), "sendMoney");
                break;
            case R.id.btnHomePay:
                Log.i("joe","pay");
                ((MainActivity)getActivity()).goToFragment(new BillsHome(), "Pay a Bill");
                break;
        }
    }
}

package com.ogresolutions.imali.semapay.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogresolutions.imali.semapay.R;

/**
 * Created by Njuguna on 01/29/2016.
*/
public class SendFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_send, container, false);
        return rootView;
    }
}

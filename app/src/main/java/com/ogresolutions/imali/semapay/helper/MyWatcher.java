package com.ogresolutions.imali.semapay.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Njuguna on 03/02/2016.
 */
public class MyWatcher implements TextWatcher {
    EditText editText;
    double min, max;
    public MyWatcher(EditText editText, String myMin, String myMax){
        this.editText = editText;
        this.min = Double.parseDouble(myMin);
        this.max = Double.parseDouble(myMax);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            double val = Double.parseDouble(s.toString());
            if(val > max) {
                s.replace(0, s.length(), String.valueOf(max) , 0, 2);
            } else if(val < min) {
                s.replace(0, s.length(), "1", 0, 1);
            }
        } catch (NumberFormatException ex) {
            editText.setError("Invalid card Number. ");
        }
    }
}

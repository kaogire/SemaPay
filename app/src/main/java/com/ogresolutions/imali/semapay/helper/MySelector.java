package com.ogresolutions.imali.semapay.helper;

import android.util.SparseBooleanArray;

/**
 * Created by Njuguna on 02/21/2016.
 */
public class MySelector {
    private SparseBooleanArray mSelectedArray = new SparseBooleanArray();

    public void setViewSelected(int position, boolean selected){
        mSelectedArray.put(position, selected);
    }
    public boolean isViewChecked(int position){
        return mSelectedArray.get(position);
    }
    public  void clearAll(){
        mSelectedArray = new SparseBooleanArray();
    }
}

package com.ogresolutions.imali.semapay.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Njuguna on 02/20/2016.
 */
public class RecyclerClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(),e.getY());
        if(childView != null && mListener !=null &mGestureDetector.onTouchEvent(e)){
            mListener.onItemClick(childView,rv.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
    public static interface OnItemClickListener{
        public void onItemClick(View view, int position);
        public void onItemLongClickListener(View view, int position);
    }
    public RecyclerClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener){
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView != null&& mListener!= null){
                    mListener.onItemLongClickListener(childView, recyclerView.getChildAdapterPosition(childView));
                }
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }
}

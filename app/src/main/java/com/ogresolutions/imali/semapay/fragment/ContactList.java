package com.ogresolutions.imali.semapay.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ogresolutions.imali.semapay.helper.Contact;
import com.ogresolutions.imali.semapay.helper.MySql;
import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.model.ContactRecycler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Njuguna on 02/12/2016.
 */
public class ContactList extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contact_list,container,false);
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.contactRecycler);
        MySql db = new MySql(getActivity().getApplicationContext());
        final ArrayList<Contact> contacts = db.getAllContacts();
        ContactRecycler adapter = new ContactRecycler(contacts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("joe", "item touched");
                try {
                    Contact contact = contacts.get(position);
                    JSONObject jsonContact = new JSONObject();
                    Log.i("joe", "item touched");
                    jsonContact.put("fname",contact.getFname());
                    jsonContact.put("lname",contact.getLname());
                    jsonContact.put("tel",contact.getTel());
                    jsonContact.put("email",contact.getEmail());
                    jsonContact.put("card",contact.getCard());
                    jsonContact.put("country",contact.getCountry());
                    String sContact = jsonContact.toString(2);
                    Log.i("joe", "the Contact"+sContact);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("joe", "error Catch Json");
                }

            }

            @Override
            public void onLongClick(View view, int position) {
                Log.i("joe", "item long touched");
            }
        }));
        return v;
    }
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ContactList.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ContactList.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}

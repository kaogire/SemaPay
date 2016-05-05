package com.ogresolutions.imali.semapay.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.helper.Card;
import com.ogresolutions.imali.semapay.helper.MySql;
import com.ogresolutions.imali.semapay.model.CardRecycler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Njuguna on 02/14/2016.
 */
public class CardList extends Fragment{
    private RecyclerView.Adapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_list,container,false);
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.cardRecycler);
        MySql db = new MySql(getActivity().getApplicationContext());
        final ArrayList<Card> cards = db.getAllCards();
        mAdapter = new CardRecycler(cards);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new ContactList.RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new ContactList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("joe", "item touched");
                try {
                    Card card = cards.get(position);
                    JSONObject jsonCard = new JSONObject();
                    Log.i("joe", "item touched");
                    jsonCard.put("name",card.getName());
                    jsonCard.put("number",card.getNumber());
                    jsonCard.put("type",card.getType());
                    jsonCard.put("security",card.getSecurity());
                    String sCard = jsonCard.toString(2);
                    Log.i("joe", "the Contact"+sCard);
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
}

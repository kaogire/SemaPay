package com.ogresolutions.imali.semapay.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.activity.MainActivity;
import com.ogresolutions.imali.semapay.helper.Card;
import com.ogresolutions.imali.semapay.helper.MySelector;
import com.ogresolutions.imali.semapay.helper.MySql;
import com.ogresolutions.imali.semapay.helper.RecyclerClickListener;
import com.ogresolutions.imali.semapay.model.CardRecycler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Njuguna on 02/19/2016.
 */
public class DialogCard extends AppCompatActivity{
    Card myCard;
    CardRecycler adapter;
    static int selectedPosition;
    public static MySelector mySelector = new MySelector();
    MySql db = new MySql(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_card);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvCard);
        adapter = new CardRecycler(db.getAllCards());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this, recyclerView, new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView tvNo = (TextView) view.findViewById(R.id.tvCardNo);
                String cardNo = tvNo.getText().toString();
                selectedPosition = position;
                if (view.isActivated()) {
                    view.setActivated(false);
                } else {
                    view.setActivated(true);
                }
                myCard = db.getCard(Double.parseDouble(cardNo));
                try {
                    JSONObject myJson = new JSONObject();
                    myJson.put("Name", myCard.getName());
                    myJson.put("number", myCard.getNumber());
                    myJson.put("type", myCard.getType());
                    myJson.put("security", myCard.getSecurity());
                    Log.i("joe", myJson.toString(2));
                    MainActivity.request.addCard(myJson);
                } catch (JSONException e) {
                    Log.i("joe", "error MyJson");
                    e.printStackTrace();
                }

//                setViewActive(view, position);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        }));
        Button btnCacel = (Button)findViewById(R.id.btnCardCancel);
        btnCacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void setViewActive(View view,int position){
        int counter = adapter.getItemCount();
        for(int i =0; i<counter ;i++){
            if( position == selectedPosition){
                view.setActivated(true);
            }else{
                view.setActivated(false);
            }
        }
    }
}

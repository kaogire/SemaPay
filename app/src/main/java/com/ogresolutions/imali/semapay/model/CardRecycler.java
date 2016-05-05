package com.ogresolutions.imali.semapay.model;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.helper.Card;

import java.util.ArrayList;

/**
 * Created by Njuguna on 02/20/2016.
 */
public class CardRecycler extends RecyclerView.Adapter<CardRecycler.CardHolder>{
    ArrayList<Card> cards = new ArrayList<>();
    public SparseBooleanArray mySelected = new SparseBooleanArray();

    class CardHolder extends RecyclerView.ViewHolder{
        public TextView tvName, tvType, tvNo;

        public CardHolder(View view){
            super(view);
            tvName = (TextView)view.findViewById(R.id.tvCardName);
            tvType = (TextView)view.findViewById(R.id.tvCardType);
            tvNo = (TextView)view.findViewById(R.id.tvCardNo);
            view.setClickable(true);
        }
    }
    public CardRecycler(ArrayList<Card> cards){
        this.cards = cards;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return  new CardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        Card card = cards.get(position);
        holder.tvName.setText(card.getName());
        holder.tvType.setText(card.getType());
        holder.tvNo.setText(card.getNumber());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void toogleSelect(int position){
        if(mySelected.get(position, false)){
            mySelected.delete(position);
        }
        else{
            mySelected.put(position, true);
        }
        notifyItemChanged(position);
    }
    public void clearSelections(){
        mySelected.clear();
        notifyDataSetChanged();
    }
    public int getSeletctedItemCount(){
        return mySelected.size();
    }
    public boolean isSelected(int position){
        return mySelected.get(position, true);
    }
}

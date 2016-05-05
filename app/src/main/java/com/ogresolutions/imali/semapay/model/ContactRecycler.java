package com.ogresolutions.imali.semapay.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.helper.Contact;

import java.util.ArrayList;

/**
 * Created by Njuguna on 02/12/2016.
 */
public class ContactRecycler extends RecyclerView.Adapter<ContactRecycler.ContactHolder>{
    ArrayList<Contact> contacts;

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.tvEmail.setText(contact.getEmail());
        holder.tvCard.setText(String.valueOf(contact.getCard()));
        holder.tvFname.setText(contact.getFname());
        holder.tvLname.setText(contact.getLname());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class ContactHolder extends RecyclerView.ViewHolder{
        TextView tvFname, tvLname, tvEmail,tvCard;
        public ContactHolder(View view){
            super(view);
            tvCard = (TextView)view.findViewById(R.id.tvConCard);
            tvEmail = (TextView)view.findViewById(R.id.tvConEmail);
            tvFname = (TextView)view.findViewById(R.id.tvConFname);
            tvLname = (TextView)view.findViewById(R.id.tvConLname);
        }
    }
    public ContactRecycler(ArrayList<Contact> contacts){
        this.contacts = contacts;
    }

}

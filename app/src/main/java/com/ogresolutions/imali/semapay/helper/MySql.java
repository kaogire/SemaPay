package com.ogresolutions.imali.semapay.helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Njuguna on 02/04/2016.
 */
public class MySql extends SQLiteOpenHelper {
    private final String DATABASE_NAME = "MyDatabase";
    private final int DATABASE_VERSION = 1;
    private final String COMMA_SEP = " , ";
    private final String TYPE_TEXT = " TEXT ";
    private final String TYPE_INTEGER = " INTEGER ";
    private final String TYPE_INTEGER_KEY = " INTEGER PRIMARY KEY ";
    //columns for card
    private final String CARD_ID= "id";
    private final String CARD_TABLE= "card_table";
    private final String CARD_NAME= " c_name";
    private final String CARD_TYPE= " c_type";
    private final String CARD_NUMBER= " c_number";
    private final String CARD_SECURITY= " c_security";
    private final String CREATE_CARD_TABLE = "CREATE TABLE IF NOT EXISTS card_table("+CARD_NAME+" TEXT,"+CARD_TYPE+" TEXT,"+CARD_NUMBER+" INTEGER,"+CARD_SECURITY+" INT(5))";
    //client table
    private final String CLIENT_TABLE = "client_table";
    private final String CLIENT_FNAME = " cl_fname";
    private final String CLIENT_LNAME = " cl_lname";
    private final String CLIENT_TEL = " cl_tel";
    private final String CLIENT_EMAIL = " cl_email";
    private final String CLIENT_COUNTRY = " cl_country";
    private final String CLIENT_PASS = " cl_pass";
    private final String CREATE_CLIENT_TABLE = "CREATE TABLE IF NOT EXISTS "+CLIENT_TABLE+" ("+CLIENT_FNAME+TYPE_TEXT+COMMA_SEP+CLIENT_LNAME+TYPE_TEXT+COMMA_SEP+CLIENT_TEL+TYPE_INTEGER+COMMA_SEP+CLIENT_EMAIL+TYPE_TEXT+COMMA_SEP+CLIENT_COUNTRY+TYPE_TEXT+COMMA_SEP+CLIENT_PASS+TYPE_TEXT+")";

    //Contacts table table
    private final String CON_FNAME = "con_fname";
    private final String CON_LNAME = "con_lname";
    private final String CON_TEL = "con_tel";
    private final String CON_CARD = "con_card";
    private final String CON_EMAIL = "con_email";
    private final String CON_COUNTRY = "con_country";
    private final String CON_TABLE = "contact_table";
    private final String CREATE_CONTACT_TABLE = "CREATE TABLE IF NOT EXISTS "+CON_TABLE+"("+CON_FNAME+TYPE_TEXT+COMMA_SEP+CON_LNAME+TYPE_TEXT+COMMA_SEP+CON_TEL+TYPE_INTEGER+COMMA_SEP+CON_CARD+TYPE_INTEGER+COMMA_SEP+CON_EMAIL+TYPE_TEXT+COMMA_SEP+CON_COUNTRY+TYPE_TEXT+")";

    public MySql(Context context)
    {
        super(context, "MyDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CARD_TABLE);
        db.execSQL(CREATE_CLIENT_TABLE);
        db.execSQL(CREATE_CONTACT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    //    card details
    public void addCard(Card card)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cardValues = new ContentValues();
        cardValues.put(CARD_NAME, card.getName());
        cardValues.put(CARD_TYPE, card.getType());
        cardValues.put(CARD_NUMBER, card.getNumber());
        cardValues.put(CARD_SECURITY, card.getSecurity());

        db.insert(CARD_TABLE,null,cardValues);
        db.close();
    }
    public Card getCard(double cardNumber)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CARD_TABLE, new String[] { CARD_NAME,
                        CARD_TYPE, CARD_NUMBER,CARD_SECURITY }, CARD_NUMBER + "=?",
                new String[] { String.valueOf(cardNumber) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Card card = new Card(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        // return card
        return card;
    }
    public ArrayList<Card> getAllCards()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        String selectQuery = "SELECT * FROM "+CARD_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Card card = new Card();
                card.setName(cursor.getString(0));
                card.setType(cursor.getString(1));
                card.setNumber(cursor.getString(2));
                card.setASecurity(cursor.getString(3));
                // Adding contact to list
                cards.add(card);
            } while (cursor.moveToNext());
        }

        // return contact list
        return cards;
    }
    public int updateCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CARD_NAME, card.getName());
        values.put(CARD_TYPE, card.getType());
        values.put(CARD_NUMBER, card.getNumber());
        values.put(CARD_SECURITY, card.getSecurity());

        // updating row
        return db.update(CARD_TABLE, values, CARD_NUMBER + " = ?",
                new String[] { String.valueOf(card.getNumber()) });
    }
    public void deleteCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CARD_TABLE, CARD_NUMBER + " = ?",
                new String[]{String.valueOf(card.getNumber())});
        db.close();
    }
    public int getCardCount() {
        String countQuery = "SELECT  * FROM " + CARD_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    //contacts details
    public void addContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contactValues = new ContentValues();
        contactValues.put(CON_FNAME, contact.getFname());
        contactValues.put(CON_LNAME, contact.getLname());
        contactValues.put(CON_TEL, contact.getTel());
        contactValues.put(CON_CARD, contact.getCard());
        contactValues.put(CON_EMAIL, contact.getEmail());
        contactValues.put(CON_COUNTRY, contact.getCountry());

        db.insert(CON_TABLE, null, contactValues);
        db.close();
    }
    public Contact getContact(double cardNo)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CON_TABLE, new String[] { CON_FNAME,
                        CON_LNAME, CON_TEL,CON_CARD, CON_EMAIL, CON_COUNTRY }, CON_CARD + "=?",
                new String[] { String.valueOf(cardNo) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(cursor.getString(0),cursor.getString(1),(cursor.getString(2)),(cursor.getString(3)),cursor.getString(4),cursor.getString(5));
        return contact;
    }
    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM "+CON_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setFname(cursor.getString(0));
                contact.setLname(cursor.getString(1));
                contact.setTel((cursor.getString(2)));
                contact.setCard((cursor.getString(3)));
                contact.setEmail(cursor.getString(4));
                contact.setCountry(cursor.getString(5));
                // Adding contact to list
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contacts;
    }
    public int updateContact( Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CON_FNAME, contact.getFname());
        values.put(CON_LNAME, contact.getLname());
        values.put(CON_TEL, contact.getTel());
        values.put(CON_CARD, contact.getCard());
        values.put(CON_EMAIL, contact.getEmail());
        values.put(CON_COUNTRY, contact.getCountry());
        return db.update(CON_TABLE, values, CON_CARD + " = ?",
                new String[] { String.valueOf(contact.getCard()) });
    }
    public void deleteContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CON_TABLE, CON_CARD + " = ?",
                new String[]{String.valueOf(contact.getCard())});
        db.close();
    }
    public int getContactCount(){
        String countQuery = "SELECT  * FROM " + CON_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    //client details
    public void addClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contactValues = new ContentValues();
        contactValues.put(CLIENT_FNAME, client.getFname());
        contactValues.put(CLIENT_LNAME, client.getLname());
        contactValues.put(CLIENT_TEL, client.getTel());
        contactValues.put(CLIENT_EMAIL, client.getEmail());
        contactValues.put(CLIENT_COUNTRY, client.getCountry());
        contactValues.put(CLIENT_PASS, client.getPass());

        db.insert(CLIENT_TABLE, null, contactValues);
        db.close();
    }
    public Client getClient (String email){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CLIENT_TABLE, new String[] { CLIENT_FNAME,
                        CLIENT_LNAME, CLIENT_TEL,CLIENT_EMAIL,CLIENT_COUNTRY,CLIENT_PASS }, CLIENT_EMAIL + "=?",
                new String[] { email }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Client client = new Client(cursor.getString(0),cursor.getString(1),Double.parseDouble(cursor.getString(2)),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        // return card
        return client;
    }
    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<Client>();
        String selectQuery = "SELECT * FROM "+CLIENT_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.setFname(cursor.getString(0));
                client.setLname(cursor.getString(1));
                client.setTel(Double.parseDouble(cursor.getString(2)));
                client.setEmail(cursor.getString(3));
                client.setCountry(cursor.getString(4));
                client.setPass(cursor.getString(5));
                // Adding contact to list
                clients.add(client);
            } while (cursor.moveToNext());
        }

        // return contact list
        return clients;
    }
    public void deleteClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CLIENT_TABLE, CLIENT_EMAIL + " = ?",
                new String[]{client.getEmail()});
        db.close();
    }
}

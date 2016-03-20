package com.example.zhenmin.justplay;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2016/1/24.
 */
public class ShowContactsActivity extends Activity {
    private RecyclerView rvContacts;
    private List<Contact> contacts;
    private AdapterForContacts adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_contacts);
        rvContacts = (RecyclerView) findViewById(R.id.rv_contacts);
        initData();
        adapter = new AdapterForContacts(this, contacts);
        rvContacts.setAdapter(adapter);
    }

    private void initData() {
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        contacts = new ArrayList<Contact>();
        Contact contact;
        String name ;
        String contact_id ;
        if (cursor.moveToFirst()&&cursor!=null){
            contact_id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            while (!cursor.isAfterLast()) {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contact_id, null, null);
                StringBuilder phonesBuilder = new StringBuilder();
                if (phones.moveToFirst()&&phones!=null){
                    while (!phones.isAfterLast()) {
                        String phoneNum = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phonesBuilder.append(phoneNum + "\n");
                        phones.moveToNext();
                    }
                    phones.close();
                }
                contact = new Contact(name, phonesBuilder.toString());
                contacts.add(contact);
                cursor.moveToNext();
            }
            cursor.close();
        }

    }
}

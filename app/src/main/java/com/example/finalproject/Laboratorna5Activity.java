package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

public class Laboratorna5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorna5);
    }

    public void onClickBtnGetAllContacts(View v)
    {
        getAllContacts();
    }

    public void onCLickBtnGetQueryContacts(View v)
    {
        getQueryContacts();
    }

    public void getAllContacts()
    {
        StringBuilder builder = new StringBuilder();
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if(cursor.getCount() > 0)
        {
            while(cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                builder.append("ID: ").append(id).append("\n");

                String whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = " + id;
                String[] whereNameParams = new String[] { ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE};
                Cursor structuredNameCursor = resolver.query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);

                while(structuredNameCursor.moveToNext())
                {
                    try {
                        String givenName = structuredNameCursor.getString(structuredNameCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
                        if(!givenName.isEmpty())
                            builder.append("Given Name: ").append(givenName).append("\n");
                    }
                    catch(Exception ex)
                    {

                    }
                    try {
                        String familyName = structuredNameCursor.getString(structuredNameCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
                        if(!familyName.isEmpty())
                            builder.append("Family Name: ").append(familyName).append("\n");
                    }
                    catch(Exception ex)
                    {

                    }
                    try {
                        String middleName = structuredNameCursor.getString(structuredNameCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME));
                        if(!middleName.isEmpty())
                            builder.append("Middle Name: ").append(middleName).append("\n");
                    }
                    catch(Exception ex)
                    {

                    }
                }
                structuredNameCursor.close();


                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if(hasPhoneNumber > 0)
                {
                    Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);

                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        builder.append("Phone Number: ").append(phoneNumber).append("\n");
                    }
                    phoneCursor.close();
                }

                Cursor emailCursor = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[] {id},
                        null);
                while(emailCursor.moveToNext())
                {
                    String email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    builder.append("Email: ").append(email).append("\n");
                }
                emailCursor.close();


                builder.append("\n");
            }
        }

        cursor.close();
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(builder.toString());

    }

    public void getQueryContacts()
    {
        StringBuilder builder = new StringBuilder();
        ContentResolver resolver = getContentResolver();

        String whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME + " LIKE " + "'Ми%'";
        String[] whereNameParams = new String[] { ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE};
        Cursor structuredNameCursor = resolver.query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);

        if(structuredNameCursor.getCount() > 0)
        {
            while(structuredNameCursor.moveToNext())
            {
                String id = structuredNameCursor.getString(structuredNameCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID));
                builder.append("ID: ").append(id).append("\n");

                try {
                    String givenName = structuredNameCursor.getString(structuredNameCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
                    if(!givenName.isEmpty())
                        builder.append("Given Name: ").append(givenName).append("\n");
                }
                catch(Exception ex)
                {

                }
                try {
                    String familyName = structuredNameCursor.getString(structuredNameCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
                    if(!familyName.isEmpty())
                        builder.append("Family Name: ").append(familyName).append("\n");
                }
                catch(Exception ex)
                {

                }
                try {
                    String middleName = structuredNameCursor.getString(structuredNameCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME));
                    if(!middleName.isEmpty())
                        builder.append("Middle Name: ").append(middleName).append("\n");
                }
                catch(Exception ex)
                {

                }

                int hasPhoneNumber = Integer.parseInt(structuredNameCursor.getString(structuredNameCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if(hasPhoneNumber > 0) {
                    Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);

                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        builder.append("Phone Number: ").append(phoneNumber).append("\n");
                    }
                    phoneCursor.close();
                }

                Cursor emailCursor = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[] {id},
                        null);
                while(emailCursor.moveToNext())
                {
                    String email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    builder.append("Email: ").append(email).append("\n");
                }
                emailCursor.close();

                builder.append("\n");
            }
        }

        structuredNameCursor.close();
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(builder.toString());
    }
}

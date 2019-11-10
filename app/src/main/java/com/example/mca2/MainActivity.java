package com.example.mca2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         data();
    }
    private void data()
    {
        ArrayList<String> contacts=new ArrayList<>();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] p={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
        String s=null;
        String[] se=null;
        String sort=null;
        ContentResolver resolver=getContentResolver();
        Cursor cursor=resolver.query(uri,p,s,se,sort);
        while(cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add(name+"\n"+num);
        }
        ListView listView=(ListView)findViewById(R.id.listContacts);
                listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts));
    }
}

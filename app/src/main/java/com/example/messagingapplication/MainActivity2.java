package com.example.messagingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class MainActivity2 extends AppCompatActivity {
    TextView messageData , SenderData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        messageData = (TextView) findViewById(R.id.MessageBody);
        SenderData = (TextView) findViewById(R.id.RecieverBody) ;
        Intent intent = getIntent();
        String indexAsAString = intent.getStringExtra("data");

        int looper = parseInt(indexAsAString);


        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);

        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;

        String SENDER_ = "";
        String MESSAGE_ = "";

       int  i=-1;
        do {
            i=i+1;
          SENDER_ = smsInboxCursor.getString(indexAddress);
          MESSAGE_ = smsInboxCursor.getString(indexBody);

            smsInboxCursor.moveToNext();
        } while ( i != looper );

        String SENDER = SENDER_;
        String MESSAGE = MESSAGE_;
        SenderData.setText(SENDER);
        messageData.setText(MESSAGE );
    }
}
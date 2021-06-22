package com.example.messagingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText phoneNumber, messageData;
    private int requestCode;
    private String[] permissions;
    private int[] grantResults;
    String msg="piyush is a bad boy";
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageData = (EditText) findViewById(R.id.textMessage);
        phoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
        button2=(Button)findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}
                Intent intent=new Intent(MainActivity.this,MainActivity3.class );
                intent.putExtra("piyush",msg);
                startActivity(intent);
            }
        });



    }

    public void sendMessageHandler(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck == 0) {
            SendMessageFunction();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);

        }

    }

    private void SendMessageFunction() {



        if (!phoneNumber.getText().toString().equals("") || !messageData.toString().equals("")) {
            String number = phoneNumber.getText().toString().trim();
            String message = messageData.getText().toString().trim();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(this, "Message Sent Successfully to " + number, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Message Failed ", Toast.LENGTH_SHORT).show();

        }
    }

}
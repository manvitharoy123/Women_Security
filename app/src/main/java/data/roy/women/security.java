package data.roy.women;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class security extends manu {
    private final static int SEND_SMS_PERMISSION_REQ = 1;
    Button b1;
    Button b2;
    Button b3;
    Button em;
EditText editText;
Button b7;
Button b6;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        b1 = findViewById(R.id.em);
        b1.setEnabled(false);
        editText=findViewById(R.id.editText);
        b2= findViewById(R.id.settings);
        b3= findViewById(R.id.button2);
        b6= findViewById(R.id.button4);
        b7=findViewById(R.id.playsound);
        final MediaPlayer mp=MediaPlayer.create(this,R.raw.sample);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });
        b6.setOnClickListener(v->{
            Intent intent =new Intent(security.this,bluetooth.class);
            startActivity(intent);
        });
        b3.setOnClickListener(v->{
            Intent intent =new Intent(security.this,LocationActivity.class);
            startActivity(intent);
        });
        b2.setOnClickListener(v -> {
            Intent intent=new Intent(security.this,manu.class);
            startActivity(intent);
        });
        if(checkPermission())
        {
            b1.setEnabled(true);
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQ);
        }
        b1.setOnClickListener(v -> {
            String s1=e1.getText().toString();
            String s2=e2.getText().toString();
            if(!TextUtils.isEmpty(s1)&&!TextUtils.isEmpty(s2))
            {

                if(checkPermission())
                {
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(s1,null,s2,null,null);
                    Toast.makeText(security.this,"messagesent!!!",Toast.LENGTH_LONG).show();}
                else {
                    Toast.makeText(security.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(security.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean checkPermission() {

        int checkpermission= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        return checkpermission== PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case SEND_SMS_PERMISSION_REQ:
                if(grantResults.length>0 &&(grantResults[0]==PackageManager.PERMISSION_GRANTED))
                {
                    b1.setEnabled(true);
                }
                break;
        }
    }
    }

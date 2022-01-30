package data.roy.women;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class VehicleActivity extends manu {
    private final static int SEND_SMS_PERMISSION_REQ = 1;
    Button b1;
    EditText editText;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        getSupportActionBar().hide();
        b1 = findViewById(R.id.save);
        editText=findViewById(R.id.editText);
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
            String s2=editText.getText().toString();
            if(!TextUtils.isEmpty(s1)&&!TextUtils.isEmpty(s2))
            {

                if(checkPermission())
                {
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(s1,null,s2,null,null);
                    Toast.makeText(VehicleActivity.this,"messagesent!!!",Toast.LENGTH_LONG).show();}
                else {
                    Toast.makeText(VehicleActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(VehicleActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
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

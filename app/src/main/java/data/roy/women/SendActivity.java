package data.roy.women;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;


public class SendActivity extends manu {
Button b1;
    private final static int SEND_SMS_PERMISSION_REQ = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        getSupportActionBar().hide();
        b1=findViewById(R.id.btn_n);

        b1.setOnClickListener(v -> {
            String s1=e1.getText().toString();
            String s2=e2.getText().toString();
            if(!TextUtils.isEmpty(s1)&&!TextUtils.isEmpty(s2))
            {
                Uri sms_uri=Uri.parse("smsto:" +s1);
                Intent sms_intent= new Intent(Intent.ACTION_VIEW, sms_uri);
                sms_intent.setData(sms_uri);
                sms_intent.putExtra("sms_body",s2);
                startActivity(sms_intent);
            }
            else
            {
                Toast.makeText(SendActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
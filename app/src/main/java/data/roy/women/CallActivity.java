package data.roy.women;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

public class CallActivity extends manu {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    Button call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        call=findViewById(R.id.btn);
        getSupportActionBar().hide();
        call.setOnClickListener(View->{
            String s1=e1.getText().toString();
            if(!TextUtils.isEmpty(s1))
            {
                Uri call_uri=Uri.parse("tel:" +s1);
                Intent call_intent= new Intent(Intent.ACTION_DIAL, call_uri);
                call_intent.setData(call_uri);
                startActivity(call_intent);
            }
            else
            {
                Toast.makeText(CallActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
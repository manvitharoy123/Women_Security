package data.roy.women;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class manu extends AppCompatActivity {

    EditText e1, e2;
    Button b2;
    String s1;
    String s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu);
        getSupportActionBar().hide();
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        b2=findViewById(R.id.save);
        SharedPreferences prefs1=PreferenceManager.getDefaultSharedPreferences(this);
        String s3=prefs1.getString("s1","");
        e1.setText(s3);
        String s4=prefs1.getString("s2","");
        e2.setText(s4);
        b2.setOnClickListener(v -> {
            s1 = e1.getText().toString();
            s2= e2.getText().toString();
            SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(manu.this);
            SharedPreferences.Editor editor=prefs.edit();
            editor.putString("s1",s1);
            editor.putString("s2",s2);
            editor.apply();
            Toast.makeText(getApplicationContext(), "Updated",
                    Toast.LENGTH_LONG).show();
        }); }}
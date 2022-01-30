package data.roy.women.Grouping;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import data.roy.women.MainScreen;
import data.roy.women.Map.MapLoc;
import data.roy.women.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Joingp extends AppCompatActivity  {

    FirebaseAuth mAuth;
    EditText jcode;
    Button join;
    String uname = "";
    LocationManager mlocManager;

    Location location = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingp);
        getSupportActionBar().hide();
        jcode = (EditText) findViewById(R.id.jcode);
        join = (Button) findViewById(R.id.join);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        final String uid = firebaseUser.getUid();
        Log.d("blabla", "Hry");
        final DatabaseReference userInfo = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Name");
        userInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uname = dataSnapshot.getValue().toString();
                Log.d("Updated", "Hry");
                Toast.makeText(getApplicationContext(), uname, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = jcode.getText().toString();


                DatabaseReference jRef = FirebaseDatabase.getInstance().getReference().child("Groups").child(code).child(uid);
                Map<String, String> newUser = new HashMap<>();
                newUser.put("Name", uname);
                newUser.put("code",code);

                jRef.setValue(newUser);
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putString("code", code);

//Add the bundle to the intent
                DatabaseReference groupRef = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                groupRef.child("Group").setValue(code);
                 Intent intent = new Intent(Joingp.this, MapLoc.class);
                intent.putExtras(bundle);
                startActivity(intent);

                Toast.makeText(getApplicationContext(),"Group Joined",Toast.LENGTH_SHORT).show();
            }
        });
    }

}

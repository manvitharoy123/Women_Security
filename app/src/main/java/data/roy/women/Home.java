package data.roy.women;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import data.roy.women.Grouping.Creategp;
import data.roy.women.Grouping.Joingp;

import data.roy.women.Registration.Login;
import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    ImageView create_gp,join_gp,btn2;

    private android.location.Location mLastLocation ;

    double latitude;
    double longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        create_gp = (ImageView) findViewById(R.id.create_grp);
        join_gp = (ImageView) findViewById(R.id.join_grp);

        create_gp.setOnClickListener(this);
        join_gp.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();


        //extra btn
        btn2 = (ImageView) findViewById(R.id.go_to_home);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Home.this, MainScreen.class);
                startActivity(i);
            }
        });



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(1, 1, 2, "Log Out");
        menu.add(1,1,1,"Setting");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case 1:

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
                finish();
                break;
            case 2:
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.create_grp: Intent i = new Intent(Home.this, latlan.class);
                startActivity(i);
                finish(); break;

            case R.id.join_grp : Intent j = new Intent(Home.this, latlan.class);
                startActivity(j);
                finish();break;
        }

    }



    public void showToast(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


}

package data.roy.women;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import data.roy.women.Grouping.Joingp;

public class latlan extends AppCompatActivity  {
    private static final long MIN_TIME_BW_UPDATES =1 ;
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES =1 ;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latlan);
        Button b;
        b = (Button) findViewById(R.id.find);
        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        b.setOnClickListener(v -> {
           locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                OnGPS();
            } else {
                getLocation();
            }
        });
    }
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", (dialog, which) -> startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))).setNegativeButton("No", (dialog, which) -> dialog.cancel());
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                latlan.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                latlan.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {

            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (locationGPS != null) {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                String uid=currentFirebaseUser.getUid();
                double Text = locationGPS.getLatitude();
                double lon = locationGPS.getLongitude();
                DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("Location").child(uid);
                Map<String, Double> newPost = new HashMap<>();
                newPost.put("lat", Text);
                newPost.put("lon", lon);
                current_user.setValue(newPost);
                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(latlan.this, Joingp.class);
                startActivity(intent);

                }
            else if (LocationNetwork != null) {
                double lat = LocationNetwork.getLatitude();
                double longi = LocationNetwork.getLongitude();

                //latitude = String.valueOf(lat);
                //longitude = String.valueOf(longi);
                Toast.makeText(latlan.this, "it's not null :D", Toast.LENGTH_SHORT).show();


                //showLocationTxt.setText("Your Location:"+"n"+"Latitude= "+latitude+"n"+"Longitude= "+longitude);
            } else if (LocationPassive != null) {
                double lat = LocationPassive.getLatitude();
                double longi = LocationPassive.getLongitude();

                //latitude = String.valueOf(lat);
                //longitude = String.valueOf(longi);
                Toast.makeText(latlan.this, "it's not null :D", Toast.LENGTH_SHORT).show();


                //showLocationTxt.setText("Your Location:"+"n"+"Latitude= "+latitude+"n"+"Longitude= "+longitude);
            }else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
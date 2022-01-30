package data.roy.women.Map;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import data.roy.women.Grouping.Joingp;
import data.roy.women.R;
import data.roy.women.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapLoc extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    private ArrayList<LatLng> locationArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_loc);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        locationArrayList = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersRef = rootRef.child("Location");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    double latitude = ds.child("lat").getValue(Double.class);
                    double longitude = ds.child("lon").getValue(Double.class);
                    locationArrayList.add(new LatLng(latitude,longitude));
                }
                System.out.println(locationArrayList.size());
                for (int i = 0; i < locationArrayList.size(); i++) {
                    mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });
    }}
package data.roy.women;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import data.roy.women.LocationUtil.LocationHelper;

public class LocationActivity extends manu implements GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback {
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.btnLocation)
        Button btnProceed;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.tvEmpty)TextView tvEmpty;
        @BindView(R.id.rlPickLocation)
        RelativeLayout rlPick;
        private final static int SEND_SMS_PERMISSION_REQ = 1;
        private Location mLastLocation;

        double latitude;
        double longitude;

        LocationHelper locationHelper;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_location);
            btnProceed=(Button)findViewById(R.id.btnLocation) ;
            btnProceed.setOnClickListener(v -> {
                String s1=e1.getText().toString();
                String s3=tvAddress.getText().toString();
                if(!TextUtils.isEmpty(s1)&&!TextUtils.isEmpty(s3))
                {

                    if(checkPermission())
                    {
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(s1,null,s3,null,null);
                        Toast.makeText(LocationActivity.this,"messagesent!!",Toast.LENGTH_LONG).show();}
                    else {
                        Toast.makeText(LocationActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(LocationActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            });

            locationHelper=new LocationHelper(this);
            locationHelper.checkpermission();

            ButterKnife.bind(this);

            rlPick.setOnClickListener(view -> {

                mLastLocation=locationHelper.getLocation();

                if (mLastLocation != null) {
                    latitude = mLastLocation.getLatitude();
                    longitude = mLastLocation.getLongitude();
                    getAddress();

                } else {

                    if(btnProceed.isEnabled())
                        btnProceed.setEnabled(false);

                    showToast("Couldn't get the location. Make sure location is enabled on the device");
                }
            });




            // check availability of play services
            if (locationHelper.checkPlayServices()) {

                // Building the GoogleApi client
                locationHelper.buildGoogleApiClient();
            }

        }


        public void getAddress()
        {
            Address locationAddress;

            locationAddress=locationHelper.getAddress(latitude,longitude);

            if(locationAddress!=null)
            {

                String address = locationAddress.getAddressLine(0);
                String address1 = locationAddress.getAddressLine(1);
                String city = locationAddress.getLocality();
                String state = locationAddress.getAdminArea();
                String country = locationAddress.getCountryName();
                String postalCode = locationAddress.getPostalCode();


                String currentLocation;

                if(!TextUtils.isEmpty(address))
                {
                    currentLocation=address;

                    if (!TextUtils.isEmpty(address1))
                        currentLocation+="\n"+address1;

                    if (!TextUtils.isEmpty(city))
                    {
                        currentLocation+="\n"+city;

                        if (!TextUtils.isEmpty(postalCode))
                            currentLocation+=" - "+postalCode;
                    }
                    else
                    {
                        if (!TextUtils.isEmpty(postalCode))
                            currentLocation+="\n"+postalCode;
                    }

                    if (!TextUtils.isEmpty(state))
                        currentLocation+="\n"+state;

                    if (!TextUtils.isEmpty(country))
                        currentLocation+="\n"+country;

                    tvEmpty.setVisibility(View.GONE);
                    tvAddress.setText(currentLocation);
                    tvAddress.setVisibility(View.VISIBLE);

                    if(!btnProceed.isEnabled())
                        btnProceed.setEnabled(true);
                }

            }
            else
                showToast("Something went wrong");
        }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            locationHelper.onActivityResult(requestCode, resultCode, data);
        }


        @Override
        protected void onResume() {
            super.onResume();
            locationHelper.checkPlayServices();
        }

        /**
         * Google api callback methods
         */
        @Override
        public void onConnectionFailed(ConnectionResult result) {
            Log.i("Connection failed:", " ConnectionResult.getErrorCode() = "
                    + result.getErrorCode());
        }

        @Override
        public void onConnected(Bundle arg0) {

            // Once connected with google api, get the location
            mLastLocation=locationHelper.getLocation();
        }

        @Override
        public void onConnectionSuspended(int arg0) {
            locationHelper.connectApiClient();
        }


        // Permission check functions
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                               @NonNull int[] grantResults) {
            // redirects to utils
            locationHelper.onRequestPermissionsResult(requestCode,permissions,grantResults);

        }

        public void showToast(String message)
        {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }
        private boolean checkPermission() {

            int checkpermission= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
            return checkpermission== PackageManager.PERMISSION_GRANTED;
        }


}
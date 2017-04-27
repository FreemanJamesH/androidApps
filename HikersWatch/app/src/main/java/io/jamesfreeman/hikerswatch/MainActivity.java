package io.jamesfreeman.hikerswatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;
    TextView latitude;
    TextView longitude;
    TextView accuracy;
    TextView altitude;
    TextView address;



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 3, locationListener);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitude = (TextView) findViewById(R.id.latitude);
        longitude = (TextView) findViewById(R.id.longitude);
        accuracy = (TextView) findViewById(R.id.accuracy);
        altitude = (TextView) findViewById(R.id.altitude);
        address = (TextView) findViewById(R.id.address);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.i("Location changed", location.toString());
                double latitudeValue = location.getLatitude();
                double longitudeValue = location.getLongitude();
                double accuracyValue = location.getAccuracy() * 3.28084;
                double altitudeValue = location.getAltitude() * 3.28084;

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (listAddresses != null && listAddresses.size() > 0){
                        String street = listAddresses.get(0).getAddressLine(0).toString();
                        String city = listAddresses.get(0).getAddressLine(1).toString();
                        String country = listAddresses.get(0).getAddressLine(2).toString();

                        address.setText(String.format("Address: %s,\n                 %s,\n                 %s\n", street, city, country));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                latitude.setText(String.format("Latitude: %.3f", latitudeValue));
                longitude.setText(String.format("Longitude: %.3f", longitudeValue));
                accuracy.setText(String.format("Accuracy: %.0f feet", accuracyValue));
                altitude.setText(String.format("Altitude: %.0f feet", altitudeValue));





            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (Build.VERSION.SDK_INT < 23){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,3,locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 3, locationListener);

            }
        }

    }
}

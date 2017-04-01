package com.example.a1514290069.contatos;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final int MAP_PERMISSION_ACCESS_COURSE_LOCATION = 9999;

    private GoogleMap mMapa;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMapa = googleMap;

        LatLng iesbSul = new LatLng(-15.7571194, -47.8788442);
        mMapa.addMarker(new MarkerOptions().position(iesbSul).title("IESB Sul"));
        mMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(iesbSul, 15));

         locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        if (ContextCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions
                    (this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MAP_PERMISSION_ACCESS_COURSE_LOCATION);
        }else{
            getLastLocation();
            getLocation();
        }

    }

    private void  getLastLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            Location ultimaLocalizacao = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (ultimaLocalizacao != null) {
                LatLng eu = new LatLng(ultimaLocalizacao.getLongitude(), ultimaLocalizacao.getLatitude());
                mMapa.addMarker(new MarkerOptions().position(eu).title("Yow"));
                mMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(eu, 21));
            }
        }
    }

    private void getLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    LatLng eu = new LatLng(location.getLatitude(), location.getLongitude());
                    mMapa.addMarker(new MarkerOptions().position(eu).title("ue sumiu"));
                    mMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(eu, 13));
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
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 2, locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MAP_PERMISSION_ACCESS_COURSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation();
                    getLocation();
                } else {
                    //Permissão negada
                }
                return;
            }
        }
    }

}

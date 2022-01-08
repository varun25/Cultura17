package com.cmrit.cultura17;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public static final CameraPosition CURRENT =
            new CameraPosition.Builder().target(new LatLng(12.957048,77.598928))
                    .zoom(12f)
                    .bearing(0)
                    .tilt(25)
                    .build();

    public static final CameraPosition CMRIT =
            new CameraPosition.Builder().target(new LatLng(12.9668,77.7115))
                    .zoom(18f)
                    .bearing(0)
                    .tilt(25)
                    .build();

    Marker marker;


    private FirebaseAnalytics mFirebaseAnalytics;

    private ImageView directions;
    private ImageView google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ImageView mapBack = (ImageView) findViewById(R.id.map_back);

        mapBack.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });

        directions = (ImageView) findViewById(R.id.directions);

        directions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Directions
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(
//                        "http://maps.google.com/maps?saddr=51.5, 0.125&daddr=12.9668,77.7115"));
//                startActivity(intent);

                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", 12.966717019444445, 77.7115859, "Where the party is at");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);

            }
        });

        google = (ImageView) findViewById(R.id.google);

        google.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Default google map
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "http://maps.google.com/maps?q=loc:12.966717019444445,77.7115859"));
                startActivity(intent);

            }
        });

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle params = new Bundle();
        params.putString("name", "kushal");
        mFirebaseAnalytics.logEvent("Location", params);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CURRENT));

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback()
        {
            @Override
            public void onMapLoaded() {

                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CURRENT));

                mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback()
                {
                    @Override
                    public void onMapLoaded() {
                        onGoToCMRIT();
                    }
                });
            }
        });

        marker = mMap.addMarker(new MarkerOptions()
                .position(CMRIT.target)
                .visible(true));
    }

    public void onGoToCMRIT() {
        if (mMap == null) {
            return;
        }
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CMRIT), 3000, null);
    }
}
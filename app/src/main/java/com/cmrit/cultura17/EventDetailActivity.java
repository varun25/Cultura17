package com.cmrit.cultura17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

public class EventDetailActivity extends AppCompatActivity {
    private static android.support.v4.app.FragmentManager fragmentManager;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        int enumb = getIntent().getExtras().getInt("enum");
        String image = getIntent().getExtras().getString("image");

        fragmentManager = getSupportFragmentManager();

        EventDetailFragment info = new EventDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("eventNum", enumb);
        bundle.putString("eventImage", image);

        info.setArguments(bundle);

        fragmentManager.beginTransaction().replace(R.id.event_details_fragment, info).commit();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle params = new Bundle();
        params.putString("name", "kushal");
        mFirebaseAnalytics.logEvent("Event_Details", params);

    }
}
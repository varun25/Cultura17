package com.cmrit.cultura17;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

//import static com.developer.rajat.cultura17.R.id.about_viewpager;

public class AboutActivity extends AppCompatActivity {

    //private AboutPagerAdapter mSectionsPagerAdapter;
    //private ViewPager mViewPager;
    //private CircleIndicator indicator;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView back = (ImageView) findViewById(R.id.about_back);
        back.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });

        final EditText textSubject = (EditText) findViewById(R.id.textSubject);
        final EditText textMessage = (EditText) findViewById(R.id.textMessage);

        Button buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String to = "culturaapp@gmail.com";
                String subject = textSubject.getText().toString();
                String message = textMessage.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }
        });

        ImageView fb = (ImageView) findViewById(R.id.imageView);
        fb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/Cultura.cmrit/"));
                startActivity(intent);
            }
        });

        ImageView insta = (ImageView) findViewById(R.id.imageView2);
        insta.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/cmritcultura/"));
                startActivity(intent);
            }
        });

        ImageView tw = (ImageView) findViewById(R.id.imageView3);
        tw.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://twitter.com/cmritcultura"));
                startActivity(intent);
            }
        });


        //indicator = (CircleIndicator) findViewById(R.id.indicator);

        //mSectionsPagerAdapter = new AboutPagerAdapter(this,getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        //mViewPager = (ViewPager) findViewById(about_viewpager);
        //mViewPager.setPageTransformer(false, new ParallaxPagerTransformer(R.id.aboutmain));
        //mViewPager.setAdapter(mSectionsPagerAdapter);

        //indicator.setViewPager(mViewPager);//

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle params = new Bundle();
        params.putString("name", "kushal");
        mFirebaseAnalytics.logEvent("About", params);
    }
}

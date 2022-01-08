package com.cmrit.cultura17;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmrit.cultura17.DBModel.Events;
import com.cmrit.cultura17.DBModel.Sponsors;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import carbon.widget.ViewPager;

public class EventCardActivity extends AppCompatActivity {

    String category[] = {"Art","Dance","Gaming","Informal","Kannada","Literary","Music","Tech","Theatre"};
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_card);

        int cno = getIntent().getExtras().getInt("categorynumber");

        RelativeLayout layout =(RelativeLayout)findViewById(R.id.activity_event_card);
        layout.setBackgroundResource(R.drawable.event+cno);

        TextView title = (TextView) findViewById(R.id.event_card_title) ;
        title.setText(category[cno]);

        ImageView back = (ImageView) findViewById(R.id.event_card_back);
        back.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);

        EventCardPagerAdapter adapter = new EventCardPagerAdapter();

        CulturaDBHelper db = new CulturaDBHelper(this);
        List<Events> data = new ArrayList<Events>();
        List<Sponsors> spon = new ArrayList<Sponsors>();
        data = db.getAllEvents(cno);
        spon = db.getSponsors();

        int[] eno = new int[data.size()+1];
        String[] eventName = new String[data.size()+1];
        String[] eventDesc = new String[data.size()+1];
        String[] eventImage= new String[data.size()+1];
        String tempEN,tempED,tempEI;
        int tempEno,mid;

        mid =(data.size()+1)/2;

        for (int j=0; j<data.size(); j++) {
                eno[j] = data.get(j).getEno();
                eventName[j] = data.get(j).getEname();
                eventDesc[j] = data.get(j).getPrize3();
                eventImage[j] = "http://203.201.63.39/html/cultura17/events/" + data.get(j).getCno() + "_" + data.get(j).getEno() + ".jpg";
        }

        tempEno = eno[mid];
        tempEN = eventName[mid];
        tempED = eventDesc[mid];
        tempEI = eventImage[mid];


        eno[mid] = 69;
        eventName[mid] = spon.get(cno).getSname();
        eventDesc[mid] = spon.get(cno).getType();
        eventImage[mid] = "http://203.201.63.39/html/cultura17/cardsponsor/" +spon.get(cno).getSno()+ ".jpg";


        eno[data.size()] = tempEno;
        eventName[data.size()] = tempEN;
        eventDesc[data.size()] = tempED;
        eventImage[data.size()] = tempEI;

        for(int i=0; i<eventName.length;i++) {
            adapter.addCardItem(new EventCardItem(eno[i], eventImage[i], eventName[i], eventDesc[i]));
        }

        ParallexShadowTransformer transformer = new ParallexShadowTransformer(mViewPager, adapter, R.id.eventImage);

        mViewPager.setPageTransformer(false, transformer);
        mViewPager.setClipToPadding(false);
        mViewPager.setPageMargin(convertDip2Pixels(this,10));
        mViewPager.setOffscreenPageLimit(3);

        mViewPager.setAdapter(adapter);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle params = new Bundle();
        params.putString("name", "kushal");
        mFirebaseAnalytics.logEvent("Event_Card", params);
    }

//    public void interested(View view)
//    {
//        ImageView awesome = (ImageView) findViewById(R.id.awesomedude);
//        awesome.setVisibility(View.VISIBLE);
//        moveup(awesome);
//    }
//
//    private void moveup(View view) {
//        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationY", 1300,0);
//        anim.setDuration(200);
//        anim.setTarget(view);
//        anim.start();
//        final View v = view;
//        Thread timer = new Thread(){
//            public void run(){
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                }
//                EventCardActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//
//                        } finally {
//                            ObjectAnimator anim2 = ObjectAnimator.ofFloat(this, "translationY", 0, 1300);
//                            anim2.setDuration(200);
//                            anim2.setTarget(v);
//                            anim2.start();
//                        }
//                    }
//            });super.run();
//        }
//    };
//    timer.start();
//    }

    public static int convertDip2Pixels(Context context, int dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }
}

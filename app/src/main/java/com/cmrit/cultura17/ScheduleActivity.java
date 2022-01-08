package com.cmrit.cultura17;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmrit.cultura17.DBModel.Schedule;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.nightonke.boommenu.Animation.BoomEnum;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;
import com.xgc1986.parallaxPagerTransformer.ParallaxPagerTransformer;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ScheduleActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private CircleIndicator indicator;
    static List<Schedule> sch1, sch2, sch ;
    private static String[] time1 = new String[60];
    private static String[] ename1 = new String[60];
    private static String[] venue1 = new String[60];
    private static String[] time2 = new String[60];
    private static String[] ename2 = new String[60];
    private static String[] venue2 = new String[60];
    private static String[] category={"Tech", "Art", "Literary", "Dance", "Music", "Kannada", "Informal", "Gaming", "Theatre"};
    public static int noOfEventsDay1=0, noOfEventsDay2=0;
    static private RecyclerView recyclerView1, recyclerView2;
    static private RecyclerView.Adapter adapter1, adapter2;
    static private RecyclerView.LayoutManager layoutManager;
    FloatingActionButton all;

    private FirebaseAnalytics mFirebaseAnalytics;

    //Bitbit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.schedulemain);
        all = (FloatingActionButton) findViewById(R.id.all);
        indicator = (CircleIndicator) findViewById(R.id.indicator);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setPageTransformer(false, new ParallaxPagerTransformer(R.id.menupic));
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        indicator.setViewPager(mViewPager);

        CulturaDBHelper db = new CulturaDBHelper(this);
        sch1 = db.getScheduleOnDay(1);
        sch2 = db.getScheduleOnDay(2);

        final BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_3);
        bmb.setBoomEnum(BoomEnum.HORIZONTAL_THROW_1);

        int imgs[] = {R.drawable.schedule_tech,R.drawable.schedule_art,R.drawable.schedule_lit,
                R.drawable.schedule_dance,R.drawable.schedule_mus,R.drawable.schedule_kannada,
                R.drawable.schedule_misc,R.drawable.schedule_game,R.drawable.schedule_thet};
        int i=0;
        for (i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            setData(index,1);
                            setData(index,2);
                            all.setVisibility(View.VISIBLE);
                            bmb.setVisibility(View.GONE);
                        }
                    })
                    .normalImageRes(imgs[i])
                    .imageRect(new Rect(Util.dp2px(10), Util.dp2px(10), Util.dp2px(70), Util.dp2px(70)))
                    .imagePadding(new Rect(20, 20, 20, 20))
                    .normalText(category[i])
                    .rippleEffect(true)
                    .unable(false)
                    .buttonRadius(Util.dp2px(40));

            bmb.addBuilder(builder);


            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

            Bundle params = new Bundle();
            params.putString("name", "xyz");
            mFirebaseAnalytics.logEvent("Schedule", params);
        }

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmb.setVisibility(View.VISIBLE);
                all.setVisibility(View.GONE);
                setData(10,1);
                setData(10,2);
            }
        });

    }

    private static void setData(int index, int day) {
        int i=0, noOfEvents;

        if(day==1) sch = sch1;
            else sch = sch2;

        noOfEvents=0;

        if(index==10)
        {
            noOfEvents = sch.size();
        }
        else
        {
            for(i=0;i<sch.size();i++)
            {
                if(sch.get(i).getCname().equalsIgnoreCase(category[index])) {
                    noOfEvents++;
                }
            }

        }
        if(day==1) {
            noOfEventsDay1=noOfEvents;
            time1 = new String[noOfEvents];
            ename1 = new String[noOfEvents];
            venue1 = new String[noOfEvents];
        }
        else
        {
            noOfEventsDay2=noOfEvents;
            time2 = new String[noOfEvents];
            ename2 = new String[noOfEvents];
            venue2 = new String[noOfEvents];
        }

        if(index==10)
        {
            if(day==1) {
                for (i = 0; i < noOfEvents; i++) {
                    time1[i] = sch.get(i).getTime();
                    ename1[i] = sch.get(i).getEname();
                    venue1[i] = sch.get(i).getVenue();
                }
            }
            else
            {
                for (i = 0; i < noOfEvents; i++) {
                    time2[i] = sch.get(i).getTime();
                    ename2[i] = sch.get(i).getEname();
                    venue2[i] = sch.get(i).getVenue();
                }
            }
        }
        else
        {   int k=0;
            if(day==1)
            {
                for(i=0;i<sch.size();i++) {
                    if (sch.get(i).getCname().equalsIgnoreCase(category[index])) {
                        time1[k] = sch.get(i).getTime();
                        ename1[k] = sch.get(i).getEname();
                        venue1[k] = sch.get(i).getVenue();
                        k++;
                    }
                }
            }
            else
            {
                for(i=0;i<sch.size();i++) {
                    if (sch.get(i).getCname().equalsIgnoreCase(category[index])) {
                        time2[k] = sch.get(i).getTime();
                        ename2[k] = sch.get(i).getEname();
                        venue2[k] = sch.get(i).getVenue();
                        k++;
                    }
                }
            }
        }
        if(day==1) {
            if (adapter1 != null) {
                adapter1 = new ScheduleList(time1, ename1, venue1, 1);
                recyclerView1.setAdapter(adapter1);
            }
        }
        else {
            if (adapter2 != null) {
                adapter2 = new ScheduleList(time2, ename2, venue2, 2);
                recyclerView2.setAdapter(adapter2);
            }
        }
}

    public void close(View v)
    {
        finish();
    }

    public static class PlaceholderFragment extends Fragment {

       private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }
        int imagess[] = {R.drawable.sch2, R.drawable.sch1};

       public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.schedule_fragment, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.day);
            textView.setText("Day " + (getArguments().getInt(ARG_SECTION_NUMBER)) + "/2");

            setData(10,getArguments().getInt(ARG_SECTION_NUMBER));

            //RecyclerView initilised from here
            if((getArguments().getInt(ARG_SECTION_NUMBER))==1) {
                recyclerView1 = (RecyclerView) rootView.findViewById(R.id.recyclervv);
                adapter1 = new ScheduleList(time1, ename1, venue1, 1);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setHasFixedSize(true);
                recyclerView1.setAdapter(adapter1);
            }
            else
            {
                recyclerView2 = (RecyclerView) rootView.findViewById(R.id.recyclervv);
                adapter2 = new ScheduleList(time2, ename2, venue2, 2);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView2.setLayoutManager(layoutManager);
                recyclerView2.setHasFixedSize(true);
                recyclerView2.setAdapter(adapter2);
            }
            //end of recyclerView initialiser

            ImageView imageView = (ImageView) rootView.findViewById(R.id.menupic);
            imageView.setImageResource(imagess[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}

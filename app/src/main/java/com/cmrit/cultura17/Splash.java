package com.cmrit.cultura17;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cmrit.cultura17.DBModel.AR;
import com.cmrit.cultura17.DBModel.EC;
import com.cmrit.cultura17.DBModel.Events;
import com.cmrit.cultura17.DBModel.Sponsors;
import com.cmrit.cultura17.Server.HttpHandler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Varun on 15-01-2017.
 */

public class Splash extends Activity {
    SharedPreferences sp;
    View rootv ;
    Thread timer;
    LinearLayout fab ;
    int count = 0, newuser=1;
    boolean nowCanI = false;
    String mesgStr="Cannot connect to Cultura 17 network";
    GifImageView cul, loading ;
    Button cont;
    Boolean verset=false;
    Boolean dataok=false;
    int duration=3000, duration1;
    int versionNo=-1, currentVersionOnDevice=0;
    boolean itsTheFirstTime=false;

    private FirebaseAnalytics mFirebaseAnalytics;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        cul = (GifImageView) findViewById(R.id.gif);
        loading = (GifImageView) findViewById(R.id.gifLoading);
        loading.setVisibility(View.GONE);

        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.gifnew);
            duration = gifDrawable.getDuration();
            gifDrawable.setLoopCount(1);
            cul.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.loadingc);
            //duration1 = gifDrawable.getDuration();
            //gifDrawable.setLoopCount(1);
            loading.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fab = (LinearLayout) findViewById(R.id.nonet);
        fab.setX(2000);
        cont = (Button) findViewById(R.id.con);

        ecList = new ArrayList<>();
        sponsorList = new ArrayList<>();
        eventList = new ArrayList<>();
        arList = new ArrayList<>();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle params = new Bundle();
        params.putString("name", "kushal");
        mFirebaseAnalytics.logEvent("Splash", params);

        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        currentVersionOnDevice = sp.getInt("version", 0);

        //Toast.makeText(getBaseContext(), "Dev Version "+currentVersionOnDevice, Toast.LENGTH_LONG).show();
        //Toast.makeText(getBaseContext(), "Ser Version "+versionNo, Toast.LENGTH_LONG).show();


        rootv = findViewById(android.R.id.content);

        new checkVersion().execute();
        //versionNo=-1;

        timer = new Thread(){
            public void run(){
                try {
                    sleep(duration);
                } catch (InterruptedException e) {
                }
                Splash.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                        }
                        finally{
                            if(checkconnection()==0 && currentVersionOnDevice!=0)
                            {   moveup(cul);
                                moveup(loading);
                                System.out.println("VERSION NUMBER!!!!!44444444"+versionNo);
                                System.out.println("CURRENT VERSION NUMBER!!!!!44444444"+currentVersionOnDevice);

                                cont.setVisibility(View.VISIBLE);
                            }
                            else if(checkconnection()==1){
                                System.out.println("Default VERSION NUMBER!!!!!"+versionNo);
                                System.out.println("CURRENT VERSION NUMBER!!!!!"+currentVersionOnDevice);
                                loading.setVisibility(View.VISIBLE);
                                //new checkVersion().execute();
                                //while (!verset);
                                //versionNo=sp.getInt("version",-5);
                                System.out.println("NEW VERSION NUMBER!!!!!"+versionNo);
                                System.out.println("NEW CURRENT VERSION NUMBER!!!!!"+currentVersionOnDevice);

                                if(currentVersionOnDevice!=versionNo) {
                                    System.out.println("VERSION NUMBER!!!!!11111111 "+versionNo);
                                    System.out.println("CURRENT VERSION NUMBER!!!!!111111111 "+currentVersionOnDevice);

                                    if(currentVersionOnDevice==0)
                                        itsTheFirstTime = true;


                                    CulturaDBHelper db = new CulturaDBHelper(getBaseContext());
                                   // db.checkExistingData();
                                    db.deleteAllTables();
                                    trimCache(getApplicationContext());
                                    // new checkVersion().execute();
                                    new getEverything().execute();
                                    /*Intent mainact = new Intent(Splash.this, MainActivity.class);
                                    startActivity(mainact);
                                    finish();*/
                                }
                                else{
                                    CulturaDBHelper db = new CulturaDBHelper(getBaseContext());
                                    if(!db.checkExistingData()){
                                        new getEverything().execute();
                                    }
                                    System.out.println("VERSION NUMBER!!!!!22222222"+versionNo);
                                    System.out.println("CURRENT VERSION NUMBER!!!!!2222222"+currentVersionOnDevice);

                                    rootv = findViewById(android.R.id.content);
                                    Intent mainact = new Intent(Splash.this, MainActivity.class);
                                    startActivity(mainact);
                                    finish();
                                }
                            }
                            else if (checkconnection()==0 && currentVersionOnDevice==0){
//                               CulturaDBHelper db = new CulturaDBHelper(getBaseContext());
//                                db.deleteAllTables();
                                moveup(cul);
                                System.out.println("VERSION NUMBER!!!!!3333333"+versionNo);
                                System.out.println("CURRENT VERSION NUMBER!!!!!33333333"+currentVersionOnDevice);

                            }
                        }
                    }
                });super.run();
            }
        };
        timer.start();

    }

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    //

    public void retry(View v)
    {
        if(checkconnection()==1) {
            loading.setVisibility(View.VISIBLE);
            if(currentVersionOnDevice==0)
                itsTheFirstTime = true;
            new checkVersion().execute();
            if (currentVersionOnDevice != versionNo) {
                CulturaDBHelper db = new CulturaDBHelper(getBaseContext());
                db.deleteAllTables();
                new getEverything().execute();
                /*Intent mainact = new Intent(Splash.this, MainActivity.class);
                startActivity(mainact);
                finish();*/
            } else {
                Intent mainact = new Intent(Splash.this, MainActivity.class);
                startActivity(mainact);
                finish();
            }
        }
        /*if (checkconnection() == 0 && currentVersionOnDevice!=versionNo && currentVersionOnDevice>0) {
            Toast.makeText(getBaseContext(), "Connect to internet and open app again to get latest updates on events", Toast.LENGTH_LONG).show();
            Intent mainact = new Intent(Splash.this, MainActivity.class);
            startActivity(mainact);
            finish();
        }
        else if(checkconnection()==0 && currentVersionOnDevice==0) {
            Toast.makeText(getBaseContext(), "Its the first time you are using Cultura17 App ! Connect to internet ", Toast.LENGTH_LONG).show();
        }
        else if(currentVersionOnDevice < versionNo)
        {
            new getEverything().execute();
        }
        else if(checkconnection()==1 && currentVersionOnDevice==versionNo)
        {
            Intent mainact = new Intent(Splash.this, MainActivity.class);
            startActivity(mainact);
            finish();
        }*/
    }

    public void chalo(View v) {
        Intent mainact = new Intent(Splash.this, MainActivity.class);
        startActivity(mainact);
        finish();
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

    private void moveup(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationY", 0,-400);
        anim.setDuration(500);
        anim.setTarget(view);
        anim.start();
        fab.setX(0);
        setFadeAnimation(fab);
    }

    public int checkconnection() {

        ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        if(!((activeNetworkInfo!=null) && (activeNetworkInfo.isConnected())))
        {
            switch(count)
            {
                case 0 : mesgStr = "\t\tCannot connect to Cultura'17 network";
                    break;
                case 1 : mesgStr = "\t\tYou seem to be in some other world";
                    break;
                case 2 : mesgStr = "\t\tTry to get   H O T S P O T   from your friend";
                    break;
                default : mesgStr = "\t\tCannot connect to Cultura'17 network";
                    break;
            }
            if(count>0)
            Snackbar.make(rootv, mesgStr, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            count++;
            return 0;
        }
        return 1;
    }

    List<EC> ecList;
    List<Sponsors> sponsorList;
    List<Events> eventList;
    List<AR> arList;
    int noOfSpon;

    private class getEverything extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        private String url = "http://203.201.63.39/html/cultura17/getEverything";
        //private String url = "http://cultura17.000webhostapp.com/culturaserver/v1/getEverything";
        String jsonStr;
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    nowCanI = true;
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray ecs = jsonObj.getJSONArray("ecs");
                    JSONArray events = jsonObj.getJSONArray("events");
                    JSONArray sponsors = jsonObj.getJSONArray("sponsors");
                    JSONArray arObjects = jsonObj.getJSONArray("cords");
                    JSONArray sponCount = jsonObj.getJSONArray("noofspon");

                    for (int i = 0; i < ecs.length(); i++) {
                        JSONObject c = ecs.getJSONObject(i);

                        final EC ecObj = new EC();

                        ecObj.setEno(c.getInt("eno"));
                        ecObj.setEcname(c.getString("name"));
                        ecObj.setEcmail(c.getString("email"));
                        ecObj.setEcphno(c.getString("phno"));

                        ecList.add(ecObj);
                    }

                    for (int i = 0; i < events.length(); i++) {
                        JSONObject c = events.getJSONObject(i);

                        final Events eventObj = new Events();

                        eventObj.setEno(c.getInt("eno"));
                        eventObj.setCno(c.getInt("cid"));
                        eventObj.setEname(c.getString("ename"));
                        eventObj.setCname(c.getString("cname"));
                        eventObj.setEdesc(c.getString("descrip"));
                        eventObj.setErules(c.getString("rules"));
                        eventObj.setErate(c.getString("rate"));
                        eventObj.setPrize1(c.getString("prize1"));
                        eventObj.setPrize2(c.getString("prize2"));
                        eventObj.setPrize3(c.getString("prize3"));
                        eventObj.setVenue(c.getString("venue"));
                        eventObj.setDay(c.getString("date"));
                        eventObj.setTime(c.getString("time"));
                        eventObj.setPicurl(c.getString("pic"));

                        eventList.add(eventObj);
                    }

                    for (int i = 0; i < sponsors.length(); i++) {
                        JSONObject c = sponsors.getJSONObject(i);

                        final Sponsors sponsorObj = new Sponsors();

                        sponsorObj.setSno(c.getInt("sid"));
                        sponsorObj.setSname(c.getString("sname"));
                        sponsorObj.setType(c.getString("type"));
                        sponsorObj.setPicurl(c.getString("pic"));
                        sponsorList.add(sponsorObj);
                    }

                    for (int i = 0; i < arObjects.length(); i++) {
                        JSONObject c = arObjects.getJSONObject(i);

                        final AR arobj = new AR();

                        arobj.setOid(c.getInt("ono"));
                        arobj.setOname(c.getString("oname"));
                        arobj.setOlat(c.getString("latd"));
                        arobj.setOlon(c.getString("longd"));
                        arobj.setOalt(c.getString("altd"));
                        arList.add(arobj);
                    }

                    for (int i = 0; i < sponCount.length(); i++) {

                        JSONObject c = sponCount.getJSONObject(i);

                        noOfSpon = c.getInt("number");
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("sponCount", noOfSpon);
                        editor.commit();
                    }


                } catch (final JSONException e) {
                    nowCanI = false;
                    System.out.print(e.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Server is being updated, please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                nowCanI = false;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*Toast.makeText(getApplicationContext(),
                                "Could not connect to server\n Please check your internet connection",
                                Toast.LENGTH_LONG)
                                .show();*/
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

             if(nowCanI==true) {
                 SharedPreferences spp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                 /*Toast.makeText(getApplicationContext(),
                         "Events and schedules updated successfully" + spp.getInt("sponCount", -1),
                         Toast.LENGTH_LONG)
                         .show();*/
                 CulturaDBHelper db = new CulturaDBHelper(getBaseContext());

                 db.insertAllEvents(eventList);
                 db.insertAllEC(ecList);
                 db.insertAllSponsors(sponsorList);
                 db.insertAllARObjects(arList);

                 if(itsTheFirstTime==true) {
                     Intent i = new Intent(Splash.this, IntroActivity.class);
                     startActivity(i);
                     finish();
                 }
                 else {
                     Intent mainact = new Intent(Splash.this, MainActivity.class);
                     startActivity(mainact);
                     finish();
                 }
             }
                }
            });
            ///ed.putInt("newuser", 0);
        }

    }
//    public int versioncheck() {
//        int ver;
//        ver = -3;
//        String url = "http://203.201.63.39/html/cultura17/checkVersion";
//        //private String url = "http://203.201.63.39/html/cultura17/checkVersion";
//
//        String jsonStr;
//
//
//        HttpHandler sh = new HttpHandler();
//        jsonStr = sh.makeServiceCall(url);
//
//        if (jsonStr != null) {
//            try {
//                JSONObject jsonObj = new JSONObject(jsonStr);
//
//                JSONArray ecs = jsonObj.getJSONArray("version");
//
//                for (int i = 0; i < 1; i++) {
//                    JSONObject c = ecs.getJSONObject(i);
//                    System.out.println("INSIDE CHECK VERSION FUNCTION!!" + c.getInt("number"));
//
//                    ver = c.getInt("number");
//
//                }
//
//            } catch (final JSONException e) {
//                ver=-4;
//            }
//        }
//
//            return ver;
//
//    }

    private class checkVersion extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        private String url = "http://203.201.63.39/html/cultura17/checkVersion";
        //private String url = "http://203.201.63.39/html/cultura17/checkVersion";

        String jsonStr;
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray ecs = jsonObj.getJSONArray("version");

                    for (int i = 0; i < 1; i++) {
                        JSONObject c = ecs.getJSONObject(i);
                        System.out.println("INSIDE CHECK VERSION!!"+c.getInt("number"));

                        versionNo = c.getInt("number");
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("version", versionNo);
                        editor.commit();
                        verset=true;
                    }

                } catch (final JSONException e) {
                    nowCanI = false;
                    System.out.print(e.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            /*Toast.makeText(getApplicationContext(),
                                    "Server is being updated, please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();*/
                        }
                    });

                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*Toast.makeText(getApplicationContext(),
                                "Could not connect to server\n Please check your internet connection",
                                Toast.LENGTH_LONG)
                                .show();*/
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            verset=true;
            super.onPostExecute(result);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                /*Toast.makeText(getApplicationContext(),
                        "Version" + versionNo,
                        Toast.LENGTH_LONG)
                        .show();*/

                }
            });
            //
            ///ed.putInt("newuser", 0);
        }

    }
}
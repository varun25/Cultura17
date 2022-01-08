package com.cmrit.cultura17;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.beyondar.android.fragment.BeyondarFragmentSupport;
import com.beyondar.android.util.location.BeyondarLocationManager;
import com.beyondar.android.world.GeoObject;
import com.beyondar.android.world.World;
import com.cmrit.cultura17.DBModel.AR;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;


public class ARActivity extends FragmentActivity {

    private static final int MY_REQUEST_CODE =200;
    BeyondarFragmentSupport mBeyondarFragment;
    World world;
    ProgressDialog pdialog;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_ar);

//        pdialog = new ProgressDialog(this);
//        pdialog.setMessage("Finding your location. Please wait!");
//        pdialog.show();
//        pdialog.setCancelable(false);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle params = new Bundle();
        params.putString("name", "xyz");
        mFirebaseAnalytics.logEvent("AR", params);

        mBeyondarFragment = (BeyondarFragmentSupport) getSupportFragmentManager().findFragmentById(R.id.beyondarFragment);
        //mBeyondarFragment.setPullCloserDistance(20);
        mBeyondarFragment.setPushAwayDistance(10);
        mBeyondarFragment.setMaxDistanceToRender(1000);
        mBeyondarFragment.setDistanceFactor(10);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        world = new World(this);

        world.setDefaultImage(R.drawable.obj);

        GeoObject go1 = new GeoObject();
        go1.setGeoPosition(12.966717019444445, 77.7115859);
        go1.setImageResource(R.drawable.main);
        world.addBeyondarObject(go1);

        GeoObject go2 = new GeoObject();
        go2.setGeoPosition(12.966289738888888, 77.71199621111111);
        go2.setImageResource(R.drawable.side);
        world.addBeyondarObject(go2);

        GeoObject go3 = new GeoObject();
        go3.setGeoPosition(12.966289738888888, 77.71199621111111, 12);
        go3.setImageResource(R.drawable.dhwani);
        world.addBeyondarObject(go3);

        GeoObject go4 = new GeoObject();
        go4.setGeoPosition(12.966717019444445, 77.7115859, 12);
        go4.setImageResource(R.drawable.canteen);
        world.addBeyondarObject(go4);

        GeoObject go5 = new GeoObject();
        go5.setGeoPosition(12.96651255, 77.71092863055556, 18);
        go5.setImageResource(R.drawable.mechhall);
        world.addBeyondarObject(go5);

        GeoObject go6 = new GeoObject();
        go6.setGeoPosition(12.966963580555555, 77.71145870000001);
        go6.setImageResource(R.drawable.bbcourt);
        world.addBeyondarObject(go6);

        GeoObject go7 = new GeoObject();
        go7.setGeoPosition(12.966192869444443, 77.7117483388889, 35);
        go7.setImageResource(R.drawable.cslabs);
        world.addBeyondarObject(go7);

        GeoObject go8 = new GeoObject();
        go8.setGeoPosition(12.96651255, 77.71092863055556, 30);
        go8.setImageResource(R.drawable.civilhall);
        world.addBeyondarObject(go8);


        GeoObject char1 = new GeoObject();
        char1.setGeoPosition(12.96680158055555, 77.71119665,-10);
        char1.setImageResource(R.drawable.obj1);//
        world.addBeyondarObject(char1);


        GeoObject char2 = new GeoObject();
        char2.setGeoPosition(12.967098388888889, 77.71159838055556,-10);
        char2.setImageResource(R.drawable.obj2);//
        world.addBeyondarObject(char2);


        GeoObject char3 = new GeoObject();
        char3.setGeoPosition(12.96701555, 77.71182615000001,-10);
        char3.setImageResource(R.drawable.obj3);//
        world.addBeyondarObject(char3);


        GeoObject char4 = new GeoObject();
        char4.setGeoPosition(12.96709203888889, 77.71186748888888,-10);
        char4.setImageResource(R.drawable.obj4);//
        world.addBeyondarObject(char4);


        GeoObject char5 = new GeoObject();
        char5.setGeoPosition(12.966774930555555, 77.71215086111111,-10);
        char5.setImageResource(R.drawable.obj5);//
        world.addBeyondarObject(char5);


        GeoObject char6 = new GeoObject();
        char6.setGeoPosition(12.966351849999999, 77.71217598888889,-10);
        char6.setImageResource(R.drawable.obj14);
        world.addBeyondarObject(char6);

        GeoObject char7 = new GeoObject();
        char7.setGeoPosition(12.966015188888889, 77.711921580555555,-10);
        char7.setImageResource(R.drawable.obj15);
        world.addBeyondarObject(char7);

        GeoObject char8 = new GeoObject();
        char8.setGeoPosition(12.9657954, 77.71176232777778,-10);
        char8.setImageResource(R.drawable.obj16);
        world.addBeyondarObject(char8);

        GeoObject char9 = new GeoObject();
        char9.setGeoPosition(12.966072980555555, 77.71163903055556,-10);
        char9.setImageResource(R.drawable.obj9);
        world.addBeyondarObject(char9);

        GeoObject char10 = new GeoObject();
        char10.setGeoPosition(12.966403788888888, 77.7115280611111111,-10);
        char10.setImageResource(R.drawable.obj10);
        world.addBeyondarObject(char10);

        GeoObject char11 = new GeoObject();
        char11.setGeoPosition(12.966267788888889, 77.71168353055556,-10);
        char11.setImageResource(R.drawable.obj11);
        world.addBeyondarObject(char11);

        GeoObject char12 = new GeoObject();
        char12.setGeoPosition(12.966285230555554, 77.7120968888889,-10);
        char12.setImageResource(R.drawable.obj12);
        world.addBeyondarObject(char12);

        GeoObject char13 = new GeoObject();
        char13.setGeoPosition(12.967215519444444, 77.71208108055556,-10);
        char13.setImageResource(R.drawable.obj13);//
        world.addBeyondarObject(char13);
        //Change Image
        GeoObject char14 = new GeoObject();
        char14.setGeoPosition(12.9671803805555556, 77.7126986,-10);
        char14.setImageResource(R.drawable.obj17);//
        world.addBeyondarObject(char14);
        //Change Image
        GeoObject char15 = new GeoObject();
        char15.setGeoPosition(12.967528930555556, 77.71399321111118,-10);
        char15.setImageResource(R.drawable.obj18);//
        world.addBeyondarObject(char15);


        CulturaDBHelper db = new CulturaDBHelper(this);
        List<AR> data = new ArrayList<>();
        data = db.getAllARObjects();

        GeoObject[] go = new GeoObject[data.size()];

        for(int i=0; i<data.size(); i++) {
                Log.d("Added OBJ" + i, data.get(i).getOname());
                String uri = "@drawable/obj"+data.get(i).getOid();
                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                go[i] = new GeoObject();
                go[i].setGeoPosition(Double.parseDouble(data.get(i).getOlat()),Double.parseDouble(data.get(i).getOlon()), Double.parseDouble(data.get(i).getOalt()));
                go[i].setImageResource(imageResource);
                world.addBeyondarObject(go[i]);
        }

        mBeyondarFragment.setWorld(world);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            final AlertDialog.Builder dialog = new AlertDialog.Builder(ARActivity.this);
            dialog.setTitle("Enable Location")
                    .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                            "use this app")
                    .setCancelable(false)
                    .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                            paramDialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        }
                    });
            dialog.show();
        }

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
//                pdialog.dismiss();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, locationListener);

        BeyondarLocationManager.setLocationManager(locationManager);

        BeyondarLocationManager.addWorldLocationUpdate(world);
    }


    @Override
    protected void onPause() {
        super.onPause();
        BeyondarLocationManager.disable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BeyondarLocationManager.enable();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if(pdialog!=null) {
//            pdialog.dismiss();
//            pdialog = null;
//        }
//    }


    String currentDateTimeString;
    FloatingActionButton lastPicButton;

    /*public void capture(View view)
    {   final MediaPlayer mp = MediaPlayer.create(this, R.raw.cameraclic);
        lastPicButton = (FloatingActionButton) findViewById(R.id.lastbutton);
        lastPicButton.show();
        Vibrator v = (Vibrator) this.getBaseContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(200);
        mBeyondarFragment.takeScreenshot(new OnScreenshotListener() {
            @Override
            public void onScreenshot(Bitmap screenshot) {

                mp.start();
                FileOutputStream out = null;
                try {
                    final File newFile = new File("/sdcard/Cultura17/AR pics");
                    if(!newFile.exists()) newFile.mkdirs();

                    currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                    out = new FileOutputStream("/sdcard/Cultura17/AR pics/IMG_" + currentDateTimeString + ".png");
                    screenshot.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                    // PNG is a lossless format, the compression factor (100) is ignored
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Toast.makeText(getBaseContext(), "Pic saved in Cultura17 folder", Toast.LENGTH_LONG).show();
    }*/

    boolean galleryon = false;
    ImageView lastclick;

//    public void viewLast(View view)
//    {
//        lastclick = (ImageView) findViewById(R.id.lastpic);
//        lastclick.setVisibility(View.VISIBLE);
//        galleryon = true;
//        Glide.with(this).load(new File("/sdcard/Cultura/AR pics/IMG_" + currentDateTimeString + ".png")).into(lastclick);
//    }

    public void dismissTutorial(View view)
    {
        LinearLayout ii = (LinearLayout) findViewById(R.id.tuto);
        ii.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if(galleryon) {
            lastclick.setVisibility(View.GONE);
            galleryon=false;
        }
        else
            finish();
            //super.onBackPressed();
    }
}

package com.cmrit.cultura17;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import static java.lang.Thread.sleep;


public class MainActivity extends Activity implements View.OnTouchListener {

    private static final int REQUEST_CODE =200;
    private boolean LOC = false, CAM = false;

    private Bitmap getBitmap(int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }





    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }





    ourview v;
    float x,y,w=0,c1=65,effect=100;
    float width,height;
    float incri;
    float c1x,c1y,c2x,c2y,c5x,c5y,c4x,c4y;
    float e1x,e1y,e2x,e2y,e3x,e3y,e4x,e4y,e8x,e8y,e7x,e7y,e9x,e9y,e10x,e10y;



    float cultura_width,event_width;

    float cxpos,cypos,e1xpos,e1ypos,diff;
    float cxpos1,cypos1,e1xpos1,e1ypos1;


    float cxpos2,cypos2,e1xpos2,e1ypos2;
    float cxpos3,cypos3,e1xpos3,e1ypos3;
    float cxpos4,cypos4,e1xpos4,e1ypos4;
    float cxpos7,cypos7,e1xpos7,e1ypos7;

    Rect bounds = new Rect();


    float startx=0,starty=0,endx=0,endy=0;
    float sum=0,sum1=0;
    float xpos=0,ypos=0;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();
    float tx=0,ty=0;
    GifImageView loading;
    float xx,yy;
    int fad=0;



    String options[]={"Event Details","Sponsors","Map","Schedule","About"};
    String check="AWESOME",spon="sponsors!";
    float right,left,top,bottom;
    boolean start=true,button=false,chk=false,chk1=false,runing=true,calling=true;
    boolean flag=true,arb=false,cam_per=false,loc_per=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v= new ourview(this);
        v.setOnTouchListener(this);
//        progressBar = new ProgressDialog(this);
//        progressBar.setCancelable(true);
//        progressBar.setMessage("Loading Infinite Grid");
//        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressBar.show();
//        progressBarStatus = 0;






        v = new MainActivity.ourview(this);
        v.setOnTouchListener(this);





        setContentView(R.layout.activity_main);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.vMain);
        layout.addView(v);
        loading = (GifImageView) findViewById(R.id.gifLoading1);

        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.loadingc);
            //duration1 = gifDrawable.getDuration();
            //gifDrawable.setLoopCount(1);
            loading.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //    q = BitmapFactory.decodeResource(getResources(), R.drawable.qbus);
//        x=y=0;
//        setContentView(v);
    }

    @Override
    protected void onPause() {
        super.onPause();
        v.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        v.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent me) {


        x=me.getX();
        y=me.getY();
        fad=100;
        loading.setVisibility(View.VISIBLE);




        if (y < height/3- Math.abs(x-(width/2))) {


            Intent myIntent = new Intent(MainActivity.this, Sponsor.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
            calling = false;
            return false;

        }
        else if(y>height*2/3+Math.abs(x-width/2)){
            Intent myIntent = new Intent(MainActivity.this, CategorySelect.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
            calling = false;
            return false;

        }
        else if(y<height/2&&x<width*9/20-Math.abs(y-height/4)){
            Intent myIntent = new Intent(MainActivity.this, ScheduleActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
            calling = false;
            return false;
        }
        else if(y>height/2&&x<width*9/20-Math.abs(y-height*3/4)){

            arb=true;
            loc_per=true;
            calling=false;
            return false;
            //myIntent.putExtra("key", value); //Optional parameters


        }
        else if(y<height/2&&x>(width-width*9/20)+Math.abs(y-height/4)){
            Intent myIntent = new Intent(MainActivity.this, VrListActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
            calling = false;
            return false;
        }
        else if(y>height/2&&x>(width-width*9/20)+Math.abs(y-height*3/4)){
            Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
            calling = false;
            return false;
        }
        else{
            Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
            calling = false;
            return false;
        }

     //   return true;
    }

    public class ourview extends SurfaceView implements Runnable{
        Thread t=null;
        SurfaceHolder holder;
        boolean isitok = false;

        Bitmap ar=BitmapFactory.decodeResource(getResources(), R.drawable.ar);
        Bitmap vr= BitmapFactory.decodeResource(getResources(), R.drawable.vr);
        Bitmap events=BitmapFactory.decodeResource(getResources(), R.drawable.events);
        Bitmap loc = BitmapFactory.decodeResource(getResources(), R.drawable.loc);
        Bitmap spon=BitmapFactory.decodeResource(getResources(), R.drawable.sp);
        Bitmap schu=BitmapFactory.decodeResource(getResources(), R.drawable.sch);
        Bitmap backg=BitmapFactory.decodeResource(getResources(), R.drawable.splashbg);
        Bitmap logo=BitmapFactory.decodeResource(getResources(), R.drawable.cultura);
        Bitmap about=BitmapFactory.decodeResource(getResources(), R.drawable.about);
        String countd,text="days     hrs      min     sec";



        public ourview(Context context) {
            super(context);
            holder=getHolder();
        }

        @Override
        public void run() {
            while(isitok==true){
                if(!holder.getSurface().isValid()){
                    continue;
                }

                Canvas c =holder.lockCanvas();

                if(arb){
                    checkPermission();
                /*    loc_per=true;
                    if(loc_per) {

                        loc_per=false;
                    }
                    if(cam_per) {
                        checkCameraPermission();
                        cam_per=false;
                        arb=false;
                    }
                        if(LOC==true && CAM==true) {
                        Intent myIntent = new Intent(MainActivity.this, ARActivity.class);
                        MainActivity.this.startActivity(myIntent);


                        //myIntent.putExtra("key", value); //Optional parameters
                    }
                    else {
//                        Toast.makeText(getBaseContext(), "Permissions not granted!", Toast.LENGTH_LONG).show();

                    }*/
                    arb=false;

                }


                if(flag){
                    ar=getResizedBitmap(ar,c.getWidth()*9/20,c.getHeight()/2);
                    schu=getResizedBitmap(schu,c.getWidth()*9/20,c.getHeight()/2);
                    spon=getResizedBitmap(spon,c.getWidth(),c.getHeight()*2/7);
                    loc=getResizedBitmap(loc,c.getWidth()*9/20,c.getHeight()/2);
                    vr=getResizedBitmap(vr,c.getWidth()*9/20,c.getHeight()/2);
                    events=getResizedBitmap(events,c.getWidth(),c.getHeight()*2/7);
                    backg=getResizedBitmap(backg,c.getWidth(),c.getHeight());
                    logo=getResizedBitmap(logo,c.getWidth()*6/10,c.getWidth()/5);
                    about=getResizedBitmap(about,c.getWidth()*4/15,c.getWidth()/12);
                    
                    width=c.getWidth();
                    height=c.getHeight();
                    flag=false;
                    top=c.getHeight()*2/7;
                    right=c.getWidth()*9/20;
                    incri=c.getHeight()/23;
                }
                if(top>0){
                    if(top>incri)
                      top-=incri;
                    else
                        top=0;
                }
                if (right > 0) {
                    if(right>incri)
                        right-=incri;
                    else
                        right=0;
                }

                c.drawBitmap(backg,0,0,null);
                c.drawBitmap(schu,0-right,0,null);
                c.drawBitmap(vr,c.getWidth()-vr.getWidth()+right,0,null);

                c.drawBitmap(spon,0,0-top,null);
                c.drawBitmap(ar,0-right,c.getHeight()/2,null);
                c.drawBitmap(loc,c.getWidth()-loc.getWidth()+right,c.getHeight()/2,null);
                c.drawBitmap(events,0,c.getHeight()-events.getHeight()+top,null);
                c.drawBitmap(logo,c.getWidth()/2-logo.getWidth()/2,c.getHeight()/2-logo.getHeight()/2-c.getWidth()/10,null);
                c.drawBitmap(about,c.getWidth()/2-about.getWidth()/2,c.getHeight()/2-about.getHeight()/2-c.getWidth()/4,null);



                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date futureDate = null;
                Date stopDate=null;
                try {
                    futureDate = dateFormat.parse("2017-03-03 09:00:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    stopDate = dateFormat.parse("2017-03-05 01:00:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Date currentDate = new Date();
                if (!currentDate.after(futureDate)) {
                    long diff = futureDate.getTime() - currentDate.getTime();
                    long days = diff / (24 * 60 * 60 * 1000);
                    diff -= days * (24 * 60 * 60 * 1000);
                    long hours = diff / (60 * 60 * 1000);
                    diff -= hours * (60 * 60 * 1000);
                    long minutes = diff / (60 * 1000);
                    diff -= minutes * (60 * 1000);
                    long seconds = diff / 1000;
                    countd=String.format("%02d", days)+" : "+String.format("%02d", hours)+" : "+String.format("%02d", minutes)+" : "+String.format("%02d", seconds);
                }
                else if(currentDate.before(stopDate)){
                    countd="Cultura is Live!!";
                    text="";

                }
                else{
                    countd="See You Next Year!!";
                    text="";
                }
                Paint fade=new Paint();
                fade.setColor(Color.argb(fad,153,153,153));
                Paint text1 = new Paint();
                text1.setColor(Color.WHITE);
                text1.setTypeface(Typeface.create("Arial",Typeface.BOLD));
                text1.setTextSize(c.getWidth()/14);



                Paint text2 = new Paint();
                text2.setColor(Color.WHITE);
                text2.setTypeface(Typeface.create("Arial",Typeface.BOLD));
                text2.setTextSize(c.getWidth()/22);

                Rect bounds = new Rect();
                text1.getTextBounds(countd, 0, countd.length(), bounds);
                float xx = bounds.width();
                float yy = bounds.height();
                //  countd="cultura jhdv";
                //   c.drawText("Rajat",0,0,text1);


                c.drawText(countd, c.getWidth()/2-xx/2,c.getHeight()/2-yy/2+c.getWidth()/10  , text1);
                c.drawText(text,c.getWidth()/2-xx/2,c.getHeight()/2+yy/2+c.getWidth()/10,text2);
                c.drawRect(0,0,c.getWidth(),c.getHeight(),fade);





                holder.unlockCanvasAndPost(c);


                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void pause(){
            isitok=false;
            loading.setVisibility(View.GONE);
            fad=0;
            while(true){
                try{
                    t.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            t=null;
        }
        public void resume(){
            isitok=true;
            loading.setVisibility(View.GONE);
         //   loading.setVisibility(View.VISIBLE);
            LOC=false;
            CAM=false;
            arb=false;
            calling=true;
            flag=true;
            t=new Thread(this);
            t.start();
        }

    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            }
            else {
                Intent myIntent = new Intent(MainActivity.this, ARActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        }
        else {
            Intent myIntent = new Intent(MainActivity.this, ARActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent myIntent = new Intent(MainActivity.this, ARActivity.class);
                MainActivity.this.startActivity(myIntent);

            }
            else if (grantResults[0] == PackageManager.PERMISSION_DENIED){
//                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.CAMERA)) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setMessage("This permission is important for AR!")
//                            .setTitle("CAMERA permission required.");
//                    builder.setCancelable(false);
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
//                        }
//                    });
//                    //ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                }
            }
        }
}
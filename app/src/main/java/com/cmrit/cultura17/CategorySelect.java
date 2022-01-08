package com.cmrit.cultura17;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


/**
 * Created by rajat on 15/12/16.
 */



public class CategorySelect extends Activity implements View.OnTouchListener {



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
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


    ourview v;
    float uy, ly, x1, x2, x3, x4, x5,x6,x7, by, lb, rb, width,ty=0,dx,dy,dyy=0,dxx=0,dyy1=0,leftlock=0,rightlock=0,fast=0;
    float q,w,e,r,height1;
    float rhomwidth;
    float tx1,ty1,temp;
    String category[] = {"Art","Dance","Gaming","Informal","Kannada","Literary","Music","Tech","Theatre"};
    String desc[] = {"Art is\nan expression of\nyourself. It is the embodiment\nof one's thoughts and beliefs. Embrace\nyour artsy self at Cultura'17!\n",
            "To move one's\nfeet or body, or both\nrhythmically in a pattern of steps,\nespecially to the accompaniment\nof music.\n" ,
            "Gaming has\nalways been a part\nof us. Except now, it has become\nadvanced. In the form of virtual warfare,\n one man teams and strategic battles.\nWhat more could you ask for?\n",
            "We've covered\ntheatre, art, literature and\nmusic, but at Cultura'17 we want\nyou to show us your're an all-rounder.\nThe category which rounds\nup the festival!\n ",
            "We live in\na state of rich culture\nand history. Bask in the glory of\nthe mother-tongue of Karnataka and\nits soul with events which bring\nout the Kannadiga in you.\n",
            "Literature\nis the most agreeable\nway of ignoring life. After all,\ntomorrow is another day!\n",
            "Melodic, harmonic,\naural extravagance, music\nappeals to everyone in its own right.\nEveryone relates to it. Show\nus your appeal to music!\n",
            "The modern\nworld of ours is full of\ninnovation in all spheres of life.\nBut the most observed innovations\nare in technology. Technically\nadept, are you?\n",
            "Theatre,\nthe age old home of\nhuman expression. It's a\ngift to the senses to see captivating\nperformances. Enthrall, entertain\nthe masses at Cultura'17.\n"};
    float x, y,startx,starty,endx,endy;
    boolean flag = false,button=true,mbutton=true,init=true,backb=false,start=true;
    int a, i = 0,incri=0;
    int n=9;
    int fad=0;
   // Bitmap[] bgr = new Bitmap[9];

   // Bitmap bgr[];
    float div=550;
    long slep=1;

    //Anlaytics Variable
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Create an ad.
//        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-2711001403231241/7939517011");
//
//        // Add the AdView to the view hierarchy. The view will have no size
//        // until the ad is loaded.
//        RelativeLayout layout = new RelativeLayout(this);
//        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,    ViewGroup.LayoutParams.MATCH_PARENT));
//        // Create an ad request.
//        // get test ads on a physical device.
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("3B63DB252D668B697B13265A0B8BC7F1") //Kushal
//                .addTestDevice("E40DE10449D59E5736D76AEB8E2E6EBB")  //Varun
//                .addTestDevice("845E8BC8E822CC9E5E2AEA00B17F503D") //Ron
//                .addTestDevice("DF5A856DA927FFD56F5CB5145D9029E7") //Rajat
//                .addTestDevice("02F453A2A41EB4425A063D876784857F") //Nihal
//                .build();
//
//        // Start loading the ad in the background.
//        adView.loadAd(adRequest);
//
//        //Optional - Request full screen
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        //Create canvas view
//        View myView = new ourview(this);
//        myView.setOnTouchListener(this);
//        x=y=0;
//        flag=false;
//
//        //define ad view parameters
//        RelativeLayout.LayoutParams adParams =
//                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                        RelativeLayout.LayoutParams.WRAP_CONTENT);
//        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//
//        layout.addView(myView);
//        layout.addView(adView, adParams);
//
//        //Set main renderer
//        setContentView(layout);

        v = new ourview(this);
        v.setOnTouchListener(this);
        x = y = 0;
        flag = false;



        setContentView(v);


        //Analytics Part!!
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle params = new Bundle();
        params.putString("name", "kushal");
        mFirebaseAnalytics.logEvent("Category", params);




//        v = new ourview(this);
//        TextView dynamicTextView = new TextView(this);
//        dynamicTextView.setText(" Hello World ");
//        v.setOnTouchListener(this);
//        x = y = 0;
//        flag = false;
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
        x = me.getX();
        y = me.getY();
     //   mbutton=true;

if(start){
  //  mbutton=false;
    button=false;
    start=false;
}


        switch (me.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x=startx=me.getX();
                y=starty=me.getY();

                break;

            case MotionEvent.ACTION_UP:
                endx=me.getX();//
                endy=me.getY();
                temp=startx-endx;
                if(Math.abs(temp)>width/100){
                    mbutton=false;
                    button=false;}
                else{
                    mbutton=true;
                    button=true;}

                break;
            case MotionEvent.ACTION_MOVE:
                tx1=me.getX()-startx;
                ty1=me.getY()-starty;

                break;
        }
        if(mbutton){


           // if(startx>height1/80&&startx<height1/80+height1/80&&starty<height1/20){
             if(startx<width/4&&starty<width/4){
               finish();
                return false;

            }

            //This is executed when left button is pressed
            //  if ((x < lb + (width)) && y > by + x && y < by + width - x) {
            if ((x < (lb+width/5) + (width)) && x>(lb+width/5)&&y > by + Math.abs((lb+width/5)+width/2-x) && y < by + width- Math.abs(((lb+width/5)+width/2-x))) {

                float m, rty1, rtx1, m1, rtx2, rty2, tx, tu, tl, tx1, tx2, tx3, tx4, tx5,inc;
                inc=(x3-x2)/div;
                //  inc=1;
                m = (uy - ly) / (x3 - x2);
                m1 = (ly - uy) / (x2 - x1);
                tx = x2;
                //  tx1 = x2;
                tu = uy;
                tl = ly;
                rty1 = ly + width / 2;
                rtx1 = x2 + width / 2;
                rtx2 = x1 + width / 2;
                rty2 = uy + width / 2;
                leftlock=width*2;
                while ((ly > tu - 1) && (uy < tl - 1) && (x3 > tx)) {
                    ly = rty1 - m * (x2 + (width / 2) - rtx1) - width / 2;
                    uy = rty2 - m1 * (x1 + (width / 2) - rtx2) - width / 2;
                    x1-=inc;
                    x2-=inc;
                    x3-=inc;
                    x4-=inc;
                    x5-=inc;
                    x6-=inc;
                    x7-=inc;
                    fad+=0;
                    dxx+=inc;
                    fast-=inc*3;

                    dyy1=dxx;
                    dyy=-dxx;
                    try {
                        sleep(slep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                incri=1;
               // i = (i + 1) % n;
                flag = false;
                mbutton=true;

                return false;


            }
            //This is executed when right button is pressed
            else if ((x > (rb-width/5)) && y > by + width / 2 - (x - (rb-width/5)) && y < by + width / 2 + (x - (rb-width/5))) {//&&y<by+width-(x-(rb-width/5))){
                // else if(x>(rb-width/5)){
                float m, rty1, rtx1, m1, rtx2, rty2, tx, tu, tl, tx1, tx2, tx3, tx4, tx5,inc;
                inc=(x3-x2)/div;
                // inc=1;

                m = (uy - ly) / (x3 - x2);
                m1 = (ly - uy) / (x2 - x1);
                tx = x3;
                //   tx1 = x2;
                tu = uy;
                tl = ly;
                rty1 = ly + width / 2;
                rtx1 = x2 + width / 2;
                rtx2 = x1 + width / 2;
                rty2 = uy + width / 2;
                rightlock=width*2;
                while ((ly > tu - 1) && (uy < tl - 1) && (x2 < tx)) {
                    ly = rty1 + m * (x2 + (width / 2) - rtx1) - width / 2;
                    uy = rty2 + m1 * (x1 + (width / 2) - rtx2) - width / 2;
                    x1+=inc;
                    x2+=inc;
                    fad+=0;
                    x3+=inc;
                    x4+=inc;
                    x5+=inc;
                    x6+=inc;
                    x7+=inc;
                    dyy+=inc;
                    fast+=inc*3;
                    dxx=dyy;
                    try {
                        sleep(slep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
              //  i = (i + (n-1)) % n;
                incri=-1;

                flag = false;
                mbutton=true;

                return false;

            }
            else if ((x < x3 + (width)) && x>x3&&y > uy + Math.abs(x3+width/2-x)+width/20 && y < uy + width- Math.abs((x3+width/2-x))) {
                //////////////////////Code for Event
                if(button) {
                    button=false;
                    Intent myIntent = new Intent(CategorySelect.this, EventCardActivity.class);
                    /*
                    Pass The Bundle here


                    pass category[i+3] into the event card activity





                    mIntent = new Intent(v.getContext(), EventDetailActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("name", mData.get(position).getName());
                    mBundle.putInt("image", mData.get(position).getImage());
                    mIntent.putExtras(mBundle);
                    v.getContext().startActivity(mIntent);*/


                    Bundle mBundle=new Bundle();
                    mBundle.putInt("categorynumber",(i+3)%n);

                    myIntent.putExtras(mBundle);


              //      myIntent.putExtra("key",); //Optional parameters
                    startActivity(myIntent);
                    flag=false;
                    mbutton=true;
                    return false;


                }






                //////////////////////////////////////////////////////////////////////////////////////////////

            }
            else if (button&&(x < x2 + (width)) && x>x2&&y > ly + Math.abs(x2+width/2-x)+width/20 && y < ly + width- Math.abs((x2+width/2-x))) {
                float m, rty1, rtx1, m1, rtx2, rty2, tx, tu, tl, tx1, tx2, tx3, tx4, tx5,inc;
                inc=(x3-x2)/div;
                // inc=1;

                m = (uy - ly) / (x3 - x2);
                m1 = (ly - uy) / (x2 - x1);
                tx = x3;
                //   tx1 = x2;
                tu = uy;
                tl = ly;
                rty1 = ly + width / 2;
                rtx1 = x2 + width / 2;
                rtx2 = x1 + width / 2;
                rty2 = uy + width / 2;
                rightlock=width*2;
                while ((ly > tu - 1) && (uy < tl - 1) && (x2 < tx)) {
                    ly = rty1 + m * (x2 + (width / 2) - rtx1) - width / 2;
                    uy = rty2 + m1 * (x1 + (width / 2) - rtx2) - width / 2;
                    x1+=inc;
                    x2+=inc;
                    x3+=inc;
                    x4+=inc;
                    x5+=inc;
                    x6+=inc;
                    x7+=inc;
                    dyy+=inc;
                    fast+=inc*3;
                    dxx=dyy;
                    try {
                        sleep(slep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
             //   i = (i + (n-1)) % n;
                incri=-1;

                flag = false;
                mbutton=true;

                return false;



            }


            else if (button&&(x < x4 + (width)) && x>x4&&y > ly + Math.abs(x4+width/2-x)+width/20 && y < ly + width- Math.abs((x4+width/2-x))) {
                float m, rty1, rtx1, m1, rtx2, rty2, tx, tu, tl, tx1, tx2, tx3, tx4, tx5,inc;
                inc=(x3-x2)/div;
                //  inc=1;
                m = (uy - ly) / (x3 - x2);
                m1 = (ly - uy) / (x2 - x1);
                tx = x2;
                //  tx1 = x2;
                tu = uy;
                tl = ly;
                rty1 = ly + width / 2;
                rtx1 = x2 + width / 2;
                rtx2 = x1 + width / 2;
                rty2 = uy + width / 2;
                leftlock=width*2;
                while ((ly > tu - 1) && (uy < tl - 1) && (x3 > tx)) {
                    ly = rty1 - m * (x2 + (width / 2) - rtx1) - width / 2;
                    uy = rty2 - m1 * (x1 + (width / 2) - rtx2) - width / 2;
                    x1-=inc;
                    x2-=inc;
                    x3-=inc;
                    x4-=inc;
                    x5-=inc;
                    x6-=inc;
                    x7-=inc;
                    dxx+=inc;
                    fast-=inc*3;

                    dyy1=dxx;
                    dyy=-dxx;
                    try {
                        sleep(slep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                incri=1;
              //  i = (i + 1) % n;
                flag = false;
                mbutton=true;

                return false;

            }





        }

        else{
            if((startx-endx)<0){



                float m, rty1, rtx1, m1, rtx2, rty2, tx, tu, tl, tx1, tx2, tx3, tx4, tx5,inc;
                inc=(x3-x2)/div;
                //   inc=1;

                m = (uy - ly) / (x3 - x2);
                m1 = (ly - uy) / (x2 - x1);
                tx = x3;
                //     tx1 = x2;
                tu = uy;
                tl = ly;
                rty1 = ly + width / 2;
                rtx1 = x2 + width / 2;
                rtx2 = x1 + width / 2;
                rty2 = uy + width / 2;
                rightlock=width*2;
                while ((ly > tu - 1) && (uy < tl - 1) && (x2 < tx)) {
                    ly = rty1 + m * (x2 + (width / 2) - rtx1) - width / 2;
                    uy = rty2 + m1 * (x1 + (width / 2) - rtx2) - width / 2;
                    x1+=inc;
                    x2+=inc;
                    x3+=inc;
                    x4+=inc;
                    x5+=inc;
                    x6+=inc;
                    x7+=inc;
                    dyy+=inc;
                    fast+=inc*3;
                    dxx=dyy;
                    try {
                        sleep(slep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
             //   i = (i + (n-1)) % n;
                incri=-1;


                flag = false;
                mbutton=true;
            //    return false;
            }
            else{
                float m, rty1, rtx1, m1, rtx2, rty2, tx, tu, tl, tx1, tx2, tx3, tx4, tx5,inc;
                inc=(x3-x2)/div;
                // inc=1;

                m = (uy - ly) / (x3 - x2);
                m1 = (ly - uy) / (x2 - x1);
                tx = x2;
                //     tx1 = x2;
                tu = uy;
                tl = ly;
                rty1 = ly + width / 2;
                rtx1 = x2 + width / 2;
                rtx2 = x1 + width / 2;
                rty2 = uy + width / 2;
                leftlock=width*2;
                while ((ly > tu - 1) && (uy < tl - 1) && (x3 > tx)) {
                    ly = rty1 - m * (x2 + (width / 2) - rtx1) - width / 2;
                    uy = rty2 - m1 * (x1 + (width / 2) - rtx2) - width / 2;
                    x1-=inc;
                    x2-=inc;
                    x3-=inc;
                    x4-=inc;
                    x5-=inc;
                    x6-=inc;
                    x7-=inc;
                    dxx+=inc;
                    fast-=inc*3;

                    dyy1=dxx;
                    dyy=-dxx;
                    try {
                        sleep(slep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                incri=1;
               // i = (i + 1) % n;
                flag = false;
                mbutton=true;

//                return false;









            }
           // return  true;

        }



        mbutton=true;

        return true;
    }



    public class ourview extends SurfaceView implements Runnable {
        Thread t = null;
        SurfaceHolder holder;
        boolean isitok = false;

        public ourview(Context context) {
            super(context);
            holder = getHolder();
        }

        @Override
        public void run() {
            Bitmap arrowr =getBitmap(R.drawable.right_arrow);
            Bitmap arrowl =getBitmap(R.drawable.left_arrow);
            Bitmap backarr=BitmapFactory.decodeResource(getResources(), R.drawable.back);
            Bitmap bg=null;
            List<Bitmap> imgList = new ArrayList<>();

   /*         for ( int k=0 ; k<5 ; k++)
            {
               // imgList.add(getBitmap(R.drawable.raghu+k));//BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("num" + i, "drawable", getPackageName())));
                bgr[k]=getBitmap(R.drawable.event+k);
            }*/


            while (isitok) {
                if (!holder.getSurface().isValid()) {
                    continue;
                }



                Canvas c = holder.lockCanvas();


                //    c.drawARGB(255, 55,120,246);


                Paint rhom = new Paint();
                rhom.setColor(Color.argb(150,255,255,255));
                //  rhom.setAlpha(200);

                 // rhom.setShadowLayer(.4f, 1f, 3f, Color.BLACK);

                Paint parrow=new Paint();
                parrow.setColor(Color.BLACK);

                Paint opac=new Paint();
                opac.setColor(Color.argb(100,255,255,255));

                Paint fade=new Paint();
                fade.setColor(Color.argb(fad,255,255,255));




                Paint text1 = new Paint();
                text1.setColor(Color.BLACK);
                text1.setTextSize(width / 8);


                text1.setShadowLayer(2f, 0f, 1f, Color.BLACK);



                Paint text2 = new Paint();
                text2.setColor(Color.BLACK);
                text2.setStyle(Paint.Style.FILL_AND_STROKE);
                text2.setTextSize(width/8);
Paint text3=new Paint();
                text3.setColor(Color.WHITE);
                text3.setTextSize(c.getHeight()/20);

                Paint border=new Paint();
                border.setColor(Color.WHITE);
                border.setStrokeWidth(5);



                rhomwidth=c.getWidth()*3/8;
                a=c.getWidth()*3/5;


/*if(init){
    for(int l=0;l<5;l++){
        bgr[l]=getResizedBitmap(bgr[l],c.getWidth(),c.getHeight());
    }
    init=false;
}*/


                if (!flag) {
                    uy = (c.getHeight() / 3) - (rhomwidth / 2);
                    ly = ((c.getHeight()) / 3) - (rhomwidth / 2) + (a / 2);
                    width = rhomwidth;


                    x3 = (c.getWidth() / 2) - (rhomwidth / 2);
                    x2 = (c.getWidth() / 2) - (rhomwidth / 2) - (a / 2);
                    x4 = (c.getWidth() / 2) - (rhomwidth / 2) + (a / 2);
                    x1 = (c.getWidth() / 2) - (rhomwidth / 2) - a;
                    x5 = (c.getWidth() / 2) - (rhomwidth / 2) + a;
                    x6=(c.getWidth() / 2) - (rhomwidth / 2) + a*3/2;
                    x7=(c.getWidth() / 2) - (rhomwidth / 2) - a*3/2;


                    by = ((c.getHeight()) / 3) - (rhomwidth / 2) + a + c.getWidth() / 20;
                    lb = (c.getWidth() / 2) - (rhomwidth / 2) - a+rhomwidth/20;
                    rb = (c.getWidth() / 2) - (rhomwidth / 2) + a-rhomwidth/20;
                    q=c.getWidth()/2;
                    w=uy-(x3-x1-width)/2;
                    e=-c.getWidth()/2;
                    r=uy-(x3-x1-width)/2+c.getWidth();
                    dx=c.getWidth()/2;
                    dy=c.getHeight()/2+width/4;
                    dyy=0;
                    dxx=0;
                    dyy1=0;
                    leftlock=0;
                    rightlock=0;
                    if(incri==1){
                        i=(i+1)%n;
                    }
                    if(incri==-1){
                        i=(i+(n-1))%n;
                    }
                    fad=0;
                    bg= BitmapFactory.decodeResource(getResources(), R.drawable.event+(i+3)%9);
                    bg=getResizedBitmap(bg,c.getWidth(),c.getHeight());

                 //   bg=bgr[i%5];
                    incri=0;
                    fast=0;
                    height1=c.getHeight();
                    flag = true;

                }


                arrowr = getResizedBitmap(arrowr, (int)rhomwidth/4,(int) rhomwidth/4);
                arrowl = getResizedBitmap(arrowl, (int)rhomwidth/4, (int)rhomwidth/4);
                backarr=getResizedBitmap(backarr,c.getHeight()/28,c.getHeight()/28);
              //  bg=imgList.get(i%3);
               // bg=getResizedBitmap(bg,c.getWidth(),c.getHeight());

                c.drawBitmap(bg,0,0,null);
                c.drawRect(0,0,c.getWidth(),c.getHeight(),fade);





                float offset = rhomwidth;
                float left = c.getWidth()/2-(c.getWidth()/4);
                float top = c.getHeight()/2-(c.getWidth()/4);
                float right = left + offset;
                float bottom = top + offset;

        /*        c.save();
                c.rotate(45, left + offset/2, top + offset/2);
                c.drawRect(left, top, right, bottom, border);
                c.restore();*/










                //Opacity
                c.save();
                c.rotate(45, (c.getWidth() / 2) - (rhomwidth / 2) + (a / 2)+offset/8 + offset, ((c.getHeight()) / 3) - (offset) + (a / 2) + offset);
                c.drawRect((c.getWidth() / 2) - (rhomwidth / 2) + (a / 2)+offset/8 , ((c.getHeight()) / 3) - (offset) + (a / 2), (c.getWidth() / 2) - (rhomwidth / 2) + (a / 2)+offset/8 +2*offset, ((c.getHeight()) / 3) - (offset) + (a / 2)+offset*2, opac);
                c.restore();

                c.save();
                c.rotate(45, (c.getWidth() / 2) - (rhomwidth) - (rhomwidth / 2) - (a / 2)-offset/8 + offset, ((c.getHeight()) / 3) - (offset) + (a / 2) + offset);
                c.drawRect((c.getWidth() / 2) - (rhomwidth) - (rhomwidth / 2) - (a / 2)-offset/8 , ((c.getHeight()) / 3) - (offset) + (a / 2), (c.getWidth() / 2) - (rhomwidth) - (rhomwidth / 2) - (a / 2)-offset/8 +2*offset, ((c.getHeight()) / 3) - (offset) + (a / 2)+offset*2, opac);
                c.restore();





                c.drawLine(q,w,e,r,border);
                c.drawLine(q,w,(c.getWidth()*3)/2,r,border);

//Black Rhombus For Button
                c.save();
                c.rotate(45, lb + offset/2, by + offset/2);
                c.drawRect(lb, by, lb+offset, by+offset, parrow);
                c.restore();


                c.save();
                c.rotate(45, rb + offset/2, by + offset/2);
                c.drawRect(rb, by, rb+offset, by+offset, parrow);
                c.restore();




                //opacity
                //         c.drawBitmap(newop, (c.getWidth() / 2) - (rhombus.getWidth() / 2) + (a / 2), ((c.getHeight()) / 3) - (newop.getHeight() / 2) + (a / 2), null);
                //       c.drawBitmap(newop, (c.getWidth() / 2) - (newop.getWidth() / 2) - (rhombus.getWidth() / 2) - (a / 2), ((c.getHeight()) / 3) - (newop.getHeight() / 2) + (a / 2), null);








                c.save();
                c.rotate(45, x3 + offset/2, uy + offset/2);
                c.drawRect(x3, uy, x3+offset, uy+offset, rhom);
                c.restore();



                c.save();
                c.rotate(45, x6 + offset/2, ly + offset/2);
                c.drawRect(x6, ly, x6+offset, ly+offset, rhom);
                c.restore();



                c.save();
                c.rotate(45, x7 + offset/2, ly + offset/2);
                c.drawRect(x7, ly, x7+offset, ly+offset, rhom);
                c.restore();




                //       c.drawBitmap(rhombus, x3, uy, null);


                Rect bounds = new Rect();
                text1.getTextBounds(category[(i + 3) % n], 0, category[(i + 3) % n].length(), bounds);
                float xx = bounds.width();
                float yy = bounds.height();


                c.drawText(category[(i + 3) % n], (x3) + rhomwidth / 2 - xx / 2, uy + yy / 2 + rhomwidth / 2, text1);

                ///////////////////////////////////////////////////////////////////

                c.save();
                c.rotate(45, x2 + offset/2, ly + offset/2);
                c.drawRect(x2, ly, x2+offset, ly+offset, rhom);
                c.restore();
                //    c.drawBitmap(rhombus, x2, ly, null);


                text1.getTextBounds(category[(i + 2) % n], 0, category[(i + 2) % n].length(), bounds);
                xx = bounds.width();
                yy = bounds.height();


                c.drawText(category[(i + 2) % n], (x2) + rhomwidth / 2 - xx / 2, ly + yy / 2 + rhomwidth / 2, text1);
                ///////////////////////////////////////////////////////////
                //  c.drawBitmap(rhombus, x4, ly, null);

                c.save();
                c.rotate(45, x4 + offset/2, ly + offset/2);
                c.drawRect(x4, ly, x4+offset, ly+offset, rhom);
                c.restore();
                text1.getTextBounds(category[(i + 4) % n], 0, category[(i + 4) % n].length(), bounds);
                xx = bounds.width();
                yy = bounds.height();


                c.drawText(category[(i + 4) % n], (x4) + rhomwidth / 2 - xx / 2, ly + yy / 2 + rhomwidth / 2, text1);

                ///////////////////////////////////////////////////////////////////////


                //c.drawBitmap(rhombus, x1, uy, null);

                c.save();
                c.rotate(45, x1 + offset/2, uy + offset/2);
                c.drawRect(x1, uy, x1+offset, uy+offset, rhom);
                c.restore();

                text1.getTextBounds(category[(i + 1) % n], 0, category[(i + 1) % n].length(), bounds);
                xx = bounds.width();
                yy = bounds.height();


                c.drawText(category[(i + 1) % n], (x1) + rhomwidth / 2 - xx / 2, uy + yy / 2 + rhomwidth / 2, text1);

                ////////////////////////////////////////////////////////////////////////
//                c.drawBitmap(rhombus, x5, uy, null);


                c.save();
                c.rotate(45, x5 + offset/2, uy + offset/2);
                c.drawRect(x5, uy, x5+offset, uy+offset, rhom);
                c.restore();

                text1.getTextBounds(category[(i + 5) % n], 0, category[(i + 5) % n].length(), bounds);
                xx = bounds.width();
                yy = bounds.height();


                c.drawText(category[(i + 5) % n], (x5) + rhomwidth / 2 - xx / 2, uy + yy / 2 + rhomwidth / 2, text1);


                //       c.drawBitmap(det, (c.getWidth() / 2) - (det.getWidth() / 2), ((c.getHeight()) / 3) - (rhombus.getHeight() / 2) + rhombus.getHeight(), null);
                c.save();
                c.rotate(45, (c.getWidth() / 2) - (rhomwidth*3 / 2) + offset*3/2,((c.getHeight()) / 3) - (rhomwidth*3 / 2) + rhomwidth*3+ offset*3/2);
                c.drawRect((c.getWidth() / 2) - (rhomwidth*3 / 2), ((c.getHeight()) / 3) - (rhomwidth*3 / 2) + rhomwidth*3, (c.getWidth() / 2) - (rhomwidth*3 / 2)+3*offset, ((c.getHeight()) / 3) - (rhomwidth*3 / 2) + rhomwidth*3+3*offset, rhom);
                c.restore();



    /*            ty=0;
                for (String line : desc[(i+2)%n].split("\n")) {
                    text2.getTextBounds(line, 0, line.length(), bounds);
                    xx = bounds.width();
                    yy = bounds.height();
                    c.drawText(line,3*(x2)/2 - xx / 2, 24*ly/7 + 2*yy +leftlock+ty, text2);
                    ty += -text2.ascent() +  text2.descent();
                }


                ty=0;
                for (String line : desc[(i+4)%n].split("\n")) {
                    text2.getTextBounds(line, 0, line.length(), bounds);
                    xx = bounds.width();
                    yy = bounds.height();
                    c.drawText(line, 11*(x4)/7  - xx / 2, 24*ly/7 + 2*yy  +ty+rightlock, text2);
                    ty += -text2.ascent() +  text2.descent();
                }




                ty=0;
                for (String line : desc[(i+3)%n].split("\n")) {
                    text2.getTextBounds(line, 0, line.length(), bounds);
                    xx = bounds.width();
                    yy = bounds.height();
                    c.drawText(line,dx-xx/2+dxx+fast , uy + yy / 2 + dy +ty+dyy, text2);
                    ty += -text2.ascent() +  text2.descent();
                }*/



                ty=0;
                for (String line : desc[(i+3)%n].split("\n")) {
                    text2.getTextBounds(line, 0, line.length(), bounds);
                    xx = bounds.width();
                    yy = bounds.height();
                    c.drawText(line, x3 +rhomwidth/2- xx/2 +fast*3/4 , uy+c.getHeight()*9/17 + yy / 2 +ty+dxx/2, text2);
                    ty += -text2.ascent() + text2.descent();
                }



                ty=0;
                for (String line : desc[(i+4)%n].split("\n")) {
                    text2.getTextBounds(line, 0, line.length(), bounds);
                    xx = bounds.width();
                    yy = bounds.height();
                    c.drawText(line, x4+rhomwidth/2  - xx / 2+rhomwidth/2+fast*64/300, +fast*64/300+ly+c.getHeight()*9/17 + yy/2 +rhomwidth/2 +ty, text2);
                    ty += -text2.ascent() +  text2.descent();
                }

                ty=0;
                for (String line : desc[(i+2)%n].split("\n")) {
                    text2.getTextBounds(line, 0, line.length(), bounds);
                    xx = bounds.width();
                    yy = bounds.height();
                    c.drawText(line, x2+rhomwidth/2  - xx / 2-rhomwidth/2+fast*64/300, ly+c.getHeight()*9/17 + yy/2 +rhomwidth/2 +ty-fast*64/300, text2);

                    //     c.drawText(line,3*(x2)/2 - xx / 2, 24*ly/7 + 2*yy +leftlock+ty, text2);
                    ty += -text2.ascent() +  text2.descent();
                }











         /*       c.save();
                c.rotate(45, left + offset/2, top+200 + offset/2);
                c.drawRect(left, top+200, right, bottom+200, border);
                c.restore();

            /*    RectShape r=new RectShape();
                c.save();
                c.rotate(-45,c.getWidth()/2,c.getHeight()/2);
                c.drawRect(c.getWidth()/2-100,c.getWidth()/2+100,300,300, text1);
              //  c.drawRect(100,100,500,500,text1);

//stuff to draw that should be rotated
                c.restore();


/*
                a = rhombus.getWidth();








                c.drawBitmap(rhombus, x3, uy, null);


                Rect bounds = new Rect();
                text1.getTextBounds(category[(i + 3) % 6], 0, category[(i + 3) % 6].length(), bounds);
                float xx = bounds.width();
                float yy = bounds.height();


                c.drawText(category[(i + 3) % 6], (x3) + width / 2 - xx / 2, uy + yy / 2 + width / 2, text1);

                ///////////////////////////////////////////////////////////////////
                c.drawBitmap(rhombus, x2, ly, null);


                text1.getTextBounds(category[(i + 2) % 6], 0, category[(i + 2) % 6].length(), bounds);
                xx = bounds.width();
                yy = bounds.height();


                c.drawText(category[(i + 2) % 6], (x2) + width / 2 - xx / 2, ly + yy / 2 + width / 2, text1);
                ///////////////////////////////////////////////////////////
                c.drawBitmap(rhombus, x4, ly, null);


                text1.getTextBounds(category[(i + 4) % 6], 0, category[(i + 4) % 6].length(), bounds);
                xx = bounds.width();
                yy = bounds.height();


                c.drawText(category[(i + 4) % 6], (x4) + width / 2 - xx / 2, ly + yy / 2 + width / 2, text1);

                ///////////////////////////////////////////////////////////////////////


                c.drawBitmap(rhombus, x1, uy, null);

                text1.getTextBounds(category[(i + 1) % 6], 0, category[(i + 1) % 6].length(), bounds);
                xx = bounds.width();
                yy = bounds.height();


                c.drawText(category[(i + 1) % 6], (x1) + width / 2 - xx / 2, uy + yy / 2 + width / 2, text1);

                ////////////////////////////////////////////////////////////////////////
                c.drawBitmap(rhombus, x5, uy, null);

                text1.getTextBounds(category[(i + 5) % 6], 0, category[(i + 5) % 6].length(), bounds);
                xx = bounds.width();
                yy = bounds.height();


                c.drawText(category[(i + 5) % 6], (x5) + width / 2 - xx / 2, uy + yy / 2 + width / 2, text1);





                /////////////////////////////////////////////////////////////////////////////////////////

                c.drawBitmap(det, (c.getWidth() / 2) - (det.getWidth() / 2), ((c.getHeight()) / 3) - (rhombus.getHeight() / 2) + rhombus.getHeight(), null);



                ty=0;
                for (String line : desc[(i+2)%6].split("\n")) {
                    text2.getTextBounds(line, 0, line.length(), bounds);
                    xx = bounds.width();
                    yy = bounds.height();
                    c.drawText(line,2*(x2) - xx / 2, 4*ly + 2*yy +leftlock+ty, text2);
                    ty += -text2.ascent() +  text2.descent();
                }


                ty=0;
                for (String line : desc[(i+4)%6].split("\n")) {
                    text2.getTextBounds(line, 0, line.length(), bounds);
                    xx = bounds.width();
                    yy = bounds.height();
                    c.drawText(line, 2*(x4)  - xx / 2, 4*ly + 2*yy  +ty+rightlock, text2);
                    ty += -text2.ascent() +  text2.descent();
                }




                ty=0;
                for (String line : desc[(i+3)%6].split("\n")) {
                    text2.getTextBounds(line, 0, line.length(), bounds);
                    xx = bounds.width();
                    yy = bounds.height();
                    c.drawText(line,dx-xx/2+dxx+fast , uy + yy / 2 + dy +ty+dyy, text2);
                    ty += -text2.ascent() +  text2.descent();
                }


                //    c.drawBitmap(bor, c.getWidth() / 2 - bor.getWidth() / 2, c.getHeight()/2  - bor.getHeight() / 2, null);
                c.drawBitmap(newop, (c.getWidth() / 2) - (rhombus.getWidth() / 2) + (a / 2), ((c.getHeight()) / 3) - (newop.getHeight() / 2) + (a / 2), null);
                c.drawBitmap(newop, (c.getWidth() / 2) - (newop.getWidth() / 2) - (rhombus.getWidth() / 2) - (a / 2), ((c.getHeight()) / 3) - (newop.getHeight() / 2) + (a / 2), null);

                c.drawBitmap(Button, lb, by, null);
                c.drawBitmap(Button, rb, by, null);
                c.drawBitmap(arrowr, c.getWidth() - arrowr.getWidth() / 2, ((c.getHeight()) / 3) - (arrowr.getHeight() / 2) + a + c.getWidth() / 24, null);


                c.drawLine(q,w,e,r,border);
                c.drawLine(q,w,(c.getWidth()*3)/2,r,border);
                c.drawBitmap(arrowl, -arrowl.getWidth() / 2, ((c.getHeight()) / 3) - (arrowr.getHeight() / 2) + a + c.getWidth() / 24, null);


*/

                c.drawBitmap(arrowr, c.getWidth() - arrowr.getWidth() / 2, ((c.getHeight() ) / 3) - (arrowr.getHeight() / 2) + a + c.getWidth() / 24, null);



                c.drawBitmap(arrowl, -arrowl.getWidth() / 2, ((c.getHeight() ) / 3) - (arrowr.getHeight() / 2) + a + c.getWidth() / 24, null);


                text3.getTextBounds("Events", 0, "Events".length(), bounds);
                 xx = bounds.width();
                 yy = bounds.height();



                c.drawText("Events", c.getWidth()/2-xx/2,c.getHeight()/24+yy/2, text3);

                c.drawBitmap(backarr,c.getHeight()/40,c.getHeight()/48, null);
                holder.unlockCanvasAndPost(c);

                try {
                    sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }


            }
        }


        public void pause() {
            isitok = false;
            mbutton=true;

            while (true) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            t = null;
        }

        public void resume() {
            isitok = true;
            mbutton=true;
            start=true;
            button=true;
            init=true;
            flag=false;
            t = new Thread(this);
            t.start();
        }


    }
}

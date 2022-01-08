package com.cmrit.cultura17;

/**
 * Created by rajat on 15/12/16.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;


public class Sponsor extends Activity implements View.OnTouchListener {

    private ProgressDialog pDialog;
    AlertDialog.Builder dialog= null;

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
    private FirebaseAnalytics mFirebaseAnalytics;

    int x,y;
   public int noi=0,buffer=0;
    float width;
    float width1,height1;
    float yl1,yl2,yl3,yl4,yl5,yl6,yl7,yl8,yl9,yl10,yl11,yl12;
    float xl1,xl2,xl3,xl4,xl5,xl6,xl7,xl8;

    float yl[]=new float[12];
    float xl[]=new float[8];

    float xpos5=0,tx5=0,ty5=0,ypos3=0,ypos6=0,xpos6=0,tx6=0,ty6=0;
    float startx=0,starty=0,endx=0,endy=0,canwidth=300;
    float sum=0,sum1=0;
    float xpos=0,ypos=0,xpos1=0,ypos1=0,ypos5=0,xpos4=0,xpos2=0,ypos4=0,ypos2=0;
    float xpos3=0;
    float anim=0;
    float tx=0,ty=0,tx1=0,tx4=0,ty1=0,ty4=0,ty2=0,tx2=0,tx3=0,ty3=0;
     Bitmap images[];
    int k=0;
    boolean imgload=true,noint=false;

    int a,b,d;
    boolean start=true,backb=false;
    private  ProgressDialog progressBar;
    private int progressBarStatus = 0;
    String msg="";
    private Handler progressBarbHandler = new Handler();
    public int status(){
        return buffer;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        noi=sp.getInt("sponCount", 5);
        images=new Bitmap[noi];
        for(int i=0;i<noi;i++){
            images[i]=BitmapFactory.decodeResource(getResources(), R.drawable.default_sponsor);
        }



        if(start){
            /*pDialog = new ProgressDialog(Sponsor.this);
            pDialog.setTitle("Loading infinite grid");
            pDialog.setIndeterminate(false);
            pDialog.setMessage("Just wait and watch");
            pDialog.setCancelable(true);
            pDialog.show();*/
            progressBar = new ProgressDialog(this);
            progressBar.setCancelable(true);
            progressBar.setMessage("Loading Infinite Grid");
            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressBar.setProgress(0);
            progressBar.setMax(noi);
            progressBar.show();
            progressBarStatus = 0;




            new Thread(new Runnable() {
                public void run() {
                    while (progressBarStatus < noi) {
                        progressBarStatus = status();
//                                System.out.println("Progress Status "+progressBarStatus+" "+buffer);
//                                System.out.println("Inside Buffer "+buffer);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        progressBarbHandler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressBarStatus);
                                if(progressBarStatus==noi){
                                    progressBar.dismiss();
                                }
                            }
                        });
                    }

                    if (progressBarStatus >= noi) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressBar.dismiss();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {

                    buffer=0;
                    for(int i=1;i<=noi;i++) {
                        try {
                            System.out.println("image before" +buffer);
                            images[i - 1] = Glide.
                                    with(Sponsor.this).
                                    load("http://203.201.63.39/html/cultura17/sponsors/spon" + i + ".png").
                                    asBitmap().error(R.drawable.default_sponsor).
                                    into(-1,-1). // Width and height
                                    get();
                            buffer++;
                            msg="";
                            System.out.println("i,mage" +buffer);
                            imgload=true;
                            // images[i - 1] = getResizedBitmap(images[i - 1], c.getWidth() / 3, c.getWidth() / 3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();//
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {

                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(buffer==0){
                        //Toast.makeText(getBaseContext(), "No internet Connectio!!", Toast.LENGTH_LONG).show();
                        progressBar.dismiss();
                       // Toast.makeText(getBaseContext(), "No internet Connectio!!", Toast.LENGTH_LONG).show();
                        noint=true;
                    }
                }
            }).start();







        }

        v = new Sponsor.ourview(this);
        v.setOnTouchListener(this);





        setContentView(v);



        //Analytics Part!!
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle params = new Bundle();
        params.putString("name", "kushal");
        mFirebaseAnalytics.logEvent("Sponsor", params);
















//        v= new ourview(this);
//        v.setOnTouchListener(this);
//        //    q = BitmapFactory.decodeResource(getResources(), R.drawable.qbus);
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




        switch (me.getAction()) {
            case MotionEvent.ACTION_DOWN:

                startx=me.getX();
                starty=me.getY();



                if(startx>height1/40&&startx<height1/40+height1/40&&starty<height1/10){

                    finish();
                    return false;

                }

             /*

               c.drawRect(0,0,c.getWidth(),c.getHeight()/10,action_colour);
                c.drawRect(0,0,c.getHeight()/40,c.getHeight(),action_colour);

                c.drawRect(c.getWidth()-c.getHeight()/40,0,c.getWidth(),c.getHeight(),action_colour);
                c.drawBitmap(arrowl,c.getHeight()/40,c.getHeight()/48, null);





              try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                break;
            case MotionEvent.ACTION_UP:
                endx=me.getX();
                endy=me.getY();
                if(tx<0){
                    xpos6=xpos5=xpos3= xpos=xpos4=xpos2=xpos1+=sum;
                }
                xpos6=xpos5=xpos3=xpos1=xpos4=xpos2=xpos+=tx;

                if(ty<0){
                    ypos6=ypos5=ypos3= ypos1=ypos4=ypos2= ypos+=width*6;
                }
                ypos6=ypos5=ypos3=ypos1=ypos4=ypos2=ypos+=ty;

                tx5=tx6=tx1=tx4=tx3=tx2=tx=0;
                ty5=ty6=ty=ty2=ty1=ty4=ty3=0;

               /* try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                break;
            case MotionEvent.ACTION_MOVE:

                tx6=tx5=tx2=tx1=tx4=tx3= tx=me.getX()-startx;
                ty6=ty5=ty2=ty1=ty4=ty3=ty=me.getY()-starty;

                break;
            default:

                break;
        }

        return true;
    }

    public class ourview extends SurfaceView implements Runnable{
        Thread t=null;
        SurfaceHolder holder;
        boolean isitok = false;
        public ourview(Context context) {
            super(context);
            holder=getHolder();
        }

        @Override
        public void run() {



            Bitmap arrowl = getBitmap(R.drawable.back);
            Bitmap bg=BitmapFactory.decodeResource(getResources(), R.drawable.sponsor_bg);

         //   q1=BitmapFactory.decodeResource(getResources(), R.drawable.spon1);
           // CulturaDBHelper db=new CulturaDBHelper(getContext());

            while(isitok){
                if(!holder.getSurface().isValid()){
                    continue;
                }
                Canvas c =holder.lockCanvas();
                if(start){
                    bg=getResizedBitmap(bg,c.getWidth(),c.getHeight());
                }
                c.drawBitmap(bg,0,0,null);


                if(imgload){





                    for(int i=1;i<=noi;i++) {
                        images[i - 1] = getResizedBitmap(images[i - 1], c.getWidth() / 3, c.getWidth() / 3);
                    }
                    imgload=false;


//
//                    buffer=0;
//                    for(int i=1;i<=noi;i++) {
//                        try {
//                            System.out.println("image before" +buffer);
//                            images[i - 1] = Glide.
//                                    with(Sponsor.this).
//                                    load("http://203.201.63.39/html/cultura17/sponsors/spon" + i + ".png").
//                                    asBitmap().
//                                    into(c.getWidth()/3, c.getWidth()/3). // Width and height
//                                    get();
//                            buffer++;
//                            System.out.println("i,mage" +buffer);
//                            images[i - 1] = getResizedBitmap(images[i - 1], c.getWidth() / 3, c.getWidth() / 3);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        } catch (ExecutionException e) {
//                            e.printStackTrace();
//                        }
//                    }

                }
                if(noint){

                   msg="NO INTERNET CONNECTION!!!!";
                    noint=false;
                }

               // c.drawARGB(255,0,0,75);
                Paint forw = new Paint();
                forw.setColor(Color.GREEN);
                forw.setTextSize(100);
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setStyle(Paint.Style.FILL);

                paint.setTextSize(50);

                Paint text1 = new Paint();
                text1.setColor(Color.WHITE);
                text1.setTextSize(c.getHeight()/20);

                Paint action_colour=new Paint();
                action_colour.setColor(Color.BLACK);

                Paint text2 = new Paint();
                text2.setColor(Color.WHITE);
                text2.setTextSize(c.getHeight()/25);


                action_colour.setColor(Color.BLACK);

//                c.drawRect((c.getWidth() / 2) - (rhomwidth / 2) + (a / 2)+offset/8 , ((c.getHeight()) / 3) - (offset) + (a / 2), (c.getWidth() / 2) - (rhomwidth / 2) + (a / 2)+offset/8 +2*offset, ((c.getHeight()) / 3) - (offset) + (a / 2)+offset*2, opac);












                if (start) {
                    width=c.getWidth()*1/2;
                    width1=c.getWidth();
                    height1=c.getHeight();
                    arrowl=getResizedBitmap(arrowl,c.getHeight()/28,c.getHeight()/28);

                /*    q1=getResizedBitmap(q1,c.getWidth()*1/3,c.getWidth()*1/3);
                    q2=getResizedBitmap(q2,c.getWidth()*1/3,c.getWidth()*1/3);

                    q3=getResizedBitmap(q3,c.getWidth()*1/3,c.getWidth()*1/3);
                    q4=getResizedBitmap(q4,c.getWidth()*1/3,c.getWidth()*1/3);
                    q5=getResizedBitmap(q5,c.getWidth()*1/3,c.getWidth()*1/3);
                    q6=getResizedBitmap(q6,c.getWidth()*1/3,c.getWidth()*1/3);*/
                    xl[0]=c.getWidth()/2-width/2-(width*3)/2+width;
                    xl[1]=c.getWidth()/2-width/2-width+width;
                    xl[2]=c.getWidth()/2-width/2-width/2+width;
                    xl[3]=c.getWidth()/2-width/2+width;
                    xl[4]=c.getWidth()/2-width/2+width/2+width;
                    xl[5]=c.getWidth()/2-width/2+width+width;
                    xl[6]=c.getWidth()/2-width/2+(width*3)/2+width;
                    xl[7]=c.getWidth()/2-width/2+(width*3)/2+width+width/2;

                    //yl1=c.getHeight()/2-width/2-(width*5)/2+2*width;
                    yl[0]=c.getHeight()/2-width/2-(width*2)+width;
                    yl[1]=c.getHeight()/2-width/2-(3*width)/2+width;
                    yl[2]=c.getHeight()/2-width/2-width+width;
                    yl[3]=c.getHeight()/2-width/2-width/2+width;
                    yl[4]=c.getHeight()/2-width/2+width;
                    yl[5]=c.getHeight()/2-width/2+width/2+width;
                    yl[6]=c.getHeight()/2-width/2+width+width;
                    yl[7]=c.getHeight()/2-width/2+(width*3)/2+width;
                    yl[8]=c.getHeight()/2-width/2+(width*2)+width;
                    yl[9]=c.getHeight()/2-width/2+(width*5)/2+width;
                    yl[10]=c.getHeight()/2-width/2+(width*3)+width;
                    yl[11]=c.getHeight()/2-width/2+(width*3)+width+width/2;

                    canwidth=c.getWidth();
                    sum=width*4;//c.getWidth()+q.getWidth();
                    sum1=6*width;
                    anim=width/28;




                    start=false;

                }




k=0;
for(int i=1;i<=8;i+=2){
    for(int j=1;j<=12;j+=2) {
        c.drawBitmap(images[(k) % noi],((xl[i-1]+xpos+tx)%sum)-width,(yl[j-1]+ypos+ty+anim)%sum1-width,null );
        k++;
    }
}


                for(int i=2;i<=8;i+=2){
                    for(int j=2;j<=12;j+=2) {
                        c.drawBitmap(images[(k) % noi],((xl[i-1]+xpos+tx)%sum)-width,(yl[j-1]+ypos+ty+anim)%sum1-width,null );
                        k++;
                    }
                }
                anim+=width/80;







/*

                c.drawBitmap(q1,((xl1+xpos1+tx)%(sum))-width,((yl1+ypos1+ty)%(sum1)-2*width),null);
                c.drawBitmap(q2,((xl3+xpos2+tx)%(sum))-width,((yl1+ypos2+ty)%(sum1)-2*width),null);
                c.drawBitmap(q3,((xl5+xpos3+tx)%(sum))-width,((yl1+ypos3+ty)%(sum1)-2*width),null);
                c.drawBitmap(q4,((xl7+xpos4+tx)%(sum))-width,((yl1+ypos4+ty)%(sum1)-2*width),null);
                c.drawBitmap(q5,((xl2+xpos5+tx)%(sum))-width,((yl2+ypos5+ty)%(sum1)-2*width),null);
                c.drawBitmap(q6,((xl4+xpos6+tx)%(sum))-width,((yl2+ypos6+ty)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl6+xpos+tx)%(sum))-width,((yl2+ypos+ty)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl8+xpos+tx)%(sum))-width,((yl2+ypos1+ty)%(sum1)-2*width),null);


                 c.drawBitmap(q2,((xl1+xpos1+tx4)%(sum))-width,((yl3+ypos1+ty4)%(sum1)-2*width),null);
                c.drawBitmap(q3,((xl3+xpos2+tx4)%(sum))-width,((yl3+ypos2+ty4)%(sum1)-2*width),null);
                c.drawBitmap(q4,((xl5+xpos3+tx4)%(sum))-width,((yl3+ypos3+ty4)%(sum1)-2*width),null);
                c.drawBitmap(q5,((xl7+xpos4+tx4)%(sum))-width,((yl3+ypos4+ty4)%(sum1)-2*width),null);
                c.drawBitmap(q6,((xl2+xpos5+tx4)%(sum))-width,((yl4+ypos5+ty4)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl4+xpos6+tx4)%(sum))-width,((yl4+ypos6+ty4)%(sum1)-2*width),null);
                c.drawBitmap(q2,((xl6+xpos+tx4)%(sum))-width,((yl4+ypos+ty4)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl8+xpos+tx)%(sum))-width,((yl4+ypos3+ty)%(sum1)-2*width),null);





                c.drawBitmap(q3,((xl1+xpos1+tx2)%(sum))-width,((yl5+ypos1+ty2)%(sum1)-2*width),null);
                c.drawBitmap(q4,((xl3+xpos2+tx2)%(sum))-width,((yl5+ypos2+ty2)%(sum1)-2*width),null);
                c.drawBitmap(q5,((xl5+xpos3+tx2)%(sum))-width,((yl5+ypos3+ty2)%(sum1)-2*width),null);
                c.drawBitmap(q6,((xl7+xpos4+tx2)%(sum))-width,((yl5+ypos4+ty2)%(sum1)-2*width),null);

                c.drawBitmap(q1,((xl2+xpos5+tx2)%(sum))-width,((yl6+ypos5+ty2)%(sum1)-2*width),null);
                c.drawBitmap(q2,((xl4+xpos6+tx2)%(sum))-width,((yl6+ypos6+ty2)%(sum1)-2*width),null);
                c.drawBitmap(q3,((xl6+xpos+tx2)%(sum))-width,((yl6+ypos+ty2)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl8+xpos+tx)%(sum))-width,((yl6+ypos1+ty)%(sum1)-2*width),null);




                c.drawBitmap(q4,((xl1+xpos1+tx3)%(sum))-width,((yl7+ypos1+ty3)%(sum1)-2*width),null);
                c.drawBitmap(q5,((xl3+xpos2+tx3)%(sum))-width,((yl7+ypos2+ty3)%(sum1)-2*width),null);
                c.drawBitmap(q6,((xl5+xpos3+tx3)%(sum))-width,((yl7+ypos3+ty3)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl7+xpos4+tx3)%(sum))-width,((yl7+ypos4+ty3)%(sum1)-2*width),null);
                c.drawBitmap(q2,((xl2+xpos5+tx3)%(sum))-width,((yl8+ypos5+ty3)%(sum1)-2*width),null);
                c.drawBitmap(q3,((xl4+xpos6+tx3)%(sum))-width,((yl8+ypos6+ty3)%(sum1)-2*width),null);
                c.drawBitmap(q4,((xl6+xpos+tx3)%(sum))-width,((yl8+ypos+ty3)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl8+xpos+tx)%(sum))-width,((yl8+ypos1+ty)%(sum1)-2*width),null);




                c.drawBitmap(q5,((xl1+xpos1+tx1)%(sum))-width,((yl9+ypos1+ty1)%(sum1)-2*width),null);
                c.drawBitmap(q6,((xl3+xpos2+tx1)%(sum))-width,((yl9+ypos2+ty1)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl5+xpos3+tx1)%(sum))-width,((yl9+ypos3+ty1)%(sum1)-2*width),null);
                c.drawBitmap(q2,((xl7+xpos4+tx1)%(sum))-width,((yl9+ypos4+ty1)%(sum1)-2*width),null);

                c.drawBitmap(q3,((xl2+xpos5+tx1)%(sum))-width,((yl10+ypos5+ty1)%(sum1)-2*width),null);
                c.drawBitmap(q4,((xl4+xpos6+tx1)%(sum))-width,((yl10+ypos6+ty1)%(sum1)-2*width),null);
                c.drawBitmap(q5,((xl6+xpos+tx1)%(sum))-width,((yl10+ypos+ty1)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl8+xpos+tx)%(sum))-width,((yl10+ypos1+ty)%(sum1)-2*width),null);


                c.drawBitmap(q5,((xl1+xpos1+tx5)%(sum))-width,((yl11+ypos1+ty5)%(sum1)-2*width),null);
                c.drawBitmap(q6,((xl3+xpos2+tx5)%(sum))-width,((yl11+ypos2+ty5)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl5+xpos3+tx5)%(sum))-width,((yl11+ypos3+ty5)%(sum1)-2*width),null);
                c.drawBitmap(q2,((xl7+xpos4+tx5)%(sum))-width,((yl11+ypos4+ty5)%(sum1)-2*width),null);

                c.drawBitmap(q3,((xl2+xpos5+tx5)%(sum))-width,((yl12+ypos5+ty5)%(sum1)-2*width),null);
                c.drawBitmap(q4,((xl4+xpos6+tx5)%(sum))-width,((yl12+ypos6+ty5)%(sum1)-2*width),null);
                c.drawBitmap(q5,((xl6+xpos+tx5)%(sum))-width,((yl12+ypos+ty5)%(sum1)-2*width),null);
                c.drawBitmap(q1,((xl8+xpos+tx)%(sum))-width,((yl12+ypos1+ty)%(sum1)-2*width),null);*/



                //c.drawRect(0,0,c.getWidth(),c.getHeight()/10,action_colour);
                c.drawBitmap(bg,0,-c.getHeight()+c.getHeight()/10,null);
                //   c.drawRect(0,0,c.getHeight()/40,c.getHeight(),action_colour);

               // c.drawRect(c.getWidth()-c.getHeight()/40,0,c.getWidth(),c.getHeight(),action_colour);
                c.drawBitmap(arrowl,c.getHeight()/40,c.getHeight()/48, null);

                 Rect bounds = new Rect();


                text1.getTextBounds("Sponsors", 0, "Sponsors".length(), bounds);
                 float xx = bounds.width();
                 float yy = bounds.height();




                c.drawText("Sponsors", c.getWidth()/2-xx/2,c.getHeight()/24+yy/2, text1);


                 bounds = new Rect();
                text2.getTextBounds(msg, 0, msg.length(), bounds);
                 xx = bounds.width();
                 yy = bounds.height();


                c.drawText(msg, c.getWidth()/2 - xx / 2, c.getHeight()/2 - yy / 2 , text2);


                holder.unlockCanvasAndPost(c);


                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void pause(){
            isitok=false;

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
            start=true;
            t=new Thread(this);
            t.start();
        }




    }
}


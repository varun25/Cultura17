package com.cmrit.cultura17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by Varun on 20-02-2017.
 */

public class VrListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vr_list);
        startAnimation();
    }

    ImageView bhoot;
    TextView vrbox;


    private void startAnimation() {
        bhoot = (ImageView) findViewById(R.id.bhoot);
        vrbox = (TextView) findViewById(R.id.getready);

        final AlphaAnimation anim, anim2;

        anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        bhoot.startAnimation(anim);

        anim2 = new AlphaAnimation(0.0f, 1.0f);
        anim2.setDuration(2000);

        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                }
                VrListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                        } finally {
                            vrbox.setVisibility(View.VISIBLE);
                            vrbox.startAnimation(anim2);
                            startAnimation2();
                        }
                    }
                });super.run();
            }
        };
        timer.start();
    }

    LinearLayout lll;
    ImageView dusrabhoot ;
    private void startAnimation2() {
        lll = (LinearLayout) findViewById(R.id.mainl);
        dusrabhoot = (ImageView) findViewById(R.id.dusrabhoot);
        Thread timer1 = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                }
                VrListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                        } finally {
                            vrbox.setVisibility(View.GONE);
                            bhoot.setVisibility(View.GONE);
                            final AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
                            anim.setDuration(1000);
                            lll.startAnimation(anim);
                            lll.setVisibility(View.VISIBLE);
                            dusrabhoot.setVisibility(View.VISIBLE);
                            final AlphaAnimation anim5 = new AlphaAnimation(0.0f, 1.0f);
                            anim5.setDuration(1000);
                            dusrabhoot.startAnimation(anim5);
                        }
                    }
                });super.run();
            }
        };
        timer1.start();
    }

    public void pic1(View view)
    {
        Intent i = new Intent(VrListActivity.this, VrActivity.class);
        Bundle mBundle=new Bundle();
        mBundle.putInt("pic",0);
        i.putExtras(mBundle);
        startActivity(i);
    }

    public void pic2(View view)
    {
        Intent i = new Intent(VrListActivity.this, VrActivity.class);
        Bundle mBundle=new Bundle();
        mBundle.putInt("pic",2);
        i.putExtras(mBundle);
        startActivity(i);
    }

    public void pic3(View view)
    {
        Intent i = new Intent(VrListActivity.this, VrActivity.class);
        Bundle mBundle=new Bundle();
        mBundle.putInt("pic",1);
        i.putExtras(mBundle);
        startActivity(i);
    }

}
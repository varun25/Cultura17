package com.cmrit.cultura17;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Varun on 11-12-2016.
 */

public class ScheduleList extends RecyclerView.Adapter<ScheduleList.RecyclerViewHolder> {

    String[] time, name, venue;
    int day;


    public ScheduleList(String[] time, String[] name, String[] venue, int day)
    {
        this.time = time;
        this.name = name;
        this.venue = venue;
        this.day = day;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.eventrow,viewGroup,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int i) {

        int noOfEvents;
        if(day==1) noOfEvents = ScheduleActivity.noOfEventsDay1;
        else       noOfEvents = ScheduleActivity.noOfEventsDay2;
        i=i*2;
        if(i<=noOfEvents)
        {
            int timeInt;
            timeInt = Integer.parseInt(time[i].substring(0, 1) + time[i].substring(1, 2));
            if(timeInt<12)
                viewHolder.etime1.setText(time[i] + " AM");
            else if(timeInt==12)
                viewHolder.etime1.setText("12:00 PM");
            else
                viewHolder.etime1.setText("0" + timeInt%12 + time[i].substring(2,4) + "0 PM");
            viewHolder.ename1.setText(name[i]);
            viewHolder.evenue1.setText(venue[i]);
            if(i!=(noOfEvents-1))
            {
                timeInt = Integer.parseInt(time[i+1].substring(0, 1) + time[i+1].substring(1, 2));
                if(timeInt<12)
                    viewHolder.etime2.setText(time[i+1] + " AM");
                else if(timeInt==12)
                    viewHolder.etime2.setText("12:00 PM");
                else
                    viewHolder.etime2.setText("0" + timeInt%12 + time[i+1].substring(2,4) + "0 PM");
                viewHolder.ename2.setText(name[i + 1]);
                viewHolder.evenue2.setText(venue[i + 1]);
                setScaleAnimation(viewHolder.fp1);
                setScaleAnimation(viewHolder.fp2);
                setScaleAnimation(viewHolder.im1);
                setScaleAnimation(viewHolder.im2);
                setScaleAnimation(viewHolder.im3);
                setScaleAnimation(viewHolder.im4);
                setScaleAnimation(viewHolder.im5);
                setScaleAnimation(viewHolder.im6);
                viewHolder.im4.setVisibility(View.VISIBLE);
                viewHolder.im5.setVisibility(View.VISIBLE);
                viewHolder.im6.setVisibility(View.VISIBLE);
                viewHolder.fp1.setVisibility(View.VISIBLE);
                viewHolder.fp2.setVisibility(View.VISIBLE);
            }
            else
            {
                viewHolder.fp2.setVisibility(View.INVISIBLE);
                viewHolder.im4.setVisibility(View.INVISIBLE);
                viewHolder.im5.setVisibility(View.INVISIBLE);
                viewHolder.im6.setVisibility(View.INVISIBLE);
                viewHolder.etime2.setText("");
                viewHolder.ename2.setText("");
                viewHolder.evenue2.setText("");
            }

            movein2(viewHolder.ename1);
            movein2(viewHolder.etime1);
            movein2(viewHolder.evenue1);
            movein5(viewHolder.ename2);
            movein5(viewHolder.etime2);
            movein5(viewHolder.evenue2);
        }
    }

    @Override
    public int getItemCount() {
        if(day==1)
        return ((ScheduleActivity.noOfEventsDay1%2==0)? ScheduleActivity.noOfEventsDay1/2: ScheduleActivity.noOfEventsDay1/2+1);
        else
            return ((ScheduleActivity.noOfEventsDay2%2==0)? ScheduleActivity.noOfEventsDay2/2: ScheduleActivity.noOfEventsDay2/2+1);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{


        TextView etime1, ename1, evenue1, etime2, ename2, evenue2;
        ImageView fp1, fp2, im1, im2, im3, im4, im5, im6;

        public RecyclerViewHolder(View view)
        {
            super(view);

            etime1 = (TextView) view.findViewById(R.id.etime1);
            ename1 = (TextView) view.findViewById(R.id.ename1);
            evenue1 = (TextView) view.findViewById(R.id.evenue1);
            etime2 = (TextView) view.findViewById(R.id.etime2);
            ename2 = (TextView) view.findViewById(R.id.ename2);
            evenue2 = (TextView) view.findViewById(R.id.evenue2);
            fp1 = (ImageView) view.findViewById(R.id.foodpic1);
            fp2 = (ImageView) view.findViewById(R.id.foodpic2);
            im1 = (ImageView) view.findViewById(R.id.im1);
            im2 = (ImageView) view.findViewById(R.id.im2);
            im3 = (ImageView) view.findViewById(R.id.im3);
            im4 = (ImageView) view.findViewById(R.id.im4);
            im5 = (ImageView) view.findViewById(R.id.im5);
            im6 = (ImageView) view.findViewById(R.id.im6);
        }
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(2000);
        view.startAnimation(anim);
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

    private void movein1(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationX", -400,0);
        anim.setDuration(1000);
        anim.setTarget(view);
        anim.start();
    }
    private void movein2(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationX", -400,0);
        anim.setDuration(1000);
        anim.setTarget(view);
        anim.start();
    }
    private void movein3(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationX", -400,0);
        anim.setDuration(200);
        anim.setTarget(view);
        anim.start();
    }
    private void movein4(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationX", 400,0);
        anim.setDuration(1000);
        anim.setTarget(view);
        anim.start();
    }
    private void movein5(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationX", 400,0);
        anim.setDuration(1000);
        anim.setTarget(view);
        anim.start();
    }
    private void movein6(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationX", 400,0);
        anim.setDuration(200);
        anim.setTarget(view);
        anim.start();
    }
}


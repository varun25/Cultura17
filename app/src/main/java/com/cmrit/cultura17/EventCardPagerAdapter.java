package com.cmrit.cultura17;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import carbon.widget.CardView;

/**
 * Created by Kushal I on 31/12/2016.
 */

public class EventCardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<EventCardItem> mData;
    private float mBaseElevation;

    public EventCardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(EventCardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.page_event_card, container, false);
        container.addView(view);

        if(mData.get(position).getEno()!=69) {
            view.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent mIntent;
                    mIntent = new Intent(v.getContext(), EventDetailActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("enum", mData.get(position).getEno());
                    mBundle.putString("image", mData.get(position).getImage());
                    mIntent.putExtras(mBundle);
                    v.getContext().startActivity(mIntent);
                }
            });
        }
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getElevation();
        }

        cardView.setElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(final EventCardItem item, final View view) {
        TextView eventName = (TextView) view.findViewById(R.id.eventName);
        eventName.setText(item.getName());

        TextView eventDesc = (TextView) view.findViewById(R.id.eventDesc);
        eventDesc.setText(item.getDesc());
        final ImageView eventImage = (ImageView) view.findViewById(R.id.eventImage);

        Glide.with(view.getContext())
                .load(item.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
                .into(eventImage);
    }

    public static float convertDip2Pixels(Context context, int dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }
}

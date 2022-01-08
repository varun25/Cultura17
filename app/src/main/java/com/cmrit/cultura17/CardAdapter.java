package com.cmrit.cultura17;


import carbon.widget.CardView;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 3;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}

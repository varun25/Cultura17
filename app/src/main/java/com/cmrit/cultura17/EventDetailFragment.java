package com.cmrit.cultura17;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cmrit.cultura17.DBModel.EC;
import com.cmrit.cultura17.DBModel.Events;

import java.util.ArrayList;
import java.util.List;

public class EventDetailFragment extends Fragment {
    TextView eName,eDesc,eRate,eRules,ePrize,eVenue,eCont;
    ImageView eImage;
    String prize,ec;
    int eno;
    String eventImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_event_detail, container, false);

        ImageView back = (ImageView) view.findViewById(R.id.event_details_back);
        back.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                getActivity().finish();
                return true;
            }
        });

        eno = getArguments().getInt("eventNum");
        eventImage = getArguments().getString("eventImage");

        CulturaDBHelper db = new CulturaDBHelper(getActivity());
        Events data = new Events();
        List<EC> eclist = new ArrayList<EC>();
        data = db.getEventDetail(eno);
        eclist = db.getECForEvent(eno);

        eImage = (ImageView) view.findViewById(R.id.eImage);
        eName = (TextView) view.findViewById(R.id.eName);
        eDesc = (TextView) view.findViewById(R.id.eDesc);
        eRate = (TextView) view.findViewById(R.id.eRate);
        eRules = (TextView) view.findViewById(R.id.eRules);
        ePrize = (TextView) view.findViewById(R.id.ePrize);
        eVenue = (TextView) view.findViewById(R.id.eVenue);
        eCont = (TextView) view.findViewById(R.id.eCont);

        Glide.with(view.getContext())
                .load(eventImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
                .into(eImage);

        eName.setText(data.getEname());
        eDesc.setText(data.getEdesc());
        eRate.setText("Rs. "+data.getErate());
        eRules.setText(data.getErules());
        eVenue.setText(data.getVenue());
        if (data.getEno() == 45) {
            if(!data.getPrize1().equals(""+0)) prize="Best Delegate : Rs. "+data.getPrize1();
            if(!data.getPrize2().equals(""+0)) prize = prize+"\nHonorable Delegate : Rs. "+data.getPrize2();
        } else {
            if(!data.getPrize1().equals(""+0)) prize="1st Place : Rs. "+data.getPrize1();
            if(!data.getPrize2().equals(""+0)) prize = prize+"\n2nd Place : Rs. "+data.getPrize2();
        }
        ePrize.setText(prize);
        for(int i=0;i<eclist.size();i++) {
            if(i==0) ec = eclist.get(i).getEcname()+" - "+ eclist.get(i).getEcphno();
            else ec = ec+"\n"+eclist.get(i).getEcname()+" - "+ eclist.get(i).getEcphno();
        }
        eCont.setText(ec);

        ImageView adViewED = (ImageView) view.findViewById(R.id.adViewED);

        Glide.with(this)
                .load("http://203.201.63.39/html/cultura17/eventsponsors/"+data.getCno()+"_"+data.getEno()+".png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(adViewED);

        ObservableScrollView scrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);

        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {

                View view = scrollView.findViewById(R.id.eImage);

                if(view!=null){
                    view.setTranslationY(scrollView.getScrollY() / 2);
                }

            }
        });

        return  view;
    }

}

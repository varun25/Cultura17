package com.cmrit.cultura17;

/**
 * Created by Kushal I on 31/12/2016.
 */

public class EventCardItem {

    private int eno;
    private String eventImage;
    private String eventName;
    private String eventDesc;


    public EventCardItem(int eno, String eventImage, String eventName, String eventDesc) {
        this.eno = eno;
        this.eventImage = eventImage;
        this.eventName = eventName;
        this.eventDesc = eventDesc;
    }

    public int getEno() {
        return eno;
    }

    public String getImage() {
        return eventImage;
    }

    public String getName() {
        return eventName;
    }

    public String getDesc() {
        return eventDesc;
    }
}

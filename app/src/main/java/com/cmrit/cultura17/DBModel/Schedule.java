package com.cmrit.cultura17.DBModel;

/**
 * Created by Kushal I on 23/12/2016.
 */

public class Schedule {

    private int eno;
    private int cno;
    private String ename;
    private String venue;
    private String time;
    private int day;
    private String cname;



    // Empty constructor
    public Schedule(){

    }

    // constructor
    public Schedule(int eno, int cno, String ename, String venue, String stime, String etime, int day){
        this.eno = eno;
        this.cno = cno;
        this.ename = ename;
        this.venue = venue;
        this.time = time;
        this.day=day;
        this.cname = cname;
    }

    public int getEno(){
        return this.eno;
    }

    public void setEno(int eno){
        this.eno = eno;
    }

    public int getCno(){
        return this.cno;
    }

    public void setCno(int cno){
        this.cno = cno;
    }

    public String getEname(){
        return this.ename;
    }

    public void setEname(String ename){
        this.ename = ename;
    }

    public String getCname(){
        return this.cname;
    }

    public void setCname(String cname){
        this.cname = cname;
    }

    public String getVenue(){
        return this.venue;
    }

    public void setVenue(String venue){
        this.venue = venue;
    }


    public String getTime(){
        return this.time;
    }

    public void setTime(String etime){
        this.time = etime;
    }

    public int getDay(){
        return this.day;
    }

    public void setDay(int day){
        this.day = day;
    }

}

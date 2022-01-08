package com.cmrit.cultura17.DBModel;

/**
 * Created by Kushal I on 17/12/2016.
 */

public class Events {

    private int id;
    private int eno;
    private int cno;
    private String ename;
    private String cname;
    private String edesc;
    private String erules;
    private String erate;
    private String prize1;
    private String prize2;
    private String prize3;
    private String venue;
    private String time;
    private String day;
    private String picurl;



    // Empty constructor
    public Events(){

    }

    // constructor
    public Events(int id, int eno, int cno, String ename,String cname, String edesc, String erules, String erate,
                  String prize1, String prize2, String prize3,String venue, String time, String day, String picurl){
        this.id = id;
        this.eno = eno;
        this.cno = cno;
        this.ename = ename;
        this.cname = cname;
        this.edesc = edesc;
        this.erules = erules;
        this.erate = erate;
        this.prize1 = prize1;
        this.prize2 = prize2;
        this.prize3 = prize3;
        this.venue = venue;
        this.cname = cname;
        this.day=day;
        this.picurl=picurl;
    }

    // constructor
    public Events(int eno, int cno, String ename, String cname, String edesc, String erules, String erate,
                  String prize1, String prize2, String prize3, String venue, String time, String day, String picurl){
        this.eno = eno;
        this.cno = cno;
        this.ename = ename;
        this.cname=cname;
        this.edesc = edesc;
        this.erules = erules;
        this.erate = erate;
        this.prize1 = prize1;
        this.prize2 = prize2;
        this.prize3 = prize3;
        this.venue = venue;
        this.time = time;
        this.day=day;
        this.picurl=picurl;
    }

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
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

    public String getErate(){
        return this.erate;
    }

    public void setErate(String erate){
        this.erate = erate;
    }

    public String getPrize1(){
        return this.prize1;
    }

    public void setPrize1(String prize1){
        this.prize1 = prize1;
    }

    public String getPrize2(){
        return this.prize2;
    }

    public void setPrize2(String prize2){
        this.prize2 = prize2;
    }

    public String getPrize3(){
        return this.prize3;
    }

    public void setPrize3(String prize3){
        this.prize3 = prize3;
    }

    public String getDay(){
        return this.day;
    }

    public void setDay(String day){
        this.day = day;
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

    public String getEdesc(){
        return this.edesc;
    }

    public void setEdesc(String edesc){
        this.edesc = edesc;
    }

    public String getErules(){
        return this.erules;
    }

    public void setErules(String erules){
        this.erules = erules;
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

    public void setTime(String time){
        this.time = time;
    }

    public String getPicurl(){
        return this.picurl;
    }

    public void setPicurl(String picurl){
        this.picurl = picurl;
    }
}

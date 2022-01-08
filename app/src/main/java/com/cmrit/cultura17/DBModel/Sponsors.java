package com.cmrit.cultura17.DBModel;

/**
 * Created by Kushal I on 23/12/2016.
 */

public class Sponsors {
    private int id;
    private int sno;
    private String sname;
    private String picurl;
    private String type;

    // Empty constructor
    public Sponsors(){
    }

    // constructor
    public Sponsors(int id, int sno, String sname, String picurl, String type){
        this.id = id;
        this.sno = sno;
        this.sname = sname;
        this.picurl = picurl;
        this.type = type;
    }

    // constructor
    public Sponsors(int sno, String sname, String picurl, String type){
        this.sno = sno;
        this.sname = sname;
        this.picurl = picurl;
        this.type = type;
    }

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getSno(){
        return this.sno;
    }

    public void setSno(int sno){
        this.sno = sno;
    }

    public String getSname(){
        return this.sname;
    }

    public void setSname(String sname){
        this.sname = sname;
    }

    public String getPicurl(){
        return this.picurl;
    }

    public void setPicurl(String picurl){
        this.picurl = picurl;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

}

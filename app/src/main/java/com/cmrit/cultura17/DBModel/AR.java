package com.cmrit.cultura17.DBModel;

/**
 * Created by Kushal I on 10/02/2017.
 */

public class AR {

    private int id;
    private int oid;
    private String oname;
    private String olat;
    private String olon;
    private String oalt;

    // Empty constructor
    public AR(){
    }

    // constructor
    public AR(int id, int oid, String oname, String olat, String olon, String oalt){
        this.id = id;
        this.oid = oid;
        this.oname = oname;
        this.olat = olat;
        this.olon = olon;
        this.oalt = oalt;
    }

    // constructor
    public AR(int oid, String oname, String olat, String olon, String oalt){
        this.oid = oid;
        this.oname = oname;
        this.olat = olat;
        this.olon = olon;
        this.oalt = oalt;
    }

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getOid(){
        return this.oid;
    }

    public void setOid(int oid){
        this.oid = oid;
    }

    public String getOname(){
        return this.oname;
    }

    public void setOname(String oname){
        this.oname = oname;
    }

    public String getOlat(){
        return this.olat;
    }

    public void setOlat(String olat) {
        this.olat = olat;
    }

    public String getOlon(){
        return this.olon;
    }

    public void setOlon(String olon) {
        this.olon = olon;
    }

    public String getOalt(){
        return this.oalt;
    }

    public void setOalt(String oalt) {
        this.oalt = oalt;
    }
}

package com.cmrit.cultura17;

/**
 * Created by Varun on 11-12-2016.
 */

public class EventProvider {

    private String abc, def, ghi;

    public EventProvider(String abc, String def, String ghi)
    {
        this.abc = abc;
        this.def = def;
        this.ghi = ghi;
    }

    public String getAbc() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }

    public String getGhi() {
        return ghi;
    }

    public void setGhi(String ghi) {
        this.ghi = ghi;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }
}

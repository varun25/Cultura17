package com.cmrit.cultura17.DBModel;

/**
 * Created by Kushal I on 23/12/2016.
 */

public class EC {

    private int id;
    private int eno;
    private String ecname;
    private String ecmail;
    private String ecphno;

    // Empty constructor
    public EC(){
    }

    // constructor
    public EC(int id, int eno, String ecname, String ecmail, String ecphno){
        this.id = id;
        this.eno = eno;
        this.ecname = ecname;
        this.ecmail = ecmail;
        this.ecphno = ecphno;
    }

    // constructor
    public EC(int eno, String ecname, String ecmail, String ecphno){
        this.eno = eno;
        this.ecname = ecname;
        this.ecmail = ecmail;
        this.ecphno = ecphno;
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

    public String getEcname(){
        return this.ecname;
    }

    public void setEcname(String ecname){
        this.ecname = ecname;
    }

    public String getEcmail(){
        return this.ecmail;
    }

    public void setEcmail(String ecmail) {
        this.ecmail = ecmail;
    }

    public String getEcphno(){
        return this.ecphno;
    }

    public void setEcphno(String ecphno){
        this.ecphno = ecphno;
    }

}

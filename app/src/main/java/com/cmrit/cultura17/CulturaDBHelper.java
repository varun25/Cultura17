package com.cmrit.cultura17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cmrit.cultura17.DBModel.AR;
import com.cmrit.cultura17.DBModel.EC;
import com.cmrit.cultura17.DBModel.Events;
import com.cmrit.cultura17.DBModel.Schedule;
import com.cmrit.cultura17.DBModel.Sponsors;

import java.util.ArrayList;
import java.util.List;


public class CulturaDBHelper  extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CulturaDB.db";

    // Create events table in SQL
    private static final String CREATE_EVENTS =
            "create table events(" +
            "_id integer primary key autoincrement,"+
            "eno integer not null,"+
            "cno integer not null,"+
            "ename text not null,"+
            "cname text not null,"+
            "edesc text not null,"+
            "erules text not null,"+
            "erate text not null,"+
            "prize1 text not null,"+
            "prize2 text not null,"+
            "prize3 text not null,"+
            "venue text not null,"+
            "time text not null,"+
            "day integer not null,"+
            "picurl text not null);";

    // Create ec table in SQL
    private static final String CREATE_EC =
            "create table ec(" +
            "_id integer primary key autoincrement,"+
            "eno int not null,"+
            "ecname text not null,"+
            "ecmail text not null,"+
            "ecphno text not null);";

    // Create sponsors table in SQL
    private static final String CREATE_SPONSORS =
            "create table sponsors(" +
            "_id integer primary key autoincrement,"+
            "sno int not null,"+
            "sname text not null,"+
            "picurl text not null,"+
            "type text not null);";

    // Create AR table in SQL
    private static final String CREATE_AR =
            "create table ar(" +
                    "_id integer primary key autoincrement,"+
                    "oid int not null,"+
                    "oname text not null,"+
                    "olat text not null,"+
                    "olon text not null,"+
                    "oalt text not null);";


    public CulturaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTS);
        db.execSQL(CREATE_EC);
        db.execSQL(CREATE_SPONSORS);
        db.execSQL(CREATE_AR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS events, ec, sponsors, ar;");
        onCreate(db);
    }


    // Insert all events in table events
    public void insertAllEvents(List<Events> data) {
        int size = data.size();
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < size ; i++) {

            ContentValues values = new ContentValues();

            values.put("eno", data.get(i).getEno());
            values.put("cno", data.get(i).getCno());
            values.put("ename", data.get(i).getEname());
            values.put("cname", data.get(i).getCname());
            values.put("edesc", data.get(i).getEdesc());
            values.put("erules", data.get(i).getErules());
            values.put("erate", data.get(i).getErate());
            values.put("prize1", data.get(i).getPrize1());
            values.put("prize2", data.get(i).getPrize2());
            values.put("prize3", data.get(i).getPrize3());
            values.put("venue", data.get(i).getVenue());
            values.put("time", data.get(i).getTime());
            values.put("day", data.get(i).getDay());
            values.put("picurl", data.get(i).getPicurl());

            // Inserting Row
            db.insert("events", null, values);
        }

        db.close();
    }

    // Insert all ec in table ec
    public void insertAllEC(List<EC> data) {
        int size = data.size();
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < size ; i++) {

            ContentValues values = new ContentValues();

            values.put("eno", data.get(i).getEno());
            values.put("ecname", data.get(i).getEcname());
            values.put("ecmail", data.get(i).getEcmail());
            values.put("ecphno", data.get(i).getEcphno());


            // Inserting Row
            db.insert("ec", null, values);
        }

        db.close();

    }

    // Insert all objects in table AR
    public void insertAllARObjects(List<AR> data) {
        int size = data.size();
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < size ; i++) {

            ContentValues values = new ContentValues();

            values.put("oid", data.get(i).getOid());
            values.put("oname", data.get(i).getOname());
            values.put("olat", data.get(i).getOlat());
            values.put("olon", data.get(i).getOlon());
            values.put("oalt", data.get(i).getOalt());

            // Inserting Row
            db.insert("ar", null, values);
        }
        db.close();

    }

    // Insert all sponsors in table sponsors
    public void insertAllSponsors(List<Sponsors> data) {
        int size = data.size();
        SQLiteDatabase db = this.getWritableDatabase();


        for (int i = 0; i < size ; i++) {

            ContentValues values = new ContentValues();

            values.put("sno", data.get(i).getSno());
            values.put("sname", data.get(i).getSname());
            values.put("picurl", data.get(i).getPicurl());
            values.put("type", data.get(i).getType());

            // Inserting Row
            db.insert("sponsors", null, values);
        }

        db.close();

    }


    // Get all events of a particular category
    public List<Events> getAllEvents(int cno) {
        List<Events> eventsList = new ArrayList<Events>();
        String query = "select * " +
                       "from events " +
                       "where cno = "+cno+";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Events events = new Events();

                events.setID(Integer.parseInt(cursor.getString(0)));
                events.setEno(Integer.parseInt(cursor.getString(1)));
                events.setCno(Integer.parseInt(cursor.getString(2)));
                events.setEname(cursor.getString(3));
                events.setCname(cursor.getString(4));
                events.setEdesc(cursor.getString(5));
                events.setErules(cursor.getString(6));
                events.setErate(cursor.getString(7));
                events.setPrize1(cursor.getString(8));
                events.setPrize2(cursor.getString(9));
                events.setPrize3(cursor.getString(10));
                events.setVenue(cursor.getString(11));
                events.setTime(cursor.getString(12));
                events.setDay(cursor.getString(13));
                events.setPicurl(cursor.getString(14));

                // Adding events to list
                eventsList.add(events);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventsList;
    }

    // Get all EC for a particular event
    public List<EC> getECForEvent(int eno) {
        List<EC> eclist = new ArrayList<EC>();
        String query = "select * " +
                "from ec " +
                "where eno = " + eno + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EC ec = new EC();

                ec.setEno(Integer.parseInt(cursor.getString(0)));
                ec.setEcname(cursor.getString(2));
                ec.setEcmail(cursor.getString(3));
                ec.setEcphno(cursor.getString(4));

                // Adding ec to list
                eclist.add(ec);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eclist;
    }

    //Get Sponsor
    public List<Sponsors> getSponsors() {
        List<Sponsors> slist = new ArrayList<Sponsors>();
        String query = "select * " +
                "from sponsors;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Sponsors s = new Sponsors();

                s.setSno(Integer.parseInt(cursor.getString(0)));
                s.setSname(cursor.getString(2));
                s.setPicurl(cursor.getString(3));
                s.setType(cursor.getString(4));

                // Adding ec to list
                slist.add(s);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return slist;
    }

    // Get details of a particular event
    public Events getEventDetail(int eno) {
        Events event = new Events();
        String query = "select * " +
                "from events " +
                "where eno = " + eno + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            event.setID(Integer.parseInt(cursor.getString(0)));
            event.setEno(Integer.parseInt(cursor.getString(1)));
            event.setCno(Integer.parseInt(cursor.getString(2)));
            event.setEname(cursor.getString(3));
            event.setCname(cursor.getString(4));
            event.setEdesc(cursor.getString(5));
            event.setErules(cursor.getString(6));
            event.setErate(cursor.getString(7));
            event.setPrize1(cursor.getString(8));
            event.setPrize2(cursor.getString(9));
            event.setPrize3(cursor.getString(10));
            event.setVenue(cursor.getString(11));
            event.setTime(cursor.getString(12));
            event.setDay(cursor.getString(13));
            event.setPicurl(cursor.getString(14));
        }
        cursor.close();
        db.close();
        return event;
    }

    // Get all events schedule on a particular day
    public List<Schedule> getScheduleOnDay(int day) {
        List<Schedule> schedulelist = new ArrayList<Schedule>();
        String query = "select eno,cno,ename,cname,venue,time,day " +
                "from events " +
                "where day = " + day + " ORDER BY time;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Schedule schedule = new Schedule();

                schedule.setEno(Integer.parseInt(cursor.getString(0)));
                schedule.setCno(Integer.parseInt(cursor.getString(1)));
                schedule.setEname(cursor.getString(2));
                schedule.setCname(cursor.getString(3));
                schedule.setVenue(cursor.getString(4));
                schedule.setTime(cursor.getString(5));
                schedule.setDay(Integer.parseInt(cursor.getString(6)));

                // Adding schedule to list
                schedulelist.add(schedule);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return schedulelist;
    }

    // Get all AR objects
    public  List<AR> getAllARObjects() {
        List<AR> arList = new ArrayList<AR>();
        String query = "select * " +
                "from ar;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                AR data = new AR();

                data.setID(Integer.parseInt(cursor.getString(0)));
                data.setOid(Integer.parseInt(cursor.getString(1)));
                data.setOname(cursor.getString(2));
                data.setOlat(cursor.getString(3));
                data.setOlon(cursor.getString(4));
                data.setOalt(cursor.getString(5));

                // Adding object to list
                arList.add(data);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arList;
    }

    public void deleteAllTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM events;");
        db.execSQL("DELETE FROM ec;");
        db.execSQL("DELETE FROM sponsors;");
        db.execSQL("DELETE FROM ar;");
        db.close();
    }

    public int getSponsorsCount() {
        String countQuery = "SELECT * FROM sponsors;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public boolean checkExistingData() {
        SQLiteDatabase db = this.getReadableDatabase();
        long cnt  = DatabaseUtils.queryNumEntries(db, "events");
        db.close();
        return cnt > 0;
    }
}

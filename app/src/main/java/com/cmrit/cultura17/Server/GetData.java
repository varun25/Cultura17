package com.cmrit.cultura17.Server;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.cmrit.cultura17.CulturaDBHelper;
import com.cmrit.cultura17.DBModel.EC;
import com.cmrit.cultura17.DBModel.Events;
import com.cmrit.cultura17.DBModel.Schedule;
import com.cmrit.cultura17.DBModel.Sponsors;
import com.cmrit.cultura17.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Varun on 30-12-2016.
 */

public class GetData extends AppCompatActivity {

    private ProgressDialog pDialog;

    private static String url = "cultura17.000webhostapp.com/culturaserver/v1/getEverything";

    List<EC> ecList;
    List<Sponsors> sponsorList;
    List<Events> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serverback);

        ecList = new ArrayList<>();
        sponsorList = new ArrayList<>();
        eventList = new ArrayList<>();

        new getEverything().execute();


    }

    private class getEverything extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(GetData.this);
            pDialog.setMessage("Refreshing events and schedules");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        String jsonStr;
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray ecs = jsonObj.getJSONArray("ecs");
                    JSONArray events = jsonObj.getJSONArray("events");
                    JSONArray sponsors = jsonObj.getJSONArray("sponsors");

                    for (int i = 0; i < ecs.length(); i++) {
                        JSONObject c = ecs.getJSONObject(i);

                        final EC ecObj = new EC();

                        ecObj.setEno(c.getInt("eno"));
                        ecObj.setEcname(c.getString("name"));
                        ecObj.setEcmail(c.getString("email"));
                        ecObj.setEcphno(c.getString("phno"));

                        ecList.add(ecObj);
                    }

                    for (int i = 0; i < events.length(); i++) {
                        JSONObject c = events.getJSONObject(i);

                        final Events eventObj = new Events();

                        eventObj.setEno(c.getInt("eno"));
                        eventObj.setCno(c.getInt("cid"));
                        eventObj.setEname(c.getString("ename"));
                        eventObj.setCname(c.getString("cname"));
                        eventObj.setEdesc(c.getString("descrip"));
                        eventObj.setErules(c.getString("rules"));
                        eventObj.setErate(c.getString("rate"));
                        eventObj.setPrize1(c.getString("prize1"));
                        eventObj.setPrize2(c.getString("prize2"));
                        eventObj.setPrize3(c.getString("prize3"));
                        eventObj.setVenue(c.getString("venue"));
                        eventObj.setDay(c.getString("date"));
                        eventObj.setTime(c.getString("time"));
                        eventObj.setPicurl(c.getString("pic"));

                        eventList.add(eventObj);
                    }

                    for (int i = 0; i < sponsors.length(); i++) {
                        JSONObject c = sponsors.getJSONObject(i);

                        final Sponsors sponsorObj = new Sponsors();

                        sponsorObj.setSno(c.getInt("sid"));
                        sponsorObj.setSname(c.getString("sname"));
                        sponsorObj.setPicurl(c.getString("pic"));
                        sponsorObj.setType(c.getString("type"));

                        sponsorList.add(sponsorObj);
                    }
                } catch (final JSONException e) {
                    System.out.print(e.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Server is being updated, please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Could not connect to server\n Please check your internet connection",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            TextView tv = (TextView) findViewById(R.id.serverdata);
            tv.setText(jsonStr);
            if (pDialog.isShowing())
                pDialog.dismiss();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Toast.makeText(getApplicationContext(),
                            "Events and schedules updated successfully",
                            Toast.LENGTH_LONG)
                            .show();
                    CulturaDBHelper db = new CulturaDBHelper(getBaseContext());

                    db.deleteAllTables();

                    List<Schedule> sl = new ArrayList<Schedule>();

                    sl = db.getScheduleOnDay(1);

                    db.insertAllEvents(eventList);
                    db.insertAllEC(ecList);
                    db.insertAllSponsors(sponsorList);
                }
            });
        }

    }
}


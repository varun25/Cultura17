package com.cmrit.cultura17;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.google.vr.sdk.widgets.pano.VrPanoramaView.Options;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Varun on 20-02-2017.
 */

public class VrActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    /** Actual panorama widget. **/
    private VrPanoramaView panoWidgetView1;//panoWidgetView2, panoWidgetView3;
    /**
     * Arbitrary variable to track load status. In this example, this variable should only be accessed
     * on the UI thread. In a real app, this variable would be code that performs some UI actions when
     * the panorama is fully loaded.
     */
    public boolean loadImageSuccessful;
    public String[] venue= "ps_bc.jpg ps_dhwani.jpg ps_ground.jpg".split(" ");
    public int pos;
    ProgressDialog pd;
    /** Tracks the file to be loaded across the lifetime of this app. **/
    private Uri fileUri;
    /** Configuration information for the panorama. **/
    private Options panoOptions = new Options();
    private ImageLoaderTask backgroundImageLoaderTask;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * Called when the app is launched via the app icon or an intent using the adb command above. This
     * initializes the app and loads the image to render.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr);
        pos = getIntent().getExtras().getInt("pic");
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait");
        pd.setTitle("Loading photosphere...");
        pd.show();
        // Make the source link clickable.
//        TextView sourceText = (TextView) findViewById(R.id.source);
//        sourceText.setText(Html.fromHtml(getString(R.string.source)));
//        sourceText.setMovementMethod(LinkMovementMethod.getInstance());

        panoWidgetView1 = (VrPanoramaView) findViewById(R.id.pano_view1);
        panoWidgetView1.setEventListener(new ActivityEventListener());
//        panoWidgetView2 = (VrPanoramaView) findViewById(R.id.pano_view2);
//        panoWidgetView2.setEventListener(new ActivityEventListener());
//        panoWidgetView3 = (VrPanoramaView) findViewById(R.id.pano_view3);
//        panoWidgetView3.setEventListener(new ActivityEventListener());
//        panoWidgetView1.setVisibility(View.VISIBLE);
//        panoWidgetView2.setVisibility(View.GONE);
//        panoWidgetView3.setVisibility(View.GONE);

        // Initial launch of the app or an Activity recreation due to rotation.
        handleIntent(getIntent());
    }

    /**
     * Called when the Activity is already running and it's given a new intent.
     */
    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, this.hashCode() + ".onNewIntent()");
        // Save the intent. This allows the getIntent() call in onCreate() to use this new Intent during
        // future invocations.
        setIntent(intent);
        // Load the new image.
        handleIntent(intent);
    }

    /**
     * Load custom images based on the Intent or load the default image. See the Javadoc for this
     * class for information on generating a custom intent via adb.
     */
    private void handleIntent(Intent intent) {
        // Determine if the Intent contains a file to load.
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Log.i(TAG, "ACTION_VIEW Intent recieved");

            fileUri = intent.getData();
            if (fileUri == null) {
                Log.w(TAG, "No data uri specified. Use \"-d /path/filename\".");
            } else {
                Log.i(TAG, "Using file " + fileUri.toString());
            }

            panoOptions.inputType = intent.getIntExtra("inputType", Options.TYPE_MONO);
            Log.i(TAG, "Options.inputType = " + panoOptions.inputType);
        } else {
            Log.i(TAG, "Intent is not ACTION_VIEW. Using default pano image.");
            fileUri = null;
            panoOptions.inputType = Options.TYPE_MONO;
        }

        // Load the bitmap in a background thread to avoid blocking the UI thread. This operation can
        // take 100s of milliseconds.
        if (backgroundImageLoaderTask != null) {
            // Cancel any task from a previous intent sent to this activity.
            backgroundImageLoaderTask.cancel(true);
        }
        backgroundImageLoaderTask = new ImageLoaderTask();
        backgroundImageLoaderTask.execute(Pair.create(fileUri, panoOptions));
    }

    @Override
    protected void onPause() {
        panoWidgetView1.pauseRendering();
//        panoWidgetView2.pauseRendering();
//        panoWidgetView3.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        panoWidgetView1.resumeRendering();
//        panoWidgetView2.resumeRendering();
//        panoWidgetView3.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        // Destroy the widget and free memory.
        panoWidgetView1.shutdown();
//        panoWidgetView2.shutdown();
//        panoWidgetView3.shutdown();

        // The background task has a 5 second timeout so it can potentially stay alive for 5 seconds
        // after the activity is destroyed unless it is explicitly cancelled.
        /*if (backgroundImageLoaderTask != null) {
            backgroundImageLoaderTask.cancel(true);
        }*/
        super.onDestroy();
        backgroundImageLoaderTask.cancel(true);
        Runtime.getRuntime().gc();
        Runtime.getRuntime().freeMemory();
        finish();
    }

    /**
     * Helper class to manage threading.
     */
    class ImageLoaderTask extends AsyncTask<Pair<Uri, Options>, Void, Boolean> {

        /**
         * Reads the bitmap from disk in the background and waits until it's loaded by pano widget.
         */
        @Override
        protected Boolean doInBackground(Pair<Uri, Options>... fileInformation) {
            Options panoOptions = null;  // It's safe to use null VrPanoramaView.Options.
            InputStream istr1=null, istr2=null, istr3=null;
            if (fileInformation == null || fileInformation.length < 1
                    || fileInformation[0] == null || fileInformation[0].first == null) {
                AssetManager assetManager = getAssets();
                try {
                    istr1 = assetManager.open(venue[pos]);
//                    istr2 = assetManager.open(venue[1]);
//                    istr3 = assetManager.open(venue[2]);
                    panoOptions = new Options();
                    panoOptions.inputType = Options.TYPE_MONO;
                } catch (IOException e) {
                    Log.e(TAG, "Could not decode default bitmap: " + e);
                    return false;
                }
            } else {
                try {
                    istr1 = new FileInputStream(new File(fileInformation[0].first.getPath()));
//                    istr2 = new FileInputStream(new File(fileInformation[0].first.getPath()));
//                    istr3 = new FileInputStream(new File(fileInformation[0].first.getPath()));
                    panoOptions = fileInformation[0].second;
                } catch (IOException e) {
                    Log.e(TAG, "Could not load file: " + e);
                    return false;
                }
            }

            try {
                panoWidgetView1.loadImageFromBitmap(BitmapFactory.decodeStream(istr1), panoOptions);
                pd.dismiss();
            }
            catch(OutOfMemoryError w){
                //backgroundImageLoaderTask.cancel(true);
                Runtime.getRuntime().gc();
                Runtime.getRuntime().freeMemory();
                new ImageLoaderTask().execute(Pair.create(fileUri, panoOptions));
                //Toast.makeText(getBaseContext(), "Out of memory!", Toast.LENGTH_LONG).show();
            }
//            panoWidgetView2.loadImageFromBitmap(BitmapFactory.decodeStream(istr2), panoOptions);
//            panoWidgetView3.loadImageFromBitmap(BitmapFactory.decodeStream(istr3), panoOptions);
            try {
                istr1.close();
//                istr2.close();
//                istr3.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close input stream: " + e);
            }

            return true;
        }
    }

    /**
     * Listen to the important events from widget.
     */
    private class ActivityEventListener extends VrPanoramaEventListener {
        /**
         * Called by pano widget on the UI thread when it's done loading the image.
         */
        @Override
        public void onLoadSuccess() {
            loadImageSuccessful = true;
        }

        /**
         * Called by pano widget on the UI thread on any asynchronous error.
         */
        @Override
        public void onLoadError(String errorMessage) {
            loadImageSuccessful = false;
            Toast.makeText(
                    VrActivity.this, "Error loading pano: " + errorMessage, Toast.LENGTH_LONG)
                    .show();
            Log.e(TAG, "Error loading pano: " + errorMessage);
        }
    }
}
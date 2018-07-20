package com.app.aadhi.dashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.aadhi.BirdApplication;
import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

import org.jsoup.Jsoup;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by administrator on 17/04/18.
 */

public class DashboardActivity extends RootActivity implements View.OnClickListener {

    @BindView(R.id.btn_informations)
    Button mInformationsBtn;
    @BindView(R.id.btn_services)
    Button mServicesBtn;
    private String currentVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_dashboard);
        //mToolBarTitle.setText("Aadhi");
        initViews();



        AppUpdater appUpdater = new AppUpdater(this);
        appUpdater.setUpdateFrom(UpdateFrom.GOOGLE_PLAY);
//        appUpdater.setTitleOnUpdateAvailable("Update available");
//        appUpdater.setContentOnUpdateAvailable("Check out the latest version available of my app!");
//        appUpdater.setTitleOnUpdateNotAvailable("Update not available");
//        appUpdater.setContentOnUpdateNotAvailable("No update available. Check for updates again later!");
        appUpdater.setCancelable(true);
        appUpdater.start();

//        try{
//            currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//        }catch (PackageManager.NameNotFoundException e){
//            Log.e(TAG, "onCreate: "+e.getMessage() );
//        }
//
//
//        new GetVersionCode().execute();

    }

    private void initViews() {
        ButterKnife.bind(this);
        hideToolbar();
        mInformationsBtn.setOnClickListener(this);
        mServicesBtn.setOnClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (BirdApplication.mBackGroundMediaPlayer != null && BirdApplication.mBackGroundMediaPlayer.isPlaying()) {
            BirdApplication.mBackGroundMediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BirdApplication.mBackGroundMediaPlayer != null) {
            BirdApplication.mBackGroundMediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_informations:
                startActivity(new Intent(DashboardActivity.this, InformationsMenuActivity.class));
                break;

            case R.id.btn_services:
                startActivity(new Intent(DashboardActivity.this, ServicesMenuActivity.class));
                break;
        }
    }

//
//
//    private class GetVersionCode extends AsyncTask<Void, String, String> {
//        @Override
//        protected String doInBackground(Void... voids) {
//
//            String newVersion = null;
//            try {
//                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + "com.pappaya.gemsams" + "&hl=it")
//                        .timeout(30000)
//                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
//                        .referrer("http://www.google.com")
//                        .get()
//                        .select(".hAyfc .htlgb")
////                        .select("div[itemprop=softwareVersion]")
//                        .first()
//                        .ownText();
//                return newVersion;
//            } catch (Exception e) {
//                return newVersion;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String onlineVersion) {
//            super.onPostExecute(onlineVersion);
//            if (onlineVersion != null && !onlineVersion.isEmpty()) {
//                if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
//                    //show dialog
//                }
//            }
//            Log.e("update", "Current version " + currentVersion + "playstore version " + onlineVersion);
//        }
//    }
//
//

//    private void showNeAppDialog(){
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DashboardActivity.this);
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Aadhi new app ...");
//
//        // Setting Dialog Message
//        alertDialog.setMessage("Are you sure you want to update new aadhi app ?");
//
//        // Setting Icon to Dialog
//        alertDialog.setIcon(R.mipmap.ic_launcher);
//
//        // Setting Positive "Yes" Button
//        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog,int which) {
//
//                // Write your code here to invoke YES event
//                Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Setting Negative "NO" Button
//        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to invoke NO event
////                Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
//                dialog.cancel();
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }

}

package com.app.aadhi;

import android.app.Application;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.app.aadhi.preference.Preferences;

/**
 * Created by administrator on 14/04/18.
 */

public class BirdApplication extends Application {

    private static BirdApplication sThis;
    private static String TAG = BirdApplication.class.getSimpleName();
    public static Typeface lato_Regular, lato_Bold;
    public static MediaPlayer mBackGroundMediaPlayer;
    @Override
    public void onCreate() {
        super.onCreate();
        sThis = this;
        mediaInit();
        Preferences.INSTANCE.createPreferences(sThis);

        lato_Regular = Typeface.createFromAsset(getAssets(), "font/Lato-Regular.ttf");
        lato_Bold = Typeface.createFromAsset(getAssets(), "font/Lato-Bold.ttf");

    }

    public static BirdApplication getThis() {
        return sThis;
    }

    private void mediaInit() {
        mBackGroundMediaPlayer = MediaPlayer.create(this, R.raw.om);
        mBackGroundMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mBackGroundMediaPlayer.prepareAsync();
        mBackGroundMediaPlayer.setLooping(true);  // for repeat song
//        mBackGroundMediaPlayer.start();
    }



}

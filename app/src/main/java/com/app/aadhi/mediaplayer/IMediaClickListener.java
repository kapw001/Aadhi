package com.app.aadhi.mediaplayer;

import android.graphics.Bitmap;

public interface IMediaClickListener {
    default void onAudioClicked(int id) { }

    default void onAudioClicked(String url) { }

    default void onVideoClicked(int id) { }

    default void onVideoClicked(String url) { }

    //default void onPhotoClicked(String url) { }
    default void onPhotoClicked(Bitmap bitmap) { }
}

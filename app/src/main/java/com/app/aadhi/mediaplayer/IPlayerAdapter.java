package com.app.aadhi.mediaplayer;

public interface IPlayerAdapter {

    void loadMedia(String url);

    void loadMedia(String url,AudioPlayer.PlayerStatus playerStatus);

    boolean isPlaying();

    void play();

    void play(AudioPlayer.PlayerStatus playerStatus);

    void pause();

    void reset();

    void release();

    void initializeProgressCallback();

    void seekTo(int position);
}

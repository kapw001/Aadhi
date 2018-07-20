package com.app.aadhi.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AudioPlayer implements IPlayerAdapter {

    private static final int PLAYBACK_POSITION_REFRESH_INTERVAL_MS = 1000;

    private final Context mContext;
    private MediaPlayer mMediaPlayer;
    private PlaybackInfoListener mPlaybackInfoListener;
    private ScheduledExecutorService mExecutor;
    private Runnable mSeekbarPositionUpdateTask;
    private boolean isMuted=false;

    private String mMediaUrl;

    public AudioPlayer(Context context) {
        this.mContext = context;
    }

    private void initializeMediaPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopUpdatingCallbackWithPosition(true);
                    logToUI("Media Player playback completed.");
                    if (mPlaybackInfoListener != null) {
                        mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.COMPLETED);
                        mPlaybackInfoListener.onPlaybackCompleted();
                    }
                }
            });
            logToUI("mMediaPlayer = new MediaPlayer()");
        }
    }

    public void setPlaybackInfoListener(PlaybackInfoListener listener) {
        mPlaybackInfoListener = listener;
    }

    // Implements PlaybackControl
    @Override
    public void loadMedia(String url) {



//        new PlayMusic(url).execute();

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mMediaUrl = url;
                initializeMediaPlayer();

                try {
                    logToUI("load() 1. setDataSource");
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mMediaPlayer.setDataSource(url);
                } catch (IOException e) {
                    logToUI(e.toString());
                }

                try {
                    logToUI("load() 2. prepare");
                    mMediaPlayer.prepare();
                } catch (Exception e) {
                    logToUI(e.toString());
                }
                initializeProgressCallback();
                play();
                logToUI("initializeProgressCallback()");
            }
        });


    }


    private class PlayMusic extends AsyncTask<Void,Void,MediaPlayer>{

        private String url;

        private PlayMusic(String url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            initializeMediaPlayer();
        }

        @Override
        protected MediaPlayer doInBackground(Void... voids) {

            try {
                logToUI("load() 1. setDataSource");
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setDataSource(url);
            } catch (IOException e) {
                logToUI(e.toString());
            }

            try {
                logToUI("load() 2. prepare");
                mMediaPlayer.prepare();
            } catch (Exception e) {
                logToUI(e.toString());
            }
            return mMediaPlayer;
        }

        @Override
        protected void onPostExecute(MediaPlayer mediaPlayer) {
            super.onPostExecute(mediaPlayer);

            initializeProgressCallback();
            play();
            logToUI("initializeProgressCallback()");
        }
    }


    @Override
    public void loadMedia(String url, PlayerStatus playerStatus) {

        playerStatus.loading();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mMediaUrl = url;
                initializeMediaPlayer();

                try {
                    logToUI("load() 1. setDataSource");
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mMediaPlayer.setDataSource(url);
                } catch (IOException e) {
                    logToUI(e.toString());
                    playerStatus.failed();
                }

                try {
                    logToUI("load() 2. prepare");
                    mMediaPlayer.prepare();
                } catch (Exception e) {
                    logToUI(e.toString());
                    playerStatus.failed();
                }
                initializeProgressCallback();
                play(playerStatus);
                logToUI("initializeProgressCallback()");
            }
        });
    }

    @Override
    public boolean isPlaying() {
        return mMediaPlayer != null && mMediaPlayer.isPlaying();
    }

    @Override
    public void play() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            logToUI("playback Start url: " + mMediaUrl);
            mMediaPlayer.start();

            if (mPlaybackInfoListener != null) {
                mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.PLAYING);
            }
            startUpdatingCallbackWithPosition();
        }
    }

    @Override
    public void play(PlayerStatus playerStatus) {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            logToUI("playback Start url: " + mMediaUrl);
            mMediaPlayer.start();

            playerStatus.playing();

            if (mPlaybackInfoListener != null) {
                mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.PLAYING);
            }
            startUpdatingCallbackWithPosition();
        }else playerStatus.failed();
    }

    @Override
    public void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();

            if (mPlaybackInfoListener != null) {
                mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.PAUSED);
                logToUI("playbackPaused()");
            }
        }
    }

    @Override
    public void reset() {
        if (mMediaPlayer != null) {
            logToUI("playbackReset()");
            mMediaPlayer.reset();
            loadMedia(mMediaUrl);

            if (mPlaybackInfoListener != null) {
                mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.RESET);
            }
            stopUpdatingCallbackWithPosition(true);
        }
    }

    @Override
    public void release() {
        if (mMediaPlayer != null) {
            logToUI("release() and mMediaPlayer = null");
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public void initializeProgressCallback() {
        final int duration = mMediaPlayer.getDuration();
        if (mPlaybackInfoListener != null) {
            mPlaybackInfoListener.onDurationChanged(duration);
            mPlaybackInfoListener.onPositionChanged(0);
            logToUI(String.format("firing setPlaybackDuration(%d sec)", TimeUnit.MILLISECONDS.toSeconds(duration)));
            logToUI("firing setPlaybackPosition(0)");
        }
    }

    @Override
    public void seekTo(int position) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(position);
            logToUI("seekTo() " + position + " milliseconds");
        }
    }

    private void logToUI(String message) {
        if (mPlaybackInfoListener != null) {
            mPlaybackInfoListener.onLogUpdated(message);
        }
    }

    private void startUpdatingCallbackWithPosition() {
        if (mExecutor == null) {
            mExecutor = Executors.newSingleThreadScheduledExecutor();
        }

        if (mSeekbarPositionUpdateTask == null) {
            mSeekbarPositionUpdateTask = new Runnable() {
                @Override
                public void run() {
                    updateProgressCallbackTask();
                }
            };
        }

        mExecutor.scheduleAtFixedRate(mSeekbarPositionUpdateTask,
                0,
                PLAYBACK_POSITION_REFRESH_INTERVAL_MS,
                TimeUnit.MILLISECONDS);
    }

    private void updateProgressCallbackTask() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            int currentPosition = mMediaPlayer.getCurrentPosition();
            if (mPlaybackInfoListener != null) {
                mPlaybackInfoListener.onPositionChanged(currentPosition);
            }
        }
    }

    // Reports media playback position to mPlaybackProgressCallback.
    private void stopUpdatingCallbackWithPosition(boolean resetUIPlaybackPosition) {
        if (mExecutor != null) {
            mExecutor.shutdown();
            mExecutor = null;
            mSeekbarPositionUpdateTask = null;
            if (resetUIPlaybackPosition && mPlaybackInfoListener != null) {
                mPlaybackInfoListener.onPositionChanged(0);
            }
        }
    }


    public void setVolume(float volume){

        if (mMediaPlayer!=null && mMediaPlayer.isPlaying()){

            if (volume>0){
                isMuted=false;
            }else {
                isMuted=true;
            }

            mMediaPlayer.setVolume(volume,volume);

        }

    }

    public boolean isMuted(){
        return isMuted;
    }


    public interface PlayerStatus{

        void loading();

        void playing();

        void failed();

    }
}

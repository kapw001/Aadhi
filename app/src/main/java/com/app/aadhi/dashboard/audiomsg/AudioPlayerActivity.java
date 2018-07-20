package com.app.aadhi.dashboard.audiomsg;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.TestimonialBaseActivity;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import com.app.aadhi.mediaplayer.AudioPlayer;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.mediaplayer.PlaybackInfoListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class AudioPlayerActivity extends TestimonialBaseActivity implements IMediaClickListener, View.OnClickListener {

    @BindView(R.id.btn_playPause)
    Button mPlayPauseBtn;
    @BindView(R.id.seekbar_audio)
    SeekBar mSeekbarAudio;
    @BindView(R.id.audio_playback_time)
    TextView mAudioPlaybackTime;
    @BindView(R.id.audio_speaker)
    ImageView mAudioSpeaker;

    @BindView(R.id.player_state)
    LatoRegularTextview mPalyerStatus;

    @BindView(R.id.song_name)
    LatoRegularTextview mSongName;

    protected AudioPlayer audioPlayer;

    private boolean mIsUserSeeking = false;


    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(getLayoutResId());
        initViews();
        initPlayerController();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (audioPlayer != null && audioPlayer.isPlaying()) {
            audioPlayer.pause();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (audioPlayer != null && audioPlayer.isPlaying()) {
            audioPlayer.release();
        }
    }


    public void reset() {
        if (audioPlayer != null && audioPlayer.isPlaying()) {
            audioPlayer.reset();
        }
    }


    protected void initViews() {
        ButterKnife.bind(this);

        //initalizePlayerUi
        mAudioSpeaker.setOnClickListener(this);
        mPlayPauseBtn.setOnClickListener(this);

        //initalizeSeekbar
        mSeekbarAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int userSelectedPosition = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    userSelectedPosition = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mIsUserSeeking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mIsUserSeeking = false;
                audioPlayer.seekTo(userSelectedPosition);
            }
        });
    }

    private void initPlayerController() {
        AudioPlayer player = new AudioPlayer(this);
        Log.d("AudioMsgActivity", "initPlayerController: created AudioPlayer object");
        player.setPlaybackInfoListener(new AudioPlayerActivity.PlaybackListener());
        Log.d("AudioMsgActivity", "initPlayerController: AudioPlayer - progress callback set");
        audioPlayer = player;
    }


    public void setSongName(String songName) {
        if (mSongName != null)
            mSongName.setText(songName);
        else Log.e(TAG, "setSongName: no view " );
    }

    public void setPlayerStatus(String status) {
        if (mPalyerStatus != null)
            mPalyerStatus.setText(status);
        else Log.e(TAG, "setSongName: no view " );
    }

//    @Override
//    public void onAudioClicked(int id) {
//        showProgressDialog("Loading");
//
//        Call<AudioVideoMessageDetailApi> AudioMessageDetailApiCall = RetrofitAdapter.getNetworkApiServiceClient().fetchAudioMessageDetails(String.valueOf(id));
//        AudioMessageDetailApiCall.enqueue(new Callback<AudioVideoMessageDetailApi>() {
//            @Override
//            public void onResponse(@NonNull Call<AudioVideoMessageDetailApi> call, @NonNull Response<AudioVideoMessageDetailApi> response) {
//                if (response.isSuccessful() && response.body().success) {
//                    hideProgressDialog();
//                    playAudioMsg(response.body().data);
//                } else {
//                    onFailure(call, null);
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<AudioVideoMessageDetailApi> call, Throwable t) {
//                hideProgressDialog();
//                // show error
//                Toast.makeText(AudioPlayerActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void playAudioMsg(AudioVideoMessageDetailApi.Data audio) {
//        audioPlayer.release();
//        audioPlayer.loadMedia(audio.audio_file);
//        audioPlayer.play();
//    }

    protected void playAudioMsg(String audioUrl) {
        audioPlayer.release();
        audioPlayer.loadMedia(audioUrl);
        audioPlayer.play();
    }

    protected void playAudioMsg(String audioUrl,String songName) {
        setSongName(songName);
        audioPlayer.release();
        audioPlayer.loadMedia(audioUrl, new AudioPlayer.PlayerStatus() {
            @Override
            public void loading() {
                setPlayerStatus("Loading...");
            }

            @Override
            public void playing() {
                setPlayerStatus("Now playing...");
            }

            @Override
            public void failed() {
                setPlayerStatus("Failed");
            }
        });
        audioPlayer.play();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);   // pass this click to base class to handle there also.

        switch (v.getId()) {
            case R.id.btn_playPause:
                if (audioPlayer.isPlaying()) {
                    audioPlayer.pause();
                } else {
                    audioPlayer.play();
                }
                break;
            case R.id.audio_speaker:

                if (audioPlayer.isMuted() && audioPlayer.isPlaying()) {

                    audioPlayer.setVolume(1);
                    mAudioSpeaker.setImageResource(R.drawable.ic_volume_up_black_24dp);
                } else if (audioPlayer.isPlaying()) {

                    audioPlayer.setVolume(0);
                    mAudioSpeaker.setImageResource(R.drawable.ic_volume_off_black_24dp);
//                    mAudioSpeaker.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_volume_up_black_24dp));
                }


                break;
        }
    }


    /**
     * Class that implements PlaybackInfoListener for handling the UI callbacks
     */
    class PlaybackListener extends PlaybackInfoListener {
        int duration = 0;

        @Override
        public void onDurationChanged(int duration) {
            this.duration = duration;
            mSeekbarAudio.setMax(duration);
            mAudioPlaybackTime.setText(String.format("0 / %s", duration / 1000));
            onLogUpdated(String.format("setPlaybackDuration: setMax:(%d)", duration));
        }

        @Override
        public void onPositionChanged(final int position) {
            if (!mIsUserSeeking) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSeekbarAudio.setProgress(position);
                        mAudioPlaybackTime.setText(String.format("%s / %s", position / 1000, duration / 1000));
                    }
                });
                onLogUpdated(String.format("setPlaybackPosition: setProgress(%d)", position));
            }
        }

        @Override
        public void onStateChanged(int state) {
            String stateString = PlaybackInfoListener.convertToString(state);
            onLogUpdated(String.format("onStateChanged(%s)", stateString));

            switch (state) {
                case State.INVALID:
                    stateString = "INVALID";
                    mPlayPauseBtn.setSelected(false);
                    break;
                case State.PLAYING:
                    stateString = "PLAYING";
                    mPlayPauseBtn.setSelected(true);
                    break;
                case State.PAUSED:
                    stateString = "PAUSED";
                    mPlayPauseBtn.setSelected(false);
                    break;
                case State.COMPLETED:
                    stateString = "COMPLETED";
                    mPlayPauseBtn.setSelected(false);
                    break;
                case State.RESET:
                    stateString = "RESET";
                    mPlayPauseBtn.setSelected(false);
                    break;
                default:
                    stateString = "N/A";
                    mPlayPauseBtn.setSelected(false);
                    break;
            }
        }

        @Override
        public void onPlaybackCompleted() {
            mAudioPlaybackTime.setText(String.format("%s / %s", 0, duration / 1000));
        }

        @Override
        public void onLogUpdated(String formattedMessage) {
            Log.d("MyAudioPlayer", formattedMessage);
        }
    }


}

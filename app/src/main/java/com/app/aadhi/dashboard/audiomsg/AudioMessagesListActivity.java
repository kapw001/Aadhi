package com.app.aadhi.dashboard.audiomsg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.mediaplayer.AudioPlayer;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.mediaplayer.PlaybackInfoListener;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.AudioVideoMessageDetailApi;
import com.app.aadhi.network.response.AudioVideoMessageListApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudioMessagesListActivity  extends RootActivity implements IMediaClickListener, View.OnClickListener {

    @BindView(R.id.rv_audio_list)
    RecyclerView mMessagesListRV;
    @BindView(R.id.btn_playPause)
    Button mPlayPauseBtn;
    @BindView(R.id.seekbar_audio)
    SeekBar mSeekbarAudio;

    @BindView(R.id.audio_speaker)
    ImageView mAudioSpeaker;

    @BindView(R.id.audio_playback_time)
    TextView mAudioPlaybackTime;

    private boolean mIsUserSeeking = false;
    private AudioPlayer mAudioPlayer;

    @BindView(R.id.layout_audio_player)
    View mAudioPlayerUI;

    @BindView(R.id.player_state)
    LatoRegularTextview mPlayerState;

    @BindView(R.id.song_name)
    LatoRegularTextview mSongName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_audio_video_messages);
        initViews();
        initPlayerController();
        callAudioMessageListApiService();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAudioPlayer != null && mAudioPlayer.isPlaying()) {
            mAudioPlayer.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAudioPlayer != null && mAudioPlayer.isPlaying()) {
            mAudioPlayer.release();
        }
    }

    private void initViews() {
        ButterKnife.bind(this);

        GridLayoutManager threeColumnGridLayout = new GridLayoutManager(this, 2);
        mMessagesListRV.setLayoutManager(threeColumnGridLayout);
        EqualSpacingItemDecoration gridSpace = new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.GRID);
        mMessagesListRV.addItemDecoration(gridSpace);
        AudioMessageListAdapter adapter = new AudioMessageListAdapter(this, this);
        mMessagesListRV.setAdapter(adapter);

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
                mAudioPlayer.seekTo(userSelectedPosition);
            }
        });

        audioPlayerShowHide(View.GONE);
    }

    private void initPlayerController() {
        AudioPlayer player = new AudioPlayer(this);
        Log.d("AudioMsgActivity", "initPlayerController: created AudioPlayer object");
        player.setPlaybackInfoListener(new PlaybackListener());
        Log.d("AudioMsgActivity", "initPlayerController: AudioPlayer - progress callback set");
        mAudioPlayer = player;
    }

    private void callAudioMessageListApiService() {
        showProgressDialog("Loading");

        Call<AudioVideoMessageListApi> audioMessageListApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchAudioMessages();
        audioMessageListApiService.enqueue(new Callback<AudioVideoMessageListApi>() {
            @Override
            public void onResponse(@NonNull Call<AudioVideoMessageListApi> call, @NonNull Response<AudioVideoMessageListApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    loadAudioListResponseInList(response.body().data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AudioVideoMessageListApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(AudioMessagesListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAudioListResponseInList(List<AudioVideoMessageListApi.Data> items) {
        ((AudioMessageListAdapter)mMessagesListRV.getAdapter()).notifyItemsChanged(items);
    }


    @Override
    public void onAudioClicked(int id) {
        showProgressDialog("Loading");

        Observable<AudioVideoMessageDetailApi> AudioMessageDetailApiCall = RetrofitAdapter.getNetworkApiServiceClient().fetchAudioMessageDetails(String.valueOf(id));

        AudioMessageDetailApiCall.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AudioVideoMessageDetailApi>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull AudioVideoMessageDetailApi audioVideoMessageDetailApi) {

                if (audioVideoMessageDetailApi.success) {
                    hideProgressDialog();

                    playAudioMsg(audioVideoMessageDetailApi.data);
                } else {
                    onError(null);
                }

            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable throwable) {
                hideProgressDialog();
                // show error
                Toast.makeText(AudioMessagesListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });


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
//                Toast.makeText(AudioMessagesListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void playAudioMsg(AudioVideoMessageDetailApi.Data audio) {
        audioPlayerShowHide(View.VISIBLE);
        mSongName.setText(audio.title);
        mAudioPlayer.release();
        mAudioPlayer.loadMedia(audio.audio_file, new AudioPlayer.PlayerStatus() {
            @Override
            public void loading() {
                mPlayerState.setText("Loading...");
            }

            @Override
            public void playing() {
                mPlayerState.setText("Now playing...");
            }

            @Override
            public void failed() {
                mPlayerState.setText("Failed");
            }
        });
        mAudioPlayer.play();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);    // pass the click event to the base activity

        switch (v.getId()) {
            case R.id.btn_playPause:
                if (mAudioPlayer.isPlaying()) {
                    mAudioPlayer.pause();
                } else {
                    mAudioPlayer.play();
                }
                break;

            case R.id.audio_speaker:

                if (mAudioPlayer.isMuted() && mAudioPlayer.isPlaying()){

                    mAudioPlayer.setVolume(1);
                    mAudioSpeaker.setImageResource(R.drawable.ic_volume_up_black_24dp);
                }else if (mAudioPlayer.isPlaying()){

                    mAudioPlayer.setVolume(0);
                    mAudioSpeaker.setImageResource(R.drawable.ic_volume_off_black_24dp);
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
            mAudioPlaybackTime.setText(String.format("0 / %s", duration/1000));
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
            mAudioPlaybackTime.setText(String.format("%s / %s", 0, duration/1000));
        }

        @Override
        public void onLogUpdated(String formattedMessage) {
            Log.d("MyAudioPlayer", formattedMessage);
        }
    }

    private void audioPlayerShowHide(int visible) {

        mAudioPlayerUI.setVisibility(visible);

    }
}

// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.audiomsg;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AudioMessagesListActivity_ViewBinding implements Unbinder {
  private AudioMessagesListActivity target;

  @UiThread
  public AudioMessagesListActivity_ViewBinding(AudioMessagesListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AudioMessagesListActivity_ViewBinding(AudioMessagesListActivity target, View source) {
    this.target = target;

    target.mMessagesListRV = Utils.findRequiredViewAsType(source, R.id.rv_audio_list, "field 'mMessagesListRV'", RecyclerView.class);
    target.mPlayPauseBtn = Utils.findRequiredViewAsType(source, R.id.btn_playPause, "field 'mPlayPauseBtn'", Button.class);
    target.mSeekbarAudio = Utils.findRequiredViewAsType(source, R.id.seekbar_audio, "field 'mSeekbarAudio'", SeekBar.class);
    target.mAudioSpeaker = Utils.findRequiredViewAsType(source, R.id.audio_speaker, "field 'mAudioSpeaker'", ImageView.class);
    target.mAudioPlaybackTime = Utils.findRequiredViewAsType(source, R.id.audio_playback_time, "field 'mAudioPlaybackTime'", TextView.class);
    target.mAudioPlayerUI = Utils.findRequiredView(source, R.id.layout_audio_player, "field 'mAudioPlayerUI'");
    target.mPlayerState = Utils.findRequiredViewAsType(source, R.id.player_state, "field 'mPlayerState'", LatoRegularTextview.class);
    target.mSongName = Utils.findRequiredViewAsType(source, R.id.song_name, "field 'mSongName'", LatoRegularTextview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AudioMessagesListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mMessagesListRV = null;
    target.mPlayPauseBtn = null;
    target.mSeekbarAudio = null;
    target.mAudioSpeaker = null;
    target.mAudioPlaybackTime = null;
    target.mAudioPlayerUI = null;
    target.mPlayerState = null;
    target.mSongName = null;
  }
}

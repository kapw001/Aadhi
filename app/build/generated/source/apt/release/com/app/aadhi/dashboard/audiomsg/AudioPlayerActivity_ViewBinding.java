// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.audiomsg;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

public class AudioPlayerActivity_ViewBinding implements Unbinder {
  private AudioPlayerActivity target;

  @UiThread
  public AudioPlayerActivity_ViewBinding(AudioPlayerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AudioPlayerActivity_ViewBinding(AudioPlayerActivity target, View source) {
    this.target = target;

    target.mPlayPauseBtn = Utils.findRequiredViewAsType(source, R.id.btn_playPause, "field 'mPlayPauseBtn'", Button.class);
    target.mSeekbarAudio = Utils.findRequiredViewAsType(source, R.id.seekbar_audio, "field 'mSeekbarAudio'", SeekBar.class);
    target.mAudioPlaybackTime = Utils.findRequiredViewAsType(source, R.id.audio_playback_time, "field 'mAudioPlaybackTime'", TextView.class);
    target.mAudioSpeaker = Utils.findRequiredViewAsType(source, R.id.audio_speaker, "field 'mAudioSpeaker'", ImageView.class);
    target.mPalyerStatus = Utils.findRequiredViewAsType(source, R.id.player_state, "field 'mPalyerStatus'", LatoRegularTextview.class);
    target.mSongName = Utils.findRequiredViewAsType(source, R.id.song_name, "field 'mSongName'", LatoRegularTextview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AudioPlayerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mPlayPauseBtn = null;
    target.mSeekbarAudio = null;
    target.mAudioPlaybackTime = null;
    target.mAudioSpeaker = null;
    target.mPalyerStatus = null;
    target.mSongName = null;
  }
}

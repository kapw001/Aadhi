// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.dashboard.audiomsg.AudioPlayerActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InformationsMenuActivity_ViewBinding extends AudioPlayerActivity_ViewBinding {
  private InformationsMenuActivity target;

  @UiThread
  public InformationsMenuActivity_ViewBinding(InformationsMenuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InformationsMenuActivity_ViewBinding(InformationsMenuActivity target, View source) {
    super(target, source);

    this.target = target;

    target.mTvCurrentEvents = Utils.findRequiredViewAsType(source, R.id.btn_current_events, "field 'mTvCurrentEvents'", Button.class);
    target.mVideoMessages = Utils.findRequiredViewAsType(source, R.id.btn_video_messages, "field 'mVideoMessages'", Button.class);
    target.mAudioMessages = Utils.findRequiredViewAsType(source, R.id.btn_audio_messages, "field 'mAudioMessages'", Button.class);
    target.mSlokasAndMantras = Utils.findRequiredViewAsType(source, R.id.btn_sloka_mantras, "field 'mSlokasAndMantras'", Button.class);
    target.mSlokaOfTheDay = Utils.findRequiredViewAsType(source, R.id.btn_sloka_of_day, "field 'mSlokaOfTheDay'", Button.class);
    target.mEAppNotice = Utils.findRequiredViewAsType(source, R.id.btn_eapp_notice, "field 'mEAppNotice'", Button.class);
    target.mAudioPlayerUI = Utils.findRequiredView(source, R.id.layout_audio_player, "field 'mAudioPlayerUI'");
  }

  @Override
  public void unbind() {
    InformationsMenuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvCurrentEvents = null;
    target.mVideoMessages = null;
    target.mAudioMessages = null;
    target.mSlokasAndMantras = null;
    target.mSlokaOfTheDay = null;
    target.mEAppNotice = null;
    target.mAudioPlayerUI = null;

    super.unbind();
  }
}

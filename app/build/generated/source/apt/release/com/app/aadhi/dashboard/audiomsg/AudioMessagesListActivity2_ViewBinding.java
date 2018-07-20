// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.audiomsg;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AudioMessagesListActivity2_ViewBinding extends AudioPlayerActivity_ViewBinding {
  private AudioMessagesListActivity2 target;

  @UiThread
  public AudioMessagesListActivity2_ViewBinding(AudioMessagesListActivity2 target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AudioMessagesListActivity2_ViewBinding(AudioMessagesListActivity2 target, View source) {
    super(target, source);

    this.target = target;

    target.mMessagesListRV = Utils.findRequiredViewAsType(source, R.id.rv_audio_list, "field 'mMessagesListRV'", RecyclerView.class);
  }

  @Override
  public void unbind() {
    AudioMessagesListActivity2 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mMessagesListRV = null;

    super.unbind();
  }
}

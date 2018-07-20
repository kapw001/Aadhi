// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.audiomsg;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AudioMessageListAdapter$AudioMessageVH_ViewBinding implements Unbinder {
  private AudioMessageListAdapter.AudioMessageVH target;

  @UiThread
  public AudioMessageListAdapter$AudioMessageVH_ViewBinding(AudioMessageListAdapter.AudioMessageVH target,
      View source) {
    this.target = target;

    target.icon = Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AudioMessageListAdapter.AudioMessageVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon = null;
  }
}

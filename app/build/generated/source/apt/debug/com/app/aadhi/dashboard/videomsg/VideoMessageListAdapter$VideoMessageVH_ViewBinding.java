// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.videomsg;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoMessageListAdapter$VideoMessageVH_ViewBinding implements Unbinder {
  private VideoMessageListAdapter.VideoMessageVH target;

  @UiThread
  public VideoMessageListAdapter$VideoMessageVH_ViewBinding(VideoMessageListAdapter.VideoMessageVH target,
      View source) {
    this.target = target;

    target.icon = Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VideoMessageListAdapter.VideoMessageVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon = null;
  }
}

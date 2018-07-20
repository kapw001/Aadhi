// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.videomsg;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoMessagesListActivityBackUp_ViewBinding implements Unbinder {
  private VideoMessagesListActivityBackUp target;

  @UiThread
  public VideoMessagesListActivityBackUp_ViewBinding(VideoMessagesListActivityBackUp target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VideoMessagesListActivityBackUp_ViewBinding(VideoMessagesListActivityBackUp target,
      View source) {
    this.target = target;

    target.mMessagesListRV = Utils.findRequiredViewAsType(source, R.id.rv_audio_list, "field 'mMessagesListRV'", RecyclerView.class);
    target.mAudioPlayerLayout = Utils.findRequiredViewAsType(source, R.id.layout_audio_player, "field 'mAudioPlayerLayout'", ViewGroup.class);
    target.mTitltTV = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitltTV'", LatoBoldTextview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VideoMessagesListActivityBackUp target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mMessagesListRV = null;
    target.mAudioPlayerLayout = null;
    target.mTitltTV = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.currentevents;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FullScreenImageViewFragment_ViewBinding implements Unbinder {
  private FullScreenImageViewFragment target;

  @UiThread
  public FullScreenImageViewFragment_ViewBinding(FullScreenImageViewFragment target, View source) {
    this.target = target;

    target.mFullScreenIV = Utils.findRequiredViewAsType(source, R.id.iv_full_screen_image, "field 'mFullScreenIV'", ImageView.class);
    target.mClose = Utils.findRequiredViewAsType(source, R.id.close, "field 'mClose'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FullScreenImageViewFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mFullScreenIV = null;
    target.mClose = null;
  }
}

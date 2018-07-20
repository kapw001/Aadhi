// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.currentevents;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CurrentEventsDetailsAdapter$CurrentEventVH_ViewBinding implements Unbinder {
  private CurrentEventsDetailsAdapter.CurrentEventVH target;

  @UiThread
  public CurrentEventsDetailsAdapter$CurrentEventVH_ViewBinding(CurrentEventsDetailsAdapter.CurrentEventVH target,
      View source) {
    this.target = target;

    target.photoView = Utils.findRequiredViewAsType(source, R.id.iv_tour_banner, "field 'photoView'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.tv_tour_title, "field 'title'", LatoBoldTextview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CurrentEventsDetailsAdapter.CurrentEventVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.photoView = null;
    target.title = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.currentevents;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CurrentEventsListAdapter$CurrentEventVH_ViewBinding implements Unbinder {
  private CurrentEventsListAdapter.CurrentEventVH target;

  @UiThread
  public CurrentEventsListAdapter$CurrentEventVH_ViewBinding(CurrentEventsListAdapter.CurrentEventVH target,
      View source) {
    this.target = target;

    target.mEventBtn = Utils.findRequiredViewAsType(source, R.id.btn_current_event, "field 'mEventBtn'", LatoRegularTextview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CurrentEventsListAdapter.CurrentEventVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEventBtn = null;
  }
}

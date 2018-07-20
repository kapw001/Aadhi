// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.currentevents;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CurrentEventsListActivity_ViewBinding implements Unbinder {
  private CurrentEventsListActivity target;

  @UiThread
  public CurrentEventsListActivity_ViewBinding(CurrentEventsListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CurrentEventsListActivity_ViewBinding(CurrentEventsListActivity target, View source) {
    this.target = target;

    target.mCurrentEventsListView = Utils.findRequiredViewAsType(source, R.id.rv_current_events, "field 'mCurrentEventsListView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CurrentEventsListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mCurrentEventsListView = null;
  }
}

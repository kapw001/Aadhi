// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.currentevents;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CurrentEventsDetailsActivity_ViewBinding implements Unbinder {
  private CurrentEventsDetailsActivity target;

  @UiThread
  public CurrentEventsDetailsActivity_ViewBinding(CurrentEventsDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CurrentEventsDetailsActivity_ViewBinding(CurrentEventsDetailsActivity target,
      View source) {
    this.target = target;

    target.mCurrentEventsListView = Utils.findRequiredViewAsType(source, R.id.rv_current_events, "field 'mCurrentEventsListView'", RecyclerView.class);
    target.mTitleView = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitleView'", LatoBoldTextview.class);
    target.mHeading = Utils.findRequiredViewAsType(source, R.id.heading, "field 'mHeading'", LatoBoldTextview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CurrentEventsDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mCurrentEventsListView = null;
    target.mTitleView = null;
    target.mHeading = null;
  }
}

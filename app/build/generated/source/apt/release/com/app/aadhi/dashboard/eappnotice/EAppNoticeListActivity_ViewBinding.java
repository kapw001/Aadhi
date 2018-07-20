// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.eappnotice;

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

public class EAppNoticeListActivity_ViewBinding implements Unbinder {
  private EAppNoticeListActivity target;

  @UiThread
  public EAppNoticeListActivity_ViewBinding(EAppNoticeListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EAppNoticeListActivity_ViewBinding(EAppNoticeListActivity target, View source) {
    this.target = target;

    target.mTitleTV = Utils.findRequiredViewAsType(source, R.id.heading, "field 'mTitleTV'", LatoBoldTextview.class);
    target.mEappListView = Utils.findRequiredViewAsType(source, R.id.rv_current_events, "field 'mEappListView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EAppNoticeListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTitleTV = null;
    target.mEappListView = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DashboardActivity_ViewBinding implements Unbinder {
  private DashboardActivity target;

  @UiThread
  public DashboardActivity_ViewBinding(DashboardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DashboardActivity_ViewBinding(DashboardActivity target, View source) {
    this.target = target;

    target.mInformationsBtn = Utils.findRequiredViewAsType(source, R.id.btn_informations, "field 'mInformationsBtn'", Button.class);
    target.mServicesBtn = Utils.findRequiredViewAsType(source, R.id.btn_services, "field 'mServicesBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DashboardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mInformationsBtn = null;
    target.mServicesBtn = null;
  }
}

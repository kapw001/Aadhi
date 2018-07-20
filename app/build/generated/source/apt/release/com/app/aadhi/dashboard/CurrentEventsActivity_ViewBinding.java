// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.common.customtextview.LatoRegularEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CurrentEventsActivity_ViewBinding implements Unbinder {
  private CurrentEventsActivity target;

  @UiThread
  public CurrentEventsActivity_ViewBinding(CurrentEventsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CurrentEventsActivity_ViewBinding(CurrentEventsActivity target, View source) {
    this.target = target;

    target.mUserName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'mUserName'", LatoRegularEditText.class);
    target.mUserEmail = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'mUserEmail'", LatoRegularEditText.class);
    target.mUserMobileNumber = Utils.findRequiredViewAsType(source, R.id.et_mobile_no, "field 'mUserMobileNumber'", LatoRegularEditText.class);
    target.mDescription = Utils.findRequiredViewAsType(source, R.id.et_description, "field 'mDescription'", LatoRegularEditText.class);
    target.mSendBtn = Utils.findRequiredViewAsType(source, R.id.btn_submit, "field 'mSendBtn'", LatoBoldTextview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CurrentEventsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mUserName = null;
    target.mUserEmail = null;
    target.mUserMobileNumber = null;
    target.mDescription = null;
    target.mSendBtn = null;
  }
}

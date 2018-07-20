// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.vaastu;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VaastuMenuActivity_ViewBinding implements Unbinder {
  private VaastuMenuActivity target;

  @UiThread
  public VaastuMenuActivity_ViewBinding(VaastuMenuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VaastuMenuActivity_ViewBinding(VaastuMenuActivity target, View source) {
    this.target = target;

    target.mTitle = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitle'", TextView.class);
    target.mContentImage = Utils.findRequiredViewAsType(source, R.id.content_image1, "field 'mContentImage'", ImageView.class);
    target.mContentDescription = Utils.findRequiredViewAsType(source, R.id.content_description, "field 'mContentDescription'", TextView.class);
    target.mRow1Title = Utils.findRequiredViewAsType(source, R.id.row1_title, "field 'mRow1Title'", TextView.class);
    target.mNameET = Utils.findRequiredViewAsType(source, R.id.row1_description, "field 'mNameET'", TextView.class);
    target.mRow2Title = Utils.findRequiredViewAsType(source, R.id.row2_title, "field 'mRow2Title'", TextView.class);
    target.mAddressET = Utils.findRequiredViewAsType(source, R.id.row2_description, "field 'mAddressET'", TextView.class);
    target.mRow3Title = Utils.findRequiredViewAsType(source, R.id.row3_title, "field 'mRow3Title'", TextView.class);
    target.mMobileNoET = Utils.findRequiredViewAsType(source, R.id.row3_description, "field 'mMobileNoET'", TextView.class);
    target.mRow4Title = Utils.findRequiredViewAsType(source, R.id.row4_title, "field 'mRow4Title'", TextView.class);
    target.mPlaceET = Utils.findRequiredViewAsType(source, R.id.row4_description, "field 'mPlaceET'", TextView.class);
    target.mRow5Title = Utils.findRequiredViewAsType(source, R.id.row5_title, "field 'mRow5Title'", TextView.class);
    target.mEmiRates = Utils.findRequiredViewAsType(source, R.id.row5_description, "field 'mEmiRates'", TextView.class);
    target.mRow6Title = Utils.findRequiredViewAsType(source, R.id.row6_title, "field 'mRow6Title'", TextView.class);
    target.mRow6Description = Utils.findRequiredViewAsType(source, R.id.row6_description, "field 'mRow6Description'", TextView.class);
    target.mRow7Title = Utils.findRequiredViewAsType(source, R.id.row7_title, "field 'mRow7Title'", TextView.class);
    target.mRow7Description = Utils.findRequiredViewAsType(source, R.id.row7_description, "field 'mRow7Description'", TextView.class);
    target.mRow5 = Utils.findRequiredView(source, R.id.row5, "field 'mRow5'");
    target.mRow6 = Utils.findRequiredView(source, R.id.row6, "field 'mRow6'");
    target.mSubmitBtn = Utils.findRequiredViewAsType(source, R.id.submit, "field 'mSubmitBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VaastuMenuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTitle = null;
    target.mContentImage = null;
    target.mContentDescription = null;
    target.mRow1Title = null;
    target.mNameET = null;
    target.mRow2Title = null;
    target.mAddressET = null;
    target.mRow3Title = null;
    target.mMobileNoET = null;
    target.mRow4Title = null;
    target.mPlaceET = null;
    target.mRow5Title = null;
    target.mEmiRates = null;
    target.mRow6Title = null;
    target.mRow6Description = null;
    target.mRow7Title = null;
    target.mRow7Description = null;
    target.mRow5 = null;
    target.mRow6 = null;
    target.mSubmitBtn = null;
  }
}

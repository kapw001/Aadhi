// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.pooja;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PoojaForFamilyMenuActivity_ViewBinding implements Unbinder {
  private PoojaForFamilyMenuActivity target;

  @UiThread
  public PoojaForFamilyMenuActivity_ViewBinding(PoojaForFamilyMenuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PoojaForFamilyMenuActivity_ViewBinding(PoojaForFamilyMenuActivity target, View source) {
    this.target = target;

    target.mTitle = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitle'", TextView.class);
    target.mDate = Utils.findRequiredViewAsType(source, R.id.date, "field 'mDate'", TextView.class);
    target.mTime = Utils.findRequiredViewAsType(source, R.id.time, "field 'mTime'", TextView.class);
    target.mContentImage = Utils.findRequiredViewAsType(source, R.id.content_image1, "field 'mContentImage'", ImageView.class);
    target.mContentDescription = Utils.findRequiredViewAsType(source, R.id.content_description, "field 'mContentDescription'", TextView.class);
    target.mRow1Title = Utils.findRequiredViewAsType(source, R.id.row1_title, "field 'mRow1Title'", TextView.class);
    target.mNameET = Utils.findRequiredViewAsType(source, R.id.row1_description, "field 'mNameET'", TextView.class);
    target.mRow2Title = Utils.findRequiredViewAsType(source, R.id.row2_title, "field 'mRow2Title'", TextView.class);
    target.mAddressET = Utils.findRequiredViewAsType(source, R.id.row2_description, "field 'mAddressET'", TextView.class);
    target.mRow3Title = Utils.findRequiredViewAsType(source, R.id.row3_title, "field 'mRow3Title'", TextView.class);
    target.mDateET = Utils.findRequiredViewAsType(source, R.id.row3_description, "field 'mDateET'", TextView.class);
    target.mRow4Title = Utils.findRequiredViewAsType(source, R.id.row4_title, "field 'mRow4Title'", TextView.class);
    target.mWhatsAppNoET = Utils.findRequiredViewAsType(source, R.id.row4_description, "field 'mWhatsAppNoET'", TextView.class);
    target.mRow5Title = Utils.findRequiredViewAsType(source, R.id.row5_title, "field 'mRow5Title'", TextView.class);
    target.mMobileNoET = Utils.findRequiredViewAsType(source, R.id.row5_description, "field 'mMobileNoET'", TextView.class);
    target.mRow6Title = Utils.findRequiredViewAsType(source, R.id.row6_title, "field 'mRow6Title'", TextView.class);
    target.mRow6Description = Utils.findRequiredViewAsType(source, R.id.row6_description, "field 'mRow6Description'", TextView.class);
    target.mPoojaOptionsSpinner = Utils.findRequiredViewAsType(source, R.id.row6_spinner, "field 'mPoojaOptionsSpinner'", Spinner.class);
    target.mRow7Title = Utils.findRequiredViewAsType(source, R.id.row7_title, "field 'mRow7Title'", TextView.class);
    target.mDescriptionET = Utils.findRequiredViewAsType(source, R.id.row7_description, "field 'mDescriptionET'", TextView.class);
    target.mRow6 = Utils.findRequiredView(source, R.id.row6, "field 'mRow6'");
    target.mSubmitBtn = Utils.findRequiredViewAsType(source, R.id.submit, "field 'mSubmitBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PoojaForFamilyMenuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTitle = null;
    target.mDate = null;
    target.mTime = null;
    target.mContentImage = null;
    target.mContentDescription = null;
    target.mRow1Title = null;
    target.mNameET = null;
    target.mRow2Title = null;
    target.mAddressET = null;
    target.mRow3Title = null;
    target.mDateET = null;
    target.mRow4Title = null;
    target.mWhatsAppNoET = null;
    target.mRow5Title = null;
    target.mMobileNoET = null;
    target.mRow6Title = null;
    target.mRow6Description = null;
    target.mPoojaOptionsSpinner = null;
    target.mRow7Title = null;
    target.mDescriptionET = null;
    target.mRow6 = null;
    target.mSubmitBtn = null;
  }
}

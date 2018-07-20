// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.pilgrimage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PilgrimageTourDetailsActivity_ViewBinding implements Unbinder {
  private PilgrimageTourDetailsActivity target;

  @UiThread
  public PilgrimageTourDetailsActivity_ViewBinding(PilgrimageTourDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PilgrimageTourDetailsActivity_ViewBinding(PilgrimageTourDetailsActivity target,
      View source) {
    this.target = target;

    target.mTitleView = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitleView'", LatoBoldTextview.class);
    target.mTourTitle = Utils.findRequiredViewAsType(source, R.id.tour_title, "field 'mTourTitle'", LatoBoldTextview.class);
    target.mBanner = Utils.findRequiredViewAsType(source, R.id.content_image1, "field 'mBanner'", ImageView.class);
    target.mToutSubTitle = Utils.findRequiredViewAsType(source, R.id.tour_sub_title, "field 'mToutSubTitle'", LatoBoldTextview.class);
    target.mDescription = Utils.findRequiredViewAsType(source, R.id.content_description, "field 'mDescription'", TextView.class);
    target.mRow1Title = Utils.findRequiredViewAsType(source, R.id.row1_title, "field 'mRow1Title'", TextView.class);
    target.mNameET = Utils.findRequiredViewAsType(source, R.id.row1_description, "field 'mNameET'", TextView.class);
    target.mRow2Title = Utils.findRequiredViewAsType(source, R.id.row2_title, "field 'mRow2Title'", TextView.class);
    target.mDateET = Utils.findRequiredViewAsType(source, R.id.row2_description, "field 'mDateET'", TextView.class);
    target.mRow3Title = Utils.findRequiredViewAsType(source, R.id.row3_title, "field 'mRow3Title'", TextView.class);
    target.mWhatsappNoET = Utils.findRequiredViewAsType(source, R.id.row3_description, "field 'mWhatsappNoET'", TextView.class);
    target.mRow7Title = Utils.findRequiredViewAsType(source, R.id.row4_title, "field 'mRow7Title'", TextView.class);
    target.mDescriptionET = Utils.findRequiredViewAsType(source, R.id.row4_description, "field 'mDescriptionET'", TextView.class);
    target.mSubmitBtn = Utils.findRequiredViewAsType(source, R.id.submit, "field 'mSubmitBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PilgrimageTourDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTitleView = null;
    target.mTourTitle = null;
    target.mBanner = null;
    target.mToutSubTitle = null;
    target.mDescription = null;
    target.mRow1Title = null;
    target.mNameET = null;
    target.mRow2Title = null;
    target.mDateET = null;
    target.mRow3Title = null;
    target.mWhatsappNoET = null;
    target.mRow7Title = null;
    target.mDescriptionET = null;
    target.mSubmitBtn = null;
  }
}

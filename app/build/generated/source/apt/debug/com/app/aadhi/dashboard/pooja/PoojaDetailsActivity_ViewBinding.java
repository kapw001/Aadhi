// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.pooja;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.common.customtextview.LatoRegularEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PoojaDetailsActivity_ViewBinding implements Unbinder {
  private PoojaDetailsActivity target;

  @UiThread
  public PoojaDetailsActivity_ViewBinding(PoojaDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PoojaDetailsActivity_ViewBinding(PoojaDetailsActivity target, View source) {
    this.target = target;

    target.mTitleView = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitleView'", LatoBoldTextview.class);
    target.mDate = Utils.findRequiredViewAsType(source, R.id.date, "field 'mDate'", LatoBoldTextview.class);
    target.mDayAndTime = Utils.findRequiredViewAsType(source, R.id.time, "field 'mDayAndTime'", LatoBoldTextview.class);
    target.mBanner = Utils.findRequiredViewAsType(source, R.id.content_image1, "field 'mBanner'", ImageView.class);
    target.mDescription = Utils.findRequiredViewAsType(source, R.id.content_description, "field 'mDescription'", TextView.class);
    target.mNameFamilyET = Utils.findRequiredViewAsType(source, R.id.ip_name_family, "field 'mNameFamilyET'", LatoRegularEditText.class);
    target.mGothramET = Utils.findRequiredViewAsType(source, R.id.ip_gothram, "field 'mGothramET'", LatoRegularEditText.class);
    target.mRaasiET = Utils.findRequiredViewAsType(source, R.id.ip_raasi, "field 'mRaasiET'", LatoRegularEditText.class);
    target.mWhatsappNoET = Utils.findRequiredViewAsType(source, R.id.ip_whatsapp_no, "field 'mWhatsappNoET'", LatoRegularEditText.class);
    target.mMobileNoET = Utils.findRequiredViewAsType(source, R.id.ip_mobile_no, "field 'mMobileNoET'", LatoRegularEditText.class);
    target.mDescriptionET = Utils.findRequiredViewAsType(source, R.id.ip_description, "field 'mDescriptionET'", LatoRegularEditText.class);
    target.mSubmitBtn = Utils.findRequiredViewAsType(source, R.id.submit, "field 'mSubmitBtn'", Button.class);
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.content_root_view, "field 'scrollView'", ScrollView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PoojaDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTitleView = null;
    target.mDate = null;
    target.mDayAndTime = null;
    target.mBanner = null;
    target.mDescription = null;
    target.mNameFamilyET = null;
    target.mGothramET = null;
    target.mRaasiET = null;
    target.mWhatsappNoET = null;
    target.mMobileNoET = null;
    target.mDescriptionET = null;
    target.mSubmitBtn = null;
    target.scrollView = null;
  }
}

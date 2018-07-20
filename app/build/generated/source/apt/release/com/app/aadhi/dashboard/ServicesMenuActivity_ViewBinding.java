// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ServicesMenuActivity_ViewBinding implements Unbinder {
  private ServicesMenuActivity target;

  @UiThread
  public ServicesMenuActivity_ViewBinding(ServicesMenuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ServicesMenuActivity_ViewBinding(ServicesMenuActivity target, View source) {
    this.target = target;

    target.mOnlinePooja = Utils.findRequiredViewAsType(source, R.id.btn_online_pooja, "field 'mOnlinePooja'", Button.class);
    target.mMatrimony = Utils.findRequiredViewAsType(source, R.id.btn_aadhi_matrimony, "field 'mMatrimony'", Button.class);
    target.mVaastuConsultancy = Utils.findRequiredViewAsType(source, R.id.btn_vaastu_consultancy, "field 'mVaastuConsultancy'", Button.class);
    target.mAstrology = Utils.findRequiredViewAsType(source, R.id.btn_astrology, "field 'mAstrology'", Button.class);
    target.mPoojaFamily = Utils.findRequiredViewAsType(source, R.id.btn_pooja_family, "field 'mPoojaFamily'", Button.class);
    target.mPoojaChildren = Utils.findRequiredViewAsType(source, R.id.btn_pooja_children, "field 'mPoojaChildren'", Button.class);
    target.mHomam = Utils.findRequiredViewAsType(source, R.id.btn_homam, "field 'mHomam'", Button.class);
    target.mSrathamPinda = Utils.findRequiredViewAsType(source, R.id.btn_sratham_pinda, "field 'mSrathamPinda'", Button.class);
    target.mPilgrimage = Utils.findRequiredViewAsType(source, R.id.btn_pilgrimage, "field 'mPilgrimage'", Button.class);
    target.mTestimonialUI = Utils.findRequiredView(source, R.id.layout_common_testimonial, "field 'mTestimonialUI'");
    target.mTestimonial_Content = Utils.findRequiredViewAsType(source, R.id.testinomial_content, "field 'mTestimonial_Content'", LatoRegularTextview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ServicesMenuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mOnlinePooja = null;
    target.mMatrimony = null;
    target.mVaastuConsultancy = null;
    target.mAstrology = null;
    target.mPoojaFamily = null;
    target.mPoojaChildren = null;
    target.mHomam = null;
    target.mSrathamPinda = null;
    target.mPilgrimage = null;
    target.mTestimonialUI = null;
    target.mTestimonial_Content = null;
  }
}

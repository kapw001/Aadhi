// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.testimonial;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TestimonialActivity_ViewBinding implements Unbinder {
  private TestimonialActivity target;

  @UiThread
  public TestimonialActivity_ViewBinding(TestimonialActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TestimonialActivity_ViewBinding(TestimonialActivity target, View source) {
    this.target = target;

    target.mTestiMonialListView = Utils.findRequiredViewAsType(source, R.id.testimonialList, "field 'mTestiMonialListView'", RecyclerView.class);
    target.mAddTestiMonial = Utils.findRequiredViewAsType(source, R.id.addTestimonial, "field 'mAddTestiMonial'", FloatingActionButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestimonialActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTestiMonialListView = null;
    target.mAddTestiMonial = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.testimonial;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TestimonialListAdapter$CurrentEventVH_ViewBinding implements Unbinder {
  private TestimonialListAdapter.CurrentEventVH target;

  @UiThread
  public TestimonialListAdapter$CurrentEventVH_ViewBinding(TestimonialListAdapter.CurrentEventVH target,
      View source) {
    this.target = target;

    target.mName = Utils.findRequiredViewAsType(source, R.id.rv_testimonial_name, "field 'mName'", TextView.class);
    target.mContent = Utils.findRequiredViewAsType(source, R.id.rv_testimonial_content, "field 'mContent'", TextView.class);
    target.letter = Utils.findRequiredViewAsType(source, R.id.image_view, "field 'letter'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestimonialListAdapter.CurrentEventVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mName = null;
    target.mContent = null;
    target.letter = null;
  }
}

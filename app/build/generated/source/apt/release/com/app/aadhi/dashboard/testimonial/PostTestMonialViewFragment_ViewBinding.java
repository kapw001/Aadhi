// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.testimonial;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoRegularEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PostTestMonialViewFragment_ViewBinding implements Unbinder {
  private PostTestMonialViewFragment target;

  @UiThread
  public PostTestMonialViewFragment_ViewBinding(PostTestMonialViewFragment target, View source) {
    this.target = target;

    target.mName = Utils.findRequiredViewAsType(source, R.id.row1_name, "field 'mName'", LatoRegularEditText.class);
    target.mEmail = Utils.findRequiredViewAsType(source, R.id.row2_email, "field 'mEmail'", LatoRegularEditText.class);
    target.mContent = Utils.findRequiredViewAsType(source, R.id.row3_content, "field 'mContent'", LatoRegularEditText.class);
    target.mSubmit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'mSubmit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PostTestMonialViewFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mName = null;
    target.mEmail = null;
    target.mContent = null;
    target.mSubmit = null;
  }
}

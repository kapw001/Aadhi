// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.slokasandmantras;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SlokasMantrasListAdapter$SlokasMantrasVH_ViewBinding implements Unbinder {
  private SlokasMantrasListAdapter.SlokasMantrasVH target;

  @UiThread
  public SlokasMantrasListAdapter$SlokasMantrasVH_ViewBinding(SlokasMantrasListAdapter.SlokasMantrasVH target,
      View source) {
    this.target = target;

    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", LatoBoldTextview.class);
    target.thumbnailImageView = Utils.findRequiredViewAsType(source, R.id.iv_thumbnail, "field 'thumbnailImageView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SlokasMantrasListAdapter.SlokasMantrasVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;
    target.thumbnailImageView = null;
  }
}

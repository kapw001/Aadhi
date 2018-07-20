// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.eappnotice;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.dashboard.audiomsg.AudioPlayerActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EAppNoticeDetailsActivity_ViewBinding extends AudioPlayerActivity_ViewBinding {
  private EAppNoticeDetailsActivity target;

  @UiThread
  public EAppNoticeDetailsActivity_ViewBinding(EAppNoticeDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EAppNoticeDetailsActivity_ViewBinding(EAppNoticeDetailsActivity target, View source) {
    super(target, source);

    this.target = target;

    target.mHeadingTV = Utils.findRequiredViewAsType(source, R.id.title, "field 'mHeadingTV'", LatoBoldTextview.class);
    target.mTitleView = Utils.findRequiredViewAsType(source, R.id.eapp_event_title, "field 'mTitleView'", LatoBoldTextview.class);
    target.mBannerImage = Utils.findRequiredViewAsType(source, R.id.eapp_event_banner_image, "field 'mBannerImage'", ImageView.class);
    target.mDescriptionTV = Utils.findRequiredViewAsType(source, R.id.eapp_event_description, "field 'mDescriptionTV'", LatoBoldTextview.class);
    target.mLayoutAudioPlayer = Utils.findRequiredView(source, R.id.layout_audio_player, "field 'mLayoutAudioPlayer'");
  }

  @Override
  public void unbind() {
    EAppNoticeDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mHeadingTV = null;
    target.mTitleView = null;
    target.mBannerImage = null;
    target.mDescriptionTV = null;
    target.mLayoutAudioPlayer = null;

    super.unbind();
  }
}

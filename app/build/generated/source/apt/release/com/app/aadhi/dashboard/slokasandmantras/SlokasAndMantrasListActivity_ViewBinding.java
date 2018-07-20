// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.slokasandmantras;

import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.dashboard.audiomsg.AudioPlayerActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SlokasAndMantrasListActivity_ViewBinding extends AudioPlayerActivity_ViewBinding {
  private SlokasAndMantrasListActivity target;

  @UiThread
  public SlokasAndMantrasListActivity_ViewBinding(SlokasAndMantrasListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SlokasAndMantrasListActivity_ViewBinding(SlokasAndMantrasListActivity target,
      View source) {
    super(target, source);

    this.target = target;

    target.mMessagesListRV = Utils.findRequiredViewAsType(source, R.id.rv_audio_list, "field 'mMessagesListRV'", RecyclerView.class);
    target.mTabLayout = Utils.findRequiredViewAsType(source, R.id.slokas_tab_layout, "field 'mTabLayout'", TabLayout.class);
    target.mAudioPlayerUI = Utils.findRequiredView(source, R.id.layout_audio_player, "field 'mAudioPlayerUI'");
  }

  @Override
  public void unbind() {
    SlokasAndMantrasListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mMessagesListRV = null;
    target.mTabLayout = null;
    target.mAudioPlayerUI = null;

    super.unbind();
  }
}

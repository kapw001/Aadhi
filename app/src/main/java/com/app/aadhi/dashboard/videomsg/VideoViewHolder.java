package com.app.aadhi.dashboard.videomsg;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.aadhi.R;

/**
 * Created by webnoodle on 6/29/2018.
 */

public class VideoViewHolder extends RecyclerView.ViewHolder{

    public FrameLayout videoContainer;
    public TextView title;

    public VideoViewHolder(View itemView) {
        super(itemView);

        videoContainer = (FrameLayout) itemView.findViewById(R.id.video_container);
    }
}

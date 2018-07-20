package com.app.aadhi.dashboard.videomsg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.aadhi.R;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.response.AudioVideoMessageListApi;

import java.util.ArrayList;
import java.util.List;

class VideoMessageListAdapterBackup extends RecyclerView.Adapter<VideoViewHolder> {

    private Context mContext;
    private List<AudioVideoMessageListApi.Data> mItems = new ArrayList<>();
    private IFragmentManager fragmentManager;

    private IMediaClickListener mClickListener;

    VideoMessageListAdapterBackup(Context context, IFragmentManager fragmentManager, IMediaClickListener clickListener) {
        this.mContext = context;
        this.mClickListener = clickListener;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_youtube_video_play, parent, false);
        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
//        AudioVideoMessageListApi.Data item = mItems.get(position);
//        //holder.containerLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.ic_youtube_video));
//        holder.title.setText(item.title);
    }

    @Override
    public void onViewAttachedToWindow(VideoViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getAdapterPosition();
        AudioVideoMessageListApi.Data video = mItems.get(position);
        video.binder.prepare();
        video.binder.bind(holder, fragmentManager);
    }

    @Override
    public void onViewDetachedFromWindow(VideoViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        int position = holder.getAdapterPosition();
        AudioVideoMessageListApi.Data video = mItems.get(position);
        video.binder.unBind(holder, fragmentManager);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void notifyItemsChanged(List<AudioVideoMessageListApi.Data> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

//    class VideoMessageVH extends RecyclerView.ViewHolder implements View.OnClickListener {
//        @BindView(R.id.icon)
//        ImageView icon;
//        @BindView(R.id.title)
//        LatoRegularTextview title;
//
//        VideoMessageVH(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            int id = mItems.get(getAdapterPosition()).id;
//            if (mClickListener != null) { mClickListener.onVideoClicked(id); }
//        }
//    }
}

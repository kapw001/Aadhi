package com.app.aadhi.dashboard.videomsg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.response.AudioVideoMessageListApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class VideoMessageListAdapter extends RecyclerView.Adapter<VideoMessageListAdapter.VideoMessageVH> {

    private Context mContext;
    private List<AudioVideoMessageListApi.Data> mItems = new ArrayList<>();

    private IMediaClickListener mClickListener;

    VideoMessageListAdapter(Context context, IMediaClickListener clickListener) {
        this.mContext = context;
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public VideoMessageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_eapp_event_audio, parent, false);
        return new VideoMessageVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoMessageVH holder, int position) {
        AudioVideoMessageListApi.Data item = mItems.get(position);
        //holder.containerLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.ic_youtube_video));
//        holder.title.setText(item.title);

        holder.icon.setImageResource(R.drawable.ic_youtube_video);
//        Picasso.get().load(R.drawable.video).fit().centerCrop().into(holder.icon);
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

    class VideoMessageVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.icon)
        ImageView icon;
//        @BindView(R.id.title)
//        LatoRegularTextview title;

        VideoMessageVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = mItems.get(getAdapterPosition()).id;
            if (mClickListener != null) { mClickListener.onVideoClicked(id); }
        }
    }
}

package com.app.aadhi.dashboard.audiomsg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.response.AudioVideoMessageListApi;
import com.app.aadhi.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class AudioMessageListAdapter extends RecyclerView.Adapter<AudioMessageListAdapter.AudioMessageVH> {

    private Context mContext;
    private List<AudioVideoMessageListApi.Data> mItems = new ArrayList<>();

    private IMediaClickListener mClickListener;

    AudioMessageListAdapter(Context context, IMediaClickListener clickListener) {
        this.mContext = context;
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public AudioMessageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_eapp_event_audio, parent, false);
        return new AudioMessageVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioMessageVH holder, int position) {
        AudioVideoMessageListApi.Data item = mItems.get(position);
        holder.title.setText(item.title);

//        holder.icon.setImageResource(R.drawable.audio);

        Picasso.get().load(R.drawable.audio).fit().centerCrop().into(holder.icon);
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

    class AudioMessageVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        LatoBoldTextview title;

        @BindView(R.id.icon)
        ImageView icon;

        AudioMessageVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = mItems.get(getAdapterPosition()).id;
            if (mClickListener != null) { mClickListener.onAudioClicked(id); }
        }
    }
}

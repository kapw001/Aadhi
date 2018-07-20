package com.app.aadhi.dashboard.slokasandmantras;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.response.SlokasMantrasListApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class SlokasMantrasListAdapter extends RecyclerView.Adapter<SlokasMantrasListAdapter.SlokasMantrasVH> {

    private Context mContext;
    private List<SlokasMantrasListApi.Data> mItems = new ArrayList<>();

    private IMediaClickListener mClickListener;

    SlokasMantrasListAdapter(Context context, IMediaClickListener clickListener) {
        this.mContext = context;
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public SlokasMantrasVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.rvitem_eapp_event_sloka_mantra, parent, false);
        return new SlokasMantrasVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SlokasMantrasVH holder, int position) {
        SlokasMantrasListApi.Data item = mItems.get(position);
        holder.title.setText(item.title);
        Picasso.get().load(item.thumbnail).fit().into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void notifyItemsChanged(List<SlokasMantrasListApi.Data> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    class SlokasMantrasVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        LatoBoldTextview title;
        @BindView(R.id.iv_thumbnail)
        ImageView thumbnailImageView;

        SlokasMantrasVH(View itemView) {
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
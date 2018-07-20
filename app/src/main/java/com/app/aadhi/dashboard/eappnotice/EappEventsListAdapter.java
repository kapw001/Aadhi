package com.app.aadhi.dashboard.eappnotice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.aadhi.R;
import com.app.aadhi.common.Constant;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.response.EappEventsListApi;
import com.app.aadhi.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class EappEventsListAdapter extends RecyclerView.Adapter<EappEventsListAdapter.EappEventsVH> {

    private Context mContext;
    private List<EappEventsListApi.Data> mItems = new ArrayList<>();

    private IMediaClickListener mClickListener;

    EappEventsListAdapter(Context context, IMediaClickListener clickListener) {
        this.mContext = context;
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public EappEventsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.rvitem_eapp_event_detail, parent, false);
        return new EappEventsVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EappEventsVH holder, int position) {
        EappEventsListApi.Data item = mItems.get(position);
        holder.title.setText(item.title);

        PicassoUtils.showImage(holder.thumbnailImageView,item.thumbnail);
//        Picasso.get().load(item.thumbnail).fit().into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void notifyItemsChanged(List<EappEventsListApi.Data> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    class EappEventsVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        LatoBoldTextview title;
        @BindView(R.id.iv_thumbnail)
        ImageView thumbnailImageView;

        EappEventsVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = mItems.get(getAdapterPosition()).id;
            String title = mItems.get(getAdapterPosition()).title;

            // Call Details Activity
            Intent eAppDetailPage = new Intent(itemView.getContext(), EAppNoticeDetailsActivity.class);
            eAppDetailPage.putExtra(Constant.Extras.EAPP_EVENT_ID, id);
            eAppDetailPage.putExtra(Constant.Extras.EAPP_EVENT_TITLE, title);
            itemView.getContext().startActivity(eAppDetailPage);
        }
    }
}
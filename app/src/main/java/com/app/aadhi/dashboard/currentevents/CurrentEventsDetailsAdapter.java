package com.app.aadhi.dashboard.currentevents;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.response.CurrentEventsDetailsApi;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentEventsDetailsAdapter extends RecyclerView.Adapter<CurrentEventsDetailsAdapter.CurrentEventVH> {

    private Context mContext;
    private IMediaClickListener mListener;

    public CurrentEventsDetailsAdapter(Context context, IMediaClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    private List<CurrentEventModel> list = new ArrayList<>();

    @Override
    public CurrentEventVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_item_tours, parent, false);
        return new CurrentEventVH(eventViewHolder);
    }

    @Override
    public void onBindViewHolder(CurrentEventVH holder, int position) {
        CurrentEventModel item = list.get(position);
        if (item.type == EventType.PHOTO) {
            Picasso.get().load(item.url).fit().into(holder.photoView);
            holder.title.setText("PHOTO " + item.position);
        } else {
            //Picasso.get().load(R.drawable.ic_youtube_video).fit().into(holder.photoView);
            holder.photoView.setImageResource(R.drawable.ic_youtube_video);
            holder.title.setText("VIDEO " + item.position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void notifyItemsChanged(CurrentEventsDetailsApi.Data response) {
        if (response != null) {
            list.clear();
            for (int i = 0; i < response.banners.size(); i++) {
                String banner = response.banners.get(i);
                list.add(new CurrentEventModel(EventType.PHOTO, banner, String.valueOf(i+1)));
            }
            for (int i = 0; i < response.videos.size(); i++) {
                String video = response.videos.get(i);
                list.add(new CurrentEventModel(EventType.VIDEO, video, String.valueOf(i+1)));
            }
            notifyDataSetChanged();
        }
    }

    public class CurrentEventVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_tour_banner)
        ImageView photoView;
        @BindView(R.id.tv_tour_title)
        LatoBoldTextview title;

        public CurrentEventVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mContext != null) {
                int type = list.get(getAdapterPosition()).type;

                try {

                    if (type == EventType.VIDEO) {
                        mListener.onVideoClicked(list.get(getAdapterPosition()).url);
                    } else {
                        //mListener.onPhotoClicked(list.get(getAdapterPosition()).url);
                        mListener.onPhotoClicked(((BitmapDrawable)photoView.getDrawable()).getBitmap());
                    }

                }catch (NullPointerException e){

                }


            }
        }

        public Context getContext() {
            return itemView.getContext();
        }
    }

    @IntDef({EventType.PHOTO, EventType.VIDEO})
    @interface EventType {
        int PHOTO = 1;
        int VIDEO = 2;
    }

    class CurrentEventModel {
        int type;
        String url;
        String position;
        CurrentEventModel(@EventType int type, String url, String position) {
            this.type = type;
            this.url = url;
            this.position = position;
        }
    }
}
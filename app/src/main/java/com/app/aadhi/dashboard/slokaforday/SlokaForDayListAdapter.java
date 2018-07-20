package com.app.aadhi.dashboard.slokaforday;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.response.SlokaOfTheDayApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administrator on 20/04/18.
 */

public class SlokaForDayListAdapter extends RecyclerView.Adapter<SlokaForDayListAdapter.PoojaViewHolder> {

    private Context mContext;
    private IMediaClickListener mAudioClickListener;

    public SlokaForDayListAdapter(Context context, IMediaClickListener listener) {
        this.mContext = context;
        this.mAudioClickListener = listener;
    }

    private List<SlokaOfTheDayApi.Data> list = new ArrayList<>();

    @Override
    public PoojaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pooja, parent, false);
        return new PoojaViewHolder(eventViewHolder);
    }

    @Override
    public void onBindViewHolder(PoojaViewHolder holder, int position) {

        if (position % 2 == 0) {
            holder.mLlRoot.setBackgroundColor(mContext.getResources().getColor(R.color.pooja_yellow));
            holder.mTvPoojaName.setTextColor(mContext.getResources().getColor(R.color.pooja_red));

        } else {
            holder.mLlRoot.setBackgroundColor(mContext.getResources().getColor(R.color.pooja_blue));
            holder.mTvPoojaName.setTextColor(mContext.getResources().getColor(R.color.pooja_black));
        }
        holder.mTvPoojaName.setText(list.get(position).title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void notifyItemsChanged(List<SlokaOfTheDayApi.Data> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }

    class PoojaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LatoBoldTextview mTvPoojaName;
        LinearLayout mLlRoot;

        PoojaViewHolder(View itemView) {
            super(itemView);

            mTvPoojaName = (LatoBoldTextview) itemView.findViewById(R.id.tv_pooja_name);
            mLlRoot = (LinearLayout) itemView.findViewById(R.id.ll_root);
            mLlRoot.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mAudioClickListener != null) {
                mAudioClickListener.onAudioClicked(list.get(getAdapterPosition()).audio_file);
            }
        }
    }
}

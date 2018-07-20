package com.app.aadhi.dashboard.pilgrimage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.aadhi.R;
import com.app.aadhi.common.Constant;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.network.response.PilgrimageToursListApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by administrator on 20/04/18.
 */

public class PilgrimageToursListAdapter extends RecyclerView.Adapter<PilgrimageToursListAdapter.PilgrimageToursViewHolder> {

    private Context mContext;

    public PilgrimageToursListAdapter(Context context) {
        mContext = context;
    }

    private List<PilgrimageToursListApi.Data> list = new ArrayList<>();

    @Override
    public PilgrimageToursViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_item_pl, parent, false);
        return new PilgrimageToursViewHolder(eventViewHolder);
    }

    @Override
    public void onBindViewHolder(PilgrimageToursViewHolder holder, int position) {
        Picasso.get().load(list.get(position).banner_image).fit().centerCrop().into(holder.mTourBanner);
        holder.mTvPilgrimageToursName.setText(list.get(position).title);

        Log.e(TAG, "onBindViewHolder: "+list.get(position).banner_image );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void notifyItemsChanged(List<PilgrimageToursListApi.Data> items) {
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    public class PilgrimageToursViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mTourBanner;
        LatoBoldTextview mTvPilgrimageToursName;

        public PilgrimageToursViewHolder(View itemView) {
            super(itemView);
            mTourBanner = (ImageView) itemView.findViewById(R.id.iv_tour_banner);
            mTvPilgrimageToursName = (LatoBoldTextview) itemView.findViewById(R.id.tv_tour_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mContext != null) {
                String title = list.get(getAdapterPosition()).title;
                int id = Integer.parseInt(list.get(getAdapterPosition()).id);
                Intent pilgrimageToursDetailsActivity = new Intent(mContext, PilgrimageTourDetailsActivity.class);
                pilgrimageToursDetailsActivity.putExtra(Constant.Extras.TOUR_ID, id);
                pilgrimageToursDetailsActivity.putExtra(Constant.Extras.TOUR_TITLE, title);
                mContext.startActivity(pilgrimageToursDetailsActivity);
            }
        }
    }
}

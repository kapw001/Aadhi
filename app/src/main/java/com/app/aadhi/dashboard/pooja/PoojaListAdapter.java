package com.app.aadhi.dashboard.pooja;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.aadhi.R;
import com.app.aadhi.common.Constant;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.network.response.PoojaListApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administrator on 20/04/18.
 */

public class PoojaListAdapter extends RecyclerView.Adapter<PoojaListAdapter.PoojaViewHolder> {

    private Context mContext;

    public PoojaListAdapter(Context context) {
        mContext = context;
    }

    private List<PoojaListApi.Data> list = new ArrayList<>();

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
        holder.mTvPoojaName.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void notifyItemsChanged(List<PoojaListApi.Data> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }

    public class PoojaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LatoBoldTextview mTvPoojaName;
        private LinearLayout mLlRoot;

        public PoojaViewHolder(View itemView) {
            super(itemView);

            mTvPoojaName = (LatoBoldTextview) itemView.findViewById(R.id.tv_pooja_name);
            mLlRoot = (LinearLayout) itemView.findViewById(R.id.ll_root);
            mLlRoot.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mContext != null) {
                String title = list.get(getAdapterPosition()).getTitle();
                int id = Integer.parseInt(list.get(getAdapterPosition()).getId());
                Intent poojaDetailsActivity = new Intent(mContext, PoojaDetailsActivity.class);
                poojaDetailsActivity.putExtra(Constant.Extras.POOJA_TITLE, title);
                poojaDetailsActivity.putExtra(Constant.Extras.POOJA_ID, id);
                mContext.startActivity(poojaDetailsActivity);
            }
        }
    }
}

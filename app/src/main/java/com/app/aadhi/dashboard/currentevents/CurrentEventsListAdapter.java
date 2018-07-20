package com.app.aadhi.dashboard.currentevents;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.aadhi.R;
import com.app.aadhi.common.Constant;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import com.app.aadhi.network.response.CurrentEventsListApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentEventsListAdapter extends RecyclerView.Adapter<CurrentEventsListAdapter.CurrentEventVH> {

    private Context mContext;

    public CurrentEventsListAdapter(Context context) {
        mContext = context;
    }

    private List<CurrentEventsListApi.InnerData> list = new ArrayList<>();

    @Override
    public CurrentEventVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvitem_current_event, parent, false);
        return new CurrentEventVH(eventViewHolder);
    }

    @Override
    public void onBindViewHolder(CurrentEventVH holder, int position) {
        holder.mEventBtn.setText(list.get(position).title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void notifyItemsChanged(List<CurrentEventsListApi.InnerData> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }

    public class CurrentEventVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.btn_current_event)
        LatoRegularTextview mEventBtn;

        public CurrentEventVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mContext != null) {
                int id = list.get(getAdapterPosition()).id;
                Intent poojaDetailsActivity = new Intent(mContext, CurrentEventsDetailsActivity.class);
                poojaDetailsActivity.putExtra(Constant.Extras.CURRENT_EVENT_ID, id);
                mContext.startActivity(poojaDetailsActivity);
            }
        }
    }
}
package com.app.aadhi.dashboard.testimonial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.app.aadhi.R;
import com.app.aadhi.common.Constant;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import com.app.aadhi.dashboard.currentevents.CurrentEventsDetailsActivity;
import com.app.aadhi.network.response.CurrentEventsListApi;
import com.app.aadhi.network.response.TestimonialListApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestimonialListAdapter extends RecyclerView.Adapter<TestimonialListAdapter.CurrentEventVH> {

    private Context mContext;

    public TestimonialListAdapter(Context context) {
        mContext = context;
    }

    private List<TestimonialListApi.Data> list = new ArrayList<>();

    @Override
    public CurrentEventVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvitem_testinomial, parent, false);
        return new CurrentEventVH(eventViewHolder);
    }

    @Override
    public void onBindViewHolder(CurrentEventVH holder, int position) {

        TestimonialListApi.Data data=list.get(position);
//        holder.mEmail.setText(data.email);
        holder.mName.setText(data.name);
        holder.mContent.setText(data.content);

        String c= String.valueOf(data.email.charAt(0)).toUpperCase();

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT

        int color = generator.getColor(data.email);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(c, color);

        holder.letter.setImageDrawable(drawable);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void notifyItemsChanged(List<TestimonialListApi.Data> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }

    public class CurrentEventVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.rv_testimonial_name)
        TextView mName;
//        @BindView(R.id.rv_testimonial_email)
//        TextView mEmail;
        @BindView(R.id.rv_testimonial_content)
        TextView mContent;

        @BindView(R.id.image_view)
        ImageView letter;

        public CurrentEventVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mContext != null) {

            }
        }
    }
}
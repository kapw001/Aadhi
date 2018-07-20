package com.app.aadhi.dashboard.currentevents;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.aadhi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullScreenImageViewFragment extends DialogFragment {

    @BindView(R.id.iv_full_screen_image)
    ImageView mFullScreenIV;

    @BindView(R.id.close)
    ImageView mClose;

    Bitmap mImageDrawable;


    public void setImageDrawable(Bitmap imageDrawable) {
        this.mImageDrawable = imageDrawable;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_full_screen_image, container, false);
        ButterKnife.bind(this, fragmentView);

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFullScreenIV.setImageBitmap(mImageDrawable);
    }

    @Override
    public void onResume() {
        super.onResume();
        // make dialog full screen
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}

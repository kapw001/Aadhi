package com.app.aadhi.dashboard.eappnotice;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.common.Constant;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.dashboard.audiomsg.AudioPlayerActivity;
import com.app.aadhi.dashboard.currentevents.FullScreenImageViewFragment;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.EappEventsDetailsApi;
import com.app.aadhi.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EAppNoticeDetailsActivity extends AudioPlayerActivity implements IMediaClickListener {

    @BindView(R.id.title)
    LatoBoldTextview mHeadingTV;
    @BindView(R.id.eapp_event_title)
    LatoBoldTextview mTitleView;
    @BindView(R.id.eapp_event_banner_image)
    ImageView mBannerImage;
    @BindView(R.id.eapp_event_description)
    LatoBoldTextview mDescriptionTV;

    @BindView(R.id.layout_audio_player)
    View mLayoutAudioPlayer;

    @Override
    protected int getLayoutResId() {
        return R.layout.eappnotice_activity_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_current_events_list);
        initViews();
        callEappEventsDetailsApiService();

        mLayoutAudioPlayer.setVisibility(View.GONE);
    }

    @Override
    protected void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        mTitleView.setText(R.string.eppapp_title);
        mHeadingTV.setText(getIntent().getExtras().getString(Constant.Extras.EAPP_EVENT_TITLE));
        mBannerImage.setOnClickListener(this);
    }

    private void callEappEventsDetailsApiService() {
        showProgressDialog("Loading");

        int id = getIntent().getIntExtra(Constant.Extras.EAPP_EVENT_ID, -1);
        Call<EappEventsDetailsApi> eventDetailsApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchEappEventDetails(String.valueOf(id));
        eventDetailsApiService.enqueue(new Callback<EappEventsDetailsApi>() {
            @Override
            public void onResponse(@NonNull Call<EappEventsDetailsApi> call, @NonNull Response<EappEventsDetailsApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    loadCurrentEventsDetailsInList(response.body().data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<EappEventsDetailsApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(EAppNoticeDetailsActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCurrentEventsDetailsInList(EappEventsDetailsApi.Data data) {
        if (data != null) {

            PicassoUtils.showImage(mBannerImage,data.banner_image);

//            Picasso.get().load(data.banner_image).fit().into(mBannerImage);
            mDescriptionTV.setText(data.description);

            boolean isValidAudioUrl = !android.text.TextUtils.isEmpty(data.audio_file);
            if (isValidAudioUrl) {
                mLayoutAudioPlayer.setVisibility(View.VISIBLE);
                playAudioMsg(data.audio_file,data.title);

            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.eapp_event_banner_image:
                if (mBannerImage.getDrawable() != null) {
                    // Picasso will hold a cache reference to the url, so use Picasso to view the image, using the same url, in the FullScreenFragment.
                    FullScreenImageViewFragment fullScreenImageFragment = new FullScreenImageViewFragment();
                    fullScreenImageFragment.setImageDrawable(((BitmapDrawable) mBannerImage.getDrawable()).getBitmap());
                    fullScreenImageFragment.show(getSupportFragmentManager(), FullScreenImageViewFragment.class.getSimpleName());
                }
                break;
        }
    }

    @Override
    public void showTestimonial(String content) {

    }
}
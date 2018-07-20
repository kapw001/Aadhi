package com.app.aadhi.dashboard.currentevents;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.Constant;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.CurrentEventsDetailsApi;
import com.app.aadhi.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentEventsDetailsActivity extends RootActivity implements IMediaClickListener {

    @BindView(R.id.rv_current_events)
    RecyclerView mCurrentEventsListView;
    @BindView(R.id.title)
    LatoBoldTextview mTitleView;

    @BindView(R.id.heading)
    LatoBoldTextview mHeading;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_current_events_list);
        initViews();
        callCurrentEventsDetailsApiService();
    }

    private void initViews() {
        ButterKnife.bind(this);
        mCurrentEventsListView.setLayoutManager(new GridLayoutManager(this, 2));
        CurrentEventsDetailsAdapter adapter = new CurrentEventsDetailsAdapter(this, this);
        mCurrentEventsListView.addItemDecoration(new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.GRID));
        mCurrentEventsListView.setAdapter(adapter);
    }

    private void callCurrentEventsDetailsApiService() {
        showProgressDialog("Loading");

        int id = getIntent().getIntExtra(Constant.Extras.CURRENT_EVENT_ID, -1);
        Call<CurrentEventsDetailsApi> eventDetailsApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchCurrentEventDetails(String.valueOf(id));
        eventDetailsApiService.enqueue(new Callback<CurrentEventsDetailsApi>() {
            @Override
            public void onResponse(@NonNull Call<CurrentEventsDetailsApi> call, @NonNull Response<CurrentEventsDetailsApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    loadCurrentEventsDetailsInList(response.body().data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrentEventsDetailsApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(CurrentEventsDetailsActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCurrentEventsDetailsInList(CurrentEventsDetailsApi.Data data) {
        mTitleView.setVisibility(View.GONE);
        mHeading.setText(data.main.title);
        ((CurrentEventsDetailsAdapter)mCurrentEventsListView.getAdapter()).notifyItemsChanged(data);
    }

    @Override
    public void onVideoClicked(String url) {
        AppUtils.playVideoMsg(CurrentEventsDetailsActivity.this, url);
    }

//    @Override
//    public void onPhotoClicked(String url) {
//        // Picasso will hold a cache reference to the url, so use Picasso to view the image, using the same url, in the FullScreenFragment.
//
//    }
    @Override
    public void onPhotoClicked(Bitmap bitmap) {
        // Picasso will hold a cache reference to the url, so use Picasso to view the image, using the same url, in the FullScreenFragment.
        FullScreenImageViewFragment fullScreenImageFragment = new FullScreenImageViewFragment();
        fullScreenImageFragment.setImageDrawable(bitmap);
        fullScreenImageFragment.show(getSupportFragmentManager(), FullScreenImageViewFragment.class.getSimpleName());
    }
}

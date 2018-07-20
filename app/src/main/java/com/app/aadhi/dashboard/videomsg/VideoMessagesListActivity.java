package com.app.aadhi.dashboard.videomsg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.AudioVideoMessageDetailApi;
import com.app.aadhi.network.response.AudioVideoMessageListApi;
import com.app.aadhi.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoMessagesListActivity extends RootActivity implements IMediaClickListener {

    @BindView(R.id.rv_audio_list)
    RecyclerView mMessagesListRV;
    @BindView(R.id.layout_audio_player)
    ViewGroup mAudioPlayerLayout;
    @BindView(R.id.title)
    LatoBoldTextview mTitltTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_audio_video_messages);
        initViews();
        callVideoMessageListApiService();
    }

    private void initViews() {
        ButterKnife.bind(this);
        mAudioPlayerLayout.setVisibility(View.GONE);
        // setTitle
        mTitltTV.setText(getString(R.string.video_msg_title));

        GridLayoutManager threeColumnGridLayout = new GridLayoutManager(this, 3);
        mMessagesListRV.setLayoutManager(threeColumnGridLayout);
        EqualSpacingItemDecoration gridSpace = new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.GRID);
        mMessagesListRV.addItemDecoration(gridSpace);
        VideoMessageListAdapter adapter = new VideoMessageListAdapter(this, this);
        mMessagesListRV.setAdapter(adapter);
    }

    private void callVideoMessageListApiService() {
        showProgressDialog("Loading");

        Call<AudioVideoMessageListApi> audioMessageListApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchVideoMessages();
        audioMessageListApiService.enqueue(new Callback<AudioVideoMessageListApi>() {
            @Override
            public void onResponse(@NonNull Call<AudioVideoMessageListApi> call, @NonNull Response<AudioVideoMessageListApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    loadVideoListResponseInList(response.body().data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AudioVideoMessageListApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(VideoMessagesListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadVideoListResponseInList(List<AudioVideoMessageListApi.Data> items) {
        ((VideoMessageListAdapter)mMessagesListRV.getAdapter()).notifyItemsChanged(items);
    }

    @Override
    public void onVideoClicked(int id) {
        showProgressDialog("Loading");

        Call<AudioVideoMessageDetailApi> AudioMessageDetailApiCall = RetrofitAdapter.getNetworkApiServiceClient().fetchVideoMessageDetails(String.valueOf(id));
        AudioMessageDetailApiCall.enqueue(new Callback<AudioVideoMessageDetailApi>() {
            @Override
            public void onResponse(@NonNull Call<AudioVideoMessageDetailApi> call, @NonNull Response<AudioVideoMessageDetailApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    AppUtils.playVideoMsg(VideoMessagesListActivity.this, response.body().data.video_url);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AudioVideoMessageDetailApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(VideoMessagesListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

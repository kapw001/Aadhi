package com.app.aadhi.dashboard.videomsg;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
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

public class VideoMessagesListActivityBackUp extends RootActivity implements IMediaClickListener,IFragmentManager  {

    @BindView(R.id.rv_audio_list)
    RecyclerView mMessagesListRV;
    @BindView(R.id.layout_audio_player)
    ViewGroup mAudioPlayerLayout;
    @BindView(R.id.title)
    LatoBoldTextview mTitltTV;
    float scale;

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
        mTitltTV.setText(getString(R.string.video_message));

        GridLayoutManager threeColumnGridLayout = new GridLayoutManager(this, 3);
        mMessagesListRV.setLayoutManager(new LinearLayoutManager(this));
//        EqualSpacingItemDecoration gridSpace = new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.VERTICAL);
//        mMessagesListRV.addItemDecoration(gridSpace);

        mMessagesListRV.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int adapterPosition = parent.getChildAdapterPosition(view);
                if (adapterPosition == 0) {
                    outRect.top = (int) (4 * scale);
                }
                outRect.bottom = (int) (10 * scale);
            }
        });
//        VideoMessageListAdapter adapter = new VideoMessageListAdapter(this,this,this);
//        mMessagesListRV.setAdapter(adapter);
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
                Toast.makeText(VideoMessagesListActivityBackUp.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadVideoListResponseInList(List<AudioVideoMessageListApi.Data> items) {

        items.clear();

        AudioVideoMessageListApi.Data audioVideoMessageListApi=new AudioVideoMessageListApi.Data();

        audioVideoMessageListApi.title="https://www.youtube.com/watch?v=Mkr8cWe0QQ8";

        items.add(audioVideoMessageListApi);

        AudioVideoMessageListApi.Data audioVideoMessageListApi1=new AudioVideoMessageListApi.Data();

        audioVideoMessageListApi1.title="https://www.youtube.com/watch?v=Mkr8cWe0QQ8";

        items.add(audioVideoMessageListApi1);

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
                    AppUtils.playVideoMsg(VideoMessagesListActivityBackUp.this, response.body().data.video_url);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AudioVideoMessageDetailApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(VideoMessagesListActivityBackUp.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public Fragment getSupportFragment() {
        return getSupportFragment();
    }
}

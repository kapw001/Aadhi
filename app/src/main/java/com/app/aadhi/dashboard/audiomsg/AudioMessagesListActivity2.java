package com.app.aadhi.dashboard.audiomsg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.AudioVideoMessageDetailApi;
import com.app.aadhi.network.response.AudioVideoMessageListApi;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudioMessagesListActivity2 extends AudioPlayerActivity implements IMediaClickListener, View.OnClickListener {

    @BindView(R.id.rv_audio_list)
    RecyclerView mMessagesListRV;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_audio_video_messages;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callAudioMessageListApiService();
    }

    @Override
    protected void initViews() {
        super.initViews();
        GridLayoutManager threeColumnGridLayout = new GridLayoutManager(this, 3);
        mMessagesListRV.setLayoutManager(threeColumnGridLayout);
        EqualSpacingItemDecoration gridSpace = new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.GRID);
        mMessagesListRV.addItemDecoration(gridSpace);
        AudioMessageListAdapter adapter = new AudioMessageListAdapter(this, this);
        mMessagesListRV.setAdapter(adapter);
    }

    private void callAudioMessageListApiService() {
        showProgressDialog("Loading");

        Call<AudioVideoMessageListApi> audioMessageListApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchAudioMessages();
        audioMessageListApiService.enqueue(new Callback<AudioVideoMessageListApi>() {
            @Override
            public void onResponse(@NonNull Call<AudioVideoMessageListApi> call, @NonNull Response<AudioVideoMessageListApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    loadAudioListResponseInList(response.body().data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AudioVideoMessageListApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(AudioMessagesListActivity2.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAudioListResponseInList(List<AudioVideoMessageListApi.Data> items) {
        ((AudioMessageListAdapter)mMessagesListRV.getAdapter()).notifyItemsChanged(items);
    }


    @Override
    public void onAudioClicked(int id) {
        showProgressDialog("Loading");

        Observable<AudioVideoMessageDetailApi> AudioMessageDetailApiCall = RetrofitAdapter.getNetworkApiServiceClient().fetchAudioMessageDetails(String.valueOf(id));

        AudioMessageDetailApiCall.subscribe(new Observer<AudioVideoMessageDetailApi>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull AudioVideoMessageDetailApi audioVideoMessageDetailApi) {
                if (audioVideoMessageDetailApi.success) {
                    hideProgressDialog();
                    playAudioMsg(audioVideoMessageDetailApi.data.audio_file);
                } else {
                    onError(null);
                }
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable throwable) {
                hideProgressDialog();
                // show error
                Toast.makeText(AudioMessagesListActivity2.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });

//        AudioMessageDetailApiCall.enqueue(new Callback<AudioVideoMessageDetailApi>() {
//            @Override
//            public void onResponse(@NonNull Call<AudioVideoMessageDetailApi> call, @NonNull Response<AudioVideoMessageDetailApi> response) {
//                if (response.isSuccessful() && response.body().success) {
//                    hideProgressDialog();
//                    playAudioMsg(response.body().data.audio_file);
//                } else {
//                    onFailure(call, null);
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<AudioVideoMessageDetailApi> call, Throwable t) {
//                hideProgressDialog();
//                // show error
//                Toast.makeText(AudioMessagesListActivity2.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void showTestimonial(String content) {

    }
}

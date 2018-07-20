package com.app.aadhi.dashboard.slokaforday;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.dashboard.audiomsg.AudioPlayerActivity;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.SlokaOfTheDayApi;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlokaForTheDayListActivity extends AudioPlayerActivity implements IMediaClickListener, View.OnClickListener {

    @BindView(R.id.rv_audio_list)
    RecyclerView mMessagesListRV;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_audio_video_messages;
        //return R.layout.activity_sloka_for_the_day_list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callSlokaOfTheDayApiService();
    }

    @Override
    protected void initViews() {
        super.initViews();
        mMessagesListRV.setLayoutManager(new LinearLayoutManager(this));
        mMessagesListRV.addItemDecoration(new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.VERTICAL));
        SlokaForDayListAdapter adapter = new SlokaForDayListAdapter(this, this);
        mMessagesListRV.setAdapter(adapter);
    }

    private void callSlokaOfTheDayApiService() {
        showProgressDialog("Loading");

        Observable<SlokaOfTheDayApi> SlokaOfTheDayApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchSlokaOfTheDayList();

        SlokaOfTheDayApiService.subscribe(new Observer<SlokaOfTheDayApi>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull SlokaOfTheDayApi slokaOfTheDayApi) {
                if (slokaOfTheDayApi.success) {
                    hideProgressDialog();
                    loadAudioListResponseInList(slokaOfTheDayApi.data);
                } else {
                    onError(new Throwable());
                }
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable throwable) {
                hideProgressDialog();
                // show error
                Toast.makeText(SlokaForTheDayListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });

//        SlokaOfTheDayApiService.enqueue(new Callback<SlokaOfTheDayApi>() {
//            @Override
//            public void onResponse(@NonNull Call<SlokaOfTheDayApi> call, @NonNull Response<SlokaOfTheDayApi> response) {
//                if (response.isSuccessful() && response.body().success) {
//                    hideProgressDialog();
//                    loadAudioListResponseInList(response.body().data);
//                } else {
//                    onFailure(call, null);
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<SlokaOfTheDayApi> call, Throwable t) {
//                hideProgressDialog();
//                // show error
//                Toast.makeText(SlokaForTheDayListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void loadAudioListResponseInList(List<SlokaOfTheDayApi.Data> items) {
        ((SlokaForDayListAdapter)mMessagesListRV.getAdapter()).notifyItemsChanged(items);
    }


    @Override
    public void onAudioClicked(int id) { }

    @Override
    public void onAudioClicked(String url) {
        playAudioMsg(url);
    }

    @Override
    public void showTestimonial(String content) {

    }
}
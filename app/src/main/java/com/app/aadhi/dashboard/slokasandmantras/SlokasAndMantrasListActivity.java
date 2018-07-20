package com.app.aadhi.dashboard.slokasandmantras;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.dashboard.audiomsg.AudioPlayerActivity;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.SlokasMantrasDetailApi;
import com.app.aadhi.network.response.SlokasMantrasListApi;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlokasAndMantrasListActivity extends AudioPlayerActivity implements IMediaClickListener, View.OnClickListener {

    @BindView(R.id.rv_audio_list)
    RecyclerView mMessagesListRV;
    @BindView(R.id.slokas_tab_layout)
    TabLayout mTabLayout;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private SlokasAndMantrasCombinedApi mSlokasAndMantrasListResponseCache;

    private boolean isSlokasDisplay=true;

    @BindView(R.id.layout_audio_player)
    View mAudioPlayerUI;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_slokas_and_mantras_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callSlokasMantrasListApiService();
    }

    @Override
    protected void onDestroy() {
        mCompositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void initViews() {
        super.initViews();
        GridLayoutManager twoColumnGridLayout = new GridLayoutManager(this, 2);
        mMessagesListRV.setLayoutManager(twoColumnGridLayout);
        EqualSpacingItemDecoration gridSpace = new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.GRID);
        mMessagesListRV.addItemDecoration(gridSpace);
        SlokasMantrasListAdapter adapter = new SlokasMantrasListAdapter(this, this);
        mMessagesListRV.setAdapter(adapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    if (mSlokasAndMantrasListResponseCache != null) {
                        isSlokasDisplay=true;
                        loadSlokasResponseInList(mSlokasAndMantrasListResponseCache.slokas.data);
                    }
                } else {
                    if (mSlokasAndMantrasListResponseCache != null) {
                        isSlokasDisplay=false;
                        loadMantrasResponseInList(mSlokasAndMantrasListResponseCache.mantras.data);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        mAudioPlayerUI.setVisibility(View.GONE);
    }

    private void callSlokasMantrasListApiService() {
        showProgressDialog("Loading");

//        Call<SlokasMantrasListApi> SlokasMantrasListApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchSlokasList();
//        SlokasMantrasListApiService.enqueue(new Callback<SlokasMantrasListApi>() {
//            @Override
//            public void onResponse(@NonNull Call<SlokasMantrasListApi> call, @NonNull Response<SlokasMantrasListApi> response) {
//                if (response.isSuccessful() && response.body().success) {
//                    hideProgressDialog();
//                    loadSlokasResponseInList(response.body().data.data);
//                } else {
//                    onFailure(call, null);
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<SlokasMantrasListApi> call, Throwable t) {
//                hideProgressDialog();
//                // show error
//                Toast.makeText(SlokasAndMantrasListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
//            }
//        });

        Observable<SlokasMantrasListApi> slokasObservable = RetrofitAdapter.getNetworkApiServiceClient().fetchSlokasList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<SlokasMantrasListApi> mantrasObservable = RetrofitAdapter.getNetworkApiServiceClient().fetchMantrasList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<SlokasAndMantrasCombinedApi> combined = Observable.zip(slokasObservable, mantrasObservable, new BiFunction<SlokasMantrasListApi, SlokasMantrasListApi, SlokasAndMantrasCombinedApi>() {
            @Override
            public SlokasAndMantrasCombinedApi apply(SlokasMantrasListApi slokasResponse, SlokasMantrasListApi mantrasResponse) throws Exception {
                return new SlokasAndMantrasCombinedApi(slokasResponse, mantrasResponse);
            }
        });

        mCompositeDisposable.add(combined
                .subscribe(slokasAndMantrasListResponse -> {
                            hideProgressDialog();
                            if (slokasAndMantrasListResponse != null) {
                                cacheTheResponse(slokasAndMantrasListResponse);
                                loadSlokasResponseInList(slokasAndMantrasListResponse.slokas.data);
                            } else {
                                // show error
                                Toast.makeText(SlokasAndMantrasListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        }, throwable -> {
                            hideProgressDialog();
                            // show error
                            Toast.makeText(SlokasAndMantrasListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
                        })
        );
    }

    private void cacheTheResponse(SlokasAndMantrasCombinedApi slokasAndMantrasListResponse) {
        mSlokasAndMantrasListResponseCache = slokasAndMantrasListResponse;
    }

    private void loadSlokasResponseInList(List<SlokasMantrasListApi.Data> items) {

//        Log.e(TAG, "loadSlokasResponseInList: "+new Gson().toJson(items));

        mMessagesListRV.getLayoutManager().smoothScrollToPosition(mMessagesListRV, null, 0);
        ((SlokasMantrasListAdapter)mMessagesListRV.getAdapter()).notifyItemsChanged(items);
    }

    private void loadMantrasResponseInList(List<SlokasMantrasListApi.Data> items) {
//        Log.e(TAG, "loadMantrasResponseInList: "+new Gson().toJson(items));
        mMessagesListRV.getLayoutManager().smoothScrollToPosition(mMessagesListRV, null, 0);
        ((SlokasMantrasListAdapter)mMessagesListRV.getAdapter()).notifyItemsChanged(items);
    }

    @Override
    public void onAudioClicked(int id) {

        if (isSlokasDisplay) {
            showProgressDialog("Loading");

            Call<SlokasMantrasDetailApi> SlokasMantrasDetailApiCall = RetrofitAdapter.getNetworkApiServiceClient().fetchSlokasWithId(String.valueOf(id));
            SlokasMantrasDetailApiCall.enqueue(new Callback<SlokasMantrasDetailApi>() {
                @Override
                public void onResponse(@NonNull Call<SlokasMantrasDetailApi> call, @NonNull Response<SlokasMantrasDetailApi> response) {
                    if (response.isSuccessful() && response.body().success) {

//                        Log.e(TAG, "onResponse: "+new Gson().toJson(response.body()) );
                        hideProgressDialog();
                        mAudioPlayerUI.setVisibility(View.VISIBLE);
//                        setSongName(response.body().data.title);
                        playAudioMsg(response.body().data.audio_file,response.body().data.title);

                        // TODO
                        //updateThumbnaiImage(response.body().data.thumbnail);
                    } else {
                        onFailure(call, null);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SlokasMantrasDetailApi> call, Throwable t) {
                    hideProgressDialog();
                    // show error
                    Toast.makeText(SlokasAndMantrasListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }else {


            showProgressDialog("Loading");

            Call<SlokasMantrasDetailApi> SlokasMantrasDetailApiCall = RetrofitAdapter.getNetworkApiServiceClient().fetchMantrasWithId(String.valueOf(id));
            SlokasMantrasDetailApiCall.enqueue(new Callback<SlokasMantrasDetailApi>() {
                @Override
                public void onResponse(@NonNull Call<SlokasMantrasDetailApi> call, @NonNull Response<SlokasMantrasDetailApi> response) {
                    if (response.isSuccessful() && response.body().success) {

//                        Log.e(TAG, "onResponse: "+new Gson().toJson(response.body()) );
                        hideProgressDialog();
                        mAudioPlayerUI.setVisibility(View.VISIBLE);
//                        setSongName(response.body().data.title);
                        playAudioMsg(response.body().data.audio_file,response.body().data.title);

                        // TODO
                        //updateThumbnaiImage(response.body().data.thumbnail);
                    } else {
                        onFailure(call, null);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SlokasMantrasDetailApi> call, Throwable t) {
                    hideProgressDialog();
                    // show error
                    Toast.makeText(SlokasAndMantrasListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void updateThumbnaiImage(String url, int position) {
        // TODO
        //((SlokasMantrasListAdapter)mMessagesListRV.getAdapter()).updateThumbnailImage(url, position);
    }

    @Override
    public void showTestimonial(String content) {

    }
}

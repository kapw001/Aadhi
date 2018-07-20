package com.app.aadhi.dashboard.pilgrimage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.PilgrimageToursListApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilgrimageTourListActivity extends RootActivity {

    private RecyclerView mRvToursList;
    private PilgrimageToursListAdapter mToursListAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_pilgrimage_list);

        initViews();
        callToursListApiService();
    }

    private void initViews() {
        mRvToursList = (RecyclerView) findViewById(R.id.rv_Tours_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRvToursList.setLayoutManager(gridLayoutManager);
        mToursListAdpater = new PilgrimageToursListAdapter(this);
        mRvToursList.addItemDecoration(new EqualSpacingItemDecoration(8, EqualSpacingItemDecoration.GRID));
        mRvToursList.setAdapter(mToursListAdpater);
    }

    private void callToursListApiService() {
        showProgressDialog("Loading");

        Call<PilgrimageToursListApi> ToursListApiCall = RetrofitAdapter.getNetworkApiServiceClient().fetchToursList();
        ToursListApiCall.enqueue(new Callback<PilgrimageToursListApi>() {
            @Override
            public void onResponse(@NonNull Call<PilgrimageToursListApi> call, @NonNull Response<PilgrimageToursListApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    loadToursListResponseInList(response.body().data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PilgrimageToursListApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(PilgrimageTourListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadToursListResponseInList(List<PilgrimageToursListApi.Data> items) {
        mToursListAdpater.notifyItemsChanged(items);
    }
}
package com.app.aadhi.dashboard.pooja;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.PoojaListApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by administrator on 20/04/18.
 */

public class PoojaListActivity extends RootActivity {

    private RecyclerView mRvPoojaList;
    private PoojaListAdapter mPoojaListAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_pooja_list);

        initViews();
        callPoojaListApiService();
    }

    private void initViews() {
        mRvPoojaList = (RecyclerView) findViewById(R.id.rv_pooja_list);
        mRvPoojaList.setLayoutManager(new LinearLayoutManager(this));
        //mRvPoojaList.addItemDecoration(new DividerItemDecoration(this));
        mPoojaListAdpater = new PoojaListAdapter(this);
        mRvPoojaList.addItemDecoration(new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.VERTICAL));
        mRvPoojaList.setAdapter(mPoojaListAdpater);
    }

    private void callPoojaListApiService() {
        showProgressDialog("Loading");

        Call<PoojaListApi> poojaListApiCall = RetrofitAdapter.getNetworkApiServiceClient().fetchPoojaInfo();
        poojaListApiCall.enqueue(new Callback<PoojaListApi>() {
            @Override
            public void onResponse(@NonNull Call<PoojaListApi> call, @NonNull Response<PoojaListApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    loadPoojaListResponseInList(response.body().data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PoojaListApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(PoojaListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPoojaListResponseInList(List<PoojaListApi.Data> items) {
        mPoojaListAdpater.notifyItemsChanged(items);
    }
}

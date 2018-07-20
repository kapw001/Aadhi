package com.app.aadhi.dashboard.eappnotice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.mediaplayer.IMediaClickListener;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.EappEventsListApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EAppNoticeListActivity extends RootActivity implements IMediaClickListener {

    @BindView(R.id.heading)
    LatoBoldTextview mTitleTV;
    @BindView(R.id.rv_current_events)
    RecyclerView mEappListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_current_events_list);
        initViews();
        callEappEventsListApiService();
    }

    private void initViews() {
        ButterKnife.bind(this);
        mTitleTV.setText(R.string.eppapp_title);
        mEappListView.setLayoutManager(new GridLayoutManager(this, 2));
        EappEventsListAdapter adapter = new EappEventsListAdapter(this, this);
        mEappListView.addItemDecoration(new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.GRID));
        mEappListView.setAdapter(adapter);
    }

    private void callEappEventsListApiService() {
        showProgressDialog("Loading");

        Call<EappEventsListApi> eappEventsListApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchEappEventsList();
        eappEventsListApiService.enqueue(new Callback<EappEventsListApi>() {
            @Override
            public void onResponse(@NonNull Call<EappEventsListApi> call, @NonNull Response<EappEventsListApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    loadEappEventsListResponseInList(response.body().data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<EappEventsListApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(EAppNoticeListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadEappEventsListResponseInList(List<EappEventsListApi.Data> items) {

        ((EappEventsListAdapter) mEappListView.getAdapter()).notifyItemsChanged(items);
    }
}

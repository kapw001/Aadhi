package com.app.aadhi.dashboard.currentevents;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.CurrentEventsListApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentEventsListActivity extends RootActivity {

    @BindView(R.id.rv_current_events)
    RecyclerView mCurrentEventsListView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_current_events_list);
        initViews();
        callCurrentEventsListApiService();
    }

    private void initViews() {
        ButterKnife.bind(this);
        mCurrentEventsListView.setLayoutManager(new GridLayoutManager(this, 3));
        CurrentEventsListAdapter adapter = new CurrentEventsListAdapter(this);
        mCurrentEventsListView.addItemDecoration(new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.GRID));
        mCurrentEventsListView.setAdapter(adapter);
    }

    private void callCurrentEventsListApiService() {
        showProgressDialog("Loading");

        Call<CurrentEventsListApi> currentEventsListApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchCurrentEventsList();
        currentEventsListApiService.enqueue(new Callback<CurrentEventsListApi>() {
            @Override
            public void onResponse(@NonNull Call<CurrentEventsListApi> call, @NonNull Response<CurrentEventsListApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    loadCurrentEventsListResponseInList(response.body().data.data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrentEventsListApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(CurrentEventsListActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCurrentEventsListResponseInList(List<CurrentEventsListApi.InnerData> items) {
        ((CurrentEventsListAdapter)mCurrentEventsListView.getAdapter()).notifyItemsChanged(items);
    }


}

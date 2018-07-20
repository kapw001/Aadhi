package com.app.aadhi.dashboard.testimonial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.Constant;
import com.app.aadhi.dashboard.DashboardActivity;
import com.app.aadhi.dashboard.InformationsMenuActivity;
import com.app.aadhi.dashboard.ServicesMenuActivity;
import com.app.aadhi.dashboard.currentevents.CurrentEventsDetailsActivity;
import com.app.aadhi.dashboard.currentevents.CurrentEventsDetailsAdapter;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.CurrentEventsDetailsApi;
import com.app.aadhi.network.response.CurrentEventsListApi;
import com.app.aadhi.network.response.TestimonialListApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestimonialActivity extends RootActivity {

    @BindView(R.id.testimonialList)
    RecyclerView mTestiMonialListView;
    @BindView(R.id.addTestimonial)
    FloatingActionButton mAddTestiMonial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_testinonial);

        initViews();
        callCurrentEventsDetailsApiService();
    }

    private void initViews() {
        ButterKnife.bind(this);
//        hideToolbar();

        mAddTestiMonial.setOnClickListener(this);

        mTestiMonialListView.setLayoutManager(new LinearLayoutManager(this));
        TestimonialListAdapter adapter = new TestimonialListAdapter(this);
        mTestiMonialListView.addItemDecoration(new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.VERTICAL));
        mTestiMonialListView.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.addTestimonial:

//                startActivity(new Intent(TestimonialActivity.this,PostTestimonialActivity.class));

                PostTestMonialViewFragment viewFragment=new PostTestMonialViewFragment();
                viewFragment.show(getSupportFragmentManager(),"Post Testimonial");


                break;

        }
    }


    private void callCurrentEventsDetailsApiService() {




        showProgressDialog("Loading");

        Observable<TestimonialListApi> testimonialApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchTestimonialInfo();

        testimonialApiService.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TestimonialListApi>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull TestimonialListApi testimonialListApi) {
                if (testimonialListApi.success) {
                    hideProgressDialog();
                    loadCurrentEventsDetailsInList(testimonialListApi.data);
                } else {
                    onError(null);
                }
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable throwable) {
                hideProgressDialog();
                // show error
                Toast.makeText(TestimonialActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });

//        List<TestimonialListApi.Data> list=new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            TestimonialListApi.Data data=new TestimonialListApi.Data();
//            data.content="Heloo , this is content of "+i;
//            data.email="Kap+"+i+"@gmail.com";
//            data.name="Test "+i;
//            list.add(data);
//        }
//        loadCurrentEventsDetailsInList(list);

    }

    private void loadCurrentEventsDetailsInList(List<TestimonialListApi.Data> data) {

        ((TestimonialListAdapter) mTestiMonialListView.getAdapter()).notifyItemsChanged(data);
    }
}

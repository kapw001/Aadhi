package com.app.aadhi;

import android.util.Log;
import android.widget.Toast;

import com.app.aadhi.dashboard.audiomsg.AudioPlayerActivity;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.TestimonialListApi;

import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by webnoodle on 6/29/2018.
 */

public abstract class TestimonialBaseActivity extends RootActivity {


    public void callCurrentEventsDetailsApiService() {

        showProgressDialog("Loading");

        io.reactivex.Observable<TestimonialListApi> testimonialApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchTestimonialInfo();

        testimonialApiService.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<TestimonialListApi>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull TestimonialListApi testimonialListApi) {
                hideProgressDialog();
                if (testimonialListApi.success) {

                    if (testimonialListApi.data.size() > 0) {

                        Random random = new Random();
                        int count = testimonialListApi.data.size() - 1;
                        if (count==0){
                            count=1;
                        }
                        int n = random.nextInt(count );
                        showTestimonial(testimonialListApi.data.get(n).content);

                    } else
                        showTestimonial("Write your recommendations and comments, Please click to post it");
                } else {
                    onError(null);
                }
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable throwable) {
                hideProgressDialog();
                // show error
                Toast.makeText(TestimonialBaseActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });


    }


    public abstract void showTestimonial(String content);

}

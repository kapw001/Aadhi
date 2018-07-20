package com.app.aadhi.dashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.TestimonialBaseActivity;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import com.app.aadhi.dashboard.audiomsg.AudioMessagesListActivity;
import com.app.aadhi.dashboard.audiomsg.AudioPlayerActivity;
import com.app.aadhi.dashboard.currentevents.CurrentEventsListActivity;
import com.app.aadhi.dashboard.eappnotice.EAppNoticeListActivity;
import com.app.aadhi.dashboard.slokaforday.SlokaForDayListAdapter;
import com.app.aadhi.dashboard.slokaforday.SlokaForTheDayListActivity;
import com.app.aadhi.dashboard.slokasandmantras.SlokasAndMantrasListActivity;
import com.app.aadhi.dashboard.testimonial.TestimonialActivity;
import com.app.aadhi.dashboard.videomsg.VideoMessagesListActivity;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.SlokaOfTheDayApi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by administrator on 19/04/18.
 */

public class InformationsMenuActivity extends AudioPlayerActivity implements View.OnClickListener {

    @BindView(R.id.btn_current_events)
    Button mTvCurrentEvents;
    @BindView(R.id.btn_video_messages)
    Button mVideoMessages;
    @BindView(R.id.btn_audio_messages)
    Button mAudioMessages;
    @BindView(R.id.btn_sloka_mantras)
    Button mSlokasAndMantras;
    @BindView(R.id.btn_sloka_of_day)
    Button mSlokaOfTheDay;
    @BindView(R.id.btn_eapp_notice)
    Button mEAppNotice;
    @BindView(R.id.layout_audio_player)
    View mAudioPlayerUI;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_informations;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setMainContentView(R.layout.activity_informations);
        initViews();
//        callCurrentEventsDetailsApiService();
    }

    @Override
    protected void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        mTvCurrentEvents.setOnClickListener(this);
        mEAppNotice.setOnClickListener(this);
        mAudioMessages.setOnClickListener(this);
        mVideoMessages.setOnClickListener(this);
        mSlokasAndMantras.setOnClickListener(this);
        mSlokaOfTheDay.setOnClickListener(this);
//        audioPlayerShowHide(View.INVISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        audioPlayerShowHide(View.INVISIBLE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        audioPlayerShowHide(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);    // pass the click event to the base activity

        switch (view.getId()) {
            case R.id.btn_current_events:
//                startActivity(new Intent(InformationsMenuActivity.this, CurrentEventsListActivity.class));
                startActivity(new Intent(InformationsMenuActivity.this, CurrentEventsListActivity.class));
                break;

            case R.id.btn_eapp_notice:
                startActivity(new Intent(InformationsMenuActivity.this, EAppNoticeListActivity.class));
                break;

            case R.id.btn_audio_messages:
                startActivity(new Intent(InformationsMenuActivity.this, AudioMessagesListActivity.class));
                break;

            case R.id.btn_video_messages:
                startActivity(new Intent(InformationsMenuActivity.this, VideoMessagesListActivity.class));
                break;

            case R.id.btn_sloka_mantras:
                startActivity(new Intent(InformationsMenuActivity.this, SlokasAndMantrasListActivity.class));
                break;

            case R.id.btn_sloka_of_day:
//                startActivity(new Intent(InformationsMenuActivity.this, SlokaForTheDayListActivity.class));
                callSlokaOfTheDayApiService();
                break;

            case R.id.layout_common_testimonial:
                startActivity(new Intent(InformationsMenuActivity.this, TestimonialActivity.class));
                break;
        }
    }

    @Override
    public void onPhotoClicked(Bitmap bitmap) {

    }


    private void callSlokaOfTheDayApiService() {
        showProgressDialog("Loading");


        Observable.create((ObservableOnSubscribe<SlokaOfTheDayApi>) observableEmitter -> {
            Observable<SlokaOfTheDayApi> SlokaOfTheDayApiService = RetrofitAdapter.getNetworkApiServiceClient().fetchSlokaOfTheDayList().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());

            SlokaOfTheDayApiService.subscribe(observableEmitter::onNext,observableEmitter::onError);
        }).map(slokaOfTheDayApi -> {
            String fullNameOfDay = "sloka for " + new SimpleDateFormat("EEEE", Locale.ENGLISH).format(new Date().getTime());

            int size = slokaOfTheDayApi.data.size();

            for (int i = 0; i < size; i++) {

                SlokaOfTheDayApi.Data data = slokaOfTheDayApi.data.get(i);

                if (fullNameOfDay.toLowerCase().equalsIgnoreCase(data.title.toLowerCase())) {

                    return data;

                }

            }
            return new SlokaOfTheDayApi.Data();
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SlokaOfTheDayApi.Data>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull SlokaOfTheDayApi.Data data) {
                hideProgressDialog();
                if (data.audio_file.length() > 0) {
                    audioPlayerShowHide(View.VISIBLE);
                    onAudioClicked(data.audio_file,data.title);

                } else {
                    onError(null);
                }
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable throwable) {
                hideProgressDialog();
                // show error
                Toast.makeText(InformationsMenuActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });

    }


    @Override
    public void onAudioClicked(String url) {
        playAudioMsg(url);
    }


    public void onAudioClicked(String url,String songName) {
        playAudioMsg(url,songName);
    }
    private void audioPlayerShowHide(int visible) {

        mAudioPlayerUI.setVisibility(visible);

    }

    @Override
    public void showTestimonial(String content) {


    }
}

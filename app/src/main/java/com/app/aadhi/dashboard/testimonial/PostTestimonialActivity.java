package com.app.aadhi.dashboard.testimonial;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.customtextview.LatoRegularEditText;
import com.app.aadhi.dashboard.matrimony.MatrimonyEmailData;
import com.app.aadhi.dashboard.pooja.EqualSpacingItemDecoration;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.request.TestimonialRequest;
import com.app.aadhi.network.response.TestimonialListApi;
import com.app.aadhi.utils.EmailUtils;
import com.app.aadhi.utils.KeyboardUtils;

import javax.mail.Message;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostTestimonialActivity extends RootActivity implements View.OnClickListener{

    @BindView(R.id.row1_name)
    LatoRegularEditText mName;
    @BindView(R.id.row2_email)
    LatoRegularEditText mEmail;
    @BindView(R.id.row3_content)
    LatoRegularEditText mContent;
    @BindView(R.id.submit)
    Button mSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_post_testimonial);

        initViews();
    }

    private void initViews() {
        ButterKnife.bind(this);

        mSubmit.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){

            case R.id.submit:

                KeyboardUtils.hideKeyboard(this);
                boolean isValidInput = validateFormInputFields();
                if (isValidInput) {
                    postTestimonialData(mName.getText().toString(),mEmail.getText().toString(),mContent.getText().toString());
                } else {
                    Snackbar.make(findViewById(R.id.content_root_view), "Please enter all the required fields!", Snackbar.LENGTH_SHORT).show();
                }

                break;


        }

    }

    private boolean validateFormInputFields() {

        return !(TextUtils.isEmpty(mName.getText()) ||
                TextUtils.isEmpty(mEmail.getText()) ||
                TextUtils.isEmpty(mContent.getText()));
    }

    private void postTestimonialData(String name,String email,String content){

        showProgressDialog("Loading");
        TestimonialRequest testimonialRequest=new TestimonialRequest();

        testimonialRequest.data.email=email;
        testimonialRequest.data.name=name;
        testimonialRequest.data.content=content;

        Observable<TestimonialRequest> postTestimonialApiService = RetrofitAdapter.getNetworkApiServiceClient().postTestimonialInfo(testimonialRequest);

        postTestimonialApiService.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TestimonialRequest>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@NonNull TestimonialRequest testimonialRequest) {

                hideProgressDialog();

                Toast.makeText(PostTestimonialActivity.this, "Successfully testimonial posted!", Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                hideProgressDialog();

                // show error
                Toast.makeText(PostTestimonialActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });

    }
}

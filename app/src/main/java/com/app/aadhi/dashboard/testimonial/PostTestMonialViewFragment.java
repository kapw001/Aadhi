package com.app.aadhi.dashboard.testimonial;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.common.customDialog.CustomProgressDialog;
import com.app.aadhi.common.customtextview.LatoRegularEditText;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.request.TestimonialRequest;
import com.app.aadhi.utils.KeyboardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostTestMonialViewFragment extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.row1_name)
    LatoRegularEditText mName;
    @BindView(R.id.row2_email)
    LatoRegularEditText mEmail;
    @BindView(R.id.row3_content)
    LatoRegularEditText mContent;
    @BindView(R.id.submit)
    Button mSubmit;
    private CustomProgressDialog mProgressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.activity_post_testimonial, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // make dialog full screen
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.submit:

                KeyboardUtils.hideKeyboard(getActivity());
                boolean isValidInput = validateFormInputFields();
                if (isValidInput) {
                    postTestimonialData(mName.getText().toString(),mEmail.getText().toString(),mContent.getText().toString());
                } else {
                    Toast.makeText(getContext(), "Please enter all the required fields!", Toast.LENGTH_SHORT).show();

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

        testimonialRequest.data=new TestimonialRequest.Data();

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

                Toast.makeText(getContext(), "Successfully testimonial posted!", Toast.LENGTH_SHORT).show();

                dismiss();
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                hideProgressDialog();

                // show error
                Toast.makeText(getContext(), "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });

    }
    public void showProgressDialog(String message) {
        initDialog();
        mProgressDialog.setCancelable(false);
        mProgressDialog.show(message);
    }
    private void initDialog() {
        // If a progress dialog instance is not available, create one.
        if (mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog(getActivity());
        }
    }
    private void hideProgressDialog() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
            mProgressDialog.dismiss();
        }
    }
}

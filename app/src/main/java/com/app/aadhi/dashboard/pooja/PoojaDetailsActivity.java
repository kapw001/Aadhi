package com.app.aadhi.dashboard.pooja;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.Constant;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.common.customtextview.LatoRegularEditText;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.PoojaDetailApi;
import com.app.aadhi.utils.EmailUtils;
import com.app.aadhi.utils.KeyboardUtils;
import com.squareup.picasso.Picasso;

import javax.mail.Message;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoojaDetailsActivity extends RootActivity implements View.OnClickListener, RootActivity.MailSendResponse {

    @BindView(R.id.title)
    LatoBoldTextview mTitleView;
    @BindView(R.id.date)
    LatoBoldTextview mDate;
    @BindView(R.id.time)
    LatoBoldTextview mDayAndTime;
    @BindView(R.id.content_image1)
    ImageView mBanner;
    @BindView(R.id.content_description)
    TextView mDescription;
    @BindView(R.id.ip_name_family)
    LatoRegularEditText mNameFamilyET;
    @BindView(R.id.ip_gothram)
    LatoRegularEditText mGothramET;
    @BindView(R.id.ip_raasi)
    LatoRegularEditText mRaasiET;
    @BindView(R.id.ip_whatsapp_no)
    LatoRegularEditText mWhatsappNoET;
    @BindView(R.id.ip_mobile_no)
    LatoRegularEditText mMobileNoET;
    @BindView(R.id.ip_description)
    LatoRegularEditText mDescriptionET;
    @BindView(R.id.submit)
    Button mSubmitBtn;

    @BindView(R.id.content_root_view)
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.pooja_activity_details);
        initViews();
        setPoojaTitle();
        callPoojaDetailsApiService();

        mNameFamilyET.setScrollview(scrollView);
        mGothramET.setScrollview(scrollView);
        mRaasiET.setScrollview(scrollView);
        mWhatsappNoET.setScrollview(scrollView);
        mMobileNoET.setScrollview(scrollView);
        mDescriptionET.setScrollview(scrollView);
    }

    private void callPoojaDetailsApiService() {
        showProgressDialog("Loading");
        int id = getIntent().getExtras().getInt(Constant.Extras.POOJA_ID, -1);

        Call<PoojaDetailApi> poojaDetailApiCall = RetrofitAdapter.getNetworkApiServiceClient().fetchPoojaDetails(String.valueOf(id));
        poojaDetailApiCall.enqueue(new Callback<PoojaDetailApi>() {
            @Override
            public void onResponse(Call<PoojaDetailApi> call, Response<PoojaDetailApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    setPoojaDetailsInUi(response.body().data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(Call<PoojaDetailApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(PoojaDetailsActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPoojaDetailsInUi(PoojaDetailApi.Data data) {
        mDate.setText(data.sub_title);
        mDayAndTime.setText(data.sub_title);
        mDescription.setText(data.description);
        Picasso.get().load(data.banner_image)
                .fit()
                //.placeholder(R.drawable.)
                //.error()
                .into(mBanner);
    }

    private void initViews() {
        ButterKnife.bind(this);
        mGothramET.setOnFocusChangeListener(com.app.aadhi.utils.TextUtils.sHideHintWhenFocussed);
        mSubmitBtn.setOnClickListener(this);
        mGothramET.setHint(R.string.optional);
    }

    private void setPoojaTitle() {
        mTitleView.setText(getIntent().getExtras().getString(Constant.Extras.POOJA_TITLE, ""));
    }

    @Override
    public void onClick(View view) {

        super.onClick(view);

        switch (view.getId()){

            case R.id.submit:

                KeyboardUtils.hideKeyboard(this);
                boolean isValidInput = validateFormInputFields();
                if (isValidInput) {
                    Message poojaDetailEmail = EmailUtils.composePoojaDetailsEmail(mTitleView.getText().toString(),
                            mNameFamilyET.getText().toString(),
                            mGothramET.getText().toString(),
                            mRaasiET.getText().toString(),
                            mWhatsappNoET.getText().toString(),
                            mMobileNoET.getText().toString(),
                            mDescriptionET.getText().toString());
                    new SendMailTask().setListener(this).execute(poojaDetailEmail);
                } else {
                    Snackbar.make(findViewById(R.id.content_root_view), "Please enter all the required fields!", Snackbar.LENGTH_SHORT).show();
                }

                break;

                default: break;

        }


    }

    private boolean validateFormInputFields() {
        return !(TextUtils.isEmpty(mNameFamilyET.getText()) ||
                 TextUtils.isEmpty(mRaasiET.getText()) ||
                 TextUtils.isEmpty(mWhatsappNoET.getText()) ||
                 TextUtils.isEmpty(mMobileNoET.getText()) ||
                 TextUtils.isEmpty(mDescriptionET.getText()));
    }

    private void clearAllInputFields() {
        mNameFamilyET.setText("");
        mNameFamilyET.requestFocus();
        mGothramET.setText("");
        mRaasiET.setText("");
        mWhatsappNoET.setText("");
        mMobileNoET.setText("");
        mDescriptionET.setText("");
    }

    @Override
    public void onSendSuccess() {
        clearAllInputFields();
    }
}

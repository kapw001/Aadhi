package com.app.aadhi.dashboard.pilgrimage;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.Constant;
import com.app.aadhi.common.DatePickerDialogFragment;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.network.RetrofitAdapter;
import com.app.aadhi.network.response.PilgrimageTourDetailsApi;
import com.app.aadhi.utils.EmailUtils;
import com.app.aadhi.utils.KeyboardUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.Message;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilgrimageTourDetailsActivity extends RootActivity implements View.OnClickListener, RootActivity.MailSendResponse, DatePickerDialogFragment.OnDateSetListener {

    @BindView(R.id.title)
    LatoBoldTextview mTitleView;
    @BindView(R.id.tour_title)
    LatoBoldTextview mTourTitle;
    @BindView(R.id.content_image1)
    ImageView mBanner;
    @BindView(R.id.tour_sub_title)
    LatoBoldTextview mToutSubTitle;
    @BindView(R.id.content_description)
    TextView mDescription;
    @BindView(R.id.row1_title)
    TextView mRow1Title;
    @BindView(R.id.row1_description)
    TextView mNameET;
    @BindView(R.id.row2_title)
    TextView mRow2Title;
    @BindView(R.id.row2_description)
    TextView mDateET;
    @BindView(R.id.row3_title)
    TextView mRow3Title;
    @BindView(R.id.row3_description)
    TextView mWhatsappNoET;
    @BindView(R.id.row4_title)
    TextView mRow7Title;
    @BindView(R.id.row4_description)
    TextView mDescriptionET;
    @BindView(R.id.submit)
    Button mSubmitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_pilgrimage_details);
        initViews();
        setTourTitle();
        callPilgrimageTourDetailService();
    }

    private void initViews() {
        ButterKnife.bind(this);
        mDateET.setFocusable(false);
        mDateET.setOnClickListener(this);
        mSubmitBtn.setOnClickListener(this);

        mDescriptionET.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mDescriptionET.setRawInputType(InputType.TYPE_CLASS_TEXT);
        mDescriptionET.setHorizontallyScrolling(false);


        mWhatsappNoET.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        com.app.aadhi.utils.TextUtils.SetMaxLength10(mWhatsappNoET);
    }

    private void setTourTitle() {
        String title = getIntent().getExtras().getString(Constant.Extras.TOUR_TITLE, "");
        mTourTitle.setText(title);
    }

    @Override
    public void onClick(View view) {
        KeyboardUtils.hideKeyboard(this);
        switch (view.getId()) {
            case R.id.submit:
                boolean isValidInput = validateFormInputFields();
                if (isValidInput) {
                    Message vastuDetailsEmail = EmailUtils.composePilgrimageTourDetailsEmail(
                            mNameET.getText().toString(),
                            mDateET.getText().toString(),
                            mWhatsappNoET.getText().toString(),
                            mDescriptionET.getText().toString());
                    new SendMailTask().setListener(this).execute(vastuDetailsEmail);
                } else {
                    Snackbar.make(findViewById(R.id.content_root_view), "Please enter all the required fields!", Snackbar.LENGTH_SHORT).show();
                }
                break;

            case R.id.row2_description:
                DatePickerDialogFragment fragment = new DatePickerDialogFragment();
                fragment.setListener(this);
                fragment.show(getSupportFragmentManager(), DatePickerDialogFragment.class.getSimpleName());
                break;
            default:

                super.onClick(view);

                break;
        }
    }

    private boolean validateFormInputFields() {
        return !(TextUtils.isEmpty(mNameET.getText()) ||
                TextUtils.isEmpty(mDateET.getText()) ||
                TextUtils.isEmpty(mWhatsappNoET.getText()) ||
                TextUtils.isEmpty(mDescriptionET.getText()));
    }

    private void callPilgrimageTourDetailService() {
        showProgressDialog("Loading");
        int id = getIntent().getExtras().getInt(Constant.Extras.TOUR_ID, -1);

        Call<PilgrimageTourDetailsApi> PilgrimageTourDetailsApiCall = RetrofitAdapter.getNetworkApiServiceClient().fetchTourDetails(String.valueOf(id));
        PilgrimageTourDetailsApiCall.enqueue(new Callback<PilgrimageTourDetailsApi>() {
            @Override
            public void onResponse(Call<PilgrimageTourDetailsApi> call, Response<PilgrimageTourDetailsApi> response) {
                if (response.isSuccessful() && response.body().success) {
                    hideProgressDialog();
                    setTourDetailsInUi(response.body().data);
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(Call<PilgrimageTourDetailsApi> call, Throwable t) {
                hideProgressDialog();
                // show error
                Toast.makeText(PilgrimageTourDetailsActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTourDetailsInUi(PilgrimageTourDetailsApi.Data data) {
        mTourTitle.setText(data.title);
        mToutSubTitle.setText(data.sub_title);
        mDescription.setText(data.description);
        Picasso.get().load(data.banner_image)
                .fit()
                //.placeholder(R.drawable.)
                //.error()
                .into(mBanner);

        Log.e(TAG, "setTourDetailsInUi: "+data.banner_image );
    }

    @Override
    public void onSendSuccess() {
        clearAllInputFields();
    }

    private void clearAllInputFields() {
        mNameET.setText("");
        mNameET.requestFocus();
        mDateET.setText("");
        mWhatsappNoET.setText("");
        mDescriptionET.setText("");
    }

    @Override
    public void onDateSet(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateString = formatter.format(date);
        mDateET.setText(dateString);
    }
}

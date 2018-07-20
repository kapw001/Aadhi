package com.app.aadhi.dashboard.astrology;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.DatePickerDialogFragment;
import com.app.aadhi.utils.EmailUtils;
import com.app.aadhi.utils.KeyboardUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.Message;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AstrologyMenuActivity extends RootActivity implements View.OnClickListener, RootActivity.MailSendResponse, DatePickerDialogFragment.OnDateSetListener {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.content_image1)
    ImageView mContentImage;
    @BindView(R.id.content_description)
    TextView mContentDescription;
    @BindView(R.id.row1_title)
    TextView mRow1Title;
    @BindView(R.id.row1_description)
    TextView mNameET;
    @BindView(R.id.row2_title)
    TextView mRow2Title;
    @BindView(R.id.row2_description)
    TextView mStarRaasiET;
    @BindView(R.id.row3_title)
    TextView mRow3Title;
    @BindView(R.id.row3_description)
    TextView mTimeOfBirthET;
    @BindView(R.id.row4_title)
    TextView mRow4Title;
    @BindView(R.id.row4_description)
    TextView mDateOfBirthET;
    @BindView(R.id.row5_title)
    TextView mRow5Title;
    @BindView(R.id.row5_description)
    TextView mWhatsappET;
    @BindView(R.id.row6_title)
    TextView mRow6Title;
    @BindView(R.id.row6_description)
    TextView mMobileNoET;
    @BindView(R.id.row7_title)
    TextView mRow7Title;
    @BindView(R.id.row7_description)
    TextView mDescriptionET;
    @BindView(R.id.row6)
    View mRow6;
    @BindView(R.id.submit)
    Button mSubmitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.layout_activity_common_menu);
        ButterKnife.bind(this);

        setTextForAllUiViews();
    }

    private void setTextForAllUiViews() {
        mTitle.setText(getString(R.string.astrology_title));
        mDate.setVisibility(View.GONE);
        mTime.setVisibility(View.GONE);
        mContentImage.setImageResource(R.drawable.ic_astrology_img1);
        mContentImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mContentDescription.setText(getString(R.string.astrology_description));
        mRow1Title.setText(getString(R.string.name));
        mRow2Title.setText(getString(R.string.star_raasi));
        mRow3Title.setText(getString(R.string.time_of_birth));
        mRow4Title.setText(getString(R.string.dob_place_of_birth));
        mRow5Title.setText(getString(R.string.whatsapp_no));
        mRow6Title.setText(getString(R.string.mobile_number));
        mRow7Title.setText(getString(R.string.description));

        mStarRaasiET.setHint("");
        mWhatsappET.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        com.app.aadhi.utils.TextUtils.SetMaxLength10(mWhatsappET);
        mMobileNoET.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        com.app.aadhi.utils.TextUtils.SetMaxLength10(mMobileNoET);

        mSubmitBtn.setOnClickListener(this);

        mDateOfBirthET.setFocusable(false);
        mDateOfBirthET.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);    // pass the click event to the base activity

        switch (view.getId()) {
            case R.id.submit:
                KeyboardUtils.hideKeyboard(this);
                boolean isValidInput = validateFormInputFields();
                if (isValidInput) {
                    Message vastuDetailsEmail = EmailUtils.composeAstrologicalDetailsEmail(mNameET.getText().toString(),
                            mStarRaasiET.getText().toString(),
                            mTimeOfBirthET.getText().toString(),
                            mDateOfBirthET.getText().toString(),
                            mWhatsappET.getText().toString(),
                            mMobileNoET.getText().toString(),
                            mDescriptionET.getText().toString());
                    new SendMailTask().setListener(this).execute(vastuDetailsEmail);
                } else {
                    Snackbar.make(findViewById(R.id.content_root_view), "Please enter all the required fields!", Snackbar.LENGTH_SHORT).show();
                }
                break;

            case R.id.row4_description:
                DatePickerDialogFragment fragment = new DatePickerDialogFragment();
                fragment.setListener(this);
                fragment.show(getSupportFragmentManager(), DatePickerDialogFragment.class.getSimpleName());
                break;
        }
    }

    private boolean validateFormInputFields() {
        return !(TextUtils.isEmpty(mNameET.getText()) ||
                TextUtils.isEmpty(mStarRaasiET.getText()) ||
                TextUtils.isEmpty(mTimeOfBirthET.getText()) ||
                TextUtils.isEmpty(mDateOfBirthET.getText()) ||
//                TextUtils.isEmpty(mWhatsappET.getText()) ||
                TextUtils.isEmpty(mMobileNoET.getText()) ||
                TextUtils.isEmpty(mDescriptionET.getText()));
    }

    private void clearAllInputFields() {
        mNameET.requestFocus();
        mNameET.setText("");
        mStarRaasiET.setText("");
        mTimeOfBirthET.setText("");
        mDateOfBirthET.setText("");
        mWhatsappET.setText("");
        mMobileNoET.setText("");
        mDescriptionET.setText("");
    }

    @Override
    public void onSendSuccess() {
        clearAllInputFields();
    }

    @Override
    public void onDateSet(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateString = formatter.format(date);
        mDateOfBirthET.setText(dateString);
    }
}

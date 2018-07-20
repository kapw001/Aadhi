package com.app.aadhi.dashboard.vaastu;

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
import com.app.aadhi.utils.EmailUtils;
import com.app.aadhi.utils.KeyboardUtils;

import javax.mail.Message;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VaastuMenuActivity extends RootActivity implements View.OnClickListener, RootActivity.MailSendResponse {

    @BindView(R.id.title)
    TextView mTitle;
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
    TextView mAddressET;
    @BindView(R.id.row3_title)
    TextView mRow3Title;
    @BindView(R.id.row3_description)
    TextView mMobileNoET;
    @BindView(R.id.row4_title)
    TextView mRow4Title;
    @BindView(R.id.row4_description)
    TextView mPlaceET;
    @BindView(R.id.row5_title)
    TextView mRow5Title;
    @BindView(R.id.row5_description)
    TextView mEmiRates;
    @BindView(R.id.row6_title)
    TextView mRow6Title;
    @BindView(R.id.row6_description)
    TextView mRow6Description;
    @BindView(R.id.row7_title)
    TextView mRow7Title;
    @BindView(R.id.row7_description)
    TextView mRow7Description;
    @BindView(R.id.row5)
    View mRow5;
    @BindView(R.id.row6)
    View mRow6;
    @BindView(R.id.submit)
    Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.vastu_activity);
        ButterKnife.bind(this);

        setTextForAllUiViews();
    }

    private void setTextForAllUiViews() {
        mTitle.setText(getString(R.string.vastu_title));
        mContentImage.setImageResource(R.drawable.ic_vaastu_img1);
        mContentImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mContentDescription.setText(getString(R.string.vastu_caption1));
        mRow1Title.setText(getString(R.string.name));
        mRow2Title.setText(getString(R.string.address));
        mRow3Title.setText(getString(R.string.mobile_number));
        mRow4Title.setText(getString(R.string.place));
        mRow5Title.setText(getString(R.string.emi_rates));
        mRow6.setVisibility(View.GONE);
        mRow7Title.setText(getString(R.string.description));

        mMobileNoET.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        com.app.aadhi.utils.TextUtils.SetMaxLength10(mMobileNoET);
        mAddressET.setText("");
        mSubmitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);    // pass the click event to the base activity

        switch (view.getId()) {
            case R.id.submit:
                KeyboardUtils.hideKeyboard(this);
                boolean isValidInput = validateFormInputFields();
                if (isValidInput) {
                    Message vastuDetailsEmail = EmailUtils.composeVastuDetailsEmail(mNameET.getText().toString(),
                            mAddressET.getText().toString(),
                            mMobileNoET.getText().toString(),
                            mPlaceET.getText().toString(),
                            mEmiRates.getText().toString(),
                            mRow7Description.getText().toString());
                    new SendMailTask().setListener(this).execute(vastuDetailsEmail);
                } else {
                    Snackbar.make(findViewById(R.id.content_root_view), "Please enter all the required fields!", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean validateFormInputFields() {
        return !(TextUtils.isEmpty(mNameET.getText()) ||
                TextUtils.isEmpty(mAddressET.getText()) ||
                TextUtils.isEmpty(mMobileNoET.getText()) ||
                TextUtils.isEmpty(mPlaceET.getText()) ||
                TextUtils.isEmpty(mEmiRates.getText()) ||
                TextUtils.isEmpty(mRow7Description.getText()));
    }

    private void clearAllInputFields() {
        mNameET.setText("");
        mNameET.requestFocus();
        mAddressET.setText("");
        mMobileNoET.setText("");
        mPlaceET.setText("");
        mEmiRates.setText("");
        mRow7Description.setText("");
    }

    @Override
    public void onSendSuccess() {
        clearAllInputFields();
    }
}

package com.app.aadhi.dashboard.matrimony;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.DatePickerDialogFragment;
import com.app.aadhi.common.DatePickerDialogFragmentNoRestrict;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.common.customtextview.LatoRegularEditText;
import com.app.aadhi.utils.AppUtils;
import com.app.aadhi.utils.EmailUtils;
import com.app.aadhi.utils.KeyboardUtils;

import java.util.Date;

import javax.mail.Message;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatrimonyMenuActivity extends RootActivity implements CompoundButton.OnCheckedChangeListener, DatePickerDialogFragmentNoRestrict.OnDateSetListener, RootActivity.MailSendResponse {

    @BindView(R.id.title)
    LatoBoldTextview mTitleTV;
    @BindView(R.id.content_image1)
    ImageView mContentImage;
    @BindView(R.id.content_description)
    LatoBoldTextview mDescriptionTV;
    @BindView(R.id.rb_user_gender_male)
    RadioButton mUserGenderMale;
    @BindView(R.id.rb_user_gender_female)
    RadioButton mUserGenderFemale;
    @BindView(R.id.subsect_description)
    LatoRegularEditText mSubSectET;
    @BindView(R.id.gothram_description)
    LatoRegularEditText mGothramET;
    @BindView(R.id.dob_description)
    LatoRegularEditText mDobET;
    @BindView(R.id.tob_description)
    LatoRegularEditText mTimeOfBirthET;
    @BindView(R.id.qualification_description)
    LatoRegularEditText mQualificationET;
    @BindView(R.id.profession_description)
    LatoRegularEditText mProfessionET;
    @BindView(R.id.address_description)
    LatoRegularEditText mAddressET;
    @BindView(R.id.whatsappno_description)
    LatoRegularEditText mWhatsappNoET;
    @BindView(R.id.fathers_name_description)
    LatoRegularEditText mFathersNameET;
    @BindView(R.id.mothers_name_description)
    LatoRegularEditText mMothersNameET;
    @BindView(R.id.brothers_description)
    LatoRegularEditText mNoOfBrothersET;
    @BindView(R.id.brothers_elder_younger_description)
    LatoRegularEditText mElderYoungerBrothersET;
    @BindView(R.id.sisters_description)
    LatoRegularEditText mNoOfSistersET;
    @BindView(R.id.sisters_elder_younger_description)
    LatoRegularEditText mElderYoungerSistersET;
    @BindView(R.id.rb_prefer_male)
    RadioButton mPreferMaleRB;
    @BindView(R.id.rb_prefer_female)
    RadioButton mPreferFemaleRB;
    @BindView(R.id.age_upto_description)
    LatoRegularEditText mPreferAgeUptoET;
    @BindView(R.id.Education_description)
    LatoRegularEditText mPreferEducation;
    @BindView(R.id.nativity_description)
    LatoRegularEditText mPreferNativity;
    @BindView(R.id.rb_prefer_working)
    RadioButton mPreferWorkingBtn;
    @BindView(R.id.rb_prefer_non_working)
    RadioButton mPreferNonWorkingBtn;
    @BindView(R.id.india_overseas_description)
    LatoRegularEditText mPreferIndiaOrOverseas;
    @BindView(R.id.submit)
    Button mSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.matrimony_activity);

        initViews();
    }

    private void initViews() {
        ButterKnife.bind(this);
        mDobET.setFocusable(false);
        mDobET.setOnClickListener(this);
        mSubmit.setOnClickListener(this);

        mWhatsappNoET.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        com.app.aadhi.utils.TextUtils.SetMaxLength14(mWhatsappNoET);
        mNoOfBrothersET.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        mNoOfSistersET.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        mPreferAgeUptoET.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

        mUserGenderMale.setOnCheckedChangeListener(this);
        mUserGenderFemale.setOnCheckedChangeListener(this);

//        mPreferMaleRB.setClickable(false);
//        mPreferFemaleRB.setClickable(false);
        mPreferMaleRB.setOnCheckedChangeListener(this);
        mPreferFemaleRB.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.submit:
                KeyboardUtils.hideKeyboard(this);
                boolean isValidInput = validateFormInputFields();
                if (isValidInput) {
                    MatrimonyEmailData.UserDetails userDetails = new MatrimonyEmailData.UserDetails();
                    userDetails.userGender = (mUserGenderMale.isChecked()) ? mUserGenderMale.getText().toString() : mUserGenderFemale.getText().toString();
                    userDetails.subSect = mSubSectET.getText().toString();
                    userDetails.gothram = mGothramET.getText().toString();
                    userDetails.dateOfBrith = mDobET.getText().toString();
                    userDetails.timeOfBirth = mTimeOfBirthET.getText().toString();
                    userDetails.qualification = mQualificationET.getText().toString();
                    userDetails.profession = mProfessionET.getText().toString();
                    userDetails.address = mAddressET.getText().toString();
                    userDetails.whatsappNo = mWhatsappNoET.getText().toString();
                    userDetails.fathersNameAndAge = mFathersNameET.getText().toString();
                    userDetails.mothersNameAndAge = mMothersNameET.getText().toString();
                    userDetails.noOfBrothers = mNoOfBrothersET.getText().toString();
                    userDetails.elderYoungerBrothers = mElderYoungerBrothersET.getText().toString();
                    userDetails.noOfSisters = mNoOfSistersET.getText().toString();
                    userDetails.elderYoungerSisters = mElderYoungerSistersET.getText().toString();

                    MatrimonyEmailData.PreferenceDails preferenceDails = new MatrimonyEmailData.PreferenceDails();
                    preferenceDails.preferenceGender = (mPreferFemaleRB.isChecked()) ? mPreferFemaleRB.getText().toString() : mPreferMaleRB.getText().toString();
                    preferenceDails.preferAgeUpto = mPreferAgeUptoET.getText().toString();
                    preferenceDails.preferEducation = mPreferEducation.getText().toString();
                    preferenceDails.preferNativity = mPreferNativity.getText().toString();
                    preferenceDails.preferWorkingType = (mPreferWorkingBtn.isChecked()) ? mPreferWorkingBtn.getText().toString() : mPreferNonWorkingBtn.getText().toString();
                    preferenceDails.preferIndiaOrOverseas = mPreferIndiaOrOverseas.getText().toString();

                    MatrimonyEmailData emailDetails = new MatrimonyEmailData(userDetails, preferenceDails);
                    Message vastuDetailsEmail = EmailUtils.composeMatrimonyDetailsEmail(emailDetails);
                    new SendMailTask().setListener(this).execute(vastuDetailsEmail);
                } else {
                    Snackbar.make(findViewById(R.id.content_root_view), "Please enter all the required fields!", Snackbar.LENGTH_SHORT).show();
                }
                break;

            case R.id.dob_description:
                DatePickerDialogFragmentNoRestrict fragment = new DatePickerDialogFragmentNoRestrict();
                fragment.setListener(this);
                fragment.show(getSupportFragmentManager(), DatePickerDialogFragment.class.getSimpleName());
                break;
        }
    }

    private boolean validateFormInputFields() {

        return !(
//                TextUtils.isEmpty(mSubSectET.getText()) ||
//                TextUtils.isEmpty(mGothramET.getText()) ||
                TextUtils.isEmpty(mDobET.getText()) ||
//                TextUtils.isEmpty(mTimeOfBirthET.getText()) ||
//                TextUtils.isEmpty(mQualificationET.getText()) ||
//                TextUtils.isEmpty(mProfessionET.getText()) ||
//                TextUtils.isEmpty(mAddressET.getText()) ||
                TextUtils.isEmpty(mWhatsappNoET.getText())
//                TextUtils.isEmpty(mFathersNameET.getText()) ||
//                TextUtils.isEmpty(mMothersNameET.getText()) ||
//                TextUtils.isEmpty(mNoOfBrothersET.getText()) ||
//                TextUtils.isEmpty(mElderYoungerBrothersET.getText()) ||
//                TextUtils.isEmpty(mNoOfSistersET.getText()) ||
//                TextUtils.isEmpty(mElderYoungerSistersET.getText()) ||

//                TextUtils.isEmpty(mPreferAgeUptoET.getText()) ||
//                TextUtils.isEmpty(mPreferEducation.getText()) ||
//                TextUtils.isEmpty(mPreferNativity.getText()) ||
//                TextUtils.isEmpty(mPreferIndiaOrOverseas.getText())
        );
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_user_gender_male:
                if (mUserGenderMale.isChecked()) {
                    mPreferFemaleRB.setChecked(true);
                }
                break;

            case R.id.rb_user_gender_female:
                if (mUserGenderFemale.isChecked()) {
                    mPreferMaleRB.setChecked(true);
                }
                break;
            case R.id.rb_prefer_male:
                if (mPreferMaleRB.isChecked()) {
                    mUserGenderFemale.setChecked(true);
                }
                break;

            case R.id.rb_prefer_female:
                if (mPreferFemaleRB.isChecked()) {
                    mUserGenderMale.setChecked(true);
                }
                break;
        }
    }

    @Override
    public void onDateSet(Date date) {
        String dateString = AppUtils.getDateFormattedText(date);
        mDobET.setText(dateString);
    }

    @Override
    public void onSendSuccess() {
        clearAllInputFields();
    }

    private void clearAllInputFields() {
        mUserGenderMale.setChecked(true);
        mSubSectET.setText("");
        mGothramET.setText("");
        mDobET.setText("");
        mTimeOfBirthET.setText("");
        mQualificationET.setText("");
        mProfessionET.setText("");
        mAddressET.setText("");
        mWhatsappNoET.setText("");
        mFathersNameET.setText("");
        mMothersNameET.setText("");
        mNoOfBrothersET.setText("");
        mElderYoungerBrothersET.setText("");
        mNoOfSistersET.setText("");
        mElderYoungerSistersET.setText("");

        mPreferFemaleRB.setChecked(true);
        mPreferAgeUptoET.setText("");
        mPreferEducation.setText("");
        mPreferNativity.setText("");
        mPreferNonWorkingBtn.setChecked(true);
        mPreferIndiaOrOverseas.setText("");
    }
}

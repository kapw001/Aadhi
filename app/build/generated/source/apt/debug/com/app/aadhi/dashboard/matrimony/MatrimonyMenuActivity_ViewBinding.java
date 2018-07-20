// Generated code from Butter Knife. Do not modify!
package com.app.aadhi.dashboard.matrimony;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.aadhi.R;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.common.customtextview.LatoRegularEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MatrimonyMenuActivity_ViewBinding implements Unbinder {
  private MatrimonyMenuActivity target;

  @UiThread
  public MatrimonyMenuActivity_ViewBinding(MatrimonyMenuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MatrimonyMenuActivity_ViewBinding(MatrimonyMenuActivity target, View source) {
    this.target = target;

    target.mTitleTV = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitleTV'", LatoBoldTextview.class);
    target.mContentImage = Utils.findRequiredViewAsType(source, R.id.content_image1, "field 'mContentImage'", ImageView.class);
    target.mDescriptionTV = Utils.findRequiredViewAsType(source, R.id.content_description, "field 'mDescriptionTV'", LatoBoldTextview.class);
    target.mUserGenderMale = Utils.findRequiredViewAsType(source, R.id.rb_user_gender_male, "field 'mUserGenderMale'", RadioButton.class);
    target.mUserGenderFemale = Utils.findRequiredViewAsType(source, R.id.rb_user_gender_female, "field 'mUserGenderFemale'", RadioButton.class);
    target.mSubSectET = Utils.findRequiredViewAsType(source, R.id.subsect_description, "field 'mSubSectET'", LatoRegularEditText.class);
    target.mGothramET = Utils.findRequiredViewAsType(source, R.id.gothram_description, "field 'mGothramET'", LatoRegularEditText.class);
    target.mDobET = Utils.findRequiredViewAsType(source, R.id.dob_description, "field 'mDobET'", LatoRegularEditText.class);
    target.mTimeOfBirthET = Utils.findRequiredViewAsType(source, R.id.tob_description, "field 'mTimeOfBirthET'", LatoRegularEditText.class);
    target.mQualificationET = Utils.findRequiredViewAsType(source, R.id.qualification_description, "field 'mQualificationET'", LatoRegularEditText.class);
    target.mProfessionET = Utils.findRequiredViewAsType(source, R.id.profession_description, "field 'mProfessionET'", LatoRegularEditText.class);
    target.mAddressET = Utils.findRequiredViewAsType(source, R.id.address_description, "field 'mAddressET'", LatoRegularEditText.class);
    target.mWhatsappNoET = Utils.findRequiredViewAsType(source, R.id.whatsappno_description, "field 'mWhatsappNoET'", LatoRegularEditText.class);
    target.mFathersNameET = Utils.findRequiredViewAsType(source, R.id.fathers_name_description, "field 'mFathersNameET'", LatoRegularEditText.class);
    target.mMothersNameET = Utils.findRequiredViewAsType(source, R.id.mothers_name_description, "field 'mMothersNameET'", LatoRegularEditText.class);
    target.mNoOfBrothersET = Utils.findRequiredViewAsType(source, R.id.brothers_description, "field 'mNoOfBrothersET'", LatoRegularEditText.class);
    target.mElderYoungerBrothersET = Utils.findRequiredViewAsType(source, R.id.brothers_elder_younger_description, "field 'mElderYoungerBrothersET'", LatoRegularEditText.class);
    target.mNoOfSistersET = Utils.findRequiredViewAsType(source, R.id.sisters_description, "field 'mNoOfSistersET'", LatoRegularEditText.class);
    target.mElderYoungerSistersET = Utils.findRequiredViewAsType(source, R.id.sisters_elder_younger_description, "field 'mElderYoungerSistersET'", LatoRegularEditText.class);
    target.mPreferMaleRB = Utils.findRequiredViewAsType(source, R.id.rb_prefer_male, "field 'mPreferMaleRB'", RadioButton.class);
    target.mPreferFemaleRB = Utils.findRequiredViewAsType(source, R.id.rb_prefer_female, "field 'mPreferFemaleRB'", RadioButton.class);
    target.mPreferAgeUptoET = Utils.findRequiredViewAsType(source, R.id.age_upto_description, "field 'mPreferAgeUptoET'", LatoRegularEditText.class);
    target.mPreferEducation = Utils.findRequiredViewAsType(source, R.id.Education_description, "field 'mPreferEducation'", LatoRegularEditText.class);
    target.mPreferNativity = Utils.findRequiredViewAsType(source, R.id.nativity_description, "field 'mPreferNativity'", LatoRegularEditText.class);
    target.mPreferWorkingBtn = Utils.findRequiredViewAsType(source, R.id.rb_prefer_working, "field 'mPreferWorkingBtn'", RadioButton.class);
    target.mPreferNonWorkingBtn = Utils.findRequiredViewAsType(source, R.id.rb_prefer_non_working, "field 'mPreferNonWorkingBtn'", RadioButton.class);
    target.mPreferIndiaOrOverseas = Utils.findRequiredViewAsType(source, R.id.india_overseas_description, "field 'mPreferIndiaOrOverseas'", LatoRegularEditText.class);
    target.mSubmit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'mSubmit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MatrimonyMenuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTitleTV = null;
    target.mContentImage = null;
    target.mDescriptionTV = null;
    target.mUserGenderMale = null;
    target.mUserGenderFemale = null;
    target.mSubSectET = null;
    target.mGothramET = null;
    target.mDobET = null;
    target.mTimeOfBirthET = null;
    target.mQualificationET = null;
    target.mProfessionET = null;
    target.mAddressET = null;
    target.mWhatsappNoET = null;
    target.mFathersNameET = null;
    target.mMothersNameET = null;
    target.mNoOfBrothersET = null;
    target.mElderYoungerBrothersET = null;
    target.mNoOfSistersET = null;
    target.mElderYoungerSistersET = null;
    target.mPreferMaleRB = null;
    target.mPreferFemaleRB = null;
    target.mPreferAgeUptoET = null;
    target.mPreferEducation = null;
    target.mPreferNativity = null;
    target.mPreferWorkingBtn = null;
    target.mPreferNonWorkingBtn = null;
    target.mPreferIndiaOrOverseas = null;
    target.mSubmit = null;
  }
}

package com.app.aadhi.dashboard.pooja;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class PoojaForFamilyMenuActivity extends RootActivity implements View.OnClickListener, DatePickerDialogFragment.OnDateSetListener, RootActivity.MailSendResponse {

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
    TextView mAddressET;
    @BindView(R.id.row3_title)
    TextView mRow3Title;
    @BindView(R.id.row3_description)
    TextView mDateET;
    @BindView(R.id.row4_title)
    TextView mRow4Title;
    @BindView(R.id.row4_description)
    TextView mWhatsAppNoET;
    @BindView(R.id.row5_title)
    TextView mRow5Title;
    @BindView(R.id.row5_description)
    TextView mMobileNoET;
    @BindView(R.id.row6_title)
    TextView mRow6Title;
    @BindView(R.id.row6_description)
    TextView mRow6Description;
    @BindView(R.id.row6_spinner)
    Spinner mPoojaOptionsSpinner;
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
        mTitle.setText(getString(R.string.pooja_family_title));
        mDate.setVisibility(View.GONE);
        mTime.setVisibility(View.GONE);
        mContentImage.setImageResource(R.drawable.ic_pooja_family_img1);
        mContentImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mContentDescription.setText(getString(R.string.pooja_family_description));
        mRow1Title.setText(getString(R.string.name));
        mRow2Title.setText(getString(R.string.address));
        mRow3Title.setText(getString(R.string.date));
        mRow4Title.setText(getString(R.string.whatsapp_no));
        mRow5Title.setText(getString(R.string.mobile_number));
        mRow6Title.setText(getString(R.string.type_of_pooja));
        mRow7Title.setText(getString(R.string.description));

        mWhatsAppNoET.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        com.app.aadhi.utils.TextUtils.SetMaxLength14(mWhatsAppNoET);
        mMobileNoET.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        com.app.aadhi.utils.TextUtils.SetMaxLength14(mMobileNoET);
        //mDateET.setEnabled(false);
        mDateET.setFocusable(false);
        mDateET.setOnClickListener(this);

        // Swap EditText With Spinner in CommonLayout.
        mRow6Description.setVisibility(View.GONE);
        mPoojaOptionsSpinner.setVisibility(View.VISIBLE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pooja_family_options, R.layout.spinner_layout_simple_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        mPoojaOptionsSpinner.setAdapter(adapter);

        mSubmitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);    // pass the click event to the base activity

        KeyboardUtils.hideKeyboard(this);
        switch (view.getId()) {
            case R.id.submit:
                boolean isValidInput = validateFormInputFields();
                if (isValidInput) {
                    Message vastuDetailsEmail = EmailUtils.composePoojaForFamilyEmail(mNameET.getText().toString(),
                            mAddressET.getText().toString(),
                            mDateET.getText().toString(),
                            mWhatsAppNoET.getText().toString(),
                            mMobileNoET.getText().toString(),
                            (String) mPoojaOptionsSpinner.getSelectedItem(),
                            mDescriptionET.getText().toString());
                    new SendMailTask().setListener(this).execute(vastuDetailsEmail);
                } else {
                    Snackbar.make(findViewById(R.id.content_root_view), "Please enter all the required fields!", Snackbar.LENGTH_SHORT).show();
                }
                break;

            case R.id.row3_description:
                DatePickerDialogFragment fragment = new DatePickerDialogFragment();
                fragment.setListener(this);
                fragment.show(getSupportFragmentManager(), DatePickerDialogFragment.class.getSimpleName());
                break;
            default:break;
        }

    }

    private boolean validateFormInputFields() {
        return !(TextUtils.isEmpty(mNameET.getText()) ||
                TextUtils.isEmpty(mAddressET.getText()) ||
                TextUtils.isEmpty(mDateET.getText()) ||
                TextUtils.isEmpty(mWhatsAppNoET.getText()) ||
//                TextUtils.isEmpty(mMobileNoET.getText()) ||
                mPoojaOptionsSpinner.getSelectedItemPosition() == 0 ||
                TextUtils.isEmpty(mDescriptionET.getText()));
    }

    @Override
    public void onDateSet(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateString = formatter.format(date);
        mDateET.setText(dateString);
    }

    private void clearAllInputFields() {
        mNameET.setText("");
        mNameET.requestFocus();
        mAddressET.setText("");
        mDateET.setText("");
        mWhatsAppNoET.setText("");
        mMobileNoET.setText("");
        mDescriptionET.setText("");
    }

    @Override
    public void onSendSuccess() {
        clearAllInputFields();
    }
}

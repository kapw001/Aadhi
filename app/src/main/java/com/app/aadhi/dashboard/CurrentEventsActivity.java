package com.app.aadhi.dashboard;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.customtextview.LatoBoldTextview;
import com.app.aadhi.common.customtextview.LatoRegularEditText;
import com.app.aadhi.utils.EmailUtils;
import com.app.aadhi.utils.NetworkUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by administrator on 19/04/18.
 */

public class CurrentEventsActivity extends RootActivity implements View.OnClickListener, RootActivity.MailSendResponse {

    @BindView(R.id.et_name)
    LatoRegularEditText mUserName;
    @BindView(R.id.et_email)
    LatoRegularEditText mUserEmail;
    @BindView(R.id.et_mobile_no)
    LatoRegularEditText mUserMobileNumber;
    @BindView(R.id.et_description)
    LatoRegularEditText mDescription;
    @BindView(R.id.btn_submit)
    LatoBoldTextview mSendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_current_events);
        initViews();
    }

    private void initViews() {
        ButterKnife.bind(this);
        mSendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String email = mUserEmail.getText().toString();
        String subject = String.format("%s : %s", mUserName.getText().toString(), mUserMobileNumber.getText().toString());
        String description = mDescription.getText().toString();

        boolean isValidInput = validateInputFields();
        if (isValidInput) {
            if (NetworkUtils.isNetworkAvailable()) {
                //Message msg = EmailUtils.buildEmailMessage(email, subject, description);
                Message msg = EmailUtils.buildEmailMessage(subject, description);
                // Send Email in background
                new SendMailTask().setListener(this).execute(msg);
            } else {
                Toast.makeText(CurrentEventsActivity.this, "Network is unavailable!",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(CurrentEventsActivity.this, "Please enter all required fields!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateInputFields() {
        return !(TextUtils.isEmpty(mUserName.getText().toString()) ||
                 TextUtils.isEmpty(mUserEmail.getText().toString()) ||
                 IsInValidEmail(mUserEmail.getText().toString()) ||
                 TextUtils.isEmpty(mUserMobileNumber.getText().toString()) ||
                 TextUtils.isEmpty(mDescription.getText().toString()));

    }

    @Override
    public void onSendSuccess() {

    }
}

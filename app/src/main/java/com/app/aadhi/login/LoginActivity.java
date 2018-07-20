package com.app.aadhi.login;

import android.os.Bundle;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;

/**
 * Created by administrator on 14/04/18.
 */

public class LoginActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

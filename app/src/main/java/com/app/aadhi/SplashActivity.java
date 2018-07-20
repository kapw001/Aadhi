package com.app.aadhi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.app.aadhi.common.ImageResize;
import com.app.aadhi.dashboard.DashboardActivity;
import com.app.aadhi.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashActivity extends RootActivity {

    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_splash);
        mToolbar.setVisibility(View.GONE);

        ButterKnife.bind(this);


//        img.setImageBitmap(ImageResize.decodeSampledBitmapFromResource(getResources(),R.drawable.ic_splash_bg_1,ImageResize.getScreenWidth(),ImageResize.getScreenHeight()));



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        }, 2000);
    }
}

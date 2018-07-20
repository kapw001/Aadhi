package com.app.aadhi.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import com.app.aadhi.dashboard.astrology.AstrologyMenuActivity;
import com.app.aadhi.dashboard.eappnotice.EAppNoticeListActivity;
import com.app.aadhi.dashboard.homam.HomamMenuActivity;
import com.app.aadhi.dashboard.matrimony.MatrimonyMenuActivity;
import com.app.aadhi.dashboard.pilgrimage.PilgrimageTourListActivity;
import com.app.aadhi.dashboard.pooja.PoojaForChildrenMenuActivity;
import com.app.aadhi.dashboard.pooja.PoojaForFamilyMenuActivity;
import com.app.aadhi.dashboard.pooja.PoojaListActivity;
import com.app.aadhi.dashboard.srathampinda.SrathamPindaMenuActivity;
import com.app.aadhi.dashboard.vaastu.VaastuMenuActivity;

/**
 * Created by administrator on 18/04/18.
 */

public class MenuActivity extends RootActivity implements View.OnClickListener {

    private LatoRegularTextview mTvCurrentEvents, mTvOnlinePocha, mTvNotice,
                                mMartimonyMenu, mVaastuMenu, mAstrologyMenu,
                                mPoojaFamilyMenu, mPoojaChildrenMenu, mHomamMenu,
                                mSastraPindaMenu, mPilgrimageToursMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_menu);

        mTvCurrentEvents = (LatoRegularTextview) findViewById(R.id.tv_current_events);
        mTvOnlinePocha = (LatoRegularTextview) findViewById(R.id.tv_online_pocha);
        mTvNotice = (LatoRegularTextview) findViewById(R.id.tv_notice);
        mMartimonyMenu = (LatoRegularTextview) findViewById(R.id.tv_matrimonial);
        mVaastuMenu = (LatoRegularTextview) findViewById(R.id.tv_vasthu);
        mAstrologyMenu = (LatoRegularTextview) findViewById(R.id.tv_astrology);
        mPoojaFamilyMenu = (LatoRegularTextview) findViewById(R.id.tv_pooja_family);
        mPoojaChildrenMenu = (LatoRegularTextview) findViewById(R.id.tv_child_pooja);
        mHomamMenu = (LatoRegularTextview) findViewById(R.id.tv_homam);
        mSastraPindaMenu = (LatoRegularTextview) findViewById(R.id.tv_sastram);
        mPilgrimageToursMenu = (LatoRegularTextview) findViewById(R.id.tv_pilgrimege);

        mToolBarTitle.setText("Aadhi");
        mTvCurrentEvents.setOnClickListener(this);
        mTvOnlinePocha.setOnClickListener(this);
        mTvNotice.setOnClickListener(this);
        mMartimonyMenu.setOnClickListener(this);
        mVaastuMenu.setOnClickListener(this);
        mAstrologyMenu.setOnClickListener(this);
        mPoojaFamilyMenu.setOnClickListener(this);
        mPoojaChildrenMenu.setOnClickListener(this);
        mSastraPindaMenu.setOnClickListener(this);
        mHomamMenu.setOnClickListener(this);
        mPilgrimageToursMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_current_events:
                startActivity(new Intent(MenuActivity.this, CurrentEventsActivity.class));
                break;

            case R.id.tv_online_pocha:
                startActivity(new Intent(MenuActivity.this, PoojaListActivity.class));
                break;

            case R.id.tv_notice:
                startActivity(new Intent(MenuActivity.this, EAppNoticeListActivity.class));
                break;

            case R.id.tv_matrimonial:
                startActivity(new Intent(MenuActivity.this, MatrimonyMenuActivity.class));
                break;

            case R.id.tv_vasthu:
                startActivity(new Intent(MenuActivity.this, VaastuMenuActivity.class));
                break;

            case R.id.tv_astrology:
                startActivity(new Intent(MenuActivity.this, AstrologyMenuActivity.class));
                break;

            case R.id.tv_pooja_family:
                startActivity(new Intent(MenuActivity.this, PoojaForFamilyMenuActivity.class));
                break;

            case R.id.tv_child_pooja:
                startActivity(new Intent(MenuActivity.this, PoojaForChildrenMenuActivity.class));
                break;

            case R.id.tv_homam:
                startActivity(new Intent(MenuActivity.this, HomamMenuActivity.class));
                break;

            case R.id.tv_sastram:
                startActivity(new Intent(MenuActivity.this, SrathamPindaMenuActivity.class));
                break;

            case R.id.tv_pilgrimege:
                startActivity(new Intent(MenuActivity.this, PilgrimageTourListActivity.class));
                break;

            default:
                break;
        }
    }
}

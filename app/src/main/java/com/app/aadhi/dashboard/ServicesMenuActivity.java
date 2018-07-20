package com.app.aadhi.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.aadhi.R;
import com.app.aadhi.RootActivity;
import com.app.aadhi.TestimonialBaseActivity;
import com.app.aadhi.common.customtextview.LatoRegularTextview;
import com.app.aadhi.dashboard.astrology.AstrologyMenuActivity;
import com.app.aadhi.dashboard.homam.HomamMenuActivity;
import com.app.aadhi.dashboard.matrimony.MatrimonyMenuActivity;
import com.app.aadhi.dashboard.pilgrimage.PilgrimageTourListActivity;
import com.app.aadhi.dashboard.pooja.PoojaForChildrenMenuActivity;
import com.app.aadhi.dashboard.pooja.PoojaForFamilyMenuActivity;
import com.app.aadhi.dashboard.pooja.PoojaListActivity;
import com.app.aadhi.dashboard.srathampinda.SrathamPindaMenuActivity;
import com.app.aadhi.dashboard.testimonial.TestimonialActivity;
import com.app.aadhi.dashboard.vaastu.VaastuMenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by administrator on 19/04/18.
 */

public class ServicesMenuActivity extends TestimonialBaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_online_pooja)
    Button mOnlinePooja;
    @BindView(R.id.btn_aadhi_matrimony)
    Button mMatrimony;
    @BindView(R.id.btn_vaastu_consultancy)
    Button mVaastuConsultancy;
    @BindView(R.id.btn_astrology)
    Button mAstrology;
    @BindView(R.id.btn_pooja_family)
    Button mPoojaFamily;
    @BindView(R.id.btn_pooja_children)
    Button mPoojaChildren;
    @BindView(R.id.btn_homam)
    Button mHomam;
    @BindView(R.id.btn_sratham_pinda)
    Button mSrathamPinda;
    @BindView(R.id.btn_pilgrimage)
    Button mPilgrimage;
    @BindView(R.id.layout_common_testimonial)
    View mTestimonialUI;

    @BindView(R.id.testinomial_content)
    LatoRegularTextview mTestimonial_Content;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_services);
        initViews();
        callCurrentEventsDetailsApiService();
    }

    protected void initViews() {
        ButterKnife.bind(this);
        mOnlinePooja.setOnClickListener(this);
        mMatrimony.setOnClickListener(this);
        mVaastuConsultancy.setOnClickListener(this);
        mAstrology.setOnClickListener(this);
        mPoojaFamily.setOnClickListener(this);
        mPoojaChildren.setOnClickListener(this);
        mHomam.setOnClickListener(this);
        mSrathamPinda.setOnClickListener(this);
        mPilgrimage.setOnClickListener(this);
        mTestimonialUI.setOnClickListener(this);
        mTestimonialUI.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);    // pass the click event to the base activity

        switch (view.getId()) {

            case R.id.btn_online_pooja:
                startActivity(new Intent(ServicesMenuActivity.this, PoojaListActivity.class));
                break;

            case R.id.btn_aadhi_matrimony:
                startActivity(new Intent(ServicesMenuActivity.this, MatrimonyMenuActivity.class));
                break;

            case R.id.btn_vaastu_consultancy:
                startActivity(new Intent(ServicesMenuActivity.this, VaastuMenuActivity.class));
                break;

            case R.id.btn_astrology:
                startActivity(new Intent(ServicesMenuActivity.this, AstrologyMenuActivity.class));
                break;

            case R.id.btn_pooja_family:
                startActivity(new Intent(ServicesMenuActivity.this, PoojaForFamilyMenuActivity.class));
                break;

            case R.id.btn_pooja_children:
                startActivity(new Intent(ServicesMenuActivity.this, PoojaForChildrenMenuActivity.class));
                break;

            case R.id.btn_homam:
                startActivity(new Intent(ServicesMenuActivity.this, HomamMenuActivity.class));
                break;

            case R.id.btn_sratham_pinda:
                startActivity(new Intent(ServicesMenuActivity.this, SrathamPindaMenuActivity.class));
                break;

            case R.id.btn_pilgrimage:
                startActivity(new Intent(ServicesMenuActivity.this, PilgrimageTourListActivity.class));
                break;

            case R.id.layout_common_testimonial:
                startActivity(new Intent(ServicesMenuActivity.this, TestimonialActivity.class));
                break;

            default:
                break;
        }
    }

    @Override
    public void showTestimonial(String content) {

        mTestimonialUI.setVisibility(View.VISIBLE);
        mTestimonial_Content.setText(content);

    }
}

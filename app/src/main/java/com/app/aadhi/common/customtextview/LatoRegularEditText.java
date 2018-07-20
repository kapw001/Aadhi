package com.app.aadhi.common.customtextview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.app.aadhi.BirdApplication;


public class LatoRegularEditText extends AppCompatEditText implements View.OnFocusChangeListener {

    private static final String TAG = "LatoRegularEditText";
    private ScrollView scrollView;



    public LatoRegularEditText(Context context) {
        super(context);
        init();
    }


    public LatoRegularEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public LatoRegularEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setTypeface(new BirdApplication().lato_Regular);//Set Typeface from MyApplication
    }

    public void setScrollview(ScrollView scrollView) {
        this.scrollView = scrollView;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(hasFocus) {
            if (scrollView!=null) {
                int left = view.getLeft();
                int top = view.getTop();
                int bottom = view.getBottom();
                int keyboardHeight = scrollView.getHeight() / 3;

                // if the bottom of edit text is greater than scroll view height divide by 3,
                // it means that the keyboard is visible
                if (bottom > keyboardHeight) {
                    // increase scroll view with padding
//                    scrollView.setPadding(0, 0, 0, keyboardHeight);
                    // scroll to the edit text position
                    scrollView.scrollTo(left, top+100);
                }
            }else {
                Log.e(TAG, "onFocusChange: no scroll view " );
            }
        }
    }
}

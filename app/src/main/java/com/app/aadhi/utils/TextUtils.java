package com.app.aadhi.utils;

import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.aadhi.R;

public class TextUtils {

    /**
     * Sets the MaxLength for EditText as 10 Characters
     * @param textView
     */
    public static void SetMaxLength10(TextView textView) {
        int maxLength = 10;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        textView.setFilters(fArray);
    }

    /**
     * Sets the MaxLength for EditText as 14 Characters
     * @param textView
     */
    public static void SetMaxLength14(TextView textView) {
        int maxLength = 14;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        textView.setFilters(fArray);
    }

    public static View.OnFocusChangeListener sHideHintWhenFocussed = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean focussed) {
            if (focussed) {
                ((EditText) view).setHint("");
            } else {
                ((EditText) view).setHint(view.getContext().getString(R.string.optional));
            }
        }
    };
}

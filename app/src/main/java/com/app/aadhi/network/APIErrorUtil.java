package com.app.aadhi.network;

import android.text.TextUtils;

import com.app.aadhi.BirdApplication;
import com.app.aadhi.R;
import com.app.aadhi.utils.Logger;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;


public class APIErrorUtil {

    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter =
                RetrofitAdapter.getRetrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error = null;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            Logger.e(e.getMessage());
        }

        if (error == null) {
            error = getDefaultError(null);
        }

        return error;
    }

    public static APIError getDefaultError(String message){
        APIError apiError = new APIError();
        if (TextUtils.isEmpty(message)) {
            apiError.setMessage(BirdApplication.getThis().getResources().getString(R.string.common_error));
        } else {
            apiError.setMessage(message);
        }
        return apiError;
    }
}

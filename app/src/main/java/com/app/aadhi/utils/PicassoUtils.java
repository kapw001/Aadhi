package com.app.aadhi.utils;

import android.widget.ImageView;

import com.app.aadhi.R;
import com.squareup.picasso.Picasso;

public class PicassoUtils {


    public static void showImage(ImageView imageView,String url){

        if (url!=null && url.length()>0){
            Picasso.get().load(url).fit().into(imageView);
        }else {
            Picasso.get().load(R.mipmap.ic_launcher).fit().into(imageView);
        }

    }

    public static void showImageFull(ImageView imageView,String url){

        if (url!=null && url.length()>0){
            Picasso.get().load(url).into(imageView);
        }else {
            Picasso.get().load(R.mipmap.ic_launcher).fit().into(imageView);
        }

    }
}

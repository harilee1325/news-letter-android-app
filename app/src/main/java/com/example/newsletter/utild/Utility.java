package com.example.newsletter.utild;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsletter.R;

public class Utility {

    public static void setPreference(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("news_letter", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();

    }

    public static String getPreference(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("news_letter", Context.MODE_PRIVATE);
        String result = prefs.getString(key, "");
        return result;
    }

    public static void setImage(String imageUrl, Context context, ImageView container) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.image_loading);
        requestOptions.error(R.drawable.image_loading);
        Glide.with(context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(container);
    }


}

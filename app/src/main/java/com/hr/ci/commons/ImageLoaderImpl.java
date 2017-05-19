package com.hr.ci.commons;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageLoaderImpl implements ImageLoader {
    @Override
    public void loadImage(String url, ImageView target, Activity activity) {
        Glide.with(activity).load(url).into(target);
    }
}

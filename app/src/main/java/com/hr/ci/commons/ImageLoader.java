package com.hr.ci.commons;

import android.app.Activity;
import android.widget.ImageView;

public interface ImageLoader {

    void loadImage(String url, ImageView target, Activity activity);
}

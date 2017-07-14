package com.hr.ci.commons.util;

import android.content.Context;

import com.hr.ci.CiApplication;
import com.hr.ci.R;

public class StringUtil {


    public static String getResource(String id) {
        Context context = CiApplication.getInstance();
        switch (id) {
            case Constants.ERROR.NETWORK_ERROR:
                return context.getString(R.string.error_no_network);
            case Constants.ERROR.SERVER_ERROR:
                return context.getString(R.string.error_server_issue);
        }
        return null;
    }
}

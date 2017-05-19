package com.hr.ci.commons.util;

import com.hr.ci.R;

public class StringUtil {

    public static int getResourceId(String s) {
        switch (s) {
            case Constants.ERROR.NETWORK_ERROR:
                return R.string.error_no_network;
            case Constants.ERROR.SERVER_ERROR:
                return R.string.error_server_issue;
        }
        return 0;
    }
}

package com.hr.ci.commons.util;

public class Constants {

    public static final String KEY = "1a3c7686e6144dfa829a97c56fd4edcc";
    public static final String BASE_URL = "https://newsapi.org/v1/";
    public static final long TIMEOUT = 30000;

    public interface NEWS_SRC {
        String TECHCRUNCH = "techcrunch";
    }

    public interface ERROR {
        String NETWORK_ERROR = "network_error";
        String SERVER_ERROR = "server_error";
    }

    public interface MESSAGE {
        String UNKNOWN_ERROR = "Unknown error";
    }
}

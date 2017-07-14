package com.hr.ci.commons.presenters;

import com.hr.ci.commons.model.ErrorResponse;
import com.hr.ci.commons.util.Constants;
import com.hr.ci.commons.util.StringUtil;
import com.hr.ci.commons.views.BaseView;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import retrofit2.HttpException;

public abstract class BasePresenter<T extends BaseView> {

    public abstract T getView();

    protected ErrorResponse getNetworkError(Throwable throwable) {
        if (throwable instanceof IOException) {
            return new ErrorResponse.Builder().code(Constants.ERROR.NETWORK_ERROR).message(StringUtil.getResource(Constants.ERROR.NETWORK_ERROR)).build();
        }

        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<ErrorResponse> jsonAdapter = moshi.adapter(ErrorResponse.class);

            try {
                ErrorResponse errorResponse = jsonAdapter.fromJson(exception.response().errorBody().string());
                errorResponse.setHttpStatus(exception.response().code());
                return errorResponse;
            } catch (IOException e) {
                return new ErrorResponse.Builder().httpStatus(exception.response().code()).message(Constants.MESSAGE.UNKNOWN_ERROR).build();
            }

        }

        return new ErrorResponse.Builder().message(Constants.MESSAGE.UNKNOWN_ERROR).build();
    }
}

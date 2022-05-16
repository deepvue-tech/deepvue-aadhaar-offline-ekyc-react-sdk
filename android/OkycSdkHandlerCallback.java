package com.okycexample;

public interface OkycSdkHandlerCallback {
     void onFailure(Integer code);
     void onSuccess(String response);
}

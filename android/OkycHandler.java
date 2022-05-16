package com.okycexample;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

import android.content.Intent;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.jetbrains.annotations.NotNull;


import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;

class OkycHandler extends ReactContextBaseJavaModule
{
    private ReactApplicationContext context;

    public OkycHandler(ReactApplicationContext reactContext)
    {
        super(reactContext);
        context = reactContext;
    }

    @NonNull
    @NotNull
    @Override
    public String getName()
    {
        return "OkycHandler";
    }

    @ReactMethod
    void initSdk(
            String clientId,
            String clientSecret,
            Boolean useFaceMatch,
            String baseUrl,
            String imageUrl,
            Callback success,
            Callback failure
    ) {

       (context.getCurrentActivity()).runOnUiThread(() -> new OkycSdkHandler(new OkycSdkHandlerCallback()
        {
            @Override
            public void onFailure(Integer code) {
                failure.invoke(code);
            }

            @Override
            public void onSuccess(@NotNull String response)
            {
                success.invoke(response);
            }


        },
                baseUrl,
                clientId,
                clientSecret,
                useFaceMatch,
                imageUrl.isEmpty() ? null : imageUrl).startSdk(context));
    }
}

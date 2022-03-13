package com.okycexample;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import androidx.annotation.NonNull;

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
        String baseUrl,
        String imageUrl,
        Callback success,
        Callback failure
    )
    {
        Objects.requireNonNull(context.getCurrentActivity()).runOnUiThread(() -> new OkycSdkHandler(new com.okycexample.Callback()
        {
            @Override
            public void onSuccess(@NotNull String response)
            {
                success.invoke(response);
            }

            @Override
            public void onFailure(int code)
            {
                failure.invoke(code);
            }
        }, baseUrl, clientId,
            clientSecret,
            false,
            imageUrl.isEmpty() ? null : imageUrl).startSdk(context));
    }
}

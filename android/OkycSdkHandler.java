package com.okycexample;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;


public class OkycSdkHandler {
    private  String FLUTTER_ENGINE_ID = "flutter_engine";
    private  String CHANNEL = "com.ayush.ekyc.flutter";
    OkycSdkHandlerCallback callback;
    private FlutterEngine flutterEngine;

    OkycSdkHandlerCallback okycSdkHandlerCallback;
    String baseUrl;
    String clientId;
    String clientSecret;
    boolean useFaceMatch;
    String imageUrl;

    public OkycSdkHandler(OkycSdkHandlerCallback okycSdkHandlerCallback, String baseUrl, String clientId, String clientSecret, boolean useFaceMatch, String imageUrl) {

        this. okycSdkHandlerCallback=okycSdkHandlerCallback;
        this. baseUrl=baseUrl;
        this. clientId=clientId;
        this.  clientSecret=clientSecret;
        this. useFaceMatch=useFaceMatch;
        this.    imageUrl=imageUrl;
    }




    public  void startSdk(Context context){
        flutterEngine = new FlutterEngine(context);
        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        FlutterEngineCache
                .getInstance()
                .put(FLUTTER_ENGINE_ID, flutterEngine);
        Intent intent =  FlutterActivity.withCachedEngine(FLUTTER_ENGINE_ID)
                .build(context);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(
                context,
                intent,
                Bundle.EMPTY
        );
        setupMethodChannel();
    }

    public void  setupMethodChannel(){
        MethodChannel methodChannel =new  MethodChannel(
                flutterEngine.getDartExecutor().getBinaryMessenger(),
                CHANNEL
        );
        methodChannel.setMethodCallHandler((call, result) -> {
            if (call.method.equals("onFailure"))
            {
                callback.onFailure(call.arguments());
            } else if (call.method.equals("onSuccess"))
            {
                callback.onSuccess(call.arguments());
            }
        });

        JSONObject json = new JSONObject();
        try {
            json.put("baseUrl", baseUrl);
            json.put("clientId", clientId);
            json.put("clientSecret", clientSecret);
            json.put("useFaceMatch", useFaceMatch);
            json.put("imageUrl", imageUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        methodChannel.invokeMethod("setUpSdk", json.toString());

    }

}

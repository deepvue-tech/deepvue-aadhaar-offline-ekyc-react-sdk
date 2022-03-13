package com.okycexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
import org.json.JSONObject

private const val FLUTTER_ENGINE_ID = "flutter_engine"
private const val CHANNEL = "com.ayush.ekyc.flutter"

class OkycSdkHandler(
    private val callback: Callback,
    private val baseUrl: String,
    private val clientId: String,
    private val clientSecret: String,
    private val useFaceMatch: Boolean,
    private val imageUrl: String?
)
{
    private lateinit var flutterEngine: FlutterEngine

    fun startSdk(context: Context)
    {
        flutterEngine = FlutterEngine(context)
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        FlutterEngineCache
            .getInstance()
            .put(FLUTTER_ENGINE_ID, flutterEngine)
        val intent = FlutterActivity.withCachedEngine(FLUTTER_ENGINE_ID)
            .build(context)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(
            context,
            intent,
            Bundle.EMPTY
        )
        setupMethodChannel()
    }

    private fun setupMethodChannel()
    {
        val methodChannel = MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        )
        methodChannel.setMethodCallHandler { call, _ ->
            if (call.method.equals("onFailure"))
            {
                callback.onFailure(call.arguments())
            } else if (call.method.equals("onSuccess"))
            {
                callback.onSuccess(call.arguments())
            }
        }
        val json = JSONObject()
        json.put("baseUrl", baseUrl)
        json.put("clientId", clientId)
        json.put("clientSecret", clientSecret)
        json.put("useFaceMatch", useFaceMatch)
        json.put("imageUrl", imageUrl)
        methodChannel.invokeMethod("setUpSdk", json.toString())
    }
}

interface Callback
{
    fun onFailure(code: Int)
    fun onSuccess(response: String)
}
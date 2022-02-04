import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import sdk.deepvue.tech.offline_aadhaar_ekyc.AadharOfflineKycListener;
import sdk.deepvue.tech.offline_aadhaar_ekyc.AadharOfflineSDK;
import sdk.deepvue.tech.offline_aadhaar_ekyc.model.upload_xml.UploadXMLResponse;

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
        AadharOfflineSDK.INSTANCE.initialiseSDK(
            clientId,
            clientSecret,
            baseUrl
        ).setFaceMatch(false)
            .setImage(imageUrl)
            .setLanguage(AadharOfflineSDK.Languages.en)
            .start(context, new AadharOfflineKycListener()
            {
                @Override
                public void onKycSuccessResult(@NotNull UploadXMLResponse result)
                {
                    success.invoke(result.toJson());
                }

                @Override
                public void onFailure(int errorCode)
                {
                    failure.invoke(errorCode);
                }
            });
    }
}

# Deepvue Aadhaar Offline e-KYC React Native SDK
![version](https://img.shields.io/badge/version-v0.5-blue)

This is a wrapper over Android and iOS SDK for react native.

Aadhaar Paperless Offline eKYC is a secure and shareable document which can be used by any Aadhaar holder for offline verification of identification. The Aadhaar Offline document can be obtained from the UIDAI website. This SDK provides a simple plugin to your mobile App which allows the user to seamlessly share their offline Aadhaar file with the service provider. 

The Aadhaar Offline file will be validated for its digital signature and the KYC data of The Aadhaar holder will be passed to the integrating App as JSON data.


# Table Of Content

- [Prerequisite](#prerequisite)
- [Setup](#setup)
- [Usage](#usage)
- [Failure Status Codes](#failure-status-codes)
- [Help](#help)

## Prerequisite

You will need valid credentials to use the Deepvue Aadhaar Offline e-KYC React Native SDK, which can be obtained by contacting `hello@deepvue.tech` 

## Setup

#### Android
1. Download our github repository for android [repo](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-android-sdk/tree/flutter_wrapper).

2. Add Repositories in your Project build.gradle
```
String storageUrl = System.env.FLUTTER_STORAGE_BASE_URL ?: "https://storage.googleapis.com"
    repositories {
        maven {
            url 'github repo path you downloaded'
        }
        maven {
            url "$storageUrl/download.flutter.io"
        }
        ...
    }
```
3. Add Dependency in your app build.gradle
```
dependencies {
    implementation 'sdk.deepvue.tech.offline_aadhaar_ekyc:flutter_release:1.0'
    ...
}
```
4. Open you Manifest File and add below activity
```
        <activity
            android:name="io.flutter.embedding.android.FlutterActivity"
            android:configChanges="orientation|keyboardHidden|keyboard|screenSize|locale|layoutDirection|fontScale|screenLayout|density|uiMode"
            android:exported="true"
            android:hardwareAccelerated="true"
            android:theme="@style/Theme.AppCompat"
            android:windowSoftInputMode="adjustResize" />
```
5. After that, Copy below files and add to your `app` alongside `MainApplication.java` file
    - [OkycPackager.java](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-react-sdk/blob/flutter_Wrapper/android/OkycPackager.java)
    - [OkycHandler.java](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-react-sdk/blob/flutter_Wrapper/android/OkycHandler.java)
    - [OkycSdkHandler.java](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-react-sdk/blob/flutter_Wrapper/android/OkycSdkHandler.kt)
6. At last, Open up `android/app/src/main/java/[...]/MainApplication.java`
    - Add `new OkycPackager()` to the list returned by the `getPackages()` method

#### IOS
1. Download our github repository for IOS [repo](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-ios-sdk/tree/pod_version).
#### For Debug
2. Open Pod File and add **pod 'Flutter', :podspec => '[path of downloaded repo]/Debug/Flutter.podspec'**
3. Go into your target’s Build Settings > Build Phases > Link Binary With Libraries and Drag all the content of [path of downloaded repo]/Debug/.
4. In the target’s build settings, add $(PROJECT_DIR)/Flutter/Debug/ to the Framework Search Paths (FRAMEWORK_SEARCH_PATHS).
5. Drag the frameworks (except for **FlutterPluginRegistrant**) from your application’s Frameworks group into your target’s Build Settings > Build Phases > Embed Frameworks. Then, select Embed & Sign from the drop-down list.

#### For Release
2. Open Pod File and add **pod 'Flutter', :podspec => '[path of downloaded repo]/Release/Flutter.podspec'**
3. Go into your target’s Build Settings > Build Phases > Link Binary With Libraries and Drag all the content of [path of downloaded repo]/Release/.
4. In the target’s build settings, add $(PROJECT_DIR)/Flutter/Release/ to the Framework Search Paths (FRAMEWORK_SEARCH_PATHS).
5. Drag the frameworks (except for **FlutterPluginRegistrant**) from your application’s Frameworks group into your target’s Build Settings > Build Phases > Embed Frameworks. Then, select Embed & Sign from the drop-down list.

6. After that, Copy below files and add to your `project` alongside `AppDelegate` file
    - [RCTOkycModule.m](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-react-sdk/blob/flutter_Wrapper/ios/RCTOkycModule.m)
    - [RCTOkycModule.h](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-react-sdk/blob/flutter_Wrapper/ios/RCTOkycModule.h)
    - [OkycSdkHandler.m](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-react-sdk/blob/flutter_Wrapper/ios/OkycSdkHandler.m)
    - [OkycSdkHandler.h](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-react-sdk/blob/flutter_Wrapper/ios/OkycSdkHandler.h)

## Usage
#### React Native Code
```
//Import Native modules from react native
import { NativeModules } from "react-native";

const clientId = "YOUR-CLIENT-ID";
const clientSecret = "YOUR-CLIENT-SECRET";
const baseUrl = "DEEPVUE-SERVER-BASE-URL"; // e.g. https://pre-production.deepvue.tech/v1

// No need to set imageUrl in default mode else can be set as your own e.g. https://i.imgur.com/Mfn9Srw.jpeg
const imageUrl = "YOUR-IMAGE-URL"; 

//use below code on button click to start offline kyc
NativeModules.OkycHandler.initSdk(
  clientId,
  clientSecret,
  baseUrl,
  imageUrl, //no need to set this in case of default mode
  (response) => {
    console.log(response);
  },
  (exception) => {
    console.log(exception);
  }
);

```

## Failure Status Codes
Following error codes will be returned on the `onFailure` method of the callback

| CODE | DESCRIPTION                  |
| ---- | ---------------------------- |
| 801  | SDK Invalid Credentials             |
| 802  | Permission Denied       |
| 803  | User Interrupted            |
| 804  | No Internet Available |
| 805  | Network Error         |
| 806  | OTP Limit Exceeded       |
| 807  | Mobile Number Not Linked to Aadhaar             |
| 808  | File Download Failed 
| 809  | File Upload Failed |
| 810  | Face Match Failed            |
| 404  | UIDAI Website Server Down            |


## Help
For any queries/feedback, contact us at `hello@deepvue.tech` 

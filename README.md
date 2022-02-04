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
1. Open up `android/build.gradle`, Add below dependency under `buildscript`
  ```groovy
  buildscript {
    ext {
      kotlin_version = "1.4.21"
    }

    dependencies {
      classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
  }
  ```
2. After that, Open up `android/build.gradle`, Add below dependency under `allprojects->repositories`
  ```groovy
  allprojects {

      repositories {
            maven { url 'https://jitpack.io' }
      }

  }
  ```
3. Post that, Open up `android/app/build.gradle`, Add below dependency under dependencies
  ```groovy
  // ...
  
  dependencies {
      // Deepvue aadhar offline core dependency
      implementation 'com.github.deepvue-tech:deepvue-aadhaar-offline-ekyc-android-sdk:<lastest verison>'

  }
  ```
4. After that, Copy below files and add to your `app` alongside `MainApplication.java` file
    - [OkycPackager.java](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-react-sdk/blob/master/android/OkycPackager.java)
    - [OkycHandler.java](https://github.com/deepvue-tech/deepvue-aadhaar-offline-ekyc-react-sdk/blob/master/android/OkycHandler.java)
5. At last, Open up `android/app/src/main/java/[...]/MainApplication.java`
    - Add `new OkycPackager()` to the list returned by the `getPackages()` method

## Usage
#### React Native Code
```
//Import Native modules from react native
import { NativeModules } from "react-native";

//use below code on button click to start offline kyc
NativeModules.OkycHandler.initSdk(
  "client-id",
  "client-secret",
  "base-url",
  "custom-image-url",
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

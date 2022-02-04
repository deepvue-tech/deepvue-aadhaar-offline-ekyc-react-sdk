# react-native-aadhaar-offline-ekyc-wrapper

## Getting started

### Manual installation

#### Android
1. If your application is on java then Open up `android/build.gradle', Add below dependency under buildscript
  ```
    ext {
        kotlin_version = "1.4.21"
    }
   dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
  ```
1. Open up `android/build.gradle', Add below dependency under allprojects->repositories
  ```
    maven { url 'https://jitpack.io' }
  ```
1. Open up `android/app/build.gradle', Add below dependency under dependencies
  ```
  implementation 'com.github.deepvue-tech:deepvue-aadhaar-offline-ekyc-android-sdk:v0.5'
  ```
1. Add these files to your app alongside MainApplication.java file
    - OkycPackager.java
    - OkycHandler.java
1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `new OkycPackager()` to the list returned by the `getPackages()` method

## Usage
#### React Native Code
```
//Import Native modules from react native
import {NativeModules} from 'react-native';

//use below code on button click to start offline kyc
NativeModules.OkycHandler.initSdk(
  'client-id',
  'client-secret',
  'base-url',
  'custom-image-url',
  response => {
    console.log(response);
  },exception => {
    console.log(exception);
  },
)

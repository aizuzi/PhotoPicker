
# PhotoPicker with Fresco
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-PhotoPicker-green.svg?style=flat)](https://android-arsenal.com/details/1/2091)
[ ![Download](https://api.bintray.com/packages/jing-wu/maven/PhotoPicker/images/download.svg) ](https://bintray.com/jing-wu/maven/PhotoPicker/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)

---
# Usage

### Gradle

```groovy
dependencies {
    compile 'cn.2crazy.photopicker:PhotoPicker:0.9.3.1'
    
    compile 'com.android.support:appcompat-v7:23.4.0'
    compile 'com.android.support:recyclerview-v7:23.4.0'
    compile 'com.android.support:design:23.4.0'
    compile 'com.nineoldandroids:library:2.4.0'
    compile 'com.facebook.fresco:fresco:1.0.0'
    compile 'com.facebook.fresco:animated-gif:1.0.0'//Add this if you want play animated images.Without this,animated images will be shown as normal pictures that can not play.
}
```
* ```appcompat-v7```version >= 23.0.0

### eclipse
![GO HOME](http://ww4.sinaimg.cn/large/5e9a81dbgw1eu90m08v86j20dw09a3yu.jpg)

### Init Fresco
```java
ImagePipelineConfig config = ImagePipelineConfig.newBuilder(getApplicationContext())
    .setDownsampleEnabled(true)// This option can help prove the performance.
    //other settings
    .build();
Fresco.initialize(getApplicationContext(), config);
```

### Pick Photo
```java
PhotoPicker.builder()
    .setPhotoCount(9)
    .setShowCamera(true)
    .setShowGif(true)
    .setPreviewEnabled(false)
    .start(this, PhotoPicker.REQUEST_CODE);
```

### Preview Photo

```java
ArrayList<String> photoPaths = ...;

PhotoPreview.builder()
    .setPhotos(selectedPhotos)
    .setCurrentItem(position)
    .start(MainActivity.this);
```

### onActivityResult
```java
@Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  super.onActivityResult(requestCode, resultCode, data);

  if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
    if (data != null) {
      ArrayList<String> photos = 
          data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
    }
  }
}
```

### manifest
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    >
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.CAMERA" />
  <application
    ...
    >
    ...
    
    <activity android:name="me.iwf.photopicker.PhotoPickerActivity"
      android:theme="@style/Theme.AppCompat.NoActionBar" 
       />

    <activity android:name="me.iwf.photopicker.PhotoPagerActivity"
      android:theme="@style/Theme.AppCompat.NoActionBar"/>
    
  </application>
</manifest>
```
### Custom style
```xml
<style name="actionBarTheme" parent="ThemeOverlay.AppCompat.Dark.ActionBar">
  <item name="android:textColorPrimary">@android:color/primary_text_light</item>
  <item name="actionBarSize">@dimen/actionBarSize</item>
</style>

<style name="customTheme" parent="Theme.AppCompat.Light.NoActionBar">
  <item name="actionBarTheme">@style/actionBarTheme</item>
  <item name="colorPrimary">#FFA500</item>
  <item name="actionBarSize">@dimen/actionBarSize</item>
  <item name="colorPrimaryDark">#CCa500</item>
</style>
```

### Proguard

```
# Fresco
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}
-keepclassmembers class * {
    native <methods>;
}
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**
# nineoldandroids
-keep interface com.nineoldandroids.view.** { *; }
-dontwarn com.nineoldandroids.**
-keep class com.nineoldandroids.** { *; }
# support-v7-appcompat
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }
-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}
# support-design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
```

---


# License

    Copyright 2015 Huang Donglu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




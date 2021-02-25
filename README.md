# Android-Widgets

[![](https://jitpack.io/v/LiteKite/Android-Widgets.svg)](https://jitpack.io/#LiteKite/Android-Widgets)

An Android Custom Widgets Library, offers custom ui components.

## SmoothSeekBar

SmoothSeekBar extends the implementation of AppCompatSeekBar, which updates the progress based on the ACTION_MOVE Touch Event rather than the ACTION_DOWN Touch Event, which makes smooth transition from the previous progress to the current one.

1) A Horizontal SmoothSeekBar.

<p align="center">
  <img src="https://github.com/LiteKite/Android-Widgets/blob/assets/assets/smooth_seek_bar.gif" alt="Smooth Seek Bar" width="50%" />
</p>

~~~
<com.litekite.widget.SmoothSeekBar
  style="@style/Widget.AppCompat.SeekBar"
  android:layout_width="300dp"
  android:layout_height="50dp"
  android:max="100"
  android:progress="0"
  android:progressTint="@android:color/holo_red_light"
  android:thumbTint="@android:color/holo_red_light" />
~~~

2) A SmoothSeekBar turned as vertical with ViewRotator.

<p align="center">
  <img src="https://github.com/LiteKite/Android-Widgets/blob/assets/assets/vertical_smooth_seek_bar.gif" alt="Vertical Smooth Seek Bar" width="25%" />
</p>

~~~
<com.litekite.widget.ViewRotator
  android:layout_width="wrap_content"
  android:layout_height="wrap_content">

  <com.litekite.widget.SmoothSeekBar
    style="@style/Widget.AppCompat.SeekBar"
    android:layout_width="300dp"
    android:layout_height="50dp"
    android:max="100"
    android:progress="0"
    android:progressTint="@android:color/holo_red_light"
    android:thumbTint="@android:color/holo_red_light" />

</com.litekite.widget.ViewRotator>
~~~

## ViewRotator

ViewRotator is a View Group which hosts a single view rotated by 90 degrees counterclockwise.

1) A Horizontal Material Slider View turned vertical by rotation.

<p align="center">
  <img src="https://github.com/LiteKite/Android-Widgets/blob/assets/assets/view_rotator.gif" alt="Vertical Rotator with Slider" width="25%" />
</p>

~~~
<com.litekite.widget.ViewRotator
  android:layout_width="wrap_content"
  android:layout_height="wrap_content">

  <com.google.android.material.slider.Slider
    style="@style/Widget.MaterialComponents.Slider"
    android:layout_width="300dp"
    android:layout_height="50dp"
    android:theme="@style/Theme.MaterialComponents"
    app:haloColor="@android:color/holo_red_light"
    app:labelBehavior="gone"
    app:thumbColor="@android:color/holo_red_light"
    app:trackColorActive="@android:color/holo_red_light"
    app:trackColorInactive="@android:color/darker_gray" />

</com.litekite.widget.ViewRotator>
~~~

## CircleImageButton

CircleImageButton is a clickable image button that makes image source [app:srcCompat] and background source [android:background] a circle.

1) Color resource as a circle (An alternative way is to use shape drawable)

<p align="center">
  <img src="https://github.com/LiteKite/Android-Widgets/blob/assets/assets/cib_src_as_color_res.png" alt="Circle Image Button Source As Color Resource" width="25%" />
</p>

~~~
<com.litekite.widget.CircleImageButton
  ...
  style="@style/Widget.AppCompat.ImageButton"
  app:srcCompat="@android:color/black"
  ... />
~~~

2) Background and image source with color resource as a circle with inner padding (An alternative way is to use shape drawable with oval type, stroke and shape color)

<p align="center">
  <img src="https://github.com/LiteKite/Android-Widgets/blob/assets/assets/cib_src_and_bg_as_color_res.png" alt="Circle Image Button Background And Source As Color Resource" width="25%" />
</p>

~~~
<com.litekite.widget.CircleImageButton
  ...
  style="@style/Widget.AppCompat.ImageButton"
  android:background="@android:color/holo_red_light"
  app:innerPadding="10dp"
  app:srcCompat="@android:color/black"
  ... />
~~~

3) Background and image source with color resource as a circle with inner padding plus ripple effect

<p align="center">
  <img src="https://github.com/LiteKite/Android-Widgets/blob/assets/assets/cib_src_and_bg_as_color_res_with_ripple.gif" alt="Circle Image Button Background And Source As Color Resource Plus Ripple Effect" width="25%" />
</p>

~~~
<com.litekite.widget.CircleImageButton
  ...
  style="@style/Widget.AppCompat.ImageButton"
  android:background="@android:color/holo_red_light"
  app:innerPadding="10dp"
  app:rippleDrawable="@drawable/drawable_ripple"
  app:srcCompat="@android:color/black"
  ... />

  <!--Ripple drawable-->
  <ripple xmlns:android="http://schemas.android.com/apk/res/android"
      android:color="#50000000" android:radius="54dp" />
~~~

4) Profile drawable image plus background plus ripple effect

<p align="center">
  <img src="https://github.com/LiteKite/Android-Widgets/blob/assets/assets/cib_profile_img_plus_background_plus_ripple.gif" alt="Profile Drawable Image Plus Background Plus Ripple Effect" width="25%" />
</p>

~~~
<com.litekite.widget.CircleImageButton
  ...
  style="@style/Widget.AppCompat.ImageButton"
  android:background="@android:color/black"
  app:innerPadding="5dp"
  app:rippleDrawable="@drawable/drawable_ripple"
  app:srcCompat="@drawable/ic_preview_user_avatar"
  ... />
~~~

5) Profile drawable image plus transparent background plus ripple effect

<p align="center">
  <img src="https://github.com/LiteKite/Android-Widgets/blob/assets/assets/cib_profile_img_plus_transparent_background_plus_ripple.gif" alt="Profile Drawable Image Plus Background Plus Ripple Effect" width="25%" />
</p>

~~~
<com.litekite.widget.CircleImageButton
  ...
  style="@style/Widget.AppCompat.ImageButton"
  android:background="@android:color/transparent"
  app:rippleDrawable="@drawable/drawable_ripple"
  app:srcCompat="@drawable/ic_preview_user_avatar"
  ... />
~~~

## Download

1) Add the jitpack repo in your root build.gradle at the end of repositories:

~~~
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
~~~

2) Add the dependency in your app build.gradle:

~~~
dependencies {
  implementation 'com.github.LiteKite:Android-Widgets:0.0.6'
}
~~~

## License

~~~

Copyright 2021 LiteKite Startup.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

~~~
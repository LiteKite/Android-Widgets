# Android-Widgets

[![](https://jitpack.io/v/LiteKite/Android-Widgets.svg)](https://jitpack.io/#LiteKite/Android-Widgets)

An Android Custom Widgets Library, offers custom ui components.

## CircleImageButton

CircleImageButton is a clickable image button that makes source [app:srcCompat] and background source [android:background] as a circle.

1) Color resource as a circle (An alternative way is to use shape drawable)

<img src="https://github.com/LiteKite/Android-Widgets/blob/assets/assets/cib_src_as_color_res.png" alt="Circle Image Button Source As Color Resource" width="20%" />

~~~
<com.litekite.widgets.CircleImageButton
  ...
  style="@style/Widget.AppCompat.ImageButton"
  app:srcCompat="@android:color/black"
  ... />
~~~

2) Background and source with color resource as a circle with inner padding (An alternative way is to use shape drawable with oval type, stroke and shape color)

<img src="https://github.com/LiteKite/Android-Widgets/blob/assets/assets/cib_src_and_bg_as_color_res.png" alt="Circle Image Button Background and Source As Color Resource" width="20%" />

~~~
<com.litekite.widgets.CircleImageButton
  ...
  style="@style/Widget.AppCompat.ImageButton"
  android:background="@android:color/holo_red_light"
  app:innerPadding="10dp"
  app:srcCompat="@android:color/black"
  ... />
~~~

## Dependency

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
  implementation 'com.github.LiteKite:Android-Widgets:0.0.2'
}
~~~

## License

~~~

Copyright 2020 LiteKite Startup.

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
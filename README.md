# TypefaceHelper

[![Gitter](http://img.shields.io/badge/Gitter-Join%20Chat-brightgreen.svg?style=flat)](https://gitter.im/Drivemode/TypefaceHelper?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-TypefaceHelper-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1246)
[![License](http://img.shields.io/badge/License-Apache%202-brightgreen.svg?style=flat)](https://github.com/Drivemode/TypefaceHelper/blob/master/LICENSE)
[![Circle CI](https://circleci.com/gh/Drivemode/TypefaceHelper/tree/master.svg?style=shield)](https://circleci.com/gh/Drivemode/TypefaceHelper/tree/master)

Helper object for injecting typeface into various text views of android.

## Overview

We can use various custom typefaces asset for any text views(like TextView, Button, RadioButton, EditText, etc.),
but there's no way to set the typeface as a styled theme to apply the typeface for overall screens in the app.

This library helps to do it in easy way :)

And there's also a serious bug that creating typeface from asset resource will cause memory leak ([See this link](https://code.google.com/p/android/issues/detail?id=9904) for more details),
this library will take care about this problem as well.

## How to use

First, put your typeface into `asset` directory.

In your application class, take care about the helper object lifecycle.

```java
public class MyApp extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    TypefaceHelper.initialize(this);
  }

  @Override
  public void onTerminate() {
    TypefaceHelper.destroy();
    super.onTerminate();
  }
}
```

And in your activity, if you would like to set your typeface to a text view,

```java
public class MyActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView hello = (TextView) findViewById(R.id.hello_world);
    TypefaceHelper.getInstance().setTypeface(hello, "font/font_file.ttf");
  }
}
```

You can also set your typeface for all text views that belong to a specific view group just like this.

```java
public class MyActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    LinearLayout container = (LinearLayout) findViewById(R.id.text_container);
    TypefaceHelper.getInstance().setTypeface(container, "font/font_file.ttf");
  }
}
```

If you want to apply the typeface for all text views under the activity layout,

```java
public class MyActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(TypefaceHelper.getInstance().setTypeface(this, R.layout.activity_main, "font/font_file.ttf"));
  }
}
```

Nice and easy!

You can apply the typeface to your whole window like this.

```java
public class MyActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_activity_main);
    TypefaceHelper.getInstance().setTypeface(this, "font/font_file.ttf");
  }
}
```

And... you can also pass the font name as a string resource id:

```java
public class MyActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_activity_main);
    TypefaceHelper.getInstance().setTypeface(this, R.string.font_primary);
  }
}
```

## Download

Gradle:

```
compile 'com.drivemode:TypefaceHelper:1.0.0@aar'
```

## License

```
Copyright (C) 2014 Drivemode, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this file except in compliance with the License. You may obtain a copy of the
License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.
```

And the sample app uses the following font under [OFL](http://scripts.sil.org/cms/scripts/page.php?site_id=nrsi&id=OFL).

- [Isserley](http://openfontlibrary.org/en/font/isserley)

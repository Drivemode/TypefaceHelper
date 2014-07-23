# TypefaceHelper

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
    setContentView(TypefaceHelper.setTypeface(this, R.layout.activity_main, "font/font_file.ttf"));
  }
}
```

Nice and easy!

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

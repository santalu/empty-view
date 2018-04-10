# EmptyView

[![](https://jitpack.io/v/santalu/emptyview.svg)](https://jitpack.io/#santalu/emptyview) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-EmptyView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6039) [![](https://img.shields.io/badge/AndroidWeekly-%23270-blue.svg)](http://androidweekly.net/issues/issue-270) [![Build Status](https://travis-ci.org/santalu/emptyview.svg?branch=master)](https://travis-ci.org/santalu/emptyview)

A view that displays states of screen like loading, error, empty etc.

## Samples

<table >
  <tr>
    <td align="left"><img src="https://github.com/santalu/emptyview/blob/master/screens/loading.png"/></td>
    <td align="right"><img src="https://github.com/santalu/emptyview/blob/master/screens/error.png"/></td>
     <td align="left"><img src="https://github.com/santalu/emptyview/blob/master/screens/empty.png"/></td>
  </tr>
</table>

## Usage

### Gradle
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```
```
dependencies {
  implementation 'com.github.santalu:emptyview:1.2.9'
}
```

### XML
```xml
<com.santalu.emptyview.EmptyView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/empty_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:ev_gravity="center"
    app:ev_empty_button="@string/try_again"
    app:ev_empty_buttonTextColor="@color/colorPrimaryDark"
    app:ev_empty_drawable="@drawable/ic_sentiment_dissatisfied"
    app:ev_empty_drawableTint="@color/colorPrimary"
    app:ev_empty_text="@string/empty"
    app:ev_empty_title="@string/error_endpoint_title"
    app:ev_error_backgroundColor="@color/red"
    app:ev_error_button="@string/try_again"
    app:ev_error_buttonTextColor="@color/white"
    app:ev_error_drawable="@drawable/ic_sentiment_very_dissatisfied"
    app:ev_error_drawableTint="@color/white"
    app:ev_error_text="@string/error_unknown"
    app:ev_error_textColor="@color/white"
    app:ev_error_title="@string/error_unknown_title"
    app:ev_error_titleTextColor="@color/white"
    app:ev_font="@font/allerta"
    app:ev_loading="circular"
    app:ev_loading_drawable="@drawable/ic_sentiment_satisfied"
    app:ev_loading_drawableTint="@color/colorPrimary"
    app:ev_loading_title="@string/loading"
    app:ev_transition="slide">

    *** your content here ***

</com.santalu.emptyview.EmptyView>
```

## Attributes

| Name        |  Value  |
| ------------- |:-------------:|
| ev_gravity | top,center,bottom default center |
| ev_transition | slide, explode, fade default none |
| ev_font | reference |
| ev_titleTextSize | dimension |
| ev_textSize | dimension |
| ev_letterSpacing | dimension |
| ev_lineSpacingExtra | dimension |
| ev_lineSpacingExtraMultiplier | dimension |
| ev_buttonTextSize | dimension |
| ev_loading | none, circular default circular |
| ev_loading_title | string |
| ev_loading_titleTextColor | color |
| ev_loading_text | string |
| ev_loading_textColor | color |
| ev_loading_drawable | reference |
| ev_loading_drawableTint | color |
| ev_loading_backgroundColor | color |
| ev_empty_title | string |
| ev_empty_titleTextColor | color |
| ev_empty_text | string |
| ev_empty_textColor | color |
| ev_empty_button | string |
| ev_empty_buttonTextColor | color |
| ev_empty_buttonBackgroundColor | color |
| ev_empty_drawable | reference |
| ev_empty_drawableTint | color |
| ev_empty_backgroundColor | color |
| ev_error_title | string |
| ev_error_titleTextColor | color |
| ev_error_text | string |
| ev_error_textColor | color |
| ev_error_button | string |
| ev_error_buttonTextColor | color |
| ev_error_buttonBackgroundColor | color |
| ev_error_drawable | reference |
| ev_error_drawableTint | color |
| ev_error_backgroundColor | color |

## Notes

* Use `exclude(int... ids)` or `exclude(View... views)` methods to exclude views from visibility changes

## License
```
Copyright 2017 Fatih Santalu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

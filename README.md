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
  implementation 'com.github.santalu:emptyview:1.1.5'
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
    app:emptyButtonText="@string/try_again"
    app:emptyButtonTextColor="@color/colorPrimaryDark"
    app:emptyDrawable="@drawable/ic_sentiment_dissatisfied"
    app:emptyDrawableTint="@color/colorPrimary"
    app:emptyFont="@font/allerta"
    app:emptyText="@string/empty"
    app:emptyTransition="slide"
    app:errorBackgroundColor="@color/red"
    app:errorButtonBackgroundColor="@color/white"
    app:errorButtonText="@string/try_again"
    app:errorButtonTextColor="@color/black"
    app:errorDrawable="@drawable/ic_sentiment_very_dissatisfied"
    app:errorDrawableTint="@color/white"
    app:errorText="@string/emptyview_error_unknown"
    app:errorTextColor="@color/white"
    app:loadingDrawable="@drawable/ic_sentiment_satisfied"
    app:loadingStyle="circular"
    app:loadingText="@string/emptyview_loading"
    app:loadingTint="@color/colorPrimary">

    *** your content here ***

</com.santalu.emptyview.EmptyView>
```

## Attributes

| Name        |  Value  |
| ------------- |:-------------:|
| loadingStyle | circular, linear, text default circular |
| loadingText  | string |
| loadingTextColor | color default black |
| loadingDrawable | reference |
| loadingTint | color default transparent |
| loadingBackgroundColor | color default transparent |
| emptyGravity | center, top, bottom default center |
| emptyFont | reference |
| emptyTransition | slide, explode, fade default null |
| emptyTitle | string |
| emptyTitleColor | color default black |
| emptyText | string |
| emptyTextColor | color default black |
| emptyLetterSpacing | float default 0 |
| emptyLineSpacingExtra | float default 1 |
| emptyLineSpacingMultiplier | float default 1 |
| emptyDrawable | reference |
| emptyDrawableTint | color default transparent |
| emptyBackgroundColor | color default transparent |
| errorText | string |
| errorTextColor | color default black |
| errorDrawable | reference |
| errorDrawableTint | color default transparent |
| errorBackgroundColor | color default transparent |
| errorButtonText | string |
| errorButtonTextColor | color default black |
| errorButtonBackgroundColor | color default transparent |

## Notes

* Use `setTransition(android.support.transition transition)` method to define which transitions is played when animating layout changes
* Use `setEmptyButton(CharSequence text, @ColorInt int color, @ColorInt int backgroundColor)` or `setErrorButton(CharSequence text, @ColorInt int color, @ColorInt int backgroundColor)` methods to set button text, textColor and backgroundColor attributes
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





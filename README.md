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
  compile 'com.github.santalu:emptyview:1.1.0'
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
    app:emptyAnimateLayoutChanges="true"
    app:emptyButtonText="@string/try_again"
    app:emptyButtonTextColor="@color/colorPrimaryDark"
    app:emptyDrawable="@drawable/ic_sentiment_dissatisfied"
    app:emptyDrawableTint="@color/colorPrimary"
    app:emptyFont="@font/allerta"
    app:emptyText="@string/empty"
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

| Name        | Description           | Value  |
| ------------- |:-------------:| -----:|
| emptyAnimateLayoutChanges      | enable/disable transitions | boolean default false |
| emptyFont      | font family | reference |
| loadingStyle     | style of loading state      | circular, linear, text |
| loadingText | text will show on loading state      |   string |
| loadingTextColor | loading state text color      |   color |
| loadingDrawable | drawable will show on loading state      |   reference |
| loadingTint | tint color of loading drawable     |   color |
| loadingBackgroundColor | background color of loading state     |   color |
| emptyText | text will show on empty state      |   string |
| emptyTextColor | empty state text color     |   color |
| emptyLetterSpacing | empty state letter spacing    |   float |
| emptyLineSpacingExtra | empty state line spacing extra    |   float |
| emptyLineSpacingMultiplier | empty state line spacing multiplier    |   float |
| emptyDrawable | drawable will show on empty state     |   reference |
| emptyDrawableTint | tint color of empty drawable      |   color |
| emptyBackgroundColor | background color of empty state     |   color |
| errorText | text will show on error state     |   string |
| errorTextColor | error state text color    |   color |
| errorDrawable | drawable will show on error state     |   reference |
| errorDrawableTint | tint color of error drawable     |   color |
| errorBackgroundColor | background color of error state     |   color |
| errorButtonText | error state button text     |   string |
| errorButtonTextColor | error state button text color    |   color |
| errorButtonBackgroundColor | error state button background color       |   color |

## Notes

* Use `setTransition(android.support.transition transition)` method to define which transitions is played when animating layout changes

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





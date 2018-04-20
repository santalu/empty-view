package com.santalu.emptyview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by santalu on 09/08/2017.
 */

@SuppressLint("ResourceType")
final class EmptyUtils {

  static Drawable getDrawable(Context context, @DrawableRes int id) {
    return id <= 0 ? null : ContextCompat.getDrawable(context, id);
  }

  static String getString(Context context, @StringRes int id) {
    return id <= 0 ? null : context.getString(id);
  }

  static void setTextSize(TextView textView, float textSize) {
    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
  }
}

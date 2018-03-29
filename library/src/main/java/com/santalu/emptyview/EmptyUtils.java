package com.santalu.emptyview;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.TransitionRes;
import android.support.transition.Transition;
import android.support.transition.TransitionInflater;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by santalu on 09/08/2017.
 */

final class EmptyUtils {

  static Drawable getDrawable(Context context, @DrawableRes int id) {
    return id <= 0 ? null : ContextCompat.getDrawable(context, id);
  }

  static int getColor(Context context, @ColorRes int id) {
    return id <= 0 ? 0 : ContextCompat.getColor(context, id);
  }

  static float getDimension(Context context, @DimenRes int id) {
    return id <= 0 ? 0 : context.getResources().getDimension(id);
  }

  static String getString(Context context, @StringRes int id) {
    return id <= 0 ? null : context.getString(id);
  }

  static Typeface getFont(Context context, @FontRes int id) {
    return id <= 0 ? null : ResourcesCompat.getFont(context, id);
  }

  static Transition getTransition(Context context, @TransitionRes int id) {
    return id <= 0 ? null : TransitionInflater.from(context).inflateTransition(id);
  }

  static String fromHtml(@Nullable String text) {
    if (TextUtils.isEmpty(text)) {
      return null;
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      return Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT).toString();
    } else {
      return Html.fromHtml(text).toString();
    }
  }

  static void setTextSize(TextView textView, float textSize) {
    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
  }
}

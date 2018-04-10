package com.santalu.emptyview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.transition.Explode;
import android.support.transition.Fade;
import android.support.transition.Slide;
import android.support.transition.Transition;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by santalu on 09/08/2017.
 */

public class EmptyViewBuilder {

  public static final int NONE = 0;

  // Gravity
  public static final int TOP = 48;
  public static final int CENTER = 17;
  public static final int BOTTOM = 80;

  // Loading
  public static final int CIRCULAR = 1;

  // State
  public static final int CONTENT = 1;
  public static final int LOADING = 2;
  public static final int EMPTY = 3;
  public static final int ERROR = 4;

  // Transition
  public static final int SLIDE = 1;
  public static final int EXPLODE = 2;
  public static final int FADE = 3;

  private final EmptyView emptyView;
  private final Context context;
  List<View> excludedViews;
  int state;
  int gravity;

  // Shared attributes
  float titleTextSize;
  float textSize;
  float buttonTextSize;
  float letterSpacing;
  float lineSpacingExtra;
  float lineSpacingMultiplier;
  Typeface font;
  Transition transition;
  View.OnClickListener onClickListener;

  // Loading state attributes
  @Loading int loading;
  CharSequence loadingTitle;
  @ColorInt int loadingTitleTextColor;
  CharSequence loadingText;
  @ColorInt int loadingTextColor;
  Drawable loadingDrawable;
  @ColorInt int loadingDrawableTint;
  @ColorInt int loadingBackgroundColor;

  // Empty state attributes
  CharSequence emptyTitle;
  @ColorInt int emptyTitleTextColor;
  CharSequence emptyText;
  @ColorInt int emptyTextColor;
  CharSequence emptyButtonText;
  @ColorInt int emptyButtonTextColor;
  @ColorInt int emptyButtonBackgroundColor;
  Drawable emptyDrawable;
  @ColorInt int emptyDrawableTint;
  @ColorInt int emptyBackgroundColor;

  // Error state attributes
  CharSequence errorTitle;
  @ColorInt int errorTitleTextColor;
  CharSequence errorText;
  @ColorInt int errorTextColor;
  CharSequence errorButtonText;
  @ColorInt int errorButtonTextColor;
  @ColorInt int errorButtonBackgroundColor;
  Drawable errorDrawable;
  @ColorInt int errorDrawableTint;
  @ColorInt int errorBackgroundColor;

  EmptyViewBuilder(EmptyView emptyView) {
    this.emptyView = emptyView;
    this.context = emptyView.getContext();
  }

  EmptyViewBuilder(EmptyView emptyView, @NonNull AttributeSet attributeSet) {
    this(emptyView);

    TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.EmptyView);
    try {
      Resources resources = context.getResources();
      float defaultTitleTextSize = resources.getDimension(R.dimen.emptyview_title_text_size);
      float defaultTextSize = resources.getDimension(R.dimen.emptyview_text_size);
      float defaultButtonTextSize = resources.getDimension(R.dimen.emptyview_button_text_size);
      int defaultTextColor = resources.getColor(android.R.color.secondary_text_dark);

      gravity = a.getInt(R.styleable.EmptyView_ev_gravity, CENTER);

      int transitionType = a.getInt(R.styleable.EmptyView_ev_transition, NONE);
      switch (transitionType) {
        case SLIDE:
          transition = new Slide();
          break;
        case EXPLODE:
          transition = new Explode();
          break;
        case FADE:
          transition = new Fade();
          break;
        case NONE:
        default:
          transition = null;
          break;
      }

      // Shared attributes
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        font = a.getFont(R.styleable.EmptyView_ev_font);
      } else {
        int fontResId = a.getResourceId(R.styleable.EmptyView_ev_font, 0);
        if (fontResId != 0) {
          font = ResourcesCompat.getFont(context, fontResId);
        }
      }

      titleTextSize = a.getDimension(R.styleable.EmptyView_ev_titleTextSize, defaultTitleTextSize);
      textSize = a.getDimension(R.styleable.EmptyView_ev_textSize, defaultTextSize);
      buttonTextSize =
          a.getDimension(R.styleable.EmptyView_ev_buttonTextSize, defaultButtonTextSize);
      letterSpacing = a.getDimension(R.styleable.EmptyView_ev_letterSpacing, 0);
      lineSpacingExtra = a.getDimension(R.styleable.EmptyView_ev_lineSpacingExtra, 1);
      lineSpacingMultiplier =
          a.getDimension(R.styleable.EmptyView_ev_lineSpacingExtraMultiplier, 1);

      // Loading state attributes
      loading = a.getInt(R.styleable.EmptyView_ev_loading, CIRCULAR);
      loadingTitle = a.getText(R.styleable.EmptyView_ev_loading_title);
      loadingTitleTextColor =
          a.getColor(R.styleable.EmptyView_ev_loading_titleTextColor, defaultTextColor);
      loadingText = a.getText(R.styleable.EmptyView_ev_loading_text);
      loadingTextColor = a.getColor(R.styleable.EmptyView_ev_loading_textColor, defaultTextColor);
      loadingDrawable = a.getDrawable(R.styleable.EmptyView_ev_loading_drawable);
      loadingDrawableTint = a.getColor(R.styleable.EmptyView_ev_loading_drawableTint, 0);
      loadingBackgroundColor = a.getColor(R.styleable.EmptyView_ev_loading_backgroundColor, 0);

      // Empty state attributes
      emptyTitle = a.getText(R.styleable.EmptyView_ev_empty_title);
      emptyTitleTextColor =
          a.getColor(R.styleable.EmptyView_ev_empty_titleTextColor, defaultTextColor);
      emptyText = a.getText(R.styleable.EmptyView_ev_empty_text);
      emptyTextColor = a.getColor(R.styleable.EmptyView_ev_empty_textColor, defaultTextColor);
      emptyButtonText = a.getText(R.styleable.EmptyView_ev_empty_button);
      emptyButtonTextColor =
          a.getColor(R.styleable.EmptyView_ev_empty_buttonTextColor, defaultTextColor);
      emptyButtonBackgroundColor =
          a.getColor(R.styleable.EmptyView_ev_empty_buttonBackgroundColor, 0);
      emptyDrawable = a.getDrawable(R.styleable.EmptyView_ev_empty_drawable);
      emptyDrawableTint = a.getColor(R.styleable.EmptyView_ev_empty_drawableTint, 0);
      emptyBackgroundColor = a.getColor(R.styleable.EmptyView_ev_empty_backgroundColor, 0);

      // Error state attributes
      errorTitle = a.getText(R.styleable.EmptyView_ev_error_title);
      errorTitleTextColor =
          a.getColor(R.styleable.EmptyView_ev_error_titleTextColor, defaultTextColor);
      errorText = a.getText(R.styleable.EmptyView_ev_error_text);
      errorTextColor = a.getColor(R.styleable.EmptyView_ev_error_textColor, defaultTextColor);
      errorButtonText = a.getText(R.styleable.EmptyView_ev_error_button);
      errorButtonTextColor =
          a.getColor(R.styleable.EmptyView_ev_error_buttonTextColor, defaultTextColor);
      errorButtonBackgroundColor =
          a.getColor(R.styleable.EmptyView_ev_error_buttonBackgroundColor, 0);
      errorDrawable = a.getDrawable(R.styleable.EmptyView_ev_error_drawable);
      errorDrawableTint = a.getColor(R.styleable.EmptyView_ev_error_drawableTint, 0);
      errorBackgroundColor = a.getColor(R.styleable.EmptyView_ev_error_backgroundColor, 0);
    } finally {
      a.recycle();
    }
  }

  public EmptyViewBuilder setOnClickListener(View.OnClickListener onClickListener) {
    this.onClickListener = onClickListener;
    return this;
  }

  public EmptyViewBuilder exclude(@IdRes int... ids) {
    excludedViews = new ArrayList<>();
    for (int id : ids) {
      View view = emptyView.findViewById(id);
      if (!excludedViews.contains(view)) {
        excludedViews.add(view);
      }
    }
    return this;
  }

  public EmptyViewBuilder exclude(View... views) {
    excludedViews = new ArrayList<>();
    for (View view : views) {
      if (!excludedViews.contains(view)) {
        excludedViews.add(view);
      }
    }
    return this;
  }

  public EmptyViewBuilder setState(@State int state) {
    this.state = state;
    return this;
  }

  public EmptyViewBuilder setLoading(@Loading int loading) {
    this.loading = loading;
    return this;
  }

  public EmptyViewBuilder setLoadingTitle(@StringRes int id) {
    return setLoadingTitle(EmptyUtils.getString(context, id));
  }

  public EmptyViewBuilder setLoadingTitle(CharSequence loadingTitle) {
    this.loadingTitle = loadingTitle;
    return this;
  }

  public EmptyViewBuilder setLoadingText(@StringRes int id) {
    return setLoadingText(EmptyUtils.getString(context, id));
  }

  public EmptyViewBuilder setLoadingText(CharSequence loadingText) {
    this.loadingText = loadingText;
    return this;
  }

  public EmptyViewBuilder setLoadingDrawable(@DrawableRes int id) {
    return setLoadingDrawable(EmptyUtils.getDrawable(context, id));
  }

  public EmptyViewBuilder setLoadingDrawable(Drawable loadingDrawable) {
    this.loadingDrawable = loadingDrawable;
    return this;
  }

  public EmptyViewBuilder setEmptyTitle(@StringRes int id) {
    return setEmptyTitle(EmptyUtils.getString(context, id));
  }

  public EmptyViewBuilder setEmptyTitle(CharSequence emptyTitle) {
    this.emptyTitle = emptyTitle;
    return this;
  }

  public EmptyViewBuilder setEmptyText(@StringRes int id) {
    return setEmptyText(EmptyUtils.getString(context, id));
  }

  public EmptyViewBuilder setEmptyText(CharSequence emptyText) {
    this.emptyText = emptyText;
    return this;
  }

  public EmptyViewBuilder setEmptyButtonText(@StringRes int id) {
    return setEmptyButtonText(EmptyUtils.getString(context, id));
  }

  public EmptyViewBuilder setEmptyButtonText(CharSequence emptyButtonText) {
    this.emptyButtonText = emptyButtonText;
    return this;
  }

  public EmptyViewBuilder setEmptyDrawable(@DrawableRes int id) {
    return setEmptyDrawable(EmptyUtils.getDrawable(context, id));
  }

  public EmptyViewBuilder setEmptyDrawable(Drawable emptyDrawable) {
    this.emptyDrawable = emptyDrawable;
    return this;
  }

  public EmptyViewBuilder setErrorTitle(@StringRes int id) {
    return setErrorTitle(EmptyUtils.getString(context, id));
  }

  public EmptyViewBuilder setErrorTitle(CharSequence errorTitle) {
    this.errorTitle = errorTitle;
    return this;
  }

  public EmptyViewBuilder setErrorText(@StringRes int id) {
    return setErrorText(EmptyUtils.getString(context, id));
  }

  public EmptyViewBuilder setErrorText(CharSequence errorText) {
    this.errorText = errorText;
    return this;
  }

  public EmptyViewBuilder setErrorButtonText(@StringRes int id) {
    return setErrorButtonText(EmptyUtils.getString(context, id));
  }

  public EmptyViewBuilder setErrorButtonText(CharSequence errorButtonText) {
    this.errorButtonText = errorButtonText;
    return this;
  }

  public EmptyViewBuilder setErrorDrawable(@DrawableRes int id) {
    return setErrorDrawable(EmptyUtils.getDrawable(context, id));
  }

  public EmptyViewBuilder setErrorDrawable(Drawable errorDrawable) {
    this.errorDrawable = errorDrawable;
    return this;
  }

  public void show() {
    emptyView.show();
  }

  @IntDef({ NONE, CIRCULAR })
  @Retention(RetentionPolicy.SOURCE)
  public @interface Loading {
  }

  @IntDef({ CONTENT, EMPTY, ERROR, LOADING })
  @Retention(RetentionPolicy.SOURCE)
  public @interface State {
  }
}

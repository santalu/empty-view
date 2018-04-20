package com.santalu.emptyview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.transition.TransitionManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import static com.santalu.emptyview.EmptyViewBuilder.CONTENT;
import static com.santalu.emptyview.EmptyViewBuilder.EMPTY;
import static com.santalu.emptyview.EmptyViewBuilder.ERROR;
import static com.santalu.emptyview.EmptyViewBuilder.LOADING;
import static com.santalu.emptyview.EmptyViewBuilder.NONE;

/**
 * Created by santalu on 09/08/2017.
 */

public class EmptyView extends ConstraintLayout {

  private final EmptyViewBuilder builder;
  private final List<View> children;

  private LinearLayout container;
  private ProgressBar progressBar;
  private ImageView imageView;
  private TextView titleView;
  private TextView textView;
  private Button button;

  public EmptyView(Context context) {
    super(context);
    builder = new EmptyViewBuilder(this);
    children = new ArrayList<>();
  }

  public EmptyView(Context context, AttributeSet attrs) {
    super(context, attrs);
    builder = new EmptyViewBuilder(this, attrs);
    children = new ArrayList<>();
  }

  public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    builder = new EmptyViewBuilder(this, attrs);
    children = new ArrayList<>();
  }

  @Override
  public void addView(View child, int index, ViewGroup.LayoutParams params) {
    super.addView(child, index, params);
    if (child.getVisibility() == VISIBLE) {
      children.add(child);
    }
  }

  @Override
  public void setOnClickListener(@Nullable OnClickListener onClickListener) {
    builder.setOnClickListener(onClickListener);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    inflate(getContext(), R.layout.empty_view, this);
    container = findViewById(R.id.empty_layout);
    imageView = findViewById(R.id.empty_icon);
    textView = findViewById(R.id.empty_text);
    titleView = findViewById(R.id.empty_title);
    button = findViewById(R.id.empty_button);
    progressBar = findViewById(R.id.empty_progress_bar);
  }

  public EmptyViewBuilder builder() {
    return builder;
  }

  public EmptyViewBuilder content() {
    return builder.setState(CONTENT);
  }

  public EmptyViewBuilder loading() {
    return builder.setState(LOADING);
  }

  public EmptyViewBuilder empty() {
    return builder.setState(EMPTY);
  }

  public EmptyViewBuilder error() {
    return builder.setState(ERROR);
  }

  public EmptyViewBuilder error(Throwable t) {
    Error error = Error.get(t);
    return error(error);
  }

  public EmptyViewBuilder error(Error error) {
    return error()
        .setErrorTitle(error.getTitle(getContext()))
        .setErrorText(error.getMessage(getContext()));
  }

  @EmptyViewBuilder.State
  public int state() {
    return builder.state;
  }

  public void showContent() {
    content().show();
  }

  public void showLoading() {
    loading().show();
  }

  public void showEmpty() {
    empty().show();
  }

  public void showError() {
    error().show();
  }

  void show() {
    // start animation
    if (builder.transition != null) {
      TransitionManager.beginDelayedTransition(this, builder.transition);
    }

    switch (builder.state) {
      case CONTENT:
        container.setVisibility(View.GONE);
        progressBar.setVisibility(GONE);
        imageView.setVisibility(GONE);
        titleView.setVisibility(GONE);
        textView.setVisibility(GONE);
        button.setVisibility(GONE);
        setChildVisibility(VISIBLE);

        setContainer(Color.TRANSPARENT);
        break;
      case EMPTY:
        container.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
        imageView.setVisibility(VISIBLE);
        titleView.setVisibility(VISIBLE);
        textView.setVisibility(VISIBLE);
        button.setVisibility(VISIBLE);
        setChildVisibility(GONE);

        setContainer(builder.emptyBackgroundColor);
        setIcon(builder.emptyDrawable, builder.emptyDrawableTint);
        setTitle(builder.emptyTitle, builder.emptyTitleTextColor);
        setText(builder.emptyText, builder.emptyTextColor);
        setButton(builder.emptyButtonText, builder.emptyButtonTextColor,
            builder.emptyButtonBackgroundColor);
        break;
      case ERROR:
        container.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
        imageView.setVisibility(VISIBLE);
        titleView.setVisibility(VISIBLE);
        textView.setVisibility(VISIBLE);
        button.setVisibility(VISIBLE);
        setChildVisibility(GONE);

        setContainer(builder.errorBackgroundColor);
        setIcon(builder.errorDrawable, builder.errorDrawableTint);
        setTitle(builder.errorTitle, builder.errorTitleTextColor);
        setText(builder.errorText, builder.errorTextColor);
        setButton(builder.errorButtonText, builder.errorButtonTextColor,
            builder.errorButtonBackgroundColor);
        break;
      case LOADING:
        container.setVisibility(View.VISIBLE);
        progressBar.setVisibility(VISIBLE);
        imageView.setVisibility(GONE);
        titleView.setVisibility(VISIBLE);
        textView.setVisibility(VISIBLE);
        button.setVisibility(GONE);
        setChildVisibility(GONE);

        setContainer(builder.loadingBackgroundColor);
        setProgressBar(builder.loading, builder.loadingDrawableTint);
        setIcon(builder.loadingDrawable, builder.loadingDrawableTint);
        setTitle(builder.loadingTitle, builder.loadingTitleTextColor);
        setText(builder.loadingText, builder.loadingTextColor);
        break;
    }
  }

  private void setChildVisibility(int visibility) {
    if (builder.excludedViews == null || builder.excludedViews.isEmpty()) {
      for (View view : children) {
        view.setVisibility(visibility);
      }
      return;
    }
    for (View view : children) {
      if (!builder.excludedViews.contains(view)) {
        view.setVisibility(visibility);
      }
    }
  }

  private void setContainer(@ColorInt int backgroundColor) {
    container.setGravity(builder.gravity);
    container.setBackgroundColor(backgroundColor);
  }

  private void setProgressBar(@EmptyViewBuilder.LoadingType int style, @ColorInt int tint) {
    if (progressBar.getVisibility() != VISIBLE) {
      return;
    }
    if (style == NONE) {
      progressBar.setVisibility(GONE);
      return;
    }
    progressBar.setVisibility(VISIBLE);
    if (tint != 0) {
      Drawable drawable = progressBar.getIndeterminateDrawable();
      if (drawable != null) {
        drawable.setColorFilter(tint, Mode.SRC_ATOP);
      }
    }
  }

  private void setIcon(Drawable drawable, @ColorInt int tint) {
    if (imageView.getVisibility() != VISIBLE) {
      return;
    }
    if (drawable == null) {
      imageView.setVisibility(GONE);
      return;
    }
    imageView.setVisibility(VISIBLE);
    imageView.setImageDrawable(drawable);
    imageView.setColorFilter(tint);
  }

  private void setTitle(CharSequence text, @ColorInt int textColor) {
    if (titleView.getVisibility() != VISIBLE) {
      return;
    }
    if (TextUtils.isEmpty(text)) {
      titleView.setVisibility(GONE);
      return;
    }
    titleView.setVisibility(VISIBLE);
    titleView.setText(text);
    titleView.setTextColor(textColor);
    titleView.setTypeface(builder.font);
    if (builder.titleTextSize != 0) {
      EmptyUtils.setTextSize(titleView, builder.titleTextSize);
    }
  }

  private void setText(CharSequence text, @ColorInt int textColor) {
    if (textView.getVisibility() != VISIBLE) {
      return;
    }
    if (TextUtils.isEmpty(text)) {
      textView.setVisibility(GONE);
      return;
    }
    textView.setVisibility(VISIBLE);
    textView.setText(text);
    textView.setTextColor(textColor);
    textView.setTypeface(builder.font);
    if (builder.textSize != 0) {
      EmptyUtils.setTextSize(textView, builder.textSize);
    }
  }

  private void setButton(CharSequence text,
      @ColorInt int textColor,
      @ColorInt int backgroundColor) {
    if (button.getVisibility() != VISIBLE) {
      return;
    }
    if (TextUtils.isEmpty(text)) {
      button.setVisibility(GONE);
      return;
    }
    button.setVisibility(VISIBLE);
    button.setText(text);
    button.setTextColor(textColor);
    button.setTypeface(builder.font);
    if (builder.buttonTextSize != 0) {
      EmptyUtils.setTextSize(button, builder.buttonTextSize);
    }
    button.setBackgroundColor(backgroundColor);
    button.setOnClickListener(builder.onClickListener);
  }
}

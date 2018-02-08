package com.santalu.emptyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.transition.Explode;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * Created by santalu on 09/08/2017.
 */

public class EmptyView extends RelativeLayout {

    //Style
    public static final int CIRCULAR = 0;
    public static final int TEXT = 1;

    //Position
    public static final int CENTER = 0;
    public static final int TOP = 1;
    public static final int BOTTOM = 2;

    //State
    public static final int LOADING = 0;
    public static final int EMPTY = 1;
    public static final int ERROR = 2;
    public static final int CONTENT = 3;

    private static final String TAG = EmptyView.class.getSimpleName();

    private ArrayList<View> childViews = new ArrayList<>();
    private LinearLayout container;
    private ProgressBar progressBar;
    private ImageView imageView;
    private TextView textView;
    private Button button;
    private Typeface emptyFont;
    private Drawable loadingDrawable;
    private Drawable emptyDrawable;
    private Drawable errorDrawable;
    private OnClickListener onClickListener;

    private CharSequence loadingText;
    private CharSequence emptyText;
    private CharSequence emptyButtonText;
    private CharSequence errorText;
    private CharSequence errorButtonText;
    private int loadingTint;
    private int loadingTextColor;
    private int loadingBackgroundColor;
    private int loadingStyle;
    private int emptyTint;
    private int emptyTextColor;
    private int emptyBackgroundColor;
    private int emptyGravity;
    private int emptyButtonTextColor;
    private int emptyButtonBackgroundColor;
    private int errorTint;
    private int errorTextColor;
    private int errorButtonTextColor;
    private int errorButtonBackgroundColor;
    private int errorBackgroundColor;
    private int state;
    private float emptyLetterSpacing;
    private float emptyLineSpacingExtra;
    private float emptyLineSpacingMultiplier;
    private boolean emptyAnimateLayoutChanges;

    private Transition transition = new Explode();

    public EmptyView(Context context) {
        super(context);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.empty_view, this);
        container = findViewById(R.id.empty_layout);
        container.setTag(TAG);
        imageView = findViewById(R.id.empty_icon);
        textView = findViewById(R.id.empty_text);
        button = findViewById(R.id.empty_button);
        progressBar = findViewById(R.id.empty_progress_bar);
        setEmptyGravity(emptyGravity);
    }

    @Override public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (child.getVisibility() == VISIBLE &&
                (child.getTag() == null || !child.getTag().equals(TAG))) {
            childViews.add(child);
        }
    }

    @Override public void setOnClickListener(@Nullable OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setTypeface(@Nullable Typeface typeface) {
        textView.setTypeface(typeface);
        button.setTypeface(typeface);
    }

    public void setEmptyGravity(@Position int gravity) {
        switch (gravity) {
            case BOTTOM:
                container.setGravity(Gravity.BOTTOM | Gravity.CENTER);
                break;
            case TOP:
                container.setGravity(Gravity.TOP | Gravity.CENTER);
                break;
            case CENTER:
            default:
                container.setGravity(Gravity.CENTER);
                break;
        }
    }

    public void setLoadingStyle(@Style int style) {
        this.loadingStyle = style;
    }

    public void setTransition(@Nullable Transition transition) {
        this.transition = transition;
    }

    @State public int getState() {
        return state;
    }

    private void setState(@State int state) {
        //start animation
        if (emptyAnimateLayoutChanges) {
            TransitionManager.beginDelayedTransition(container, transition);
        }

        this.state = state;
        switch (state) {
            case LOADING:
                container.setVisibility(VISIBLE);
                progressBar.setVisibility(VISIBLE);
                imageView.setVisibility(GONE);
                textView.setVisibility(GONE);
                button.setVisibility(GONE);
                setupLoadingView();
                setChildVisibility(GONE);
                break;
            case EMPTY:
                container.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                imageView.setVisibility(VISIBLE);
                textView.setVisibility(VISIBLE);
                button.setVisibility(GONE);
                setupEmptyView();
                setChildVisibility(GONE);
                break;
            case ERROR:
                container.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                imageView.setVisibility(VISIBLE);
                textView.setVisibility(VISIBLE);
                button.setVisibility(VISIBLE);
                setupErrorView();
                setChildVisibility(GONE);
                break;
            case CONTENT:
                container.setVisibility(GONE);
                progressBar.setVisibility(GONE);
                imageView.setVisibility(GONE);
                textView.setVisibility(GONE);
                button.setVisibility(GONE);
                setChildVisibility(VISIBLE);
                break;
        }
    }

    public void showLoading(@StringRes int text) {
        showLoading(getString(text));
    }

    public void showLoading(@Nullable CharSequence text) {
        loadingText = text;
        showLoading();
    }

    public void showLoading() {
        setState(LOADING);
    }

    public void showError(@StringRes int text) {
        showError(getString(text));
    }

    public void showError(@Nullable CharSequence text) {
        errorText = text;
        showError();
    }

    public void showError() {
        setState(ERROR);
    }

    public void showEmpty(@StringRes int text) {
        showEmpty(getString(text));
    }

    public void showEmpty(@Nullable CharSequence text) {
        emptyText = text;
        showEmpty();
    }

    public void showEmpty() {
        setState(EMPTY);
    }

    public void showContent() {
        setState(CONTENT);
    }

    private void init(@NonNull AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EmptyView);
        try {
            if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
                emptyFont = a.getFont(R.styleable.EmptyView_emptyFont);
            } else {
                int fontResId = a.getResourceId(R.styleable.EmptyView_emptyFont, 0);
                if (fontResId != 0) {
                    emptyFont = ResourcesCompat.getFont(getContext(), fontResId);
                }
            }

            emptyAnimateLayoutChanges = a.getBoolean(
                    R.styleable.EmptyView_emptyAnimateLayoutChanges, false);

            //Loading state attrs
            loadingText = a.getText(R.styleable.EmptyView_loadingText);
            loadingTextColor = a.getColor(R.styleable.EmptyView_loadingTextColor, Color.BLACK);
            loadingBackgroundColor = a.getColor(R.styleable.EmptyView_loadingBackgroundColor, 0);
            loadingDrawable = a.getDrawable(R.styleable.EmptyView_loadingDrawable);
            loadingTint = a.getColor(R.styleable.EmptyView_loadingTint, 0);
            loadingStyle = a.getInt(R.styleable.EmptyView_loadingStyle, CIRCULAR);

            //Empty state attrs
            emptyText = a.getText(R.styleable.EmptyView_emptyText);
            emptyTextColor = a.getColor(R.styleable.EmptyView_emptyTextColor, Color.BLACK);
            emptyBackgroundColor = a.getColor(R.styleable.EmptyView_emptyBackgroundColor, 0);
            emptyDrawable = a.getDrawable(R.styleable.EmptyView_emptyDrawable);
            emptyTint = a.getColor(R.styleable.EmptyView_emptyDrawableTint, 0);
            emptyGravity = a.getInt(R.styleable.EmptyView_emptyGravity, CENTER);
            emptyLetterSpacing = a.getFloat(R.styleable.EmptyView_emptyLetterSpacing, 0);
            emptyLineSpacingExtra = a.getFloat(R.styleable.EmptyView_emptyLineSpacingExtra, 1);
            emptyLineSpacingMultiplier = a.getFloat(
                    R.styleable.EmptyView_emptyLineSpacingMultiplier, 1f);
            emptyButtonText = a.getText(R.styleable.EmptyView_emptyButtonText);
            emptyButtonTextColor = a.getColor(R.styleable.EmptyView_emptyButtonTextColor,
                    Color.BLACK);
            emptyButtonBackgroundColor = a.getColor(
                    R.styleable.EmptyView_emptyButtonBackgroundColor, 0);

            //Error state attrs
            errorText = a.getText(R.styleable.EmptyView_errorText);
            errorTextColor = a.getColor(R.styleable.EmptyView_errorTextColor, Color.BLACK);
            errorButtonText = a.getText(R.styleable.EmptyView_errorButtonText);
            errorButtonTextColor = a.getColor(R.styleable.EmptyView_errorButtonTextColor,
                    Color.BLACK);
            errorButtonBackgroundColor = a.getColor(
                    R.styleable.EmptyView_errorButtonBackgroundColor, 0);
            errorBackgroundColor = a.getColor(R.styleable.EmptyView_errorBackgroundColor, 0);
            errorDrawable = a.getDrawable(R.styleable.EmptyView_errorDrawable);
            errorTint = a.getColor(R.styleable.EmptyView_errorDrawableTint, 0);
        } finally {
            a.recycle();
        }
    }

    private void setChildVisibility(int visibility) {
        for (View view : childViews) {
            view.setVisibility(visibility);
        }
    }

    private void setupLoadingView() {
        container.setBackgroundColor(loadingBackgroundColor);
        if (loadingStyle == TEXT) {
            progressBar.setVisibility(GONE);
        } else {
            progressBar.setVisibility(VISIBLE);
            if (loadingTint != 0) {
                progressBar.getIndeterminateDrawable().setColorFilter(loadingTint, Mode.SRC_IN);
            }
        }
        setIcon(loadingDrawable, loadingTint);
        setText(loadingText, loadingTextColor);
        if (emptyFont != null) {
            setTypeface(emptyFont);
        }
    }

    private void setupEmptyView() {
        container.setBackgroundColor(emptyBackgroundColor);
        if (TextUtils.isEmpty(emptyButtonText)) {
            container.setOnClickListener(onClickListener);
        }
        setIcon(emptyDrawable, emptyTint);
        setText(emptyText, emptyTextColor);
        setButton(emptyButtonText, emptyButtonTextColor, emptyButtonBackgroundColor);
        if (emptyFont != null) {
            setTypeface(emptyFont);
        }
    }

    private void setupErrorView() {
        container.setBackgroundColor(errorBackgroundColor);
        setIcon(errorDrawable, errorTint);
        setText(errorText, errorTextColor);
        setButton(errorButtonText, errorButtonTextColor, errorButtonBackgroundColor);
        if (emptyFont != null) {
            setTypeface(emptyFont);
        }
    }

    private void setIcon(@Nullable Drawable drawable, @ColorInt int tint) {
        if (drawable == null) {
            imageView.setVisibility(GONE);
        } else {
            imageView.setVisibility(VISIBLE);
            imageView.setImageDrawable(drawable);
            imageView.setColorFilter(tint);
        }
    }

    private void setText(@Nullable CharSequence text, @ColorInt int color) {
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(GONE);
        } else {
            textView.setVisibility(VISIBLE);
            textView.setText(fromHtml(text.toString()));
            textView.setTextColor(color);
            textView.setLineSpacing(emptyLineSpacingExtra, emptyLineSpacingMultiplier);
            if (Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
                textView.setLetterSpacing(emptyLetterSpacing);
            }
        }
    }

    private void setButton(@Nullable CharSequence text,
            @ColorInt int color,
            @ColorInt int backgroundColor) {
        if (TextUtils.isEmpty(text)) {
            button.setVisibility(GONE);
        } else {
            button.setVisibility(VISIBLE);
            button.setText(fromHtml(text.toString()));
            button.setTextColor(color);
            if (backgroundColor != 0) {
                button.setBackgroundColor(backgroundColor);
            }
            button.setOnClickListener(onClickListener);
        }
    }

    private String getString(@StringRes int id) {
        return id < 0 ? null : getContext().getString(id);
    }

    private String fromHtml(@Nullable String text) {
        if (TextUtils.isEmpty(text)) {
            return null;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT).toString();
        } else {
            return Html.fromHtml(text).toString();
        }
    }

    @IntDef({CONTENT, EMPTY, ERROR, LOADING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {}

    @IntDef({CIRCULAR, TEXT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {}

    @IntDef({CENTER, TOP, BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Position {}

}

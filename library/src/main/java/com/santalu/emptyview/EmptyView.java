package com.santalu.emptyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
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

    @IntDef({ State.CONTENT, State.EMPTY, State.ERROR, State.LOADING })
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
        int LOADING = 0;
        int EMPTY = 1;
        int ERROR = 2;
        int CONTENT = 3;
    }

    @IntDef({ Style.CIRCULAR, Style.TEXT })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {
        int CIRCULAR = 0;
        int TEXT = 1;
    }

    @IntDef({ Position.CENTER, Position.TOP, Position.BOTTOM })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Position {
        int CENTER = 0;
        int TOP = 1;
        int BOTTOM = 2;
    }

    private static final String TAG = EmptyView.class.getSimpleName();

    private ArrayList<View> mChildViews = new ArrayList<>();
    private LinearLayout mContainer;
    private ProgressBar mProgressBar;
    private ImageView mImageView;
    private TextView mTextView;
    private Button mButton;
    private OnClickListener mOnClickListener;

    private CharSequence mLoadingText;
    private CharSequence mEmptyText;
    private CharSequence mErrorText;
    private CharSequence mErrorButtonText;
    private int mLoadingTint;
    private int mLoadingTextColor;
    private int mLoadingBackgroundColor;
    private int mLoadingStyle;
    private int mEmptyTint;
    private int mEmptyTextColor;
    private int mEmptyBackgroundColor;
    private int mEmptyGravity;
    private int mErrorTint;
    private int mErrorTextColor;
    private int mErrorButtonTextColor;
    private int mErrorButtonBackgroundColor;
    private int mErrorBackgroundColor;
    private int mState;
    private Drawable mLoadingDrawable;
    private Drawable mEmptyDrawable;
    private Drawable mErrorDrawable;

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
        mContainer = findViewById(R.id.empty_layout);
        mContainer.setTag(TAG);
        mImageView = findViewById(R.id.empty_icon);
        mTextView = findViewById(R.id.empty_text);
        mButton = findViewById(R.id.empty_button);
        mProgressBar = findViewById(R.id.empty_progress_bar);
        setEmptyGravity(mEmptyGravity);
    }

    @Override public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (child.getVisibility() == VISIBLE && (child.getTag() == null || !child.getTag().equals(TAG))) {
            mChildViews.add(child);
        }
    }

    @Override public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setEmptyGravity(@Position int gravity) {
        switch (gravity) {
            case Position.BOTTOM:
                mContainer.setGravity(Gravity.BOTTOM | Gravity.CENTER);
                break;
            case Position.TOP:
                mContainer.setGravity(Gravity.TOP | Gravity.CENTER);
                break;
            case Position.CENTER:
            default:
                mContainer.setGravity(Gravity.CENTER);
                break;
        }
    }

    public void showLoading(@StringRes int text) {
        showLoading(getString(text));
    }

    public void showLoading(CharSequence text) {
        mLoadingText = text;
        showLoading();
    }

    public void showLoading() {
        setState(State.LOADING);
    }

    public void showError(@StringRes int text) {
        showError(getString(text));
    }

    public void showError(CharSequence text) {
        mErrorText = text;
        showError();
    }

    public void showError() {
        setState(State.ERROR);
    }

    public void showEmpty(@StringRes int text) {
        showEmpty(getString(text));
    }

    public void showEmpty(CharSequence text) {
        mEmptyText = text;
        showEmpty();
    }

    public void showEmpty() {
        setState(State.EMPTY);
    }

    public void showContent() {
        setState(State.CONTENT);
    }

    @State public int getState() {
        return mState;
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EmptyView);
        try {
            //Loading state attrs
            mLoadingText = a.getText(R.styleable.EmptyView_loadingText);
            mLoadingTextColor = a.getColor(R.styleable.EmptyView_loadingTextColor, Color.BLACK);
            mLoadingBackgroundColor = a.getColor(R.styleable.EmptyView_loadingBackgroundColor, 0);
            mLoadingDrawable = a.getDrawable(R.styleable.EmptyView_loadingDrawable);
            mLoadingTint = a.getColor(R.styleable.EmptyView_loadingTint, 0);
            mLoadingStyle = a.getInt(R.styleable.EmptyView_loadingStyle, Style.CIRCULAR);

            //Empty state attrs
            mEmptyText = a.getText(R.styleable.EmptyView_emptyText);
            mEmptyTextColor = a.getColor(R.styleable.EmptyView_emptyTextColor, Color.BLACK);
            mEmptyBackgroundColor = a.getColor(R.styleable.EmptyView_emptyBackgroundColor, 0);
            mEmptyDrawable = a.getDrawable(R.styleable.EmptyView_emptyDrawable);
            mEmptyTint = a.getColor(R.styleable.EmptyView_emptyDrawableTint, 0);
            mEmptyGravity = a.getInt(R.styleable.EmptyView_emptyGravity, Position.CENTER);

            //Error state attrs
            mErrorText = a.getText(R.styleable.EmptyView_errorText);
            mErrorTextColor = a.getColor(R.styleable.EmptyView_errorTextColor, Color.BLACK);
            mErrorButtonText = a.getText(R.styleable.EmptyView_errorButtonText);
            mErrorButtonTextColor = a.getColor(R.styleable.EmptyView_errorButtonTextColor, Color.BLACK);
            mErrorButtonBackgroundColor = a.getColor(R.styleable.EmptyView_errorButtonBackgroundColor, 0);
            mErrorBackgroundColor = a.getColor(R.styleable.EmptyView_errorBackgroundColor, 0);
            mErrorDrawable = a.getDrawable(R.styleable.EmptyView_errorDrawable);
            mErrorTint = a.getColor(R.styleable.EmptyView_errorDrawableTint, 0);
        } finally {
            a.recycle();
        }
    }

    private void setState(@State int state) {
        mState = state;
        switch (state) {
            case State.LOADING:
                mContainer.setVisibility(VISIBLE);
                mProgressBar.setVisibility(VISIBLE);
                mImageView.setVisibility(GONE);
                mTextView.setVisibility(GONE);
                mButton.setVisibility(GONE);
                setupLoadingView();
                setChildVisibility(GONE);
                break;
            case State.EMPTY:
                mContainer.setVisibility(VISIBLE);
                mProgressBar.setVisibility(GONE);
                mImageView.setVisibility(VISIBLE);
                mTextView.setVisibility(VISIBLE);
                mButton.setVisibility(GONE);
                setupEmptyView();
                setChildVisibility(GONE);
                break;
            case State.ERROR:
                mContainer.setVisibility(VISIBLE);
                mProgressBar.setVisibility(GONE);
                mImageView.setVisibility(VISIBLE);
                mTextView.setVisibility(VISIBLE);
                mButton.setVisibility(VISIBLE);
                setupErrorView();
                setChildVisibility(GONE);
                break;
            case State.CONTENT:
                mContainer.setVisibility(GONE);
                mProgressBar.setVisibility(GONE);
                mImageView.setVisibility(GONE);
                mTextView.setVisibility(GONE);
                mButton.setVisibility(GONE);
                setChildVisibility(VISIBLE);
                break;
        }
    }

    private void setChildVisibility(int visibility) {
        for (View view : mChildViews) {
            view.setVisibility(visibility);
        }
    }

    private void setupLoadingView() {
        mContainer.setBackgroundColor(mLoadingBackgroundColor);
        if (mLoadingStyle == Style.TEXT) {
            mProgressBar.setVisibility(GONE);
        } else {
            mProgressBar.setVisibility(VISIBLE);
            if (mLoadingTint != 0) {
                mProgressBar.getIndeterminateDrawable().setColorFilter(mLoadingTint, Mode.SRC_IN);
            }
        }
        setIcon(mLoadingDrawable, mLoadingTint);
        setText(mLoadingText, mLoadingTextColor);
    }

    private void setupEmptyView() {
        mContainer.setBackgroundColor(mEmptyBackgroundColor);
        mContainer.setOnClickListener(mOnClickListener);
        setIcon(mEmptyDrawable, mEmptyTint);
        setText(mEmptyText, mEmptyTextColor);
    }

    private void setupErrorView() {
        mContainer.setBackgroundColor(mErrorBackgroundColor);
        setIcon(mErrorDrawable, mErrorTint);
        setText(mErrorText, mErrorTextColor);
        setButton(mErrorButtonText, mErrorButtonTextColor, mErrorButtonBackgroundColor);
    }

    private void setIcon(@Nullable Drawable drawable, @ColorInt int tint) {
        if (drawable == null) {
            mImageView.setVisibility(GONE);
        } else {
            mImageView.setVisibility(VISIBLE);
            mImageView.setImageDrawable(drawable);
            mImageView.setColorFilter(tint);
        }
    }

    private void setText(@Nullable CharSequence text, @ColorInt int color) {
        if (TextUtils.isEmpty(text)) {
            mTextView.setVisibility(GONE);
        } else {
            mTextView.setVisibility(VISIBLE);
            mTextView.setText(fromHtml(text.toString()));
            mTextView.setTextColor(color);
        }
    }

    private void setButton(@Nullable CharSequence text, @ColorInt int color, @ColorInt int backgroundColor) {
        if (TextUtils.isEmpty(text)) {
            mButton.setVisibility(GONE);
        } else {
            mButton.setVisibility(VISIBLE);
            mButton.setText(fromHtml(text.toString()));
            mButton.setTextColor(color);
            if (backgroundColor != 0) {
                mButton.setBackgroundColor(backgroundColor);
            }
            mButton.setOnClickListener(mOnClickListener);
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

}

package com.santalu.emptyview;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.santalu.emptyview.EmptyView.CONTENT;
import static com.santalu.emptyview.EmptyView.EMPTY;
import static com.santalu.emptyview.EmptyView.ERROR;
import static com.santalu.emptyview.EmptyView.LOADING;

/**
 * Created by santalu on 09/08/2017.
 */

@IntDef({ CONTENT, EMPTY, ERROR, LOADING })
@Retention(RetentionPolicy.SOURCE)
public @interface State {
}
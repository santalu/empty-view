package com.santalu.emptyview;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.santalu.emptyview.EmptyView.CIRCULAR;
import static com.santalu.emptyview.EmptyView.NONE;

/**
 * Created by santalu on 09/08/2017.
 */

@IntDef({ NONE, CIRCULAR })
@Retention(RetentionPolicy.SOURCE)
public @interface Loading {
}
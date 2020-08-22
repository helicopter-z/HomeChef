package com.hackthe6ix.homechef.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Dytstudio.
 */

public class FixedFocusScrollView extends androidx.core.widget.NestedScrollView {

    public FixedFocusScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FixedFocusScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedFocusScrollView(Context context) {
        super(context);
    }

    @Override
    public ArrayList<View> getFocusables(int direction) {
        return new ArrayList<View>();
    }

}
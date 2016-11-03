package com.example.nut.happyhealthy;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Nut on 2/11/2559.
 */
public class CustomTextView extends TextView {
    public CustomTextView (Context context) {
        super(context);
        initTypFace();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypFace();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTypFace();
    }

    private void initTypFace() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "LayijiMahaniyom-Bao-1.2.ttf");
        setTypeface(typeface);
    }
}



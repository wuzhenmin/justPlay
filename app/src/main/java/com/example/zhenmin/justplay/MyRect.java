package com.example.zhenmin.justplay;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhenmin on 2015/12/4.
 */
public class MyRect extends View {
    public MyRect(Context context) {
        super(context);
    }

    public MyRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MyRect);
        int color = ta.getColor(R.styleable.MyRect_rect_color,0xffff0000);
        setBackgroundColor(color);
        ta.recycle();

    }
}

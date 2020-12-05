package com.example.helper.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ToogleView extends View {
    //控件
    private ImageView mIcon;
    private ImageView mbuton;
    private TextView mText;
    //属性

    public ToogleView(Context context) {
        this(context,null);
    }

    public ToogleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ToogleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {


    }
}

package com.example.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.helper.R;
import com.example.helper.ui.CustemApp;

public class FakeAppleListItemView extends View {
    private String title;
    private String desc;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public FakeAppleListItemView(Context context) {
        this(context, null);
        //Log.e("WANG", "FakeAppleListItemView: 第一层" );
    }
    //inflate调用这个方法
    public FakeAppleListItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        //Log.e("WANG", "FakeAppleListItemView: 第二层" );
    }

    public FakeAppleListItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getResources().obtainAttributes(attrs, R.styleable.FakeAppleListItemView);
        title = ta.getString(R.styleable.FakeAppleListItemView_title);
        desc = ta.getString(R.styleable.FakeAppleListItemView_desc);
        ta.recycle();
        //Log.e("WANG", "FakeAppleListItemView: 第三层" );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //setMeasuredDimension(100,100);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        float paddingToLeft = w*0.05f;
        float paddingToRight = w*0.05f;
        float titleSize = h*0.5f;
        float descSize = h*0.4f;

        Paint backPaint = new Paint();
        backPaint.setColor(R.color.frag_back);

        Paint titlePaint = new Paint();
        titlePaint.setStyle(Paint.Style.FILL);
        titlePaint.setColor(0xdf222222);
        titlePaint.setStrokeWidth(1);
        titlePaint.setTextSize(titleSize);
        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setTypeface(CustemApp.gTypeface);

        Paint desPaint = new Paint(titlePaint);
        desPaint.setTextSize(descSize);
        desPaint.setColor(R.color.desc);
        desPaint.setTextAlign(Paint.Align.RIGHT);

        Paint linePaint = new Paint();
        linePaint.setStrokeWidth(2);
        linePaint.setColor(R.color.desc);


        //canvas.drawRect(0,0,w,h,backPaint);
        Paint.FontMetrics fontMetrics = titlePaint.getFontMetrics();
        //计算偏离baseline的距离 h方向
        float offset = (float) ((fontMetrics.descent - fontMetrics.ascent)/2 - fontMetrics.descent*1.2);
        canvas.drawText(title,paddingToLeft,h/2 + offset,titlePaint);
        canvas.drawText(desc,w - paddingToRight,h/2 + offset,desPaint);
        canvas.drawLine(paddingToLeft,h,w,h,linePaint);
    }

}

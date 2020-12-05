package com.example.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import com.example.helper.R;

public class CornerTextView extends androidx.appcompat.widget.AppCompatTextView {
    private float mCornerSize;//圆角大小
    private int mTextBackground;//背景颜色

    public CornerTextView(Context context) {
        this(context,null);
    }

    public CornerTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CornerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //obtainStyledAttributes 先来的参数优先级高
        //attrs是布局文件中的属性
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs,R.styleable.CornerTextView,0,0);
        int n = typedArray.getIndexCount();
        for (int i =0;i < n;i++){
            //只能取出自定义属性？？？？
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.CornerTextView_cornerSize:
                    mCornerSize = typedArray.getDimension(attr,0);
                    break;
                case R.styleable.CornerTextView_textBackground:
                    mTextBackground = typedArray.getColor(attr,0xffffff);//默认白色
                    break;
                    default:
                        Log.d("WANG", "OTHER ");
            }

        }
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //笔刷大小为3
        Paint paint = new Paint();
        paint.setColor(mTextBackground);
        paint.setStrokeWidth(3);

        RectF rectF = new RectF(0,0,getWidth(),getHeight());
        canvas.drawRoundRect(rectF,mCornerSize,mCornerSize,paint);

        super.onDraw(canvas);
    }
}

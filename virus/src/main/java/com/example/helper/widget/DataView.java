package com.example.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.helper.R;
import com.example.helper.ui.CustemApp;

public class DataView extends View {
    //属性
    private float mWHQCornerSize;//圆角大小
    private int mWHQTextBackground;//背景颜色
    private String mWHQTitle;//
    private Bitmap mWHQBitmap;//左侧图片
    private String mWHQNumber = "-1";//数据
    private String mWHQUnit = "U";//单位
    //
    private int mHeight;
    private int mWidth;


    public DataView(Context context) {
        this(context,null);
    }

    public DataView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    public void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        TypedArray typedArray = context.getTheme().
                obtainStyledAttributes(attrs, R.styleable.DataView,defStyleAttr,0);

        int res = typedArray.getResourceId(R.styleable.DataView_WHQBitmap,R.drawable.dog);//默认值是狗
        mWHQBitmap = BitmapFactory.decodeResource(getResources(),res);
        mWHQTitle = typedArray.getString(R.styleable.DataView_WHQTitle);
        mWHQNumber = typedArray.getString(R.styleable.DataView_WHQNumber);
        mWHQUnit = typedArray.getString(R.styleable.DataView_WHQUnit);
        mWHQCornerSize = typedArray.getDimensionPixelSize(R.styleable.DataView_WHQCornerSize,5);
        mWHQTextBackground = typedArray.getColor(R.styleable.DataView_WHQTextBackground,Color.alpha(100));
        typedArray.recycle();//一定要记得调用 释放属性资源
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画圆角矩形
        drawCornerRect(canvas);
        //画图像
        drawIcon(canvas);
        //画文字
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        float paddingToRight = mWidth * 0.1f;
        Paint titlePaint = new Paint();
        titlePaint.setStrokeWidth(1);
        titlePaint.setColor(0xdf222222);
        titlePaint.setTextSize(mHeight / 4);
        titlePaint.setTextAlign(Paint.Align.RIGHT);
        titlePaint.setTypeface(CustemApp.gTypeface);

        Paint descPaint = new Paint(titlePaint);
        descPaint.setTextSize(mHeight / 5);

        float titleHeight = titlePaint.getFontMetrics().descent - titlePaint.getFontMetrics().ascent;
        //文字中心相当于基线的偏移 +
        float titleOffset = titleHeight / 2 - titlePaint.getFontMetrics().descent;
        float descHeight = descPaint.getFontMetrics().descent - descPaint.getFontMetrics().ascent;
        float descOffset = descHeight / 2 - descPaint.getFontMetrics().descent;

        canvas.drawText(mWHQTitle, mWidth - paddingToRight, mHeight / 3 + titleOffset, titlePaint);
        canvas.drawText(mWHQNumber + mWHQUnit, mWidth - paddingToRight, 2*mHeight / 3 + descOffset, titlePaint);

    }

    private void drawCornerRect(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(mWHQTextBackground);
        paint.setStrokeWidth(1);
        RectF rectF = new RectF(0,0,mWidth,mHeight);
        canvas.drawRoundRect(rectF,mWHQCornerSize,mWHQCornerSize,paint);
    }
    private void drawIcon(Canvas canvas){
        Matrix matrix = new Matrix();
        float sy = (float) ((mHeight*2/3.0)/mWHQBitmap.getHeight());//view高度的2/3
        //float sx = (float) ((mHeight*1/3.0)/mBitmap.getWidth());//view宽度的1/3
        matrix.setScale(sy,sy);
        Bitmap bitmap = Bitmap.createBitmap(mWHQBitmap,0,0,mWHQBitmap.getWidth(),mWHQBitmap.getHeight(),matrix,false);
        canvas.drawBitmap(bitmap,0,mHeight/6,new Paint());//从1/6处开始画
    }

    public void setNumber(String data) {
        mWHQNumber = data;
    }

    public String getmWHQNumber() {
        return mWHQNumber;
    }
}

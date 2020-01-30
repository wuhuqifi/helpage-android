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

import com.example.helper.R;

public class DataView extends android.support.v7.widget.AppCompatTextView {
    //属性
    private float mWHQCornerSize;//圆角大小
    private int mWHQTextBackground;//背景颜色
    private Bitmap mBitmap;//左侧图片
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

        int res = typedArray.getResourceId(R.styleable.DataView_bitmap,R.drawable.dog);//默认值是狗
        mBitmap = BitmapFactory.decodeResource(getResources(),res);
        mWHQNumber = typedArray.getString(R.styleable.DataView_WHQNumber);
        mWHQUnit = typedArray.getString(R.styleable.DataView_WHQUnit);
        mWHQCornerSize = typedArray.getDimensionPixelSize(R.styleable.DataView_WHQCornerSize,5);
        mWHQTextBackground = typedArray.getColor(R.styleable.DataView_WHQTextBackground,Color.alpha(100));
        typedArray.recycle();//一定要记得调用 释放属性资源
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        switch (wMode){
            case MeasureSpec.AT_MOST://wrap_content
                break;
            case MeasureSpec.EXACTLY://match_parent 10dp etc
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        switch (hMode){
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        setMeasuredDimension(w,h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getWidth();
        mHeight = getHeight();
        //画圆角矩形
        drawCornerRect(canvas);
        //画图像
        drawIcon(canvas);
        //TODO 画数据
        super.onDraw(canvas);
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
        float sy = (float) ((mHeight*2/3.0)/mBitmap.getHeight());//view高度的2/3
        //float sx = (float) ((mHeight*1/3.0)/mBitmap.getWidth());//view宽度的1/3
        matrix.setScale(sy,sy);
        Bitmap bitmap = Bitmap.createBitmap(mBitmap,0,0,mBitmap.getWidth(),mBitmap.getHeight(),matrix,false);
        canvas.drawBitmap(bitmap,0,mHeight/6,new Paint());//从1/6处开始画
    }
}

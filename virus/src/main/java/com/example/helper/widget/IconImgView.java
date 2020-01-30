package com.example.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.example.helper.R;

public class IconImgView extends AppCompatImageView {
    private int IIVcornerSize;
    private int IIVtextBackground;

    private Paint mPaintBack;
    public IconImgView(Context context) {
        this(context,null);
    }

    public IconImgView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IconImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init( context,  attrs,  defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray typedArray = context.getResources()
                .obtainAttributes(attrs, R.styleable.IconImgView);
        IIVcornerSize = typedArray.getDimensionPixelSize(R.styleable.IconImgView_IIVcornerSize,20);
        IIVtextBackground = typedArray.getColor(R.styleable.IconImgView_IIVtextBackground,0);
        //int resourceId = typedArray.getResourceId(R.styleable.IconImgView_IIVicon,R.drawable.dog);
        //IIVicon= BitmapFactory.decodeResource(getResources(), resourceId);

        mPaintBack = new Paint();
        mPaintBack.setColor(IIVtextBackground);
        mPaintBack.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rectF = new RectF();
        rectF.set(0,0,getWidth(),getHeight());
        canvas.drawRoundRect(rectF,IIVcornerSize,IIVcornerSize,mPaintBack);
        super.onDraw(canvas);
    }
}

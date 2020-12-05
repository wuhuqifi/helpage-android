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
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.helper.R;

public class CornerToggleView extends androidx.appcompat.widget.AppCompatTextView{
    //属性
    private int CTVcornerSize;
    private int CTVbackground;
    public Bitmap CTVbuttonON;
    public Bitmap CTVbuttonOFF;
    public Bitmap CTVbuttonCurrent;
    //button标志
    public boolean isBtnON = false;
    //全局画布
    Canvas mCanvas;
    public CornerToggleView(Context context) {
        this(context,null);
    }

    public CornerToggleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CornerToggleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CornerToggleView,0,0);
        CTVbackground = typedArray.getColor(R.styleable.CornerToggleView_CTVbackground, Color.GRAY);
        CTVcornerSize = typedArray.getDimensionPixelSize(R.styleable.CornerToggleView_CTVcornerSize,20);
        int res = typedArray.getResourceId(R.styleable.CornerToggleView_CTVbuttonON,R.drawable.dog);
        CTVbuttonON = BitmapFactory.decodeResource(getResources(),res);
        res = typedArray.getResourceId(R.styleable.CornerToggleView_CTVbuttonOFF,R.drawable.dog);
        CTVbuttonOFF = BitmapFactory.decodeResource(getResources(),res);

        CTVbuttonCurrent = CTVbuttonOFF;

        //设置监听器
        this.setOnClickListener(this::onClick);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取画布
        mCanvas = canvas;

        //画笔
        Paint paint = new Paint();
        paint.setColor(CTVbackground);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1);


        //画圆角矩形
        RectF rectF = new RectF(0,0,getWidth(),getHeight());
        canvas.drawRoundRect(rectF,CTVcornerSize,CTVcornerSize,paint);
        //画button
        drawBtn(CTVbuttonCurrent,mCanvas);
        super.onDraw(canvas);
    }
    public void drawBtn(Bitmap btn,Canvas canvas){
        float sy = (float) ((getHeight()*2/3.0)/btn.getHeight());//view高度的2/3
        Bitmap bitmap = scaleBitmap(sy,sy, btn);

        canvas.drawBitmap(bitmap,4*getWidth()/5,getHeight()/6,new Paint());
    }
    public Bitmap scaleBitmap(float sx,float sy,Bitmap src){
        Matrix matrix = new Matrix();
        matrix.setScale(sx,sy);
        Bitmap bitmap = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, false);
        return bitmap;
    }


    private void onClick(View v) {

    }
}

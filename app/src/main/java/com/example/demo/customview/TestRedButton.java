package com.example.demo.customview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demo.R;

/*
做一个圆形的红色按钮
中间有一个白色的数字
数字起始为20
每点击一次减少1
 */
public class TestRedButton extends View implements View.OnClickListener {

    private Paint mPaint;
    private int mNumber=20;
    private Rect mRect;
    private int mBackgroundColor;
    private int mTextColor;
    private float mTextSize;

    public TestRedButton(Context context) {
        this(context,null);
    }


    public TestRedButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestRedButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    /*
    init the view
     */
    public void init(Context context,AttributeSet attrs){
        mPaint=new Paint();
        mRect=new Rect();
        this.setOnClickListener(this);

        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.TestRedButton);
        mTextColor=typedArray.getColor(R.styleable.TestRedButton_textColor,Color.WHITE);
        mTextSize=typedArray.getDimension(R.styleable.TestRedButton_textSize,60);
        mBackgroundColor=typedArray.getColor(R.styleable.TestRedButton_backgroundColor,Color.BLUE);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        //做一个圆形的红色按钮
        //设置画布为红色
        mPaint.setColor(mBackgroundColor);
        /*
        画过红色的圆
         */
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,mPaint);
        //中间有一个白色的数字
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        String text=String.valueOf(mNumber);
        //mRect是这个数字四周的边距
        mPaint.getTextBounds(text,0,text.length(),mRect);
        int textWidth=mRect.width();
        int textHeight=mRect.height();
        canvas.drawText(text,getWidth()/2-textWidth/2,getHeight()/2+textHeight/2,mPaint);

    }

    @Override
    public void onClick(View view) {
        //每点击一次减少1
        if(mNumber>0) {
            mNumber--;
        }else{
            mNumber=20;
        }
        invalidate();
    }
}

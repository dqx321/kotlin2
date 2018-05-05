package com.example.administrator.myapplication.reView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

// Created by CIDI daiqinxue on 2018/5/3.
public class RoundView extends View {
    private int mViewWidth;
    private int mViewHeight;
    private int mRoundWidth;
    private int mRoundColor;
    private int mStartAngle;
    private int mSweepAngle;


    Paint mPaint = new Paint();
    public RoundView(Context context) {
        super(context);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // 2.画背景大圆弧
        int centerX = mViewWidth / 2;
        int centerY = mViewHeight / 2;
        // 设置圆弧画笔的宽度
        mPaint.setStrokeWidth(mRoundWidth);
        // 设置为 ROUND
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        // 设置画笔颜色
        mPaint.setColor(mRoundColor);
        mPaint.setStyle(Paint.Style.STROKE);
        // 半径
        int radius = (int) (centerX - mRoundWidth);
        RectF oval = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        // 画背景圆弧
        canvas.drawArc(oval, mStartAngle, mSweepAngle, false, mPaint);
    }

}

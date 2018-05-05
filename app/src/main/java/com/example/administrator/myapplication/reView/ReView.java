package com.example.administrator.myapplication.reView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

// Created by CIDI daiqinxue on 2018/4/27.
public class ReView extends View {
    Paint paint = new Paint();

    public ReView(Context context) {
        super(context);
    }

    public ReView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ReView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getResources().getColor(com.vise.common_base.R.color.common_black));
        canvas.drawCircle(100, 100, 100, paint);
    }
}

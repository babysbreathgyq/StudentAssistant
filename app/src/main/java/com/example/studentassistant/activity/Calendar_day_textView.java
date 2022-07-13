package com.example.studentassistant.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class Calendar_day_textView extends TextView {

    public boolean isToday = false;
    private Paint paint = new Paint();

    public Calendar_day_textView(Context context) {
        super(context);
    }

    public Calendar_day_textView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl();
    }

    public Calendar_day_textView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl();
    }

    //先准备好自定义的画笔，设置好样式
    private void initControl() {
        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setStrokeWidth((float)2.6);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isToday) {
            // 将canvas的原点移到当前TextView的中心点的位置
            canvas.translate(getWidth() / 2, getHeight() / 2);
            // 描绘一个红色圆圈
            canvas.drawCircle(0,0,getWidth() / 2, paint);
        }
    }
}

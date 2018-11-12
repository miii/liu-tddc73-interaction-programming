package com.example.jacob.tddc73lab3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ResultItem extends View {
    String name;

    public ResultItem(Context context, String n) {
        super(context);
        name = n;
    }

    public String getName() {
        return name;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Create text
        Paint text = new Paint();
        text.setColor(Color.WHITE);
        text.setTextSize(40);

        // Create background
        Paint bg = new Paint();
        bg.setColor(Color.BLACK);

        // Draw elements
        canvas.drawPaint(bg);
        canvas.drawText(name, 24, 64, text);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Based on https://stackoverflow.com/a/42430834

        int desiredWidth = 48 + name.length() * 24;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

}

package com.example.test.levelmetertest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

class MyView extends View {

    int radius, circle_radius, vWidth, vHeight;
    int round, centerX, centerY;
    int vertical_left, vertical_top, vertical_right, vertical_bottom;
    int horizontal_left, horizontal_top, horizontal_right, horizontal_bottom;
    float xV, yV;
    Paint wPaint, gPaint;
    final int bRad = 30;

    public MyView(Context context) {
        super(context);

        wPaint = new Paint();
        wPaint.setColor(Color.WHITE);
        wPaint.setStyle(Paint.Style.STROKE);
        wPaint.setAntiAlias(true);
        wPaint.setStrokeWidth(10);

        gPaint = new Paint();
        gPaint.setColor(Color.YELLOW);
        gPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        gPaint.setAntiAlias(true);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        vWidth = display.getWidth();
        vHeight = display.getHeight();
        //radius = (Math.min(vWidth, vHeight)) / 2 - 20;
        radius = 300;
        circle_radius = 360;
        round = 30;

        horizontal_top = vHeight / 5;
        horizontal_bottom = horizontal_top + 120;
        centerX = vWidth / 2;
        horizontal_left = centerX - 360;
        horizontal_right = centerX + 360;

        vertical_left = vWidth / 20;
        vertical_right = vertical_left + 120;
        centerY = vHeight / 2;
        vertical_top = centerY - 360;
        vertical_bottom = centerY + 360;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        /* vertical */
        RectF vertical_rect1 = new RectF();
        vertical_rect1.set(vertical_left, vertical_top, vertical_right, vertical_bottom);
        canvas.drawRoundRect(vertical_rect1, round, round, wPaint);

        RectF vertical_rect2 = new RectF();
        vertical_rect2.set(vertical_left, centerY - 80, vertical_right, centerY + 80);
        canvas.drawRect(vertical_rect2, wPaint);

        canvas.drawCircle(centerX - ((radius / 10) * -xV),horizontal_top + ((horizontal_bottom - horizontal_top) / 2), bRad * 2, gPaint);

        /* horizontal */
        RectF horizontal_rect1 = new RectF();
        horizontal_rect1.set(horizontal_left, horizontal_top, horizontal_right, horizontal_bottom);
        canvas.drawRoundRect(horizontal_rect1, round, round, wPaint);

        RectF horizontal_rect2 = new RectF();
        horizontal_rect2.set(centerX - 80, horizontal_top, centerX + 80, horizontal_bottom);
        canvas.drawRect(horizontal_rect2, wPaint);

        canvas.drawCircle(vertical_left + ((vertical_right - vertical_left) / 2), centerY - ((radius / 10) * yV), bRad * 2, gPaint);

        /* circle */
        canvas.drawCircle(centerX, centerY, circle_radius, wPaint);
        canvas.drawLine(centerX - circle_radius, centerY, centerX  + circle_radius, centerY, wPaint);
        canvas.drawLine(centerX, centerY - circle_radius, centerX, centerY + circle_radius, wPaint);

        canvas.drawCircle(centerX + ((radius / 10) * xV), centerY - ((radius / 10) * yV),bRad * 2, gPaint);
    }
}
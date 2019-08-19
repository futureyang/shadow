package com.example.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

/**
 * Created by yangqc on 2019/8/12
 *  阴影背景Drawable
 * @Author yangqc
 */

public class ShadowDrawable extends Drawable {

    public static final int ALL = 0x1111;

    public static final int LEFT = 0x0001;

    public static final int TOP = 0x0010;

    public static final int RIGHT = 0x0100;

    public static final int BOTTOM = 0x1000;
    /**
     * 背景颜色
     */
    private int backgroundColor;
    /**
     * 阴影颜色
     */
    private int shadowColor;
    /**
     * 阴影半径
     */
    private float shadowRadius;
    /**
     * 阴影x偏移
     */
    private float shadowDx;
    /**
     * 阴影y偏移
     */
    private float shadowDy;

    /**
     * 阴影边
     */
    private int shadowSide;

    /**
     * 圆角半径
     */
    private float radius;
    /**
     * 圆角左上角半径
     */
    private float topLeftRadius;
    /**
     * 圆角右上角半径
     */
    private float topRightRadius;
    /**
     * 圆角左下角半径
     */
    private float bottomLeftRadius;
    /**
     * 圆角右下角半径
     */
    private float bottomRightRadius;

    private Context context;

    private Paint mPaint = new Paint();

    private Path path = new Path();

    private RectF mRectF = new RectF();

    private float[] mRadii = new float[8];

    public ShadowDrawable() {
        backgroundColor = Color.TRANSPARENT;
        shadowColor = Color.TRANSPARENT;
        shadowRadius = 0;
        shadowDx = 0;
        shadowDy = 0;
        shadowSide = ALL;
        radius = 0;
        topLeftRadius = 0;
        topRightRadius = 0;
        bottomLeftRadius = 0;
        bottomRightRadius = 0;
    }

    public ShadowDrawable(Context context) {
        this.context = context;
        backgroundColor = Color.TRANSPARENT;
        shadowColor = Color.TRANSPARENT;
        shadowRadius = 0;
        shadowDx = 0;
        shadowDy = 0;
        shadowSide = ALL;
        radius = 0;
        topLeftRadius = 0;
        topRightRadius = 0;
        bottomLeftRadius = 0;
        bottomRightRadius = 0;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        int shadowOffset = getShadowOffset();
        if (bounds.right - bounds.left > 0 && bounds.bottom - bounds.top > 0) {
            int left = (shadowSide & LEFT) == LEFT ? shadowOffset : 0;
            int top = (shadowSide & TOP) == TOP ? shadowOffset : 0;
            int right = ((bounds.right - bounds.left) - ((shadowSide & RIGHT) == RIGHT ? shadowOffset : 0));
            int bottom = ((bounds.bottom - bounds.top) - ((shadowSide & BOTTOM) == BOTTOM ? shadowOffset : 0));
            mRectF = new RectF(left, top, right, bottom);
            invalidateSelf();
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        setUpShadowPaint();
        mRadii[0] = mRadii[1] = topLeftRadius;
        mRadii[2] = mRadii[3] = topRightRadius;
        mRadii[4] = mRadii[5] = bottomRightRadius;
        mRadii[6] = mRadii[7] = bottomLeftRadius;
        path.addRoundRect(mRectF, mRadii, Path.Direction.CW);
        canvas.drawPath(path, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    private void setUpShadowPaint() {
        mPaint.reset();
        mPaint.setFilterBitmap(true);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(backgroundColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
    }

    private int getShadowOffset() {
        return 0 >= shadowRadius ? 0 : (int) (Math.max(shadowDx, shadowDy) + shadowRadius);
    }

    public ShadowDrawable setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public ShadowDrawable setBackgroundColorId(int backgroundColorId) {
        this.backgroundColor = context.getResources().getColor(backgroundColorId);
        return this;
    }

    public ShadowDrawable setBackgroundColorId(Context context, int backgroundColorId) {
        this.backgroundColor = context.getResources().getColor(backgroundColorId);
        return this;
    }

    public ShadowDrawable setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
        this.backgroundColor = shadowColor;
        return this;
    }

    public ShadowDrawable setShadowColorId(int shadowColorId) {
        this.shadowColor = context.getResources().getColor(shadowColorId);
        this.backgroundColor = context.getResources().getColor(shadowColorId);
        return this;
    }

    public ShadowDrawable setShadowColorId(Context context, int shadowColorId) {
        this.shadowColor = context.getResources().getColor(shadowColorId);
        this.backgroundColor = context.getResources().getColor(shadowColorId);
        return this;
    }

    public ShadowDrawable setShadowRadius(float shadowRadius) {
        this.shadowRadius = shadowRadius;
        return this;
    }

    public ShadowDrawable setShadowDx(float shadowDx) {
        this.shadowDx = shadowDx;
        return this;
    }

    public ShadowDrawable setShadowDy(float shadowDy) {
        this.shadowDy = shadowDy;
        return this;
    }

    public ShadowDrawable setShadowSide(int shadowSide) {
        this.shadowSide = shadowSide;
        return this;
    }

    public ShadowDrawable setRadius(float radius) {
        this.radius = radius;
        topLeftRadius = this.radius;
        topRightRadius = this.radius;
        bottomLeftRadius = this.radius;
        bottomRightRadius = this.radius;
        return this;
    }

    public ShadowDrawable setTopLeftRadius(float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
        return this;
    }

    public ShadowDrawable setTopRightRadius(float topRightRadius) {
        this.topRightRadius = topRightRadius;
        return this;
    }

    public ShadowDrawable setBottomLeftRadius(float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        return this;
    }

    public ShadowDrawable setBottomRightRadius(float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
        return this;
    }

    public ShadowDrawable into(@NonNull View view){
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, this);
        return this;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
}


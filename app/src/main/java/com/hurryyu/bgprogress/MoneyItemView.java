package com.hurryyu.bgprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

public class MoneyItemView extends View {

    private Context mContext;

    private final int DEFAULT_LEFT_TEXT_COLOR = Color.BLACK;
    private final int DEFAULT_RIGHT_TEXT_COLOR = Color.BLACK;
    private final int DEFAULT_PROGRESS_BACKGROUND_COLOR = Color.RED;
    private final int DEFAULT_LEFT_TEXT_SIZE = (int) dp2px(14F);
    private final int DEFAULT_RIGHT_TEXT_SIZE = (int) dp2px(14F);
    private final float DEFAULT_PROGRESS_PERCENT = 0;
    private final int DEFAULT_LEFT_TEXT_MARGIN_LEFT = 0;
    private final int DEFAULT_RIGHT_TEXT_MARGIN_RIGHT = 0;

    private int mLeftTextColor = DEFAULT_LEFT_TEXT_COLOR;
    private int mLeftTextSize = DEFAULT_LEFT_TEXT_SIZE;
    private int mRightTextColor = DEFAULT_RIGHT_TEXT_COLOR;
    private int mRightTextSize = DEFAULT_RIGHT_TEXT_SIZE;
    private int mLeftTextMarginLeft = DEFAULT_LEFT_TEXT_MARGIN_LEFT;
    private int mRightTextMarginRight = DEFAULT_RIGHT_TEXT_MARGIN_RIGHT;
    private String mLeftText;
    private String mRightText;

    private int mProgressBackgroundColor = DEFAULT_PROGRESS_BACKGROUND_COLOR;
    private float mProgressPercent = DEFAULT_PROGRESS_PERCENT;

    private Paint mLeftTextPaint;
    private Paint mRightPaint;
    private Paint mBackgroundPaint;

    private RectF bgRectF = new RectF();

    private int mWidth;
    private int mHeight;

    public MoneyItemView(Context context) {
        this(context, null);
    }

    public MoneyItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoneyItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void init() {
        mLeftTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLeftTextPaint.setColor(mLeftTextColor);
        mLeftTextPaint.setTextSize(mLeftTextSize);

        mRightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRightPaint.setColor(mRightTextColor);
        mRightPaint.setTextSize(mRightTextSize);

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(mProgressBackgroundColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(measureWidth(widthSize, widthMode), measureHeight(heightSize, heightMode));
    }

    private int measureWidth(int specSize, int mode) {
        int size = specSize;
        if (mode == MeasureSpec.AT_MOST) {
            float totalWidth;
            totalWidth = mRightPaint.measureText(mRightText) +
                    mLeftTextPaint.measureText(mLeftText) +
                    mLeftTextMarginLeft +
                    mRightTextMarginRight +
                    dp2px(10);
            size = (int) totalWidth;
        }
        return size;
    }

    private int measureHeight(int specSize, int mode) {
        int size = specSize;
        if (mode == MeasureSpec.AT_MOST) {
            Rect rect = new Rect();
            mRightPaint.getTextBounds(mRightText, 0, mRightText.length(), rect);
            int rightTextHeight = rect.bottom - rect.top;
            mLeftTextPaint.getTextBounds(mLeftText, 0, mLeftText.length(), rect);
            int leftTextHeight = rect.bottom - rect.top;
            size = Math.max(leftTextHeight, rightTextHeight);
        }
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawProgressBackground(canvas);
        drawLeftText(canvas);
        drawRightText(canvas);
    }

    private void drawProgressBackground(Canvas canvas) {
        bgRectF.left = mWidth * (1 - mProgressPercent);
        bgRectF.top = 0;
        bgRectF.right = mWidth;
        bgRectF.bottom = mHeight;
        canvas.drawRect(bgRectF, mBackgroundPaint);
    }

    private void drawRightText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mRightPaint.getFontMetrics();
        float offset = (fontMetrics.top + fontMetrics.bottom) / 2;
        canvas.drawText(mRightText, mWidth - mRightPaint.measureText(mRightText) - mRightTextMarginRight, mHeight / 2.0F - offset, mRightPaint);
    }

    private void drawLeftText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mLeftTextPaint.getFontMetrics();
        float offset = (fontMetrics.top + fontMetrics.bottom) / 2;
        canvas.drawText(mLeftText, mLeftTextMarginLeft, mHeight / 2.0F - offset, mLeftTextPaint);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MoneyItemView);
        mLeftTextColor = typedArray.getColor(R.styleable.MoneyItemView_left_text_color, DEFAULT_LEFT_TEXT_COLOR);
        mRightTextColor = typedArray.getColor(R.styleable.MoneyItemView_right_text_color, DEFAULT_RIGHT_TEXT_COLOR);
        mLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.MoneyItemView_left_text_size, DEFAULT_LEFT_TEXT_SIZE);
        mRightTextSize = typedArray.getDimensionPixelSize(R.styleable.MoneyItemView_right_text_size, DEFAULT_RIGHT_TEXT_SIZE);
        mProgressBackgroundColor = typedArray.getColor(R.styleable.MoneyItemView_process_background_color, DEFAULT_PROGRESS_BACKGROUND_COLOR);
        mProgressPercent = typedArray.getFloat(R.styleable.MoneyItemView_process_percent, DEFAULT_PROGRESS_PERCENT);
        mLeftText = typedArray.getString(R.styleable.MoneyItemView_left_text);
        mRightText = typedArray.getString(R.styleable.MoneyItemView_right_text);
        mLeftTextMarginLeft = typedArray.getDimensionPixelSize(R.styleable.MoneyItemView_left_text_margin_left, DEFAULT_LEFT_TEXT_MARGIN_LEFT);
        mRightTextMarginRight = typedArray.getDimensionPixelSize(R.styleable.MoneyItemView_right_text_margin_right, DEFAULT_RIGHT_TEXT_MARGIN_RIGHT);
        typedArray.recycle();
    }

    public int getLeftTextColor() {
        return mLeftTextColor;
    }

    public void setLeftTextColor(int leftTextColor) {
        mLeftTextColor = leftTextColor;
    }

    public int getLeftTextSize() {
        return mLeftTextSize;
    }

    public void setLeftTextSize(int leftTextSize) {
        mLeftTextSize = leftTextSize;
    }

    public int getRightTextColor() {
        return mRightTextColor;
    }

    public void setRightTextColor(int rightTextColor) {
        mRightTextColor = rightTextColor;
    }

    public int getRightTextSize() {
        return mRightTextSize;
    }

    public void setRightTextSize(int rightTextSize) {
        mRightTextSize = rightTextSize;
    }

    public int getLeftTextMarginLeft() {
        return mLeftTextMarginLeft;
    }

    public void setLeftTextMarginLeft(int leftTextMarginLeft) {
        mLeftTextMarginLeft = leftTextMarginLeft;
    }

    public int getRightTextMarginRight() {
        return mRightTextMarginRight;
    }

    public void setRightTextMarginRight(int rightTextMarginRight) {
        mRightTextMarginRight = rightTextMarginRight;
    }

    public String getLeftText() {
        return mLeftText;
    }

    public void setLeftText(String leftText) {
        mLeftText = leftText;
    }

    public String getRightText() {
        return mRightText;
    }

    public void setRightText(String rightText) {
        mRightText = rightText;
    }

    public int getProgressBackgroundColor() {
        return mProgressBackgroundColor;
    }

    public void setProgressBackgroundColor(int progressBackgroundColor) {
        mProgressBackgroundColor = progressBackgroundColor;
    }

    public float getProgressPercent() {
        return mProgressPercent;
    }

    public void setProgressPercent(float progressPercent) {
        mProgressPercent = progressPercent;
    }

    private float dp2px(float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }
}

package com.example.thinking.newsell.view.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.example.thinking.newsell.R;

import static android.os.Build.VERSION_CODES.M;
import static com.baidu.location.d.j.n;

/**
 * *****************************************
 * Created by thinking on 2017/8/31.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class HorizontalProgressBar extends ProgressBar {

    private static final int DEFAULT_TEXT_COLOR = 0xFFe9735e;
    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_UNREACHED_COLOR = 0xFFd3d6da;
    private static final int DEFAULT_REACHED_COLOR = 0xFFe9735e;
    private static final int DEFAULT_UNREACHED_HEIGHT =5;
    private static final int DEFAULT_REACHED_HEIGHT = 5;
    private static final int DEFAULT_TEXT_OFFSET = 10;


    protected Paint mpaint = new Paint();
    protected int textColor = DEFAULT_TEXT_COLOR;
    protected int textSize = sp2px(DEFAULT_TEXT_SIZE);
    protected int unreachedColor = DEFAULT_UNREACHED_COLOR;
    protected int reachedColor = DEFAULT_REACHED_COLOR;
    protected int unreachedHeight = dp2px(DEFAULT_UNREACHED_HEIGHT);
    protected int reachedHeight = dp2px(DEFAULT_REACHED_HEIGHT);
    protected int textOffset = dp2px(DEFAULT_TEXT_OFFSET);

    protected static final int VISIBLE = 0;
    protected boolean mIfDrawText = true;
    protected int mRealWidth;

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(attrs);
        mpaint.setTextSize(textSize);
        mpaint.setColor(textColor);
    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
        mRealWidth=getMeasuredWidth()-getPaddingLeft()-getPaddingRight();
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY)
        {
            result = specSize;
        } else
        {
            float textHeight = (mpaint.descent() - mpaint.ascent());
            result = (int) (getPaddingTop() + getPaddingBottom() +
                    Math.max(Math.max(reachedHeight, unreachedHeight), Math.abs(textHeight)));
            if (specMode == MeasureSpec.AT_MOST)
            {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private void obtainStyledAttributes(AttributeSet attributeSet) {
        final TypedArray typedArray = getResources().obtainAttributes(attributeSet, R.styleable.Horizontal_progressbar);

        textColor = typedArray.getColor(R.styleable.Horizontal_progressbar_text_color, DEFAULT_TEXT_COLOR);
        textSize = (int) typedArray.getDimension(R.styleable.Horizontal_progressbar_text_size, textSize);
        unreachedColor = typedArray.getColor(R.styleable.Horizontal_progressbar_process_unreached_color, DEFAULT_UNREACHED_COLOR);
        reachedColor = typedArray.getColor(R.styleable.Horizontal_progressbar_process_reached_color, DEFAULT_REACHED_COLOR);
        unreachedHeight = (int) typedArray.getDimension(R.styleable.Horizontal_progressbar_process_unreached_height, unreachedHeight);
        reachedHeight = (int) typedArray.getDimension(R.styleable.Horizontal_progressbar_process_reached_height, reachedHeight);
        textOffset = (int) typedArray.getDimension(R.styleable.Horizontal_progressbar_process_text_offset, textOffset);

        int textvisible = typedArray.getInt(R.styleable.Horizontal_progressbar_text_visibility, VISIBLE);
        if (textvisible != VISIBLE) {
            mIfDrawText = false;
        }
        typedArray.recycle();
    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);

        boolean noNeedBg = false;
        float radio = getProgress() * 1.0f / getMax();
        float progressPosX = (int) (mRealWidth * radio);
        String text = getProgress() + "%";
        // mPaint.getTextBounds(text, 0, text.length(), mTextBound);

        float textWidth = mpaint.measureText(text);
        float textHeight = (mpaint.descent() + mpaint.ascent()) / 2;

        if (progressPosX + textWidth > mRealWidth)
        {
            progressPosX = mRealWidth - textWidth;
            noNeedBg = true;
        }
        // draw reached bar
        float endX = progressPosX - textOffset / 2;
        if (endX > 0)
        {
            mpaint.setColor(reachedColor);
            mpaint.setStrokeWidth(reachedHeight);
            canvas.drawLine(0, 0, endX, 0, mpaint);
        }
        // draw progress bar
        // measure text bound
        if (mIfDrawText)
        {
            mpaint.setColor(textColor);
            canvas.drawText(text, progressPosX, -textHeight, mpaint);
        }
        // draw unreached bar
        if (!noNeedBg)
        {
            float start = progressPosX + textOffset / 2 + textWidth;
            mpaint.setColor(unreachedColor);
            mpaint.setStrokeWidth(unreachedHeight);
            canvas.drawLine(start, 0, mRealWidth, 0, mpaint);
        }

        canvas.restore();

    }

    protected int dp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spVal, getResources().getDisplayMetrics());
    }

    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());
    }
}

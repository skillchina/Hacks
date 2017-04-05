package club.iandroid.hack50.demo.systemwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by gabriel on 2017/3/31.
 */

public class CircleView extends View {

    private static final String TAG = CircleView.class.getName();
    private int mMeasureWidth;
    private int mMeasureHeigth;
    private int mRadius;
    private float mCircleXY;

    private String text = "test";
    private float textSize = 20;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeigth = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "mMeasureWidth:" + mMeasureWidth + " mMeasureHeigth:" + mMeasureHeigth);

        setMeasuredDimension(mMeasureWidth, mMeasureHeigth);
    }

    private Paint mPaint;
    private Paint mTextPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔为无锯齿
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth((float) 3.0);
        mPaint.setStyle(Paint.Style.STROKE);//空心

        mTextPaint = new Paint();
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        float length = 0;
        if (mMeasureHeigth > mMeasureWidth) {
            length = mMeasureWidth;
        } else {
            length = mMeasureHeigth;
        }

        mCircleXY = length / 2;

        mRadius = (int) (length * 0.5 / 2);
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mPaint);

        int textWidth = getTextWidth(mTextPaint, text);
        canvas.drawText(text, 0, text.length(), mCircleXY, mCircleXY, mTextPaint);

        canvas.drawText(text, 0, text.length(), mCircleXY+mRadius-textWidth/2, mCircleXY, mTextPaint);
    }

    public int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }
}

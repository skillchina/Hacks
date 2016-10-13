package club.iandroid.hack50.subject1;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import club.iandroid.hack50.R;

/**
 * Created by Administrator on 2016/10/13.
 */

public class CascadeLayout extends ViewGroup {
    private int mHorizonTalSpacing;
    private int mVerticalSpacing;


    public CascadeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CascadeLayout);
        mHorizonTalSpacing = typedArray.getDimensionPixelSize(
                R.styleable.CascadeLayout_horizontal_spacing,
                getResources().getDimensionPixelSize(
                        R.dimen.cascade_horizontal_spacing
                )
        );

        mVerticalSpacing = typedArray.getDimensionPixelSize(
                R.styleable.CascadeLayout_vertical_spacing,
                getResources().getDimensionPixelSize(
                        R.dimen.cascade_vertical_spacing
                )
        );

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //使用宽、高计算布局的最终大小以及子视图的x与y轴位置
        int width = 0;
        int height = getPaddingTop();

        //令每个子视图测量自己
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            CascaLayoutParams lp = (CascaLayoutParams) child.getLayoutParams();
            int verticalSpacing = mVerticalSpacing;
            if (lp.verticalSpacing >= 0) {
                verticalSpacing = lp.verticalSpacing;
            }

            width = getPaddingLeft() + mHorizonTalSpacing * i;

            //在自定义LayoutParams中保存每个子视图的x和y坐标
            lp.x = width;
            lp.y = height;

            width += child.getMeasuredWidth();
            height += verticalSpacing;
        }

        width += getPaddingRight();
        height += getChildAt(getChildCount() - 1).getMeasuredHeight() + getPaddingBottom();

        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            CascaLayoutParams lp = (CascaLayoutParams) child.getLayoutParams();
            child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(), lp.y + child.getMeasuredHeight());
        }
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof CascaLayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CascaLayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CascaLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new CascaLayoutParams(p.width, p.height);
    }
}

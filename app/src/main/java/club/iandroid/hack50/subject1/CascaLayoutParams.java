package club.iandroid.hack50.subject1;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

import club.iandroid.hack50.R;

/**
 * Created by Administrator on 2016/10/13.
 */

public class CascaLayoutParams extends ViewGroup.LayoutParams {
    int x;
    int y;
    int verticalSpacing;

    public CascaLayoutParams(Context c, AttributeSet attrs) {
        super(c, attrs);
        TypedArray typedArray = c.obtainStyledAttributes(R.styleable.CascadeLayout_LayoutParams);
        verticalSpacing = typedArray.getDimensionPixelSize(R.styleable.CascadeLayout_LayoutParams_layout_vertical_spacing, -1);
        typedArray.recycle();
    }

    public CascaLayoutParams(int width, int height) {
        super(width, height);

    }
}

package club.iandroid.hack50;

import android.view.View;

/**
 * Created by Administrator on 2016/10/9.
 */

public class Utils {

    /**
     * 获取宽高
     * @param view
     * @return
     */
    public static int[] getWidthAndHeight(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }
}

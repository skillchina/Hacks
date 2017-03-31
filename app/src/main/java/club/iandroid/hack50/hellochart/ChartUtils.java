package club.iandroid.hack50.hellochart;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;

public abstract class ChartUtils {

    public static final int COLOR_RED = Color.parseColor("#ED4151");
    public static final int COLOR_ORANGE = Color.parseColor("#F89D44");
    public static final int COLOR_YELLOW = Color.parseColor("#FEBF4B");
    public static final int COLOR_GREEN = Color.parseColor("#85C441");
    public static final int COLOR_GREEN_DARK = Color.parseColor("#34BD93");
    public static final int COLOR_BLUE_SKY = Color.parseColor("#36B3E3");
    public static final int COLOR_BLUE_DARK = Color.parseColor("#1E95A9");
    public static final int COLOR_BLUE = Color.parseColor("#EF6BA5");
    public static final int COLOR_PURPLE = Color.parseColor("#BF5CA4");
    public static final int COLOR_PURPLE_DARK = Color.parseColor("#9A3267");

    public static final int DEFAULT_COLOR = Color.parseColor("#DFDFDF");
    public static final int DEFAULT_DARKEN_COLOR = Color.parseColor("#DDDDDD");

    public static final int[] COLORS = new int[]{
            COLOR_RED,
            COLOR_ORANGE,
            COLOR_YELLOW,
            COLOR_GREEN,
            COLOR_GREEN_DARK,
            COLOR_BLUE_SKY,
            COLOR_BLUE_DARK,
            COLOR_BLUE,
            COLOR_PURPLE,
            COLOR_PURPLE_DARK
    };
    private static final float DARKEN_SATURATION = 1.1f;
    private static final float DARKEN_INTENSITY = 0.9f;
    private static int COLOR_INDEX = 0;

    public static final int pickColor() {
        return COLORS[(int) Math.round(Math.random() * (COLORS.length - 1))];
    }

    public static final int nextColor() {
        if (COLOR_INDEX >= COLORS.length) {
            COLOR_INDEX = 0;
        }
        return COLORS[COLOR_INDEX++];
    }

    public static int dp2px(float density, int dp) {
        if (dp == 0) {
            return 0;
        }
        return (int) (dp * density + 0.5f);

    }

    public static int px2dp(float density, int px) {
        return (int) Math.ceil(px / density);
    }

    public static int sp2px(float scaledDensity, int sp) {
        if (sp == 0) {
            return 0;
        }
        return (int) (sp * scaledDensity + 0.5f);
    }

    public static int px2sp(float scaledDensity, int px) {
        return (int) Math.ceil(px / scaledDensity);
    }

    public static int mm2px(Context context, int mm) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mm, context.getResources()
                .getDisplayMetrics()) + 0.5f);
    }

    public static int darkenColor(int color) {
        float[] hsv = new float[3];
        int alpha = Color.alpha(color);
        Color.colorToHSV(color, hsv);
        hsv[1] = Math.min(hsv[1] * DARKEN_SATURATION, 1.0f);
        hsv[2] = hsv[2] * DARKEN_INTENSITY;
        int tempColor = Color.HSVToColor(hsv);
        return Color.argb(alpha, Color.red(tempColor), Color.green(tempColor), Color.blue(tempColor));
    }

}

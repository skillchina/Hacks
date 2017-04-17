package club.iandroid.hack50.collection.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jiaronggeng on 15/7/pic4.
 */
public class MMarqueeTextView extends TextView {
    public MMarqueeTextView(Context context) {
        super(context);
    }

    public MMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MMarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}

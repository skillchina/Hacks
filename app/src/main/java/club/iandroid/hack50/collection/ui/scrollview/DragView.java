package club.iandroid.hack50.collection.ui.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 在View进行绘制时，会调用onLayout()方法来设置显示的位置。
 * 同样，可以通过修改View的left,top,right,bottom四个属性来控制view的坐标。
 *
 * 在每次回调onTouchEvent的时候，我们都来获取一下触摸点的坐标
 * int x = (int)event.getX();
 * int y = (int)event.getY();
 *
 * 接着在ACTION_DOWN事件中记录触摸点的坐标
 * case MotionEvent.ACTION_DOWN:
 * lastX = x;
 * lastY = y;
 * break;
 *
 * 最后，可以在ACTION_MOVE事件中计算偏移量，并将偏移量作用到Layout方法中，
 * 在目前Layout的left,top,right,bottom基础上，增加计算出来的偏移量
 *
 * offsetLeftAndRight()与offsetTopAndBottom()
 * 这个方法相当于系统提供的一个对左右，上下移动的API的封装。
 * 当计算出偏移量后，只需要使用如下代码就可以完成View的重新布局，效果与使用Layout方法一样，代码如下所示：
 *
 * 同时对left和right进行偏移
 * offsetLeftAndRight(offsetX)
 * 同时对Top和bottom进行偏移
 * offsetTopAndBottom(offsetY)
 *
 * 另外可以通过LayoutParams来动态地修改一个布局的位置参数，从而达到改变View位置的效果
 * 使用getLayoutParams()来获取一个View的LayoutParams
 * 计算到偏移量offset，就可以通过setLayoutParams()来改变其LayoutParams
 * 代码如下所示
 *
 *  LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)getLayoutParams();
 *  layoutParams.leftMargin = getLeft() + offsetX;
 *  layoutParams.topMargin = getTop() + offsetY;
 *  setLayoutParams(layoutParams);
 *
 * Created by haoxi on 2016/1/22.
 */
public class DragView extends View {

    private int lastX;
    private int lastY;


    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 使用getX()和getY()来获取位置 视图坐标
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //每次都获取下触摸点的坐标
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (event.getAction()){
            //接着在ACTION_DOWN事件中记录触摸点的坐标
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            //最后，可以在ACTION_MOVE事件中计算偏移量，并将偏移量作用到Layout方法中
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //在当前left, top, right, bottom 的基础上加上偏移量
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);

                //这样每次移动后，View都会调用Layout方法来对自己重新布局，从而达到移动View的效果。

                //方法二 另外可以通过LayoutParams来动态地修改一个布局的位置参数，从而达到改变View位置的效果
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                /**
                 * 不过需要注意的是，通过getLayoutParams()获取LayoutParams时，需要根据View所在父布局的类型来设置不同的类型。
                 * 比如这里讲View放在LinearLayout中，那么久可以使用 LinearLayout.LayoutParams
                 *
                 * 类似地，如果在RelativeLayout中，如果在RelativeLayout中.LayoutParams 。
                 * 当然这一切的前提是你必须要有一个父布局，不然系统无法获取LayoutParams.
                 */


                /**
                 * 在通过改变LayoutParams来改变一个View的位置时，通常改变的是这个View的Margin属性，所以除了使用布局的LayoutParams之外，
                 * 还可以使用ViewGroup.MarginLayoutParams来实现这样一个功能：代码如下：
                 */
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                //我们可以发现，使用ViewGroup.MarginLayoutParams 更加的方便，
                // 不需要考虑父布局的类型，当然它们的本质都是一样的。

                //scrollTo:以滚动视图起始位置开始计算的
                //scrollBy:相对前一次的位置，去滚动对应的位置
                /**
                 * scrollTo scrollBy移动的是View的content,即让View的内容移动，如果在ViewGruop中使用scrollTo/scrollBy方法
                 * 那么移动的是所有子View，蛋如果在View中使用，那么移动的将是View的内容
                 * 例如TextView，content就是它的文本，
                 * ImageView content就是它的drawable对象
                 *
                 */

//                ((View)getParent()).scrollBy(offsetX, offsetY);
                break;
        }
        return true;
    }


    /**
     * 绝对坐标
     * 同样可以使用getRawX()和getRawY()来获取位置
     * @param event
     * @return
     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //每次都获取下触摸点的坐标
//        int rawX = (int)event.getRawX();
//        int rawY = (int)event.getRawY();
//
//        switch (event.getAction()){
//            //接着在ACTION_DOWN事件中记录触摸点的坐标
//            case MotionEvent.ACTION_DOWN:
//                lastX = rawX;
//                lastY = rawY;
//                break;
//            //最后，可以在ACTION_MOVE事件中计算偏移量，并将偏移量作用到Layout方法中
//            case MotionEvent.ACTION_MOVE:
//                //计算偏移量
//                int offsetX = rawX - lastX;
//                int offsetY = rawY  - lastY;
//                //在当前left, top, right, bottom 的基础上加上偏移量
//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);
//                //这样每次移动后，View都会调用Layout方法来对自己重新布局，从而达到移动View的效果。
//                //但是有个特殊，就是每次执行完以上逻辑后，一定要重新设置初始坐标，这样才能准确地获取偏移量
//                lastX = rawX;
//                lastY = rawY;
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
}

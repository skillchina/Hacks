package club.iandroid.hack50.collection.ui.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mydemo.R;

/**
 * 事件拦截分析
 * 事件的传递顺序是ViewGroupA(总经理)->ViewGropuB(部长)->View（你）
 * 事件传递的时候，先执行disptchTouchEvent()方法，再执行onIntercepTouchEvent()方法
 *
 * 事件处理的顺序是：View（你）->ViewGroupB（部长）->ViewGropuA（总经理）
 * 事件处理都是执行onTouchEvent()方法
 *
 * 事件传递的返回值非常容易理解：True，拦截，不继续。  False，拦截，继续流程
 * 事件处理的返回值也类似：True ，处理了，不用审核了。 False，给上级处理。
 *
 * 在事件传递中，我们只关心onInterceptTouchEvent()方法，而dispatchTouchEvent()方法虽然是事件分发的第一步，
 * 但一般情况下，我们不太会去改写这个方法，所以暂时不管这个方法。
 */
public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
    }
}

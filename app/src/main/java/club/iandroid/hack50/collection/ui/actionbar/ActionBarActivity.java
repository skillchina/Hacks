package club.iandroid.hack50.collection.ui.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mydemo.R;

/**
 * android3.0版本，配置中目标版本应该高于11
 * ActionBar提供如下功能：
 * pic1、显示选项菜单的菜单项（将菜单项显示成Action Item）
 * pic2、使用程序图标作为返回Home主屏或向上的导航操作
 * pic3、提供交互式View作为Action View
 * pic4、提供基于Tab的导航方式，可用于切换多个Fragment
 * pic5、提供基于下拉的导航方式。
 * 如果希望关闭ActionBar 设置如下：android:theme="@android:style/Theme.Holo.NoActionBar"
 * 实际项目中，推荐使用代码来控制ActionBar的显示、隐藏，
 * show()  hide()
 *
 * 显示选项菜单
 * ActionBar可以将选项菜单显示成ActionItem
 * 从Android3.0开始，MenuItem新增了如下方法：
 * setShowAsAction(int actionEnum):该方法设置是否将该菜单项显示在ActionBar上，作为ActionItem
 * 该方法支持如下参数值
 * pic1、SHOW_AS_ACTION_ALWAYS:总是将该MenuItem显示在ActionBar上
 * pic2、SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW:将该Action View折叠成普通菜单项
 * pic3、SHOW_AS_ACTION_IF_ROOM:当ActionBar位置足够时才显示MenuItem
 * pic4、SHOW_AS_ACTION_NEVER:不将该MenuItem显示在ActionBar上
 * pic5、SHOW_AS_ACTION_WITH_TEXT:将该MenuItem显示在ActionBar上，并显示该菜单项的文本
 */
public class ActionBarActivity extends Activity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_action_bar);

        actionBar = getActionBar();

        ((Button)findViewById(R.id.btn_show)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.show();
            }
        });

        ((Button)findViewById(R.id.btn_hide)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.hide();
            }
        });
    }

}

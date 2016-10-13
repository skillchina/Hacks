package club.iandroid.hack50.subject1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import club.iandroid.hack50.R;

/**
 * Hack3 创建定制的ViewGroup
 * @author jiarong
 * @time 2016/10/13 13:57
 *
 * 绘制布局由两个遍历过程组成：测量过程 和 布局过程
 * 测量过程由measure(int, int)方法完成，该方法从上到下遍历视图，在递归遍历过程中，每个视图都会向下层传递尺寸和规格。
 * 当measure方法遍历结束，每个视图都保存了各自的尺寸信息。
 *
 * 布局过程由layout(int, int, int ,int)方法完成，该方法也是由上而下遍历视图树，
 * 在遍历过程中每个父视图通过测量过程的结果定位所有子视图的位置信息
 */
public class ViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
    }
}

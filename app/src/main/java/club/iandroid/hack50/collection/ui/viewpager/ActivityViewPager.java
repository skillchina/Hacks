package club.iandroid.hack50.collection.ui.viewpager;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mydemo.R;
import com.example.mydemo.adapter.MyFragmentPagerAdpater2;
import com.example.mydemo.ui.mfragment.Fragment1;
import com.example.mydemo.ui.mfragment.Fragment2;

import java.util.ArrayList;
import java.util.List;


public class ActivityViewPager extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private List<View> viewList;
    private List<String> titleList;
    private ViewPager viewPager;
    private PagerTabStrip tab;


    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_view_pager);

        viewList = new ArrayList<View>();

        View viewpager_view1 = View.inflate(this, R.layout.viewpage_view1, null);
        View viewpager_view2 = View.inflate(this, R.layout.viewpage_view2, null);
        View viewpager_view3 = View.inflate(this, R.layout.viewpage_view3, null);
        View viewpager_view4 = View.inflate(this, R.layout.viewpage_view4, null);

        viewList.add(viewpager_view1);
        viewList.add(viewpager_view2);
        viewList.add(viewpager_view3);
        viewList.add(viewpager_view4);

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());

        //未ViewPager页卡设置标题
        titleList = new ArrayList<String>();
        titleList.add("微信");
        titleList.add("通讯录");
        //titleList.add("发现");
        //titleList.add("我");

        tab = (PagerTabStrip)findViewById(R.id.mtab);
        tab.setBackgroundColor(Color.GREEN);
        tab.setTextColor(Color.WHITE);
        tab.setDrawFullUnderline(false);
        tab.setTabIndicatorColor(Color.YELLOW);

        viewPager = (ViewPager)findViewById(R.id.mviewpager);
        //创建PagerAdapter的适配器
        //MyPagerAdapter myPagerAdapter = new MyPagerAdapter(viewList, titleList);
        //viewPager.setAdapter(myPagerAdapter);

        /*MyFragmentPagerAdpater fragmentPagerAdpater = new MyFragmentPagerAdpater(fragmentList,
                titleList, getSupportFragmentManager());
        */

        MyFragmentPagerAdpater2 fragmentPagerAdpater = new MyFragmentPagerAdpater2(fragmentList,
                titleList, getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdpater);

        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "当前是第"+position+"个界面",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

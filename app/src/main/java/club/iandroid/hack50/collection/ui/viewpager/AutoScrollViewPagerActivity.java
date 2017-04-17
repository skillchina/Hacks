package club.iandroid.hack50.collection.ui.viewpager;

import android.app.Activity;


public class AutoScrollViewPagerActivity extends Activity {

//    private AutoScrollViewPager view_pager;
//    private List<String> imageIdList;
//    private TextView view_pager_index;
//    private Context context;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_auto_scroll_view_pager);
//        context = this;
//
//        view_pager = (AutoScrollViewPager)findViewById(R.id.view_pager);
//        view_pager_index = (TextView)findViewById(R.id.view_pager_index);
//        imageIdList = new ArrayList<String>();
//        imageIdList.add("http://m.360buyimg.com/mobilecms/s480x180_jfs/t2299/341/427646184/42646/7cee89f5/560a5505Nb64f5f02.jpg");
//        imageIdList.add("http://m.360buyimg.com/mobilecms/s480x180_jfs/t2326/14/437635747/74619/f9c22230/560a531fN9f5f8fd4.jpg");
//        imageIdList.add("http://m.360buyimg.com/mobilecms/s480x180_jfs/t2323/163/444556218/148842/860b83ea/560b5b63Nba8b3ab5.jpg");
//
//        view_pager.setAdapter(new ImagePagerAdapter(context, imageIdList).setInfiniteLoop(true));
//        view_pager.setOnPageChangeListener(new MyOnPageChangeListener());
//
//        view_pager.setInterval(2000);
//        view_pager.startAutoScroll();
//        view_pager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % ListUtils.getSize(imageIdList));
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        view_pager.stopAutoScroll();
//    }
//
//    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
//
//        @Override
//        public void onPageSelected(int position) {
//            view_pager_index.setText(new StringBuilder().append((position) % ListUtils.getSize(imageIdList) + 1).append("/")
//                    .append(ListUtils.getSize(imageIdList)));
//        }
//
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
//
//        @Override
//        public void onPageScrollStateChanged(int arg0) {}
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // start auto scroll when onResume
//        view_pager.startAutoScroll();
//    }
}

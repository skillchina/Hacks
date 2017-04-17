package club.iandroid.hack50.collection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.bean.NewsBean;
import com.example.mydemo.util.ImageLoader;

import java.util.List;

/**
 * Created by gabriel on 15/7/27.
 */
public class MListViewAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private List<NewsBean> mList;
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    private int mStart;
    private int mEnd;
    public static String[] URLS;
    private boolean isfirstIn;

    public void loadImages(int start, int end){
        for(int i=start;i<end;i++){
            String url = MListViewAdapter.URLS[i];
        }
    }

    public MListViewAdapter(Context context, List<NewsBean> mList, ListView listView){
        this.mList = mList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        imageLoader = new ImageLoader(listView);
        URLS = new String[mList.size()];
        for(int i=0;i<mList.size();i++){
            URLS[i] = mList.get(i).getImgUrl();
        }
        isfirstIn = true;
        listView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.mlistview_item, null);
            viewHolder.img = (ImageView)convertView.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.tvContent = (TextView)convertView.findViewById(R.id.tvContent);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.img.setImageResource(R.drawable.ic_launcher);
        String url = mList.get(position).getImgUrl();
        viewHolder.img.setTag(url);
        //new ImageLoader().showImageByThread(viewHolder.img, url);
        imageLoader.showImageByAsyncTask(viewHolder.img, url);
        viewHolder.tvTitle.setText(mList.get(position).getTitle());
        viewHolder.tvContent.setText(mList.get(position).getContent());
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState == SCROLL_STATE_IDLE){
            //加载可见项
            imageLoader.loadImageView(mStart,mEnd);
        }else{
            //停止任务
            imageLoader.cancelAllTask();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem+visibleItemCount;
        if(isfirstIn && visibleItemCount>0){
            imageLoader.loadImageView(mStart, mEnd);
            isfirstIn = false;
        }
    }

    public class ViewHolder{
        private ImageView img;
        private TextView tvTitle;
        private TextView tvContent;

    }
}

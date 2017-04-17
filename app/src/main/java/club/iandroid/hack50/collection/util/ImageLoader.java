package club.iandroid.hack50.collection.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mydemo.R;
import com.example.mydemo.adapter.MListViewAdapter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gabriel on 15/7/27.
 */
public class ImageLoader {

    private ImageView mImg;
    private String murl;
    //创建Cache
    private LruCache<String, Bitmap> mCaches;
    private ListView mListView;
    private Set<NewsAsyncTask> mTask;


    public ImageLoader(ListView listView){
        this.mListView = listView;
        mTask = new HashSet<NewsAsyncTask>();
        //获取最大可用内存
        int maxMemory = (int)Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mCaches = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mImg.getTag().equals(murl)){
                mImg.setImageBitmap((Bitmap) msg.obj);
            }

        }
    };

    //增加到缓存
    public void addBitMapToCache(String url, Bitmap value){
        if(getBitMapFromCache(url) == null) {
            mCaches.put(url, value);
        }
    }

    //从缓存中获取数据
    public Bitmap getBitMapFromCache(String url){
        return mCaches.get(url);
    }

    public void showImageByThread(ImageView imageView, final String url){
        mImg = imageView;
        murl = url;
        new Thread(){
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromUrl(url);
                Message msg = new Message();
                msg.obj = bitmap;
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    public Bitmap getBitmapFromUrl(String urlString){
        Bitmap bitmap;
        InputStream is=null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();;
            Thread.sleep(100);
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        return  null;
    }

    public void cancelAllTask(){
        if(mTask!=null){
            for(NewsAsyncTask task : mTask){
                task.cancel(false);
            }
        }
    }

    public void loadImageView(int start, int end){
        for(int i=start;i<end;i++){
            String url = MListViewAdapter.URLS[i];

            //从缓存中取出对应的图片
            Bitmap bitmap = getBitMapFromCache(url);
            //如果缓存中没有，那么必须去下载
            if(bitmap == null){
                NewsAsyncTask task = new NewsAsyncTask(url);
                task.execute(url);
                mTask.add(task);
            }else{
                ImageView imgView = (ImageView)mListView.findViewWithTag(url);
                imgView.setImageBitmap(bitmap);
            }
        }
    }

    /*
    public void showImageByAsyncTask(ImageView imageView, String url){
        //从缓存中取出对应的图片
        Bitmap bitmap = getBitMapFromCache(url);
        //如果缓存中没有，那么必须去下载
        if(bitmap == null){
            new NewsAsyncTask(imageView, url).execute(url);
        }else{
            imageView.setImageBitmap(bitmap);
        }

    }*/

    public void showImageByAsyncTask(ImageView imageView, String url){
        //从缓存中取出对应的图片
        Bitmap bitmap = getBitMapFromCache(url);
        //如果缓存中没有，那么必须去下载
        if(bitmap == null){
            imageView.setImageResource(R.drawable.ic_launcher);
        }else{
            imageView.setImageBitmap(bitmap);
        }

    }

    private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap>{

        private String url;
        /*public NewsAsyncTask(ImageView imageView, String url){
            this.mImageView = imageView;
            this.url = url;
        }*/

        public NewsAsyncTask(String url){
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            //从网络获取图片
            Bitmap bitmap = getBitmapFromUrl(url);
            if(bitmap!=null) {
                //将不在缓存的图片 加入缓存
                addBitMapToCache(url, bitmap);
            }
            return bitmap;
        }

        /*
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(mImageView.getTag().equals(url)) {
                mImageView.setImageBitmap(bitmap);
            }
        }*/

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView)mListView.findViewWithTag(url);
            if(imageView!=null && bitmap!=null) {
                imageView.setImageBitmap(bitmap);
            }
            mTask.remove(this);
        }
    }
}

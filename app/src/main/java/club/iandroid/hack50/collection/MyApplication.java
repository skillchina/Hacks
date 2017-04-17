package club.iandroid.hack50.collection;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.mydemo.util.ImageLoaderConfig;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Volley使用
 * 1、Volley的get和post请求方式的使用
 *
 * 2、Volley的网络请求队列建立和取消队列请求
 *
 * 3、Volley与Activity生命周期的联动
 *
 * 4、Volley的简单的二次回调封装
 * Created by gabriel on 15/8/14.
 *
 * ImageCache
 *
 * LruCache
 *
 * ImageLoader
 *
 * ImageRequest
 *
 * NetworkImageView
 *
 * ImageListener
 */
public class MyApplication extends Application {

    public static RequestQueue queues;

    @Override
    public void onCreate() {
        super.onCreate();
        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        ImageLoaderConfig.initImageLoader(this, cacheDir.getAbsolutePath());
        queues = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpRequestQueues(){
        return queues;
    }
}

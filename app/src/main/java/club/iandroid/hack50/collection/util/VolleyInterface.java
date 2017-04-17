package club.iandroid.hack50.collection.util;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by gabriel on 15/8/17.
 */
public abstract class VolleyInterface {
    public Context context;
    public static Response.Listener<String> listener;
    public static Response.ErrorListener errorListener;

    public VolleyInterface(Context context, Response.Listener<String> listener,
                           Response.ErrorListener errorListener){
        this.context = context;
        this.listener = listener;
        this.errorListener = errorListener;
    }

    public abstract void onMySuccess(String result);
    public abstract void onMyError(VolleyError volleyError);

    public Response.Listener<String> loadingListener(){
        this.listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //可以配置 弹出加载中。。。
                onMySuccess(s);
            }
        };
        return listener;
    }

    public Response.ErrorListener errorListener(){
        this.errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyError(volleyError);
                //加载失败处理。。。。
            }
        };
        return this.errorListener;
    }
}

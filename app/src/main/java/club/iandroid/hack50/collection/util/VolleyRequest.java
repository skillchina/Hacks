package club.iandroid.hack50.collection.util;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.mydemo.MyApplication;

import java.util.Map;

/**
 * Created by gabriel on 15/8/17.
 */
public class VolleyRequest {

    public static StringRequest stringRequest;
    public static Context context;

    /**
     * StringRequest Get
     * @param context
     * @param url
     * @param tag
     * @param volleyInterface
     */
    public static void RequestGet(Context context, String url, String tag, VolleyInterface volleyInterface){
        MyApplication.getHttpRequestQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET, url, volleyInterface.loadingListener(),
                volleyInterface.errorListener());
        stringRequest.setTag(tag);
        MyApplication.getHttpRequestQueues().add(stringRequest);
        MyApplication.getHttpRequestQueues().start();

    }

    /**
     * StringRequest
     * @param context
     * @param url
     * @param tag
     * @param params
     * @param volleyInterface
     */
    public static void RequestPost(Context context, String url, String tag,
                                   final Map<String, String> params,
                                   VolleyInterface volleyInterface){
        MyApplication.getHttpRequestQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST, url, volleyInterface.loadingListener(),
                volleyInterface.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        stringRequest.setTag(tag);
        MyApplication.getHttpRequestQueues().add(stringRequest);
        MyApplication.getHttpRequestQueues().start();
    }
}

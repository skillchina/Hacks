package club.iandroid.hack50.collection.volley;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.example.mydemo.MyApplication;
import com.example.mydemo.R;
import com.example.mydemo.util.BitmapCache;
import com.example.mydemo.util.VolleyInterface;
import com.example.mydemo.util.VolleyRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class VolleyActivity extends Activity {

    private Context context;
    private ImageView mImageView;
    private NetworkImageView mNetworkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        //Volley StringRequest Get请求
        ((Button)findViewById(R.id.btnStringRequestGet)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=13857845517";
                /**
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(VolleyActivity.this, s,
                                Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(VolleyActivity.this,volleyError.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });

                request.setTag("strGet");
                MyApplication.getHttpRequestQueues().add(request);
                 */
                VolleyRequest.RequestGet(context, url, "strGet", new VolleyInterface(context,
                        VolleyInterface.listener, VolleyInterface.errorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        Toast.makeText(VolleyActivity.this, result,
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onMyError(VolleyError volleyError) {
                        Toast.makeText(VolleyActivity.this, volleyError.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        ((Button)findViewById(R.id.btnStringRequestPost)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
                StringRequest request = new StringRequest(Method.POST, url, new Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(VolleyActivity.this, s,
                                Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(VolleyActivity.this, volleyError.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("tel","13857845517");
                        return map;
                    }
                };

                request.setTag("strPost");
                MyApplication.getHttpRequestQueues().add(request);
            }
        });

        ((Button)findViewById(R.id.btnJsonObjectRequestGet)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=13857845517";
                JSONObject obj = null;
                JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, "", new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(VolleyActivity.this, jsonObject.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(VolleyActivity.this, volleyError.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                request.setTag("jsonGet");
                MyApplication.getHttpRequestQueues().add(request);
            }
        });

        ((Button)findViewById(R.id.btnJsonObjectRequestPost)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
                JsonObjectRequest request = new JsonObjectRequest(Method.POST, url, new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(VolleyActivity.this, jsonObject.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(VolleyActivity.this, volleyError.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("tel","13857845517");
                        return map;
                    }
                };

                request.setTag("jsonPost");
                MyApplication.getHttpRequestQueues().add(request);
            }
        });

        mImageView = (ImageView)findViewById(R.id.mImageView);
        mNetworkImageView =  (NetworkImageView)findViewById(R.id.mNetworkImageView);
        final String imgUrl = "https://www.baidu.com/img/bdlogo.png";
        ((Button)findViewById(R.id.btnLoadImg)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、
                //MImageRequest(imgUrl);

                //2、
                //MImageLoader(imgUrl);

                //3、
                MNetworkImageView(imgUrl);
            }
        });

    }

    void MNetworkImageView(String imgUrl){
        ImageLoader loader = new ImageLoader(MyApplication.getHttpRequestQueues(),
                new BitmapCache());
        mNetworkImageView.setDefaultImageResId(R.drawable.ic_launcher);
        mNetworkImageView.setErrorImageResId(R.drawable.ic_launcher);
        mNetworkImageView.setImageUrl(imgUrl, loader);
    }

    void MImageLoader(String imgUrl){
        ImageLoader loader = new ImageLoader(MyApplication.getHttpRequestQueues(),
                new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(mImageView,
                R.drawable.ic_launcher,R.drawable.ic_launcher);
        loader.get(imgUrl, listener);
    }

    void MImageRequest(String imgUrl){
        ImageRequest request = new ImageRequest(imgUrl, new Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                mImageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
            }
        });
        request.setTag("imgRequest");
        MyApplication.getHttpRequestQueues().add(request);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getHttpRequestQueues().cancelAll("strGet");
        MyApplication.getHttpRequestQueues().cancelAll("strPost");
        MyApplication.getHttpRequestQueues().cancelAll("jsonGet");
        MyApplication.getHttpRequestQueues().cancelAll("jsonPost");
    }
}

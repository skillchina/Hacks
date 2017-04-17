package club.iandroid.hack50.collection.ui.webview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mydemo.R;


/**
 * Created by gabriel on 15/7/7.
 */
public class ActivityWebView extends Activity {

    WebView mWebView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mwebview);
        mWebView = (WebView)findViewById(R.id.mWebView);
        //mWebView.loadUrl("file:///android_asset/test.html");
        mWebView.loadUrl("http://wwww.baidu.com");
        //覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebView中打开
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候，控制网页在WebView中区打开，如果是false调用系统浏览器或者第三方浏览器打开
                view.loadUrl(url);
                return true;
            }
            //WebViewClient帮助WebView去处理一些页面控制 和请求通知

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }


        });

        //启用支持javascript
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //newProgress pic1-100之间的整数
                if(newProgress==100){
                    //加载完毕，关闭进度条对话框
                    closeDialog();
                }else {
                    //正在加载,打开对话框进度条
                    openDialog(newProgress);
                }
            }

            private void openDialog(int newProgress){
                if(progressDialog == null){
                    progressDialog = new ProgressDialog(ActivityWebView.this);
                    progressDialog.setTitle("正在加载...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setProgress(newProgress);
                    progressDialog.show();
                }else{
                    progressDialog.setProgress(newProgress);
                }
            }

            private void closeDialog(){
                if(progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        });
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

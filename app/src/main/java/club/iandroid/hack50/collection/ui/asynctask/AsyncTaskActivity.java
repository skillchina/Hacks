package club.iandroid.hack50.collection.ui.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mydemo.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 异步任务
 * 三步
 * pic1、创建AsyncTask的子类，并为三个泛型参数指定类型。如果某个泛型参数不需要指定类型，
 * 可将它指定为Void
 * pic2、根据需要，实现AsyncTask的如下方法：
 *
 * pic3、调用AsyncTask子类的实例的execute(Params...params)开始执行耗时任务。
 *
 * 使用AsyncTask时必须遵守如下规则：：
 * pic1、必须在UI线程中创建AsyncTask的实例
 * pic2、必须在UI线程中调用AsyncTask的execute方法
 * pic3、AsyncTask的onPreExecute()、onPostExecute(Result result)、doInBackground(Params...params)，
 * pic4、onProgressUpdate(Progress... values)方法，不应该由程序员代码调用，而是由Android系统负责调用
 * pic5、每个AsyncTask只能被执行一次，多次调用将会引发异常。
 */
public class AsyncTaskActivity extends Activity {

    private TextView show;
    private Button btn_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        show = (TextView)findViewById(R.id.txtInfo);
        btn_download = (Button)findViewById(R.id.btn_download);
        btn_download.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    download(v);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void download(View source) throws MalformedURLException
    {
        DownTask task = new DownTask(this);
        task.execute(new URL("http://www.lanshuicar.net"));

    }

    class DownTask extends AsyncTask<URL, Integer, String>
    {
        //可变长的输入参数，与AsyncTask.execute()对应
        ProgressDialog pdialog;
        //定义记录已经读取行的数量
        int hasRead = 0;
        Context mContext;
        public DownTask(Context context)
        {
            mContext = context;
        }

        @Override
        protected String doInBackground(URL... params) {//完成实际的下载任务
            StringBuilder sb = new StringBuilder();
            try {
                URLConnection conn = params[0].openConnection();
                //打开conn连接对应的输入流，并将它包装成BufferedReader
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()
                        , "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                    hasRead++;
                    publishProgress(hasRead);
                }
                return sb.toString();
            }catch (Exception e)
            {
                e.printStackTrace();;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {//负责下载完成后，将下载的代码显示出来
            //返回HTML页面的内容
            show.setText(s);
            pdialog.dismiss();
        }

        @Override
        protected void onPreExecute() {//负责在下载开始的时候显示一个进度条
            pdialog = new ProgressDialog(mContext);
            //设置对话框显示的标题
            pdialog.setTitle("任务正在执行中");
            //设置对话框显示的内容
            pdialog.setMessage("任务正在执行中，敬请等待...");
            //设置对话框不能用“取消”按钮关闭
            pdialog.setCancelable(false);
            //设置该进度条的最大进度值
            pdialog.setMax(100);
            //设置对话框的进度条风格
            pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //设置对话框的进度条是否显示进度
            pdialog.setIndeterminate(false);
            pdialog.show();;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {//负责随着下载进度的改变更新进度条的进度值
            //更新进度
            show.setText("已读取了【"+values[0]+"】 行！");
            pdialog.setProgress(values[0]);

        }
    }
}

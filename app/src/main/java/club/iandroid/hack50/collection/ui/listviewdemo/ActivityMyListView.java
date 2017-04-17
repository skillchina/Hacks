package club.iandroid.hack50.collection.ui.listviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.mydemo.R;
import com.example.mydemo.adapter.MListViewAdapter;
import com.example.mydemo.bean.NewsBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ActivityMyListView extends Activity {

    private ListView mListView;
    private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_my_list_view);
        context = this;

        initView();

        initData();
    }

    private void initView(){
        mListView = (ListView)findViewById(R.id.mListView);
    }

    private void initData(){
        new MyListViewAsyncTask().execute(URL);
    }

    private List<NewsBean> getJsonData(String url){
        List<NewsBean> mList = new ArrayList<NewsBean>();
        try {
            String jsonString = readStream(new URL(url).openStream());
            Log.d("jsonStr",jsonString);
            JSONObject jsonObject;
            NewsBean newsBean;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    newsBean = new NewsBean();
                    newsBean.setImgUrl(jsonObject.getString("picBig"));
                    newsBean.setTitle(jsonObject.getString("name"));
                    newsBean.setContent(jsonObject.getString("description"));
                    mList.add(newsBean);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return mList;
    }

    private String readStream(InputStream is){
        InputStreamReader isr =null;
        String result = "";
        BufferedReader br = null;
        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            br = new BufferedReader(isr);
            while((line=br.readLine())!=null){
                result+=line;
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
                isr.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }


    public class MyListViewAsyncTask extends AsyncTask<String, Void, List<NewsBean>>{
        @Override
        protected List<NewsBean> doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsBeans) {
            super.onPostExecute(newsBeans);
            MListViewAdapter adapter = new MListViewAdapter(context, newsBeans, mListView);
            mListView.setAdapter(adapter);
        }
    }
}

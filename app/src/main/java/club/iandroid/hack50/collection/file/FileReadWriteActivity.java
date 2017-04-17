package club.iandroid.hack50.collection.file;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydemo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileReadWriteActivity extends Activity {

    private EditText txt_msg;
    private TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_read_write);

        txt_msg = (EditText)findViewById(R.id.txt_msg);
        tv_msg = (TextView)findViewById(R.id.tv_msg);

        ((Button)findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeContent(txt_msg.getText().toString());
            }
        });

        ((Button)findViewById(R.id.btn_read)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_msg.setText(readContent());
            }
        });

        //文件写入
        File file = new File("/mnt/sdcard/test");
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(FileReadWriteActivity.this, "文件已存在",Toast.LENGTH_LONG).show();
        }

        File mfile = getFilesDir();//这个目录是当前应用程序默认的数据存储目录

        //把一些不是非常重要的文件在此创建 使用
        //如果手机的内存不足的时候 系统会自动去删除App的cache目录的数据
        File mcache = getFilesDir();//这个目录是当前应用程序默认的缓存存储目录
        Log.i("File path",mfile.toString());

        //自定义目录/data/data/包名/files
        /**
         * 1、MODE_PRIVATE 为默认模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，
         * 写入的内容会覆盖原文件的内容
         * 2、MODE_APPEND 模式会检查文件是否存在，存在就往该文件追加内容，否则就创建新文件。
         */
        File mfileNew = this.getDir("imooc", MODE_PRIVATE);

        //可以得到外部的存储位置，该位置的数据跟内置的使用是一样的
        //如果App卸载了 这里面的数据也会自动清除掉
        //如果说开发者不遵守这样的规则 不把数据放入data/data/包名
        //   /mnt/sdcard/Android/data/包名
        //卸载之后数据将不会自动清除掉 将会造成所谓的数据垃圾
        //File f = this.getExternalFilesDir(type);
        File excache = this.getExternalCacheDir();
    }

    void writeContent(String content){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("msg.txt", MODE_PRIVATE);
            fos.write(content.getBytes());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    String readContent(){
        String content = "";
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try{
            fis = openFileInput("msg.txt");
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            content = baos.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return content;
    }

}

package club.iandroid.hack50.utils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by gabriel on 2017/4/10.
 */

public class FileUtils {

    private Context mContext;
    public FileUtils(Context context){
        mContext = context;
    }

    /**
     * 打开应用程序的数据文件夹下的name文件对应输入流
     * @param name
     */
    public void doOpenFileInput(String name) throws FileNotFoundException {
        FileInputStream fileInputStream = mContext.openFileInput(name);
    }

    /**
     * 打开文件输出流
     * MODE_PRIVATE -> 该文件只能被当前程序读写
     * Context.MODE_APPEND -> 以追加方式打开该文件，应用程序可以向该文件中追加内容
     * Context.MODE_WORLD_READABLE -> 该文件的内容可以被其他程序读取
     *  Context.MODE_WORLD_WRITEABLE -> 该文件的内容可以被其他程序读、写
     *
     * @param name
     */
    public void doOpenFileOutput(String name) throws FileNotFoundException {
        FileOutputStream fileOutputStream = mContext.openFileOutput(name, Context.MODE_PRIVATE);

        /**
         * 在应用程序的数据文件夹下 获取 或创建name对应的子目录
         */
        mContext.getDir("", Context.MODE_PRIVATE);

        /**
         * 获取该应用程序的数据文件夹的绝对路径
         */
        mContext.getFilesDir();

        /**
         * 返回该应用程序的数据文件夹下的全部文件
         */
        mContext.fileList();

        /**
         * 删除应用程序的数据文件夹下 的指定文件
         */
        mContext.deleteFile("test");
    }
}

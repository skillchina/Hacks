package club.iandroid.hack50.utils;

import java.io.Closeable;

/**
 * Closeable标识了一个可关闭的对象，它只有一个close方法
 * 所有实现了Closeable这个借口的，都可以用这个方法进行关闭
 * Created by gabriel on 2017/3/31.
 */

public class CloseUtils {

    private CloseUtils(){}

    /**
     * 关闭closeable对象
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable){
        if(null!=closeable){
            try {
                closeable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

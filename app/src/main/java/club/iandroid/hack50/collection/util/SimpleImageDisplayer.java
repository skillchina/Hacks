package club.iandroid.hack50.collection.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

/**
 * Created by haoxi on 2015/11/16.
 */
public class SimpleImageDisplayer implements BitmapDisplayer {

    private int targetWidth;

    public SimpleImageDisplayer(int targetWidth) {
        this.targetWidth = targetWidth;
    }


    @Override
    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        if (bitmap != null) {
            bitmap = resizeImageByWidth(bitmap, targetWidth);
        }
        imageAware.setImageBitmap(bitmap);
    }

    /**
     * 根据宽度等比例缩放图片
     *
     * @param defaultBitmap
     * @param targetWidth
     * @return
     */
    public static Bitmap resizeImageByWidth(Bitmap defaultBitmap,
                                            int targetWidth) {
        int rawWidth = defaultBitmap.getWidth();
        int rawHeight = defaultBitmap.getHeight();
        float targetHeight = targetWidth * (float) rawHeight / (float) rawWidth;
        float scaleWidth = targetWidth / (float) rawWidth;
        float scaleHeight = targetHeight / (float) rawHeight;
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(scaleHeight, scaleWidth);
        return Bitmap.createBitmap(defaultBitmap, 0, 0, rawWidth, rawHeight,
                localMatrix, true);
    }
}

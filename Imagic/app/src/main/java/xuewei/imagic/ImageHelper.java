package xuewei.imagic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by Avril on 11/2/17.
 */

public class ImageHelper {

    public static Bitmap handleImageEffect(Bitmap mp, float hue, float saturation, float lum){
        //width, height, config
        Bitmap bmp = Bitmap.createBitmap(mp.getWidth(), mp.getHeight(), Bitmap.Config.ARGB_8888);
        //do something on the canvas but not on the bmp
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //set hue, saturation, lum
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        //put settings together
        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        //paint by the imageMatrix on the canvas
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(mp, 0, 0, paint);
        return bmp;
    }
}

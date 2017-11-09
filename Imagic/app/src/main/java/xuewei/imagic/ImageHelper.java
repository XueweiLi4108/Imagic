package xuewei.imagic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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

    public static Bitmap handleImageNegative(Bitmap mp){
        int width = mp.getWidth();
        int height = mp.getHeight();
        int color;
        int r, g, b, a;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        mp.getPixels(oldPx, 0, width, 0, 0, width, height);
        for(int i = 0; i < width * height; i++){
            //get r, g, b, a from current pixel
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            //change based on effect
            r = 255 - r;
            g = 255 - g;
            b = 255 - b;
            r = Math.min(Math.max(0, r), 255);
            g = Math.min(Math.max(0, g), 255);
            b = Math.min(Math.max(0, b), 255);

            newPx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }

    public static Bitmap handleImageOldPhoto(Bitmap mp){
        int width = mp.getWidth();
        int height = mp.getHeight();
        int color;
        int r, g, b, a, r1, g1, b1;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        mp.getPixels(oldPx, 0, width, 0, 0, width, height);
        for(int i = 0; i < width * height; i++){
            //get r, g, b, a from current pixel
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            //change based on effect
            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);
            r1 = Math.min(Math.max(0, r1), 255);
            g1 = Math.min(Math.max(0, g1), 255);
            b1 = Math.min(Math.max(0, b1), 255);

            newPx[i] = Color.argb(a, r1, g1, b1);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }

    public static Bitmap handleImageRelif(Bitmap mp){
        int width = mp.getWidth();
        int height = mp.getHeight();
        int color, color1;
        int r, g, b, a, r1, g1, b1, a1;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        mp.getPixels(oldPx, 0, width, 0, 0, width, height);
        for(int i = 1; i < width * height; i++){
            //get r, g, b, a from current pixel
            color = oldPx[i - 1];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            color1 = oldPx[i - 1];
            r1 = Color.red(color);
            g1 = Color.green(color);
            b1 = Color.blue(color);
            a1 = Color.alpha(color);

            //change based on effect
            r = r - r1 + 127;
            g = r - g1 + 127;
            b = r - b1 + 127;
            r = Math.min(Math.max(0, r), 255);
            g = Math.min(Math.max(0, g), 255);
            b = Math.min(Math.max(0, b), 255);

            newPx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }
}

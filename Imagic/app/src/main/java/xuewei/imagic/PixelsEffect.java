package xuewei.imagic;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Avril on 11/8/17.
 */

public class PixelsEffect extends Activity {

    private ImageView imageView1, imageView2, imageView3, imageView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pixels_effect);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rose);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);

        imageView1.setImageBitmap(bitmap);
        imageView2.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
        imageView3.setImageBitmap(ImageHelper.handleImageOldPhoto(bitmap));
        imageView4.setImageBitmap(ImageHelper.handleImageRelif(bitmap));
    }
}
package xuewei.imagic;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * Created by Avril on 11/8/17.
 */

public class ColorMatrix extends Activity{

    private ImageView mImageView;
    private GridLayout mGroup;
    private Bitmap bitmap;
    private int mEtWidth, mEtHeight;
    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrix = new float[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_matrix);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sunrise);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mGroup = (GridLayout) findViewById(R.id.group);

        mImageView.setImageBitmap(bitmap);

        //1. Initialize GridLayout
        //1.1 add editText for each one in grid
        //1.2 set editText as initial values
        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 4;
                addEts();
                initMatrix();
            }
        });
    }

    private void addEts(){
        for(int i = 0; i < 20; i++){
            EditText editText = new EditText(ColorMatrix.this);
            mEts[i] = editText;
            mGroup.addView(editText, mEtWidth, mEtHeight);
        }
    }

    private void initMatrix(){
        for(int i = 0; i < 20; i++){
            if(i % 6 == 0)
                mEts[i].setText(String.valueOf(1));
            else
                mEts[i].setText(String.valueOf(0));
        }
    }

    private void getMatrix(){
        for(int i = 0; i < 20; i++){
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }
    private void setImageMatrix(){
        //1. create a new bmp based on bitmap(resourse image)
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        android.graphics.ColorMatrix colorMatrix = new android.graphics.ColorMatrix();
        //2. set color matrix
        colorMatrix.set(mColorMatrix);
        //3. create canvas of bmp
        Canvas canvas = new Canvas(bmp);
        //4.create paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //5. set paint by color matrix
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        //6. draw on canvas, bmp changed
        canvas.drawBitmap(bitmap, 0, 0, paint);
        //7. set image view by bmp
        mImageView.setImageBitmap(bmp);
    }

    //change image: get editText matrix --> set color matrix --> set image matrix
    public void btn_change(View view){
        getMatrix();
        setImageMatrix();

    }
    //reset image: initialize matrix --> set color matrix --> set image matrix
    public void btn_reset(View view){
        initMatrix();
        getMatrix();
        setImageMatrix();
    }
}

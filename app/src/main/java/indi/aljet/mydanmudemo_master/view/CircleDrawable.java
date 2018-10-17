package indi.aljet.mydanmudemo_master.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import indi.aljet.mydanmudemo_master.R;

/**
 * 圆形图片 （有个红心）
 * 需要考虑
 */
public class CircleDrawable extends Drawable {
    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mBitmapHeart;
    private boolean mHasHeart;

    private static final int BLACK_COLOR =
            0xb2000000;//黑色 背景

    private static final int BLACKGROUDE_ADD_SIZE =
            4;//背景比图片多出来的部分

    public CircleDrawable(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        BitmapShader bitmapShader = new BitmapShader
                (mBitmap, Shader.TileMode.CLAMP,
                        Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
    }

    public CircleDrawable(Context context,Bitmap bitmap,
                          boolean hasHeart){
        this(bitmap);
        mHasHeart = hasHeart;
        if(hasHeart){
            setBitmapHeart(context);
        }
    }

    private void setBitmapHeart(Context context){
        Bitmap bitmap = BitmapFactory.decodeResource
                (context.getResources(),
                        R.mipmap.ic_liked);
        if(bitmap != null){
            Matrix matrix = new Matrix();
            matrix.postScale(0.8f,0.8f);
            mBitmapHeart = Bitmap.createBitmap(bitmap,
                    0,0,bitmap.getWidth(),
                    bitmap.getHeight(),matrix,true);
        }

    }


    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
    }


    /**
     * 绘制红心 比较需要考虑
     * @param canvas
     */
    @Override
    public void draw(@NonNull Canvas canvas) {
        if(mHasHeart && mBitmapHeart != null){
            Paint backgroundPaint = new Paint();
            backgroundPaint.setAntiAlias(true);
            backgroundPaint.setColor(BLACK_COLOR);
            canvas.drawCircle(getIntrinsicWidth() / 2
            + BLACKGROUDE_ADD_SIZE ,getIntrinsicHeight() / 2
            + BLACKGROUDE_ADD_SIZE,
                    getIntrinsicWidth() / 2 +
            BLACKGROUDE_ADD_SIZE,backgroundPaint);

            //先将画布平移，防止图片不在正中间，然后绘制图片
            canvas.translate(BLACKGROUDE_ADD_SIZE,
                    BLACKGROUDE_ADD_SIZE);
            canvas.drawCircle(getIntrinsicWidth() / 2,
                    getIntrinsicHeight() / 2,
                    getIntrinsicWidth() / 2,
                    mPaint);

            //在右下角绘制‘心’
            Rect srcRect = new Rect(0,0,mBitmapHeart
            .getWidth(),mBitmapHeart.getHeight());
            Rect desRect = new Rect(getIntrinsicWidth()
            - mBitmapHeart.getWidth() + BLACKGROUDE_ADD_SIZE * 2,
                    getIntrinsicHeight() - mBitmapHeart.getHeight()
            + BLACKGROUDE_ADD_SIZE * 2,
                    getIntrinsicWidth() + BLACKGROUDE_ADD_SIZE * 2,
                    getIntrinsicHeight() + BLACKGROUDE_ADD_SIZE * 2);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            canvas.drawBitmap(mBitmapHeart,srcRect,
                    desRect,paint);
        }else{
            canvas.drawCircle(getIntrinsicWidth() / 2,
                    getIntrinsicHeight() / 2,
                    getIntrinsicWidth() / 2,
                    mPaint);
        }
    }


    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }


    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }


    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
    }


    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}

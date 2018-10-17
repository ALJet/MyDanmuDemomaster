package indi.aljet.mydanmudemo_master.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import java.lang.ref.WeakReference;

/**
 * 图文混排使图片文字基于中线对齐
 */
public class CenteredImageSpan extends ImageSpan {

    private WeakReference<Drawable> mDrawableRef;

    public CenteredImageSpan(final Drawable drawable){
        super(drawable);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        Drawable d = getCachedDrawable();
        Rect rect = d.getBounds();
        if(fm != null){
            Paint.FontMetricsInt pfm = paint
                    .getFontMetricsInt();
            fm.ascent = pfm.ascent;
            fm.top = pfm.top;
            fm.descent = pfm.descent;
            fm.bottom = pfm.bottom;
        }
        return rect.right;
    }


    /**
     * 通个这个 设置文字和图片在同一水平基于中线对齐
     * 重点算法
     * @param canvas
     * @param text
     * @param start
     * @param end
     * @param x
     * @param top
     * @param y
     * @param bottom
     * @param paint
     */
    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable b = getCachedDrawable();
        canvas.save();
        int drawableHeight = b.getIntrinsicHeight();
        int fontAscent = paint.getFontMetricsInt().ascent;
        int fontDescent = paint.getFontMetricsInt().descent;
        int transY = bottom - b.getBounds().bottom +
                (drawableHeight - fontDescent + fontAscent) / 2;
        canvas.translate(x,transY);
        b.draw(canvas);
        canvas.restore();

    }


    private Drawable getCachedDrawable(){
        WeakReference<Drawable> wr = mDrawableRef;
        Drawable d = null;
        if(wr != null){
            d = wr.get();
        }
        if(d == null){
            d = getDrawable();
            mDrawableRef = new WeakReference<>(d);
        }
        return d;
    }
}

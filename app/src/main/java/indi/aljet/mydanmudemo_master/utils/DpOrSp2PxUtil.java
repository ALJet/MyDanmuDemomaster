package indi.aljet.mydanmudemo_master.utils;

import android.content.Context;
import android.util.TypedValue;

public class DpOrSp2PxUtil {

    public static int dp2pxConvertInt(Context context,
                                      float dpValue){
        return (int) TypedValue.applyDimension
                (TypedValue.COMPLEX_UNIT_DIP,
                        dpValue,context.getResources()
                .getDisplayMetrics());
    }


    public static int sp2pxConvertInt(Context context,
                                  float spValue){
        return (int) TypedValue.applyDimension
                (TypedValue.COMPLEX_UNIT_SP,
                        spValue,context.getResources()
                .getDisplayMetrics());
    }


    public static float dp2px(Context context,
                              float dpValue){
        return TypedValue.applyDimension
                (TypedValue.COMPLEX_UNIT_DIP,
                        dpValue,context
                .getResources().getDisplayMetrics());
    }


    public static float sp2px(Context context,
                              float spValue){
        return TypedValue.applyDimension(TypedValue
        .COMPLEX_UNIT_SP,spValue,context
        .getResources().getDisplayMetrics());
    }
}

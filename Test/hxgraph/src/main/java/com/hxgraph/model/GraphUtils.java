package com.hxgraph.model;

/**
 * 辅助计算的工具类
 * Created by liulinru on 2017/4/20.
 */

public class GraphUtils {
    private static final float FLOAT_ZERO_POSITIVE = 0.000001f;
    private static final float FLOAT_ZERO_NEGATIVE = -FLOAT_ZERO_POSITIVE;

    private static final double DOUBLE_ZERO_POSITIVE = 0.000001;
    private static final double DOUBLE_ZERO_NEGATIVE = -DOUBLE_ZERO_POSITIVE;

    /**
     * float类型比较大小
     * @param f1
     * @param f2
     * @return 前者大，返回true，后者大返回false，相等返回null
     */
    public static Boolean floatCompare(float f1,float f2){
        float d = f1-f2;
        if(floatEqual0(d))
            return null;
        if(d > FLOAT_ZERO_POSITIVE)
            return true;
        else
            return false;
    }

    /**
     * double类型比较大小
     * @param f1
     * @param f2
     * @return 前者大，返回true，后者大返回false，相等返回null
     */
    public static Boolean doubleCompare(double f1,double f2){
        double d = f1-f2;
        if(doubleEqual0(d))
            return null;
        if(d > DOUBLE_ZERO_POSITIVE)
            return true;
        else
            return false;
    }

    public static boolean floatEqual0(float f1){
        if(f1 <= FLOAT_ZERO_POSITIVE && f1 >= FLOAT_ZERO_NEGATIVE)
            return true;
        return false;
    }

    public static boolean doubleEqual0(double d1){
        if(d1 <= DOUBLE_ZERO_POSITIVE && d1 >= DOUBLE_ZERO_NEGATIVE)
            return true;
        return false;
    }
}

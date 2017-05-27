package com.hxgraph.model.param;

import com.hxgraph.model.Constant;

/**
 * 柱状图 BarGraph 参数集合
 * Created by liulinru on 2017/4/24.
 */

public class BarGraphStrategyParam extends IStrategyParamsImp {
    private int[] colors = null;//多柱对比时，对应的颜色数组
    //柱状图底部位于图上的相对位置，1.0表示在canvas坐标轴上y=height坐标位置上，0.5f表示在显示区域的中部，1.0f表示在坐标轴y=0的位置上
    private float referenceRelativePos = 1.0f;
    private float strokeWidth  = Constant.fDefaultStrokeWidth;//线条宽度
    private int defaultColor =  Constant.iDefaultStrokeColor;//默认柱子颜色
    private float defaultBarWidth =  Constant.fDefaultBarWidth;//默认柱子宽度
    private boolean isStroke = true;//填充类型,默认为不填充内部


    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] olors) {
        this.colors = olors;
    }

    public float getReferenceRelativePos() {
        return referenceRelativePos;
    }

    public void setReferenceRelativePos(float referenceRelativePos) {
        this.referenceRelativePos = referenceRelativePos;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }

    public float getDefaultBarWidth() {
        return defaultBarWidth;
    }

    public void setDefaultBarWidth(float defaultBarWidth) {
        this.defaultBarWidth = defaultBarWidth;
    }

    public boolean isStroke() {
        return isStroke;
    }

    public void setStroke(boolean stroke) {
        isStroke = stroke;
    }
}

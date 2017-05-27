package com.hxgraph.model.param.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.param.IStrategyParams;
import com.hxgraph.model.param.IStrategyParamsImp;

/**
 * 点画线 RedGreenGraphModel 参数集合
 * Created by liulinru on 2017/4/21.
 */

public class RedGreenGraphStrategyParam extends IStrategyParamsImp {

    private float strokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private int colorRise = Constant.iDefaultRiseColor;//上涨颜色
    private int colorDown = Constant.iDefaultDownColor;//下跌颜色
    private float referenceLineRelativePos = 0.5f;//参考线所处的位置，默认居中
    private int[] colors;//数据点对应的颜色

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getColorRise() {
        return colorRise;
    }

    public void setColorRise(int colorRise) {
        this.colorRise = colorRise;
    }

    public int getColorDown() {
        return colorDown;
    }

    public void setColorDown(int colorDown) {
        this.colorDown = colorDown;
    }

    public float getReferenceLineRelativePos() {
        return referenceLineRelativePos;
    }

    public void setReferenceLineRelativePos(float referenceLineRelativePos) {
        this.referenceLineRelativePos = referenceLineRelativePos;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }
}

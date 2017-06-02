package com.hxgraph.model.param.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.param.IStrategyParams;
import com.hxgraph.model.param.IStrategyParamsImp;

/**
 * 分时量 指标图形参数
 * Created by liulinru on 2017/4/24.
 */

public class VolumeGraphStrategyParam extends IStrategyParamsImp {

//    private int colorRise = Constant.iDefaultRiseColor;//上涨颜色
//    private int colorDown = Constant.iDefaultDownColor;//下跌颜色
    private float barWidth;//柱子的宽度
    private float strokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private int[] colors;//数据点对应的颜色
    private float strokeWidthBlod = Constant.fDefaultStrokeWidth;//线条宽度加粗
    private boolean useLine = false;//是否使用柱与直线形式判断
    private boolean[] fillOrStroke;//对应的柱子是否是实心柱
    private boolean[] isBold;//对应的柱子是否加粗

//    public int getColorRise() {
//        return colorRise;
//    }
//
//    public void setColorRise(int colorRise) {
//        this.colorRise = colorRise;
//    }
//
//    public int getColorDown() {
//        return colorDown;
//    }
//
//    public void setColorDown(int colorDown) {
//        this.colorDown = colorDown;
//    }

    public float getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(float barWidth) {
        this.barWidth = barWidth;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public float getStrokeWidthBlod() {
        return strokeWidthBlod;
    }

    public void setStrokeWidthBlod(float strokeWidthBlod) {
        this.strokeWidthBlod = strokeWidthBlod;
    }


    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public boolean isUseLine() {
        return useLine;
    }

    public void setUseLine(boolean useLine) {
        this.useLine = useLine;
    }

    public boolean[] getFillOrStroke() {
        return fillOrStroke;
    }

    public void setFillOrStroke(boolean[] fillOrStroke) {
        this.fillOrStroke = fillOrStroke;
    }

    public boolean[] getIsBold() {
        return isBold;
    }

    public void setIsBold(boolean[] isBold) {
        this.isBold = isBold;
    }
}

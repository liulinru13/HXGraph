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
//    private float strokeWidthBlod = Constant.fDefaultStrokeWidth;//线条宽度加粗

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

//    public float getStrokeWidthBlod() {
//        return strokeWidthBlod;
//    }
//
//    public void setStrokeWidthBlod(float strokeWidthBlod) {
//        this.strokeWidthBlod = strokeWidthBlod;
//    }
}

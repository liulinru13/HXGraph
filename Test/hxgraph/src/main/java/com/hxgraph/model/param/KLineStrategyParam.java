package com.hxgraph.model.param;

import com.hxgraph.model.Constant;

/**
 * k线 图形参数
 * Created by liulinru on 2017/4/25.
 */

public class KLineStrategyParam extends IStrategyParamsImp {

    private double pointWidth = Constant.fDefaultBarWidth;//宽度
    private int riseColor = Constant.iDefaultRiseColor;//涨的颜色
    private int downColor = Constant.iDefaultDownColor;//跌的颜色
    private float strokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private float strokeWidthBlod = Constant.fDefaultStrokeWidthBlod;//加粗的线条宽度

    private boolean isRiseStroke = true;//涨的点是否不使用填充进行展示
    private boolean isUseLineNotBar = false;//使用线来展示K线

    public double getPointWidth() {
        return pointWidth;
    }

    public void setPointWidth(double pointWidth) {
        this.pointWidth = pointWidth;
    }

    public int getRiseColor() {
        return riseColor;
    }

    public void setRiseColor(int riseColor) {
        this.riseColor = riseColor;
    }

    public int getDownColor() {
        return downColor;
    }

    public void setDownColor(int downColor) {
        this.downColor = downColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public boolean isRiseStroke() {
        return isRiseStroke;
    }

    public void setRiseStroke(boolean riseStroke) {
        isRiseStroke = riseStroke;
    }

    public boolean isUseLineNotBar() {
        return isUseLineNotBar;
    }

    public void setUseLineNotBar(boolean useLineNotBar) {
        isUseLineNotBar = useLineNotBar;
    }

    public float getStrokeWidthBlod() {
        return strokeWidthBlod;
    }

    public void setStrokeWidthBlod(float strokeWidthBlod) {
        this.strokeWidthBlod = strokeWidthBlod;
    }
}

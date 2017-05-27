package com.hxgraph.model.param.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.param.IStrategyParamsImp;

/**
 * Created by liulinru on 2017/4/28.
 */

public class MagicWaveGraphStrategyParam extends IStrategyParamsImp {

    private int drawStyle = 1;//绘制样式，数值为步宽，1个像素为直线，3个像素为三条竖线，其他为等腰三角形状
    private int dividerLineColor = Constant.iDefaultMagicWaveDividerColor;
    private float strokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private int[] colors;

    public int getDrawStyle() {
        return drawStyle;
    }

    public void setDrawStyle(int drawStyle) {
        this.drawStyle = drawStyle;
    }

    public int getDividerLineColor() {
        return dividerLineColor;
    }

    public void setDividerLineColor(int dividerLineColor) {
        this.dividerLineColor = dividerLineColor;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
}

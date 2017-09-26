package com.hxgraph.model.param;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.LineModel;

/**
 * 点画线 LineModel 参数集合
 * Created by liulinru on 2017/4/21.
 */

public class DotToLineStrategyParam extends IStrategyParamsImp {

    private int color = Constant.iDefaultStrokeColor;//线条颜色
    private float strokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private boolean fillColor = false;//线条所围成的图形是否使用线条颜色作为背景渐变色
    private int bgColor = Constant.NULLVALUE;//背景渐变色的颜色，如果没有设置就用线条颜色
    private LineModel.LineType lineType = LineModel.LineType.SOLID_LINE;//默认实线
    private float[] dotLineParam = Constant.iDefaultDotLineParam;//默认虚线绘制参数

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public LineModel.LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineModel.LineType lineType) {
        this.lineType = lineType;
    }

    public float[] getDotLineParam() {
        return dotLineParam;
    }

    public void setDotLineParam(float[] dotLineParam) {
        this.dotLineParam = dotLineParam;
    }

    public boolean isFillColor() {
        return fillColor;
    }

    public void setFillColor(boolean fillColor) {
        this.fillColor = fillColor;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }
}

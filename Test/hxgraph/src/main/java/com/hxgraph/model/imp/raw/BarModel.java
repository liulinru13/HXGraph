package com.hxgraph.model.imp.raw;

import android.graphics.Paint;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.PointImp;

/**
 * 柱状图的数据结构，最基本的数据结构
 * Created by liulinru on 2017/4/19.
 */

public class BarModel extends PointImp {

    private float fXcoordinateRaw;//原始x坐标，是柱子的左侧坐标
    private float fYcoordinateRaw;//原始y坐标，相对于柱状图的起始参照线也是高度/顶部坐标
    private float fYReferenceCoordinateRaw;//起始点参考系y坐标,相当于柱的底部坐标
    private float fBarWidth = Constant.fDefaultBarWidth;//柱子的宽度
    private float fStrokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private boolean bIsStroke = true;//填充类型,默认为不填充内部
    private boolean bUseLine = false;//是否使用柱与直线形式判断
    private float fLineRange;//当柱宽在改值范围内时，以直线形式展示，在 bUseLine 为true时使用
    private float fValue;//代表的值
    private float fXcoordinate = -1.0f;//x实际坐标值


    public float getfXcoordinateRaw() {
        return fXcoordinateRaw;
    }

    public void setfXcoordinateRaw(float fXcoordinateRaw) {
        this.fXcoordinateRaw = fXcoordinateRaw;
    }

    public float getfYcoordinateRaw() {
        return fYcoordinateRaw;
    }

    public void setfYcoordinateRaw(float fYcoordinateRaw) {
        this.fYcoordinateRaw = fYcoordinateRaw;
    }

    public float getfYReferenceCoordinateRaw() {
        return fYReferenceCoordinateRaw;
    }

    public void setfYReferenceCoordinateRaw(float fYReferenceCoordinateRaw) {
        this.fYReferenceCoordinateRaw = fYReferenceCoordinateRaw;
    }

    public float getfBarWidth() {
        return fBarWidth;
    }

    public void setfBarWidth(float fBarWidth) {
        this.fBarWidth = fBarWidth;
    }

    public float getfStrokeWidth() {
        return fStrokeWidth;
    }

    public void setfStrokeWidth(float fStrokeWidth) {
        this.fStrokeWidth = fStrokeWidth;
    }

    public boolean isbIsStroke() {
        return bIsStroke;
    }

    public void setbIsStroke(boolean bIsStroke) {
        this.bIsStroke = bIsStroke;
    }

    public boolean isbUseLine() {
        return bUseLine;
    }

    public void setbUseLine(boolean bUseLine) {
        this.bUseLine = bUseLine;
    }

    public float getfLineRange() {
        return fLineRange;
    }

    public void setfLineRange(float fLineRange) {
        this.fLineRange = fLineRange;
    }

    public float getfValue() {
        return fValue;
    }

    public void setfValue(float fValue) {
        this.fValue = fValue;
    }

    public float getfXcoordinate() {
        return fXcoordinate;
    }

    public void setfXcoordinate(float fXcoordinate) {
        this.fXcoordinate = fXcoordinate;
    }
}

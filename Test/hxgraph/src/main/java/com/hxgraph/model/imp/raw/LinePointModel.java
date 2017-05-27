package com.hxgraph.model.imp.raw;

import com.hxgraph.model.IPoint;
import com.hxgraph.model.imp.PointImp;

/**
 * 线条数据结构中一个点的数据结构，最基本的数据结构
 * Created by liulinru on 2017/4/19.
 */

public class LinePointModel extends PointImp {

    private float fXcoordinateRaw = 1.0f;//原始x坐标,一般为1.0f，表示与上一个点距离一个单位的步长
    private float fYcoordinateRaw;//原始y坐标
    private float fYValuePercent;//所代表值在整个数据集合中所占的比例，最小0.0f,最大1.0f
    private float fValue;//实际数据值
    private float fXcoordinate = -1.0f;//x实际坐标值
    private float fYcoordinate = -1.0f;//y实际坐标值

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

    public float getfYValuePercent() {
        return fYValuePercent;
    }

    public void setfYValuePercent(float fYValuePercent) {
        this.fYValuePercent = fYValuePercent;
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

    public float getfYcoordinate() {
        return fYcoordinate;
    }

    public void setfYcoordinate(float fYcoordinate) {
        this.fYcoordinate = fYcoordinate;
    }
}

package com.hxgraph.model.imp.raw;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.PointImp;

/**
 * k线 一个点的数据结构
 * Created by liulinru on 2017/4/25.
 */

public class KLinePointModel extends PointImp {
    private double dOpenValue;//开
    private double dHighValue;//高
    private double dLowValue;//低
    private double dCloseValue;//收

    private double dPointWidth = Constant.fDefaultBarWidth;//宽度
    private boolean bIsStroke = true;//填充类型,默认为不填充内部
    private boolean bIsLine = false;//是否使用线条替代柱子

    private float fXcoordinateRaw = 1.0f;//原始x坐标,一般为1.0f，表示与上一个点距离一个单位的步长
    private float fOpenCoordinate;
    private float fHighCoordinate;
    private float fLowCoordinate;
    private float fCloseCoordinate;

    public double getdOpenValue() {
        return dOpenValue;
    }

    public void setdOpenValue(double dOpenValue) {
        this.dOpenValue = dOpenValue;
    }

    public double getdHighValue() {
        return dHighValue;
    }

    public void setdHighValue(double dHighValue) {
        this.dHighValue = dHighValue;
    }

    public boolean isbIsLine() {
        return bIsLine;
    }

    public void setbIsLine(boolean bIsLine) {
        this.bIsLine = bIsLine;
    }

    public double getdLowValue() {
        return dLowValue;
    }

    public void setdLowValue(double dLowValue) {
        this.dLowValue = dLowValue;
    }

    public double getdCloseValue() {
        return dCloseValue;
    }

    public void setdCloseValue(double dCloseValue) {
        this.dCloseValue = dCloseValue;
    }

    public double getdPointWidth() {
        return dPointWidth;
    }

    public void setdPointWidth(double dPointWidth) {
        this.dPointWidth = dPointWidth;
    }

    public boolean isbIsStroke() {
        return bIsStroke;
    }

    public void setbIsStroke(boolean bIsStroke) {
        this.bIsStroke = bIsStroke;
    }

    public float getfXcoordinateRaw() {
        return fXcoordinateRaw;
    }

    public void setfXcoordinateRaw(float fXcoordinateRaw) {
        this.fXcoordinateRaw = fXcoordinateRaw;
    }

    public float getfOpenCoordinate() {
        return fOpenCoordinate;
    }

    public void setfOpenCoordinate(float fOpenCoordinate) {
        this.fOpenCoordinate = fOpenCoordinate;
    }

    public float getfHighCoordinate() {
        return fHighCoordinate;
    }

    public void setfHighCoordinate(float fHighCoordinate) {
        this.fHighCoordinate = fHighCoordinate;
    }

    public float getfLowCoordinate() {
        return fLowCoordinate;
    }

    public void setfLowCoordinate(float fLowCoordinate) {
        this.fLowCoordinate = fLowCoordinate;
    }

    public float getfCloseCoordinate() {
        return fCloseCoordinate;
    }

    public void setfCloseCoordinate(float fCloseCoordinate) {
        this.fCloseCoordinate = fCloseCoordinate;
    }
}

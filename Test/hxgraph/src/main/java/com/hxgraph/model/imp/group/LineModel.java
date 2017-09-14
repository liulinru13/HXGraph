package com.hxgraph.model.imp.group;

import com.hxgraph.model.Constant;
import com.hxgraph.model.IPoint;
import com.hxgraph.model.imp.raw.LinePointModel;
import com.hxgraph.model.imp.PointCollectionImp;

/**
 * 由点组成的线条数据结构，属于组合数据结构
 * 使用 LineModel 本类中的颜色
 * Created by liulinru on 2017/4/19.
 */

public class LineModel extends PointCollectionImp<LinePointModel> implements IPoint{

    private int mIColor = Constant.iDefaultStrokeColor;//线条颜色
    private float mFStrokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private boolean mBFillColor = false;//线条所围成的图形是否使用线条颜色作为背景渐变色
    private LineType mOLineType = LineType.SOLID_LINE;//默认实线
    private float[] mDotLineParam = Constant.iDefaultDotLineParam;//默认虚线绘制参数
//    private boolean mBUseScaleDrawGraph = false;//使用点的比例来计算和绘制图表

    public int getmIColor() {
        return mIColor;
    }

    public void setmIColor(int mIColor) {
        this.mIColor = mIColor;
    }

    public float getmFStrokeWidth() {
        return mFStrokeWidth;
    }

    public void setmFStrokeWidth(float mFStrokeWidth) {
        this.mFStrokeWidth = mFStrokeWidth;
    }

    public LineType getmOLineType() {
        return mOLineType;
    }

    public void setmOLineType(LineType mOLineType) {
        this.mOLineType = mOLineType;
    }

    public float[] getmDotLineParam() {
        return mDotLineParam;
    }

    public void setmDotLineParam(float[] mDotLineParam) {
        this.mDotLineParam = mDotLineParam;
    }

    public boolean ismBFillColor() {
        return mBFillColor;
    }

    public void setmBFillColor(boolean mBFillColor) {
        this.mBFillColor = mBFillColor;
    }

    //    public boolean ismBUseScaleDrawGraph() {
//        return mBUseScaleDrawGraph;
//    }
//
//    public void setmBUseScaleDrawGraph(boolean mBUseScaleDrawGraph) {
//        this.mBUseScaleDrawGraph = mBUseScaleDrawGraph;
//    }

    public enum LineType{
        DOTTED_LINE(1),
        SOLID_LINE(2);

        private int type;
        private LineType(int type){
            this.type = type;
        }
        int toInt(){
            return this.type;
        }
    }
}

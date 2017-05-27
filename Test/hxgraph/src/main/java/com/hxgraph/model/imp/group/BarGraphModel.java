package com.hxgraph.model.imp.group;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.PointCollectionImp;
import com.hxgraph.model.imp.raw.BarModel;
import com.hxgraph.model.imp.raw.BarsModel;

import java.util.List;

/**
 * 柱状图图形数据结构，属于组合数据结构
 * Created by liulinru on 2017/4/24.
 */

public class BarGraphModel extends PointCollectionImp<BarsModel> {
    private int[] mIColors = null;//多柱对比时，对应的颜色数组
    private int mIDefaultColor =  Constant.iDefaultStrokeColor;//默认柱子颜色
    //柱状图底部位于图上的相对位置，1.0表示在canvas坐标轴上y=height坐标位置上，0.5f表示在显示区域的中部，1.0f表示在坐标轴y=0的位置上
    private float mFReferenceRelativePos = 1.0f;
    private float mFYReferenceCoordinateRaw;//起始点参考系y坐标,相当于柱的底部坐标
    private float mFStrokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private float mFBarWidth;//柱子的宽度
    private boolean mBIsStroke = true;//填充类型,默认为不填充内部


    public int[] getmIColors() {
        return mIColors;
    }

    public void setmIColors(int[] mIColors) {
        this.mIColors = mIColors;
    }

    public float getmFReferenceRelativePos() {
        return mFReferenceRelativePos;
    }

    public void setmFReferenceRelativePos(float mFReferenceRelativePos) {
        this.mFReferenceRelativePos = mFReferenceRelativePos;
    }

    public float getmFYReferenceCoordinateRaw() {
        return mFYReferenceCoordinateRaw;
    }

    public void setmFYReferenceCoordinateRaw(float mFYReferenceCoordinateRaw) {
        this.mFYReferenceCoordinateRaw = mFYReferenceCoordinateRaw;
    }

    public float getmFStrokeWidth() {
        return mFStrokeWidth;
    }

    public void setmFStrokeWidth(float mFStrokeWidth) {
        this.mFStrokeWidth = mFStrokeWidth;
    }

    public float getmFBarWidth() {
        return mFBarWidth;
    }

    public void setmFBarWidth(float mFBarWidth) {
        this.mFBarWidth = mFBarWidth;
    }

    public int getmIDefaultColor() {
        return mIDefaultColor;
    }

    public void setmIDefaultColor(int mIDefaultColor) {
        this.mIDefaultColor = mIDefaultColor;
    }

    public boolean ismBIsStroke() {
        return mBIsStroke;
    }

    public void setmBIsStroke(boolean mBIsStroke) {
        this.mBIsStroke = mBIsStroke;
    }
}

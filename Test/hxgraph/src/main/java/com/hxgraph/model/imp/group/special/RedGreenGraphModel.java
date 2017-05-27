package com.hxgraph.model.imp.group.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.PointCollectionImp;
import com.hxgraph.model.imp.group.LineModel;
import com.hxgraph.model.imp.raw.BarModel;

/**
 * 用于绘制分时红绿柱和成交量的数据结构，属于组合数据结构
 * 使用 BarModel 类中的颜色
 * Created by liulinru on 2017/4/19.
 */

public class RedGreenGraphModel extends PointCollectionImp<BarModel> {

    private float mFStrokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private int[] mIColors;//数据点对应颜色
    private int mIColorRise = Constant.iDefaultRiseColor;//上涨颜色
    private int mIColorDown = Constant.iDefaultDownColor;//下跌颜色
    private float mFReferenceCoordinate = -1.0f;//参考线坐标
    private float mFReferenceLineRelativePos = 0.5f;//参考线所处的位置，默认居中



    public float getmFStrokeWidth() {
        return mFStrokeWidth;
    }

    public void setmFStrokeWidth(float mFStrokeWidth) {
        this.mFStrokeWidth = mFStrokeWidth;
    }

    public int[] getmIColors() {
        return mIColors;
    }

    public void setmIColors(int[] mIColors) {
        this.mIColors = mIColors;
    }

    public int getmIColorRise() {
        return mIColorRise;
    }

    public void setmIColorRise(int mIColorRise) {
        this.mIColorRise = mIColorRise;
    }

    public int getmIColorDown() {
        return mIColorDown;
    }

    public void setmIColorDown(int mIColorDown) {
        this.mIColorDown = mIColorDown;
    }

    public float getmFReferenceCoordinate() {
        return mFReferenceCoordinate;
    }

    public void setmFReferenceCoordinate(float mFReferenceCoordinate) {
        this.mFReferenceCoordinate = mFReferenceCoordinate;
    }

    public float getmFReferenceLineRelativePos() {
        return mFReferenceLineRelativePos;
    }

    public void setmFReferenceLineRelativePos(float mFReferenceLineRelativePos) {
        this.mFReferenceLineRelativePos = mFReferenceLineRelativePos;
    }
}

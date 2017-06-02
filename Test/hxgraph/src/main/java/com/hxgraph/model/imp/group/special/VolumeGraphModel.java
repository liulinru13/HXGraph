package com.hxgraph.model.imp.group.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.PointCollectionImp;
import com.hxgraph.model.imp.raw.BarModel;
import com.hxgraph.model.imp.raw.BarsModel;

/**
 * 分时量 指标图数据结构
 * Created by liulinru on 2017/4/24.
 */

public class VolumeGraphModel extends PointCollectionImp<BarModel> {
//    private int mIColorRise = Constant.iDefaultRiseColor;//上涨颜色
//    private int mIColorDown = Constant.iDefaultDownColor;//下跌颜色
    private float mFBarWidth;//柱子的宽度
    private float mFStrokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private float mFStrokeWidthBlod = Constant.fDefaultStrokeWidth;//线条宽度加粗
    private int[] mIColors;//数据点对应的颜色
    private boolean mBUseLine = false;//是否使用柱与直线形式判断

//    public int getmIColorRise() {
//        return mIColorRise;
//    }

//    public void setmIColorRise(int mIColorRise) {
//        this.mIColorRise = mIColorRise;
//    }
//
//    public int getmIColorDown() {
//        return mIColorDown;
//    }
//
//    public void setmIColorDown(int mIColorDown) {
//        this.mIColorDown = mIColorDown;
//    }

    public float getmFBarWidth() {
        return mFBarWidth;
    }

    public void setmFBarWidth(float mFBarWidth) {
        this.mFBarWidth = mFBarWidth;
    }

    public float getmFStrokeWidth() {
        return mFStrokeWidth;
    }

    public void setmFStrokeWidth(float mFStrokeWidth) {
        this.mFStrokeWidth = mFStrokeWidth;
    }
//
    public float getmFStrokeWidthBlod() {
        return mFStrokeWidthBlod;
    }

    public void setmFStrokeWidthBlod(float mFStrokeWidthBlod) {
        this.mFStrokeWidthBlod = mFStrokeWidthBlod;
    }

    public int[] getmIColors() {
        return mIColors;
    }

    public void setmIColors(int[] mIColors) {
        this.mIColors = mIColors;
    }

    public boolean ismBUseLine() {
        return mBUseLine;
    }

    public void setmBUseLine(boolean mBUseLine) {
        this.mBUseLine = mBUseLine;
    }
}

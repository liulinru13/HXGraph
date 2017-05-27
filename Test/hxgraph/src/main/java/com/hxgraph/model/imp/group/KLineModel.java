package com.hxgraph.model.imp.group;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.PointCollectionImp;
import com.hxgraph.model.imp.raw.KLinePointModel;

/**
 * Created by liulinru on 2017/4/25.
 */

public class KLineModel extends PointCollectionImp<KLinePointModel> {

    private double mDPointWidth = Constant.fDefaultBarWidth;//宽度
    private int mIRiseColor = Constant.iDefaultRiseColor;//涨的颜色
    private int mIDownColor = Constant.iDefaultDownColor;//跌的颜色
    private float mFStrokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private float mFStrokeWidthBlod = Constant.fDefaultStrokeWidthBlod;//加粗的线条宽度
    private boolean mBIsRiseStroke = true;//涨的点是否不使用填充进行展示
    private boolean mBIsUseLineNotBar = false;//使用线来展示K线

    public double getmDPointWidth() {
        return mDPointWidth;
    }

    public void setmDPointWidth(double mDPointWidth) {
        this.mDPointWidth = mDPointWidth;
    }

    public int getmIRiseColor() {
        return mIRiseColor;
    }

    public void setmIRiseColor(int mIRiseColor) {
        this.mIRiseColor = mIRiseColor;
    }

    public int getmIDownColor() {
        return mIDownColor;
    }

    public void setmIDownColor(int mIDownColor) {
        this.mIDownColor = mIDownColor;
    }

    public float getmFStrokeWidth() {
        return mFStrokeWidth;
    }

    public void setmFStrokeWidth(float mFStrokeWidth) {
        this.mFStrokeWidth = mFStrokeWidth;
    }

    public boolean ismBIsRiseStroke() {
        return mBIsRiseStroke;
    }

    public void setmBIsRiseStroke(boolean mBIsRiseStroke) {
        this.mBIsRiseStroke = mBIsRiseStroke;
    }

    public boolean ismBIsUseLineNotBar() {
        return mBIsUseLineNotBar;
    }

    public void setmBIsUseLineNotBar(boolean mBIsUseLineNotBar) {
        this.mBIsUseLineNotBar = mBIsUseLineNotBar;
    }

    public float getmFStrokeWidthBlod() {
        return mFStrokeWidthBlod;
    }

    public void setmFStrokeWidthBlod(float mFStrokeWidthBlod) {
        this.mFStrokeWidthBlod = mFStrokeWidthBlod;
    }
}

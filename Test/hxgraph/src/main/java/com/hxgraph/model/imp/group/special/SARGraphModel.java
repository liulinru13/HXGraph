package com.hxgraph.model.imp.group.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.PointCollectionImp;
import com.hxgraph.model.imp.raw.LinePointModel;

/**
 * SAR 曲线数据模型
 * Created by liulinru on 2017/5/2.
 */

public class SARGraphModel extends PointCollectionImp<LinePointModel> {
    private float mFStrokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private int[] mIColors;

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
}

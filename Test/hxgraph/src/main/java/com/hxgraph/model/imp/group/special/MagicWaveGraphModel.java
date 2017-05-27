package com.hxgraph.model.imp.group.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.PointCollectionImp;
import com.hxgraph.model.imp.raw.MagicWavePointModel;

/**
 * 神奇电波 数据结构
 * Created by liulinru on 2017/4/28.
 */

public class MagicWaveGraphModel extends PointCollectionImp<MagicWavePointModel> {
    private int mIDrawStyle = 1;//绘制样式，数值为步宽，1个像素为直线，3个像素为三条竖线，其他为等腰三角形状
    private int mIDividerLineColor = Constant.iDefaultMagicWaveDividerColor;
    private float mFStrokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private int[] mIColors;

    public int getmIDrawStyle() {
        return mIDrawStyle;
    }

    public void setmIDrawStyle(int mIDrawStyle) {
        this.mIDrawStyle = mIDrawStyle;
    }

    public int getmIDividerLineColor() {
        return mIDividerLineColor;
    }

    public void setmIDividerLineColor(int mIDividerLineColor) {
        this.mIDividerLineColor = mIDividerLineColor;
    }

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

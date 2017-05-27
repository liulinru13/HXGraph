package com.hxgraph.model.imp;

import com.hxgraph.model.Constant;
import com.hxgraph.model.IPoint;
import com.hxgraph.model.IPointCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现了基本数据类型集合 IPointCollection 接口的抽象实现类
 * Created by liulinru on 2017/4/19.
 */

public abstract class PointCollectionImp<T extends IPoint> implements IPointCollection {

    protected List<T> mPointList;
    protected float mFXscale = Constant.fDefaultXScale;//x方向缩放比例，用于计算实际步宽
    protected float mFYscale = Constant.fDefaultYScale;//y方向缩放比例
    protected float mFXSetp = Constant.fDefaultXStep;//x方向上的步宽，实际步宽=mFXSetp*mFXscale
    protected int mIColor = Constant.iDefaultStrokeColor;//颜色
    protected PointMaxMin mMaxMinPoints = new PointMaxMin();//最大值最小值，也是这组数据的一个范围表示
    protected float mFTop = Constant.fGraphDefaultTop;//画布上允许绘制的y方向上最高点坐标
    protected int mIHeight = -1;//画布上允许绘制的y方向上范围/高度
    protected float[] mFXCoordinates;

    protected int startIndex;//开始绘制的点下标
    protected int endIndex;//开始绘制的点下标

    @Override
    public List<T> getIPointSet() {
        return mPointList;
    }

    public void setIPointSet(List<T> list){
        this.mPointList = list;
    }

    public float getmFXscale() {
        return mFXscale;
    }

    public void setmFXscale(float mFXscale) {
        this.mFXscale = mFXscale;
    }

    public float getmFYscale() {
        return mFYscale;
    }

    public void setmFYscale(float mFYscale) {
        this.mFYscale = mFYscale;
    }

    public float getmFXSetp() {
        return mFXSetp;
    }

    public void setmFXSetp(float mFXSetp) {
        this.mFXSetp = mFXSetp;
    }

    public int getmIColor() {
        return mIColor;
    }

    public void setmIColor(int mIColor) {
        this.mIColor = mIColor;
    }

    public PointMaxMin getmMaxMinPoints() {
        return mMaxMinPoints;
    }

    public void setmMaxMinPoints(PointMaxMin mMaxMinPoints) {
        this.mMaxMinPoints = mMaxMinPoints;
    }

    public float getmFTop() {
        return mFTop;
    }

    public void setmFTop(float mFTop) {
        this.mFTop = mFTop;
    }

    public int getmIHeight() {
        return mIHeight;
    }

    public void setmIHeight(int mIHeight) {
        this.mIHeight = mIHeight;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public float[] getmFXCoordinates() {
        return mFXCoordinates;
    }

    public void setmFXCoordinates(float[] mFXCoordinates) {
        this.mFXCoordinates = mFXCoordinates;
    }

    public class PointMaxMin{
        T maxPoint;
        int maxIndex;
        T minPoint;
        int minIndex;

        public T getMaxPoint() {
            return maxPoint;
        }

        public void setMaxPoint(T maxPoint,int maxIndex) {
            this.maxPoint = maxPoint;
            this.maxIndex = maxIndex;
        }

        public T getMinPoint() {
            return minPoint;
        }

        public void setMinPoint(T minPoint,int minIndex) {
            this.minPoint = minPoint;
            this.minIndex = minIndex;
        }

        public int getMaxIndex() {
            return maxIndex;
        }

        public int getMinIndex() {
            return minIndex;
        }
    }
}

package com.hxgraph.adapter.special;

import com.hxgraph.adapter.BarGraphAdapter;
import com.hxgraph.adapter.GraphAdapterImp;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.special.VolumeGraphStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.special.VolumeGraphModel;
import com.hxgraph.model.imp.raw.BarModel;
import com.hxgraph.model.param.special.VolumeGraphStrategyParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 股票 成交量 指标图形数据适配器
 * Created by liulinru on 2017/4/24.
 */

public abstract class VolumeGraphAdapter extends GraphAdapterImp<VolumeGraphModel,VolumeGraphStrategyParam> {
    protected double[] mDValues;//转换并处理后的原始数据
    protected double mDMaxValue;//mDValues 范围内的最大值
    protected int mIMaxIndex;//mDValues 范围内的最大值的坐标
    protected double mDMinValue;//mDValues 范围内的最小值
    protected int mIMinIndex;//mDValues 范围内的最小值坐标
    @Override
    public Object doSomethingWithRawData(Object values) {
        return values;
    }

    @Override
    public void calculateYcoordinate(int graphHeight) {
        if(mData == null)
            return;
        List<BarModel> list = mData.getIPointSet();
        float height = graphHeight;
        if(list != null){
            height += Constant.LINE_OFFSET;
            for(BarModel point:list) {
                float scale = point.getfYcoordinateRaw();
                float lineHeight = height * scale;
                if (Math.abs(lineHeight) < 1.0f) {
                    lineHeight = lineHeight > 0.0f ? 1.0f : -1.0f;
                }
                point.setfYcoordinateRaw(lineHeight);
            }
        }
    }

    @Override
    protected VolumeGraphModel getNewModel() {
        return new VolumeGraphModel();
    }

    @Override
    public GraphStrategyImp<VolumeGraphModel> getGraphStrategy() {
        mStrategy = new VolumeGraphStrategy();
        return mStrategy;
    }

    @Override
    public VolumeGraphModel wrapRawData(VolumeGraphStrategyParam params){
        //参数形式需要是 double[][] 或者是 double[]
        if(this.mORawData == null || !(mORawData instanceof double[]))
            return null;
        double[] values = (double[])this.mORawData;
        Object obj = doSomethingWithRawData(values);
        if(obj == null || !(obj instanceof double[]))
            return null;
        this.mDValues =  (double[])obj;
        mData = getNewModel();
        //线条参数设置
        if(params != null){
            mData.setmFBarWidth(params.getBarWidth());
            if(params.getxCoordinates() != null)
                mData.setmFXCoordinates(params.getxCoordinates());
        }
        maxMinValue();
        calculateYcoordinateScale();
        return mData;
    }

    //搜索最值以及下标
    private void maxMinValue(){
        if(mDValues != null){
            mIMaxIndex = 0;
            mIMinIndex = 0;
            mDMaxValue = mDValues[0];
            mDMinValue= mDValues[0];
            for(int i=0;i<mDValues.length;i++){
                if(mDMaxValue < mDValues[i]){
                    mDMaxValue = mDValues[i];
                    mIMaxIndex = i;
                }else if(mDMinValue > mDValues[i]){
                    mDMinValue = mDValues[i];
                    mIMinIndex = i;
                }
            }
        }
    }

    /**
     * 这个方法需要在外面实现
     * 根据最值和展示方向，计算每个点在y方向上最大值最小值之间所占比例
     */
    protected abstract void calculateYcoordinateScale();
//    {
//        List<BarModel> list = new ArrayList<BarModel>();
//        double diff = mDMaxValue - mDMinValue;
//        diff = diff < 0.0 ? 0.0 : diff;
//
//        for (int i = 0; i < mDValues.length; i++) {
//            BarModel point = new BarModel();
//            point.setfBarWidth(mData.getmFBarWidth());
//            point.setbUseLine(true);
//            point.setfLineRange();
//            point.setfValue((float) mDValues[i]);
//            point.setmIColor();
//            point.setbIsStroke();
//            point.setfYcoordinateRaw((float) (mDValues[i]/diff));
//
//            list.add(point);
//
//            if(mIMinIndex == i){
//                mData.getmMaxMinPoints().setMinPoint(point,i);
//            }else if(mIMaxIndex == i){
//                mData.getmMaxMinPoints().setMaxPoint(point,i);
//            }
//        }
//        mData.setIPointSet(list);
//    }
}

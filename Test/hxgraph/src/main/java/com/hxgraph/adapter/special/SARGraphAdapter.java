package com.hxgraph.adapter.special;

import com.hxgraph.adapter.GraphAdapterImp;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.special.SARGraphStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.special.SARGraphModel;
import com.hxgraph.model.imp.raw.LinePointModel;
import com.hxgraph.model.param.special.SARGraphStrategyParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liulinru on 2017/5/2.
 */

public class SARGraphAdapter extends GraphAdapterImp<SARGraphModel,SARGraphStrategyParam> {
    protected int[] mColors;

    @Override
    protected SARGraphModel getNewModel() {
        return new SARGraphModel();
    }

    @Override
    public Object doSomethingWithRawData(Object values) {
        return values;
    }

    @Override
    public void calculateYcoordinate(int graphHeight) {
        if(mData == null)
            return;
        List<LinePointModel> list = mData.getIPointSet();
        if(list != null){
            for(LinePointModel pointModel : list){
                float scale = pointModel.getfYValuePercent();
                pointModel.setfYcoordinateRaw(graphHeight*scale);
            }
        }
    }

    @Override
    public GraphStrategyImp<SARGraphModel> getGraphStrategy() {
        mStrategy = new SARGraphStrategy();
        return mStrategy;
    }

    @Override
    public SARGraphModel wrapRawData(SARGraphStrategyParam params) {
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
            mColors = params.getColors();
            mData.setmIColors(mColors);
            mData.setmFStrokeWidth(params.getStrokeWidth());
            maxMin = params.getMaxMin();
            if(params.getxCoordinates() != null)
                mData.setmFXCoordinates(params.getxCoordinates());
        }
        calculateMaxMin();
        calculateYcoordinateScale();
        return mData;
    }

    /**
     * 根据最值和展示方向，计算每个点在y方向上最大值最小值之间所占比例
     */
    protected void calculateYcoordinateScale(){
        List<LinePointModel> list = new ArrayList<LinePointModel>();
        double diff = mDMaxValue - mDMinValue;
        diff = diff < 0.0 ? 0.0 : diff;
        for (int i = 0; i < mDValues.length; i++) {
            LinePointModel point = new LinePointModel();
            point.setfXcoordinateRaw(Constant.fDefaultX);
            double scale = (mDValues[i] - mDMinValue)/diff;
            point.setfYValuePercent((float)scale);
            point.setfValue((float) mDValues[i]);
            if(mColors != null && mColors.length == mDValues.length ){
                point.setmIColor(mColors[i]);
            }
            list.add(point);
            if(mIMinIndex == i){
                mData.getmMaxMinPoints().setMinPoint(point,i);
            }else if(mIMaxIndex == i){
                mData.getmMaxMinPoints().setMaxPoint(point,i);
            }
        }
        mData.setIPointSet(list);
    }

    //搜索最值以及下标
    protected void maxMinValue(){
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
}

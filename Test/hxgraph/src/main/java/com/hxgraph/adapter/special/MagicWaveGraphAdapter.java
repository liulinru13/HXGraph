package com.hxgraph.adapter.special;

import com.hxgraph.adapter.GraphAdapterImp;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.special.MagicWaveGraphStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.special.MagicWaveGraphModel;
import com.hxgraph.model.imp.raw.MagicWavePointModel;
import com.hxgraph.model.param.special.MagicWaveGraphStrategyParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 神奇电波 图线数据适配器
 * Created by liulinru on 2017/4/28.
 */

public class MagicWaveGraphAdapter extends GraphAdapterImp<MagicWaveGraphModel,MagicWaveGraphStrategyParam> {
    protected int[] mColors;
    protected int[] mDValues;

    protected double mDMaxValue;//mDValues 范围内的最大值
    protected int mIMaxIndex;//mDValues 范围内的最大值的坐标
    protected double mDMinValue;//mDValues 范围内的最小值
    protected int mIMinIndex;//mDValues 范围内的最小值坐标
    @Override
    protected MagicWaveGraphModel getNewModel() {
        return new MagicWaveGraphModel();
    }

    @Override
    public Object doSomethingWithRawData(Object values) {
        return values;
    }

    @Override
    public void calculateYcoordinate(int graphHeight) {
    }

    @Override
    public GraphStrategyImp<MagicWaveGraphModel> getGraphStrategy() {
        mStrategy = new MagicWaveGraphStrategy();
        return mStrategy;
    }

    @Override
    public MagicWaveGraphModel wrapRawData(MagicWaveGraphStrategyParam params) {
        //参数形式需要是 double[][] 或者是 double[]
        if(this.mORawData == null || !((mORawData instanceof int[])))
            return null;
        int[] values = (int[])this.mORawData;
        Object obj = doSomethingWithRawData(values);
        if(obj == null || !(obj instanceof int[]))
            return null;
        this.mDValues =  (int[])obj;
        mData = getNewModel();
        //线条参数设置
        if(params != null){
            mColors = params.getColors();
            mData.setmIDrawStyle(params.getDrawStyle());
            mData.setmIDividerLineColor(params.getDividerLineColor());
            mData.setmFStrokeWidth(params.getStrokeWidth());
            mData.setmIColors(mColors);
            if(params.getxCoordinates() != null)
                mData.setmFXCoordinates(params.getxCoordinates());
        }
        calculateYcoordinateScale();
        return mData;
    }

    protected void calculateYcoordinateScale(){
        List<MagicWavePointModel> list = new ArrayList<MagicWavePointModel>();
        for (int i = 0; i < mDValues.length; i++) {
            MagicWavePointModel point = new MagicWavePointModel();
            point.setIValue(mDValues[i]);
            if(mColors != null && mColors.length > i){
                point.setmIColor(mColors[i]);
            }else{
                point.setmIColor(Constant.iDefaultStrokeColor);
            }
            list.add(point);
        }
        mData.setIPointSet(list);
    }
}

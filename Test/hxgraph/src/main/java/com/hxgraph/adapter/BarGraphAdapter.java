package com.hxgraph.adapter;

import android.graphics.Canvas;

import com.hxgraph.graphstrategy.BarGraphStrategy;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.BarGraphModel;
import com.hxgraph.model.imp.raw.BarModel;
import com.hxgraph.model.imp.raw.BarsModel;
import com.hxgraph.model.param.BarGraphStrategyParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图数据适配器,支持多个柱做同类对比
 * Created by liulinru on 2017/4/24.
 */

public class BarGraphAdapter extends GraphAdapterImp<BarGraphModel,BarGraphStrategyParam> {
    protected double[][] mDValues;//转换并处理后的原始数据
    protected double mDMaxValue;//mDValues 范围内的最大值
    protected int mIMaxIndex;//mDValues 范围内的最大值的坐标
    protected double mDMinValue;//mDValues 范围内的最小值
    protected int mIMinIndex;//mDValues 范围内的最小值坐标

    @Override
    public Object doSomethingWithRawData(Object values) {
        return values;
    }

    @Override
    protected BarGraphModel getNewModel() {
        return new BarGraphModel();
    }

    @Override
    public void calculateYcoordinate(int graphHeight) {
        if(mData == null)
            return;
        List<BarsModel> list = mData.getIPointSet();
        //计算参考线位置,颜色柱状图底部的y坐标
        float referenceCoordinate = (graphHeight - 2 * Constant.LINE_OFFSET)
                * mData.getmFReferenceRelativePos();
        mData.setmFYReferenceCoordinateRaw(referenceCoordinate);
        float heightPositive = referenceCoordinate;//参考线以上部分的高度
        float heightNegative = graphHeight - referenceCoordinate;//参考线以下部分的高度

        if(list != null){
            referenceCoordinate += Constant.LINE_OFFSET;
            for(BarsModel point : list){
                if(point.getBarModels() != null) {
                    BarModel[] array = point.getBarModels();
                    //计算同一个类型的对比柱图数据
                    for (int i=0;i<array.length;i++) {
                        BarModel model = array[i];
                        float scale = model.getfYcoordinateRaw();
                        float lineHeight;
                        if(scale < 0.0f){
                            lineHeight = heightNegative*scale;
                        }else{
                            lineHeight = heightPositive*scale;
                        }
                        if (Math.abs(lineHeight) < 1.0f) {
                            lineHeight = lineHeight > 0.0f ? 1.0f : -1.0f;
                        }
                        model.setfYcoordinateRaw(lineHeight);
                    }
                }
            }
        }
    }

    @Override
    public GraphStrategyImp<BarGraphModel> getGraphStrategy() {
        mStrategy = new BarGraphStrategy();
        return mStrategy;
    }

    //计算最值、位置比例、设置每个柱的颜色
    @Override
    public BarGraphModel wrapRawData(BarGraphStrategyParam params) {
        //参数形式需要是 double[][] 或者是 double[]
        if(this.mORawData == null || !((mORawData instanceof double[][]) || (mORawData instanceof double[])))
            return null;
        //在适配器中，识别的数据类型是 double[][]
        if(mORawData instanceof double[]){
            double[][] temp = new double[][]{(double[])mORawData};
            this.mORawData = temp;
        }
        double[][] values = (double[][])this.mORawData;
        Object obj = doSomethingWithRawData(values);
        if(obj == null || !(obj instanceof double[][]))
            return null;
        this.mDValues =  (double[][])obj;
        mData = getNewModel();
        //线条参数设置
        if(params != null){
            mData.setmIColors(params.getColors());
            mData.setmFReferenceRelativePos(params.getReferenceRelativePos());
            mData.setmFStrokeWidth(params.getStrokeWidth());
            mData.setmIDefaultColor(params.getDefaultColor());
            mData.setmFBarWidth(params.getDefaultBarWidth());
            mData.setmBIsStroke(params.isStroke());
            if(params.getxCoordinates() != null)
                mData.setmFXCoordinates(params.getxCoordinates());
        }
        maxMinValue();
        calculateYcoordinateScale();
        return mData;
    }

    /**
     * 根据最值和展示方向，计算每个点在y方向上最大值最小值之间所占比例
     */
    protected void calculateYcoordinateScale(){
        List<BarsModel> list = new ArrayList<BarsModel>();
        double diff = mDMaxValue - mDMinValue;
        diff = diff < 0.0 ? 0.0 : diff;

        for (int i = 0; i < mDValues.length; i++) {
            if(mDValues[i] != null) {
                BarsModel point = new BarsModel();
                BarModel[] models = new BarModel[mDValues[i].length];
                for(int j=0;j<mDValues[i].length;j++){
                    models[j] = new BarModel();
                    models[j].setfXcoordinateRaw(Constant.fDefaultX);
                    models[j].setfValue((float) mDValues[i][j]);
                    models[j].setfYcoordinateRaw((float) (mDValues[i][j]/diff));
                    models[j].setfBarWidth(mData.getmFBarWidth());
                    models[j].setbIsStroke(mData.ismBIsStroke());

                    if(mData.getmIColors() != null && mData.getmIColors().length > j){
                        models[j].setmIColor(mData.getmIColors()[j]);
                    }else {
                        models[j].setmIColor(mData.getmIDefaultColor());
                    }
                }
                point.setBarModels(models);
                list.add(point);

                if(mIMinIndex == i){
                    mData.getmMaxMinPoints().setMinPoint(point,i);
                }else if(mIMaxIndex == i){
                    mData.getmMaxMinPoints().setMaxPoint(point,i);
                }
            }
        }
        mData.setIPointSet(list);
    }

    //搜索最值以及下标
    private void maxMinValue(){
        if(mDValues != null){
            mIMaxIndex = 0;
            mIMinIndex = 0;
            mDMaxValue = mDValues[0][0];
            mDMinValue= mDValues[0][0];
            for(int i=0;i<mDValues.length;i++){
                if(mDValues[i] != null) {
                    for (int j = 0; j < mDValues[i].length; j++)
                        if (mDMaxValue < mDValues[i][j]) {
                            mDMaxValue = mDValues[i][j];
                            mIMaxIndex = i;
                        } else if (mDMinValue > mDValues[i][j]) {
                            mDMinValue = mDValues[i][j];
                            mIMinIndex = i;
                        }
                }
            }
        }
    }

    public double[][] getmDValues() {
        return mDValues;
    }

    public void setmDValues(double[][] mDValues) {
        this.mDValues = mDValues;
    }

    public double getmDMaxValue() {
        return mDMaxValue;
    }

    public void setmDMaxValue(double mDMaxValue) {
        this.mDMaxValue = mDMaxValue;
    }

    public int getmIMaxIndex() {
        return mIMaxIndex;
    }

    public void setmIMaxIndex(int mIMaxIndex) {
        this.mIMaxIndex = mIMaxIndex;
    }

    public double getmDMinValue() {
        return mDMinValue;
    }

    public void setmDMinValue(double mDMinValue) {
        this.mDMinValue = mDMinValue;
    }

    public int getmIMinIndex() {
        return mIMinIndex;
    }

    public void setmIMinIndex(int mIMinIndex) {
        this.mIMinIndex = mIMinIndex;
    }
}

package com.hxgraph.adapter;

import com.hxgraph.graphstrategy.DotToLineStrategy;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.KLineStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.KLineModel;
import com.hxgraph.model.imp.raw.BarModel;
import com.hxgraph.model.imp.raw.BarsModel;
import com.hxgraph.model.imp.raw.KLinePointModel;
import com.hxgraph.model.param.KLineStrategyParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liulinru on 2017/4/25.
 */

public class KLineAdapter extends GraphAdapterImp<KLineModel,KLineStrategyParam> {

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
    public GraphStrategyImp<KLineModel> getGraphStrategy() {
        mStrategy = new KLineStrategy();
        return mStrategy;
    }

    @Override
    protected KLineModel getNewModel() {
        return new KLineModel();
    }

    @Override
    public KLineModel wrapRawData(KLineStrategyParam params) {
        if(this.mORawData == null || !(mORawData instanceof double[][]))
            return null;
        double[][] values = (double[][])this.mORawData;
        Object obj = doSomethingWithRawData(values);
        if(obj == null || !(obj instanceof double[][] || ((double[][])obj).length != 4 ))
            return null;
        values = (double[][])obj;
        if(values[0] != null){
            int size = values[0].length;
            if( (values[1] == null || values[1].length != size)
                    || (values[2] == null || values[2].length != size)
                    || (values[3] == null || values[3].length != size) )
                return null;

            this.mDValues =  (double[][])obj;
            mData = getNewModel();
            //线条参数设置
            if(params != null){
                mData.setmIRiseColor(params.getRiseColor());
                mData.setmIDownColor(params.getDownColor());
                mData.setmFStrokeWidth(params.getStrokeWidth());
                mData.setmBIsRiseStroke(params.isRiseStroke());
                mData.setmBIsUseLineNotBar(params.isUseLineNotBar());
                mData.setmFStrokeWidthBlod(params.getStrokeWidthBlod());
                if(params.getxCoordinates() != null)
                    mData.setmFXCoordinates(params.getxCoordinates());
            }
            maxMinValue();

            calculateYcoordinateScale();
        }
        return mData;
    }

    /**
     * 根据最值和展示方向，计算每个点在y方向上最大值最小值之间所占比例
     */
    protected void calculateYcoordinateScale(){
        List<KLinePointModel> list = new ArrayList<KLinePointModel>();
        double diff = mDMaxValue - mDMinValue;
        diff = diff <= 0.0 ? 1.0 : diff;

        for (int i = 0; i < mDValues[0].length; i++) {
            KLinePointModel point = new KLinePointModel();
            point.setdOpenValue(mDValues[0][i]);//开
            point.setdHighValue(mDValues[1][i]);//高
            point.setdLowValue(mDValues[2][i]);//低
            point.setdCloseValue(mDValues[3][i]);//收

            point.setfOpenCoordinate((float) calculateScale(point.getdOpenValue(),diff));
            point.setfHighCoordinate((float) calculateScale(point.getdHighValue(),diff));
            point.setfLowCoordinate((float) calculateScale(point.getdLowValue(),diff));
            point.setfCloseCoordinate((float) calculateScale(point.getdCloseValue(),diff));

            point.setbIsLine(mData.ismBIsUseLineNotBar());
            // 涨的情况 开盘价 《 收盘价 或者 开盘价=收盘价 且 当日收盘 》= 前日收盘
            if(mDValues[0][i] < mDValues[3][i]
                    || (i > 0 && mDValues[0][i] == mDValues[3][i] && mDValues[3][i] >= mDValues[3][i-1])){
                point.setmIColor(mData.getmIRiseColor());
            }else{
                point.setmIColor(mData.getmIDownColor());
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

    protected double calculateScale(double value,double diff){
        if(mDMaxValue == mDMinValue)
            return 0;
        return 1.0 - (value - mDMinValue)/diff;
    }

    //搜索最值以及下标
    protected void maxMinValue(){
        if(mDValues != null && mDValues[0] != null){
            mIMaxIndex = 0;
            mIMinIndex = 0;
            mDMaxValue = mDValues[0][0];
            mDMinValue= mDValues[0][0];
            for(int i = 0;i<mDValues.length;i++){
                findMaxMin(i);
            }
        }
    }

    protected void findMaxMin(int index){
        if(mDValues != null && mDValues.length > index){
            double[] temp = mDValues[index];
            for(int i=0;i<temp.length;i++){
                if(mDMaxValue < temp[i]){
                    mDMaxValue = temp[i];
                    mIMaxIndex = i;
                }else if(mDMinValue > temp[i]){
                    mDMinValue = temp[i];
                    mIMinIndex = i;
                }
            }
        }
    }

    @Override
    public void calculateYcoordinate(int graphHeight) {
        if(mData == null)
            return;
        List<KLinePointModel> list = mData.getIPointSet();
        if(list != null){
            for(KLinePointModel pointModel : list){
                pointModel.setfHighCoordinate(graphHeight*pointModel.getfHighCoordinate());
                pointModel.setfOpenCoordinate(graphHeight*pointModel.getfOpenCoordinate());
                pointModel.setfLowCoordinate(graphHeight*pointModel.getfLowCoordinate());
                pointModel.setfCloseCoordinate(graphHeight*pointModel.getfCloseCoordinate());
            }
        }
    }
}

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
    //转换并处理后的原始数据
    //每一个点数据都包括 开 高 低 收 四个子数据 --》 double[][4]
    protected double[][] mDValues;

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
        if(obj == null || !(obj instanceof double[][]))
            return null;
        values = (double[][])obj;
        if(values[0] != null){
            double[] first = values[0];
            if( first == null || first.length != 4)
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
                mData.setmDPointWidth(params.getPointWidth());
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

        for (int i = 0; i < mDValues.length; i++) {
            KLinePointModel point = new KLinePointModel();
            if(mDValues[i][0] != Constant.MINVALUE) {
                point.setdOpenValue(mDValues[i][0]);//开
                point.setdHighValue(mDValues[i][1]);//高
                point.setdLowValue(mDValues[i][2]);//低
                point.setdCloseValue(mDValues[i][3]);//收

                point.setfOpenCoordinate((float) calculateScale(point.getdOpenValue(), diff));
                point.setfHighCoordinate((float) calculateScale(point.getdHighValue(), diff));
                point.setfLowCoordinate((float) calculateScale(point.getdLowValue(), diff));
                point.setfCloseCoordinate((float) calculateScale(point.getdCloseValue(), diff));

                point.setbIsLine(mData.ismBIsUseLineNotBar());
                // 涨的情况 开盘价 《 收盘价 或者 开盘价=收盘价 且 当日收盘 》= 前日收盘
                if (mDValues[i][0] < mDValues[i][3]
                        || (i > 0 && mDValues[i][0] == mDValues[i][3] && mDValues[i][3] >= mDValues[i - 1][3])) {
                    point.setmIColor(mData.getmIRiseColor());
                } else {
                    point.setmIColor(mData.getmIDownColor());
                }
            }else{
                point.setmBNeedSkip(true);
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

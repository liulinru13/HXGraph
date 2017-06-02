package com.hxgraph.adapter.special;

import com.hxgraph.adapter.KLineAdapter;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.special.HXKLineStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.KLineModel;
import com.hxgraph.model.imp.group.special.HXKLineModel;
import com.hxgraph.model.imp.raw.HXKLinePointModel;
import com.hxgraph.model.imp.raw.KLinePointModel;
import com.hxgraph.model.param.KLineStrategyParam;
import com.hxgraph.model.param.special.HXKLineStrategyParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 带买入卖出点的k线图
 * Created by liulinru on 2017/4/26.
 */

public class HXKLineAdapter extends KLineAdapter {
    private int[] techArray;

    @Override
    protected KLineModel getNewModel() {
        return new HXKLineModel();
    }

    @Override
    public KLineModel wrapRawData(KLineStrategyParam params) {
        super.wrapRawData(params);
        if(params != null && params instanceof HXKLineStrategyParam){
            techArray = ((HXKLineStrategyParam) params).getTechArray();
            ((HXKLineModel)mData).setmIArrowBodyLength(((HXKLineStrategyParam) params).getArrowBodyLength());
            ((HXKLineModel)mData).setmIArrowHeadLength(((HXKLineStrategyParam) params).getArrowHeadLength());
            ((HXKLineModel)mData).setmITechDownColor(((HXKLineStrategyParam) params).getTechDownColor());
        }
        return mData;
    }

    @Override
    public GraphStrategyImp<KLineModel> getGraphStrategy() {
        mStrategy = new HXKLineStrategy();
        return mStrategy;
    }

    protected void calculateYcoordinateScale(){
        List<KLinePointModel> list = new ArrayList<KLinePointModel>();
        double diff = mDMaxValue - mDMinValue;
        diff = diff <= 0.0 ? 1.0 : diff;

        for (int i = 0; i < mDValues.length; i++) {
            HXKLinePointModel point = new HXKLinePointModel();
            point.setdOpenValue(mDValues[i][0]);//开
            point.setdHighValue(mDValues[i][1]);//高
            point.setdLowValue(mDValues[i][2]);//低
            point.setdCloseValue(mDValues[i][3]);//收

            if(techArray != null && techArray.length > i){
                if(techArray[i] != Constant.NULLVALUE)
                    point.setTech(new HXKLinePointModel.HXKLineTechModel(techArray[i]));
                else
                    point.setTech(null);
            }

            point.setfOpenCoordinate((float) calculateScale(point.getdOpenValue(),diff));
            point.setfHighCoordinate((float) calculateScale(point.getdHighValue(),diff));
            point.setfLowCoordinate((float) calculateScale(point.getdLowValue(),diff));
            point.setfCloseCoordinate((float) calculateScale(point.getdCloseValue(),diff));

            point.setbIsLine(mData.ismBIsUseLineNotBar());
            // 涨的情况 开盘价 《 收盘价 或者 开盘价=收盘价 且 当日收盘 》= 前日收盘
            if(mDValues[i][0] < mDValues[i][3]
                    || (i > 0 && mDValues[i][0] == mDValues[i][3] && mDValues[i][3] >= mDValues[i-1][3])){
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

    public int[] getTechArray() {
        return techArray;
    }

    public void setTechArray(int[] techArray) {
        this.techArray = techArray;
    }
}

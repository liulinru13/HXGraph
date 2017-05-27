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
        diff = diff < 0.0 ? 0.0 : diff;

        for (int i = 0; i < mDValues[0].length; i++) {
            HXKLinePointModel point = new HXKLinePointModel();
            point.setdOpenValue(mDValues[0][i]);//开
            point.setdHighValue(mDValues[1][i]);//高
            point.setdLowValue(mDValues[2][i]);//低
            point.setdCloseValue(mDValues[3][i]);//收

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

    public int[] getTechArray() {
        return techArray;
    }

    public void setTechArray(int[] techArray) {
        this.techArray = techArray;
    }
}

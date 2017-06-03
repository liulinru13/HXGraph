package com.hxgraph.adapter.special;

import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.special.StockWeatherglassGraphStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.special.MagicWaveGraphModel;
import com.hxgraph.model.imp.raw.MagicWavePointModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 大盘晴雨表
 * Created by liulinru on 2017/5/2.
 */

public class StockWeatherglassGraphAdapter extends MagicWaveGraphAdapter {

    @Override
    public GraphStrategyImp<MagicWaveGraphModel> getGraphStrategy() {
        mStrategy = new StockWeatherglassGraphStrategy();
        return mStrategy;
    }

    protected void calculateYcoordinateScale(){
        List<MagicWavePointModel> list = new ArrayList<MagicWavePointModel>();
        for (int i = 0; i < mDValues.length; i++) {
            MagicWavePointModel point = new MagicWavePointModel();
            if(mDValues[i] != Constant.MINVALUE) {
                point.setIValue(mDValues[i]);
                if (mColors != null && mColors.length >= 4) {
                    if (mDValues[i] <= 3 && mDValues[i] >= 1) {
                        point.setmIColor(mColors[mDValues[i] - 1]);
                    }
                } else {
                    point.setmIColor(Constant.iDefaultStrokeColor);
                }
            }else{
                point.setmBNeedSkip(true);
            }
            list.add(point);
        }
        mData.setIPointSet(list);
    }


}

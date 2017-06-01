package com.hxgraph.adapter.special;

import com.hxgraph.adapter.DotToLineAdapter;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.special.StockBarGraphStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.LineModel;
import com.hxgraph.model.imp.group.special.StockBarGraphModel;
import com.hxgraph.model.imp.raw.LinePointModel;
import com.hxgraph.model.param.DotToLineStrategyParam;
import com.hxgraph.model.param.special.StockBarGraphParams;

import java.util.ArrayList;
import java.util.List;

/**
 * 绘制 主力买卖、BBD 图形 数据适配器
 * Created by liulinru on 2017/4/27.
 */

public class StockBarGraphAdapter extends DotToLineAdapter {

    private int[] mColors;
    @Override
    public GraphStrategyImp<LineModel> getGraphStrategy() {
        mStrategy = new StockBarGraphStrategy();
        return mStrategy;
    }

    @Override
    protected LineModel getNewModel() {
        return new StockBarGraphModel();
    }

    @Override
    public LineModel wrapRawData(DotToLineStrategyParam params) {
        super.wrapRawData(params);
        if(params != null && params instanceof StockBarGraphParams) {
            ((StockBarGraphModel)mData).setType(((StockBarGraphParams) params).getType());
            ((StockBarGraphModel)mData).setColors(((StockBarGraphParams) params).getColors());
            mColors = ((StockBarGraphModel) mData).getColors();

        }
        return mData;
    }

    protected void calculateYcoordinateScale(GraphOrientation orientation){
        List<LinePointModel> list = new ArrayList<LinePointModel>();
        double diff = mDMaxValue - mDMinValue;
        diff = diff <= 0.0 ? 1.0 : diff;
        for (int i = 0; i < mDValues.length; i++) {
            LinePointModel point = new LinePointModel();
            point.setfXcoordinateRaw(Constant.fDefaultX);
            point.setfYValuePercent((float) (mDValues[i]/diff));
            point.setfValue((float) mDValues[i]);

            if(mColors != null && mColors.length > i){
                point.setmIColor(mColors[i]);
            }else{
                point.setmIColor(mData.getmIColor());
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

    public void calculateYcoordinate(int graphHeight){
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
}

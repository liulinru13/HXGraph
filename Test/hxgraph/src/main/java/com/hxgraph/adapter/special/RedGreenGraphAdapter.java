package com.hxgraph.adapter.special;

import com.hxgraph.adapter.GraphAdapterImp;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.special.RedGreenGraphStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.special.RedGreenGraphModel;
import com.hxgraph.model.imp.raw.BarModel;
import com.hxgraph.model.param.special.RedGreenGraphStrategyParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 红绿柱数据适配器
 * Created by liulinru on 2017/4/21.
 */

public class RedGreenGraphAdapter extends GraphAdapterImp<RedGreenGraphModel,RedGreenGraphStrategyParam> {

    protected int[] mIColors;//转换并处理后的对应原始数据的颜色参数

    @Override
    public GraphStrategyImp<RedGreenGraphModel> getGraphStrategy() {
        mStrategy = new RedGreenGraphStrategy();
        return mStrategy;
    }

    @Override
    protected RedGreenGraphModel getNewModel() {
        return new RedGreenGraphModel();
    }

    @Override
    public RedGreenGraphModel wrapRawData(RedGreenGraphStrategyParam params) {
        if(this.mORawData == null)
            return null;
        double[] values = null;
//        int[] colors = null;

        Object obj = doSomethingWithRawData(mORawData);
        if(obj == null)
            return null;

//        if(obj instanceof Object[] && ((Object[])obj).length == 2) {
//            //如果是object数组，第一个应该是数据
//            if (((Object[]) obj)[0] != null && ((Object[]) obj)[0] instanceof double[]) {
//                values = (double[]) ((Object[]) obj)[0];
//            }
//            //第二个应该是对应数据的颜色值
//            if (((Object[]) obj)[1] != null && ((Object[]) obj)[1] instanceof int[]) {
//                colors = (int[]) ((Object[]) obj)[1];
//            }
//        }
        if(obj instanceof double[])
            values = (double[])obj;

        if(values == null)
            return null;

        this.mDValues = values;
        mData = getNewModel();
        //线条参数设置
        if(params != null){
            mData.setmIColorRise(params.getColorRise());
            mData.setmFStrokeWidth(params.getStrokeWidth());
            mData.setmIColorDown(params.getColorDown());
            mData.setmIColors(params.getColors());
            this.mIColors = mData.getmIColors();
            mData.setmFReferenceLineRelativePos(params.getReferenceLineRelativePos());
            maxMin = params.getMaxMin();
            if(params.getxCoordinates() != null)
                mData.setmFXCoordinates(params.getxCoordinates());
        }
        calculateMaxMin();
        calculateYcoordinateScale();
        return mData;
    }

    protected void calculateYcoordinateScale(){
        List<BarModel> list = new ArrayList<BarModel>();
        double diff = mDMaxValue - mDMinValue;
        diff = diff < 0.0 ? 0.0 : diff;
        for (int i = 0; i < mDValues.length; i++) {
            BarModel point = new BarModel();
            point.setfXcoordinateRaw(Constant.fDefaultX);
            //实际值
            point.setfValue((float) mDValues[i]);
            //因为y坐标需要以参考线为基准来计算，现在没有高度，所以把比例先放到y坐标中
            //实际高度坐标等于 referenceCoordinate - height * value / diff
            //现在计算的是 value / diff
            point.setfYcoordinateRaw((float) (mDValues[i]/diff));
            //如果颜色值存在对应的也一并放入,否则根据正负值用默认颜色
            if(this.mIColors != null && this.mIColors.length > i){
                point.setmIColor(mIColors[i]);
            }else{
                point.setmIColor(mDValues[i] < 0 ? mData.getmIColorDown():mData.getmIColorRise());
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

    /**
     * 根据画布高度，计算每个点在y方向上的坐标
     * @param graphHeight
     */
    public void calculateYcoordinate(int graphHeight){
        if(mData == null)
            return;
        List<BarModel> list = mData.getIPointSet();
        //计算参考线位置
        float referenceCoordinate = (graphHeight - 2 * Constant.LINE_OFFSET)
                * mData.getmFReferenceLineRelativePos();
        float height = graphHeight / 3.0f;
        mData.setmFReferenceCoordinate(referenceCoordinate);
        if(list != null){
            referenceCoordinate += Constant.LINE_OFFSET;
            for(BarModel point : list){
                float scale = point.getfYcoordinateRaw();
                float lineHeight = height * scale;
                if(Math.abs(lineHeight) < 1.0f){
                    lineHeight = lineHeight > 0.0f ? 1.0f : -1.0f;
                }
                point.setfYcoordinateRaw(lineHeight);
            }
        }
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

    /**
     * 参数 values 必须是一个长度为2的Object数组，第一个元素是doubte[]
     * 第二个元素是int[]
     * @param obj
     * @return
     */
    @Override
    public Object doSomethingWithRawData(Object obj) {
//        double[] values = null;
//        int[] colors = null;
//        if(obj != null && obj instanceof Object[] && ((Object[])obj).length == 2) {
//            //如果是object数组，第一个应该是数据
//            if (((Object[]) obj)[0] != null && ((Object[]) obj)[0] instanceof double[]) {
//                values = ((double[]) ((Object[]) obj)[0]).clone();
//            }
//            //第二个应该是对应数据的颜色值
//            if (((Object[]) obj)[1] != null && ((Object[]) obj)[1] instanceof int[]) {
//                colors = ((int[]) ((Object[]) obj)[1]).clone();
//            }
//        }else if(obj instanceof double[]){
//            values = ((double[]) obj).clone();
//        }
//        if(values != null){
//            return new Object[]{values,colors};
//        }
//        return null;
        return obj;
    }
}

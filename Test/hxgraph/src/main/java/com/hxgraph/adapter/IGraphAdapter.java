package com.hxgraph.adapter;

import android.view.MotionEvent;

import com.hxgraph.model.IPoint;
import com.hxgraph.model.imp.PointCollectionImp;
import com.hxgraph.model.param.IStrategyParams;
import com.hxgraph.model.param.IStrategyParamsImp;

/**
 * 用于提供原始数据解析以及封装为绘图数据结构的
 * 适配器接口
 * Created by liulinru on 2017/4/20.
 */

public interface IGraphAdapter<T extends PointCollectionImp,P extends IStrategyParamsImp> {

    //绘图的绘制范围，画布的长宽
    /**
     * 向适配器设入原始数据源
     * @param rawData
     * @return
     */
    void receiveData(Object rawData);

    /**
     * 用于处理原始数据，包装为绘图所需数据结构的方法
     * @param params
     * @return
     */
    T wrapRawData(P params);

    /**
     * 获取解析包装后的、用于绘图的数据结构
     * @return
     */
    T getPointCollection();

    /**
     * 用于处理触摸事件，根据触摸位置返回对应点的数据
     * @param event
     * @return
     */
    IPoint onTouchAction(MotionEvent event);
}

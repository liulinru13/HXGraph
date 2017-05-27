package com.hxgraph.graphstrategy;

import android.graphics.Canvas;

import com.hxgraph.model.imp.PointCollectionImp;

/**
 * 实际绘制图线的类，根据不同图线的画法
 * 实现不同的绘制策略
 * Created by liulinru on 2017/4/20.
 */

public interface IGraphStrategy<T extends PointCollectionImp> {

    /**
     * 填充数据结构，执行具体绘制策略
     * @param pointCollection
     * @param canvas
     */
    void draw(T pointCollection,Canvas canvas);

//    /**
//     * 设入所需要的数据源
//     * @param pointCollection
//     */
//    void setData(T pointCollection);
}

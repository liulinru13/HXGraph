package com.hxgraph.adapter.special;

import com.hxgraph.adapter.KLineAdapter;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.KLineStrategy;
import com.hxgraph.graphstrategy.special.BoolGraphStrategy;
import com.hxgraph.model.imp.group.KLineModel;

/**
 * 股票 bool 线绘制 数据适配器
 * Created by liulinru on 2017/4/27.
 */

public class BoolGraphAdapter extends KLineAdapter {

    @Override
    public GraphStrategyImp<KLineModel> getGraphStrategy() {
        mStrategy = new BoolGraphStrategy();
        return mStrategy;
    }
}

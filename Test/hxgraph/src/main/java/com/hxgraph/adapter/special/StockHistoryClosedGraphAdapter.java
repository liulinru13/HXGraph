package com.hxgraph.adapter.special;

import com.hxgraph.adapter.DotToLineAdapter;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.special.StockHistroyClosedGraphStrategy;
import com.hxgraph.model.imp.group.LineModel;

/**
 * 股票 封闭的分时量 图表 数据适配器
 * Created by liulinru on 2017/4/27.
 */

public class StockHistoryClosedGraphAdapter extends DotToLineAdapter {

    @Override
    public GraphStrategyImp<LineModel> getGraphStrategy() {
        mStrategy = new StockHistroyClosedGraphStrategy();
        return mStrategy;
    }
}

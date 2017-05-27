package com.hxgraph.model.imp.raw;

import com.hxgraph.model.IPoint;

/**
 * 多柱并列的柱状图的数据结构，最基本的数据结构
 * Created by liulinru on 2017/4/24.
 */

public class BarsModel implements IPoint {
    private BarModel[] barModels;


    public BarModel[] getBarModels() {
        return barModels;
    }

    public void setBarModels(BarModel[] barModels) {
        this.barModels = barModels;
    }
}

package com.hxgraph.model.imp.group.special;

import com.hxgraph.model.imp.group.LineModel;
import com.hxgraph.model.param.special.StockBarGraphParams;

/**
 * Created by liulinru on 2017/4/27.
 */

public class StockBarGraphModel extends LineModel {
    private StockBarGraphParams.StockBarType type = StockBarGraphParams.StockBarType.BBD;//绘制类型
    private int[] colors;

    public StockBarGraphParams.StockBarType getType() {
        return type;
    }

    public void setType(StockBarGraphParams.StockBarType type) {
        this.type = type;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }
}

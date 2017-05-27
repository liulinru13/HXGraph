package com.hxgraph.model.param.special;

import com.hxgraph.model.param.DotToLineStrategyParam;

/**
 * Created by liulinru on 2017/4/27.
 */

public class StockBarGraphParams extends DotToLineStrategyParam{

    private StockBarType type = StockBarType.BBD;//绘制类型
    private int[] colors;//颜色数组

    public StockBarType getType() {
        return type;
    }

    public void setType(StockBarType type) {
        this.type = type;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public enum StockBarType{
        BBD,//BBD
        ZLMM,//主力买卖
    }

}

package com.hxgraph.model.param.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.param.KLineStrategyParam;

/**
 * 除了正常的k线之外可能需要绘制大盘晴雨表或者macd云参数买入卖出点
 * Created by liulinru on 2017/4/26.
 */

public class HXKLineStrategyParam extends KLineStrategyParam {
    private int techDownColor = Constant.iDefaultTechDownColor;
    private int[] techArray = null;
    private int arrowHeadLength = 4;
    private int arrowBodyLength = 5;

    public int getTechDownColor() {
        return techDownColor;
    }

    public void setTechDownColor(int techDownColor) {
        this.techDownColor = techDownColor;
    }

    public int[] getTechArray() {
        return techArray;
    }

    public void setTechArray(int[] techArray) {
        this.techArray = techArray;
    }

    public int getArrowHeadLength() {
        return arrowHeadLength;
    }

    public void setArrowHeadLength(int arrowHeadLength) {
        this.arrowHeadLength = arrowHeadLength;
    }

    public int getArrowBodyLength() {
        return arrowBodyLength;
    }

    public void setArrowBodyLength(int arrowBodyLength) {
        this.arrowBodyLength = arrowBodyLength;
    }
}

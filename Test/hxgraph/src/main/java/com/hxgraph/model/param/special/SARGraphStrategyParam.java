package com.hxgraph.model.param.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.param.IStrategyParamsImp;

/**
 * SAR曲线绘制参数
 * Created by liulinru on 2017/5/2.
 */

public class SARGraphStrategyParam extends IStrategyParamsImp {
    private float strokeWidth = Constant.fDefaultStrokeWidth;//线条宽度
    private int[] colors;



    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }
}

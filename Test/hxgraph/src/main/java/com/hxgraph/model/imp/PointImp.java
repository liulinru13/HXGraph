package com.hxgraph.model.imp;

import com.hxgraph.model.Constant;
import com.hxgraph.model.IPoint;

/**
 * 基础数据接口 IPoint 的抽象实现类
 * Created by liulinru on 2017/4/19.
 */

public abstract class PointImp implements IPoint {
    private int mIColor = Constant.iDefaultStrokeColor;//颜色

    /**
     * 获取对应点的颜色
     * @return
     */
    public int getmIColor() {
        return mIColor;
    }

    /**
     * 设置这个点显示的颜色，根据数据适配器不同，这个颜色可能不会被使用
     * @param mIColor
     */
    public void setmIColor(int mIColor) {
        this.mIColor = mIColor;
    }
}

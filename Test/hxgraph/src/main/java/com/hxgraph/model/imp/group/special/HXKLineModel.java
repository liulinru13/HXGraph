package com.hxgraph.model.imp.group.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.KLineModel;

/**
 * Created by liulinru on 2017/4/26.
 */

public class HXKLineModel extends KLineModel {
    private int mIArrowHeadLength = 4;
    private int mIArrowBodyLength = 5;
    private int mITechDownColor = Constant.iDefaultTechDownColor;


    public int getmIArrowHeadLength() {
        return mIArrowHeadLength;
    }

    public void setmIArrowHeadLength(int mIArrowHeadLength) {
        this.mIArrowHeadLength = mIArrowHeadLength;
    }

    public int getmIArrowBodyLength() {
        return mIArrowBodyLength;
    }

    public void setmIArrowBodyLength(int mIArrowBodyLength) {
        this.mIArrowBodyLength = mIArrowBodyLength;
    }

    public int getmITechDownColor() {
        return mITechDownColor;
    }

    public void setmITechDownColor(int mITechDownColor) {
        this.mITechDownColor = mITechDownColor;
    }
}

package com.hxgraph.adapter.special;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.raw.BarModel;

import java.util.List;

/**
 * 股票分时量 数据适配器，流程与红绿柱大致相同
 * Created by liulinru on 2017/4/24.
 */

public class StockHistoryGraphAdapter extends RedGreenGraphAdapter {
    public void calculateYcoordinate(int graphHeight){
        if(mData == null)
            return;
        List<BarModel> list = mData.getIPointSet();
        //计算参考线位置
        float referenceCoordinate = (graphHeight - 2 * Constant.LINE_OFFSET);
        mData.setmFReferenceCoordinate(referenceCoordinate);
        float height = graphHeight - Constant.LINE_OFFSET;
        if(list != null){
            for(BarModel point : list){
                float scale = point.getfYcoordinateRaw();
                float lineHeight = height * scale;
                if(lineHeight < 1.0f){
                    lineHeight = 1.5f;
                }
                point.setfYcoordinateRaw(lineHeight);
            }
        }
    }
}

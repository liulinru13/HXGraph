package com.hxgraph.graphstrategy.special;

import android.graphics.Canvas;

import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.special.RedGreenGraphModel;
import com.hxgraph.model.imp.raw.BarModel;

import java.util.List;

/**
 * 红绿柱和分时量
 * Created by liulinru on 2017/4/21.
 */

public class RedGreenGraphStrategy extends GraphStrategyImp<RedGreenGraphModel> {

    @Override
    protected void initPaint(RedGreenGraphModel pointCollection) {
        super.initPaint(pointCollection);
        mPaint.setStrokeWidth(mPointCollection.getmFStrokeWidth());
//        mPaint.setColor(mPointCollection.getmIColor());
    }

    @Override
    protected void draw(Canvas canvas) {
        if(mPointCollection == null)
            return;
        List<BarModel> data = mPointCollection.getIPointSet();
        if(data == null || data.size() <= 0)
            return;

        float fXscale = mPointCollection.getmFXscale();//x缩放比例
        float fYscale = mPointCollection.getmFYscale();//y缩放比例
        float fXcoordinate = Constant.LINE_OFFSET;//当前的x方向实际坐标
        float fXstepWidth = mPointCollection.getmFXSetp()*fXscale;//实际步宽
        float fTopLimit = mPointCollection.getmFTop()+1;
        float fBottomLimit = mPointCollection.getmIHeight() - 1.0f;
//        float referenceCoordinate = (fTopLimit + fBottomLimit)
//                * mPointCollection.getmFReferenceLineRelativePos();
        float referenceCoordinate = mPointCollection.getmFReferenceCoordinate();

        boolean calculateXSelf = false;
        float[] xCoordinates = null;
        if(mPointCollection.getmFXCoordinates() == null){
            calculateXSelf = true;
        }else{
            if(mPointCollection.getmFXCoordinates().length == data.size()) {
                calculateXSelf = false;
                xCoordinates = mPointCollection.getmFXCoordinates();
            }
        }

        for(int index = 0;index < data.size();index++){
            BarModel point = data.get(index);
            if(calculateXSelf)
                fXcoordinate += fXstepWidth * fXscale + mPointCollection.getmFStrokeWidth();
            else
                fXcoordinate = xCoordinates[index];
            if(point == null)
                continue;
            float yCoordinate = (referenceCoordinate - point.getfYcoordinateRaw())*fYscale;
            if(yCoordinate <= 0.0f){
                yCoordinate = fTopLimit;
            }else if(yCoordinate >= fBottomLimit){
                yCoordinate = fBottomLimit;
            }
            point.setfYcoordinateRaw(yCoordinate);
            point.setfXcoordinate(fXcoordinate);
            mPaint.setColor(point.getmIColor());
            canvas.drawLine(point.getfXcoordinate(),referenceCoordinate
                    ,point.getfXcoordinate(),point.getfYcoordinateRaw(),mPaint);
        }
    }
}

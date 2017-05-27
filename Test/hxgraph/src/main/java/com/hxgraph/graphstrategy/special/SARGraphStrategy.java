package com.hxgraph.graphstrategy.special;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.special.SARGraphModel;
import com.hxgraph.model.imp.raw.LinePointModel;

import java.util.List;

/**
 * SAR 曲线绘制策略
 * Created by liulinru on 2017/5/2.
 */

public class SARGraphStrategy extends GraphStrategyImp<SARGraphModel> {

    @Override
    protected void initPaint(SARGraphModel pointCollection) {
        super.initPaint(pointCollection);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mPointCollection.getmFStrokeWidth());
    }

    @Override
    protected void draw(Canvas canvas) {
        if(mPointCollection == null)
            return;
        List<LinePointModel> data = mPointCollection.getIPointSet();
        if(data == null || data.size() <= 0)
            return;

        float fXscale = mPointCollection.getmFXscale();//x缩放比例
        float fYscale = mPointCollection.getmFYscale();//y缩放比例
        float fXcoordinate = Constant.LINE_OFFSET;//当前的x方向实际坐标
        float fXstepWidth = mPointCollection.getmFXSetp()*fXscale;//实际步宽
        float fTopLimit = mPointCollection.getmFTop()+1;
        float fBottomLimit = mPointCollection.getmIHeight() - 1.0f;
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

        for(int index=0;index<data.size();index++){
            if(calculateXSelf)
                fXcoordinate += fXstepWidth * fXscale;
            else
                fXcoordinate = xCoordinates[index];

            LinePointModel point = data.get(index);
            float yCoordinate = point.getfYcoordinateRaw()*fYscale;
            if(yCoordinate <= 0.0f){
                yCoordinate = fTopLimit;
            }else if(yCoordinate >= fBottomLimit){
                yCoordinate = fBottomLimit;
            }
            mPaint.setColor(point.getmIColor());
            float middleX = fXcoordinate + fXstepWidth / 2.0f;
            canvas.drawCircle(middleX,yCoordinate,fXstepWidth / 2.0f,mPaint);
        }

    }
}

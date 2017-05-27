package com.hxgraph.graphstrategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.BarGraphModel;
import com.hxgraph.model.imp.raw.BarModel;
import com.hxgraph.model.imp.raw.BarsModel;

import java.util.List;

/**
 * Created by liulinru on 2017/4/24.
 */

public class BarGraphStrategy extends GraphStrategyImp<BarGraphModel> {

    @Override
    protected void initPaint(BarGraphModel pointCollection) {
        super.initPaint(pointCollection);
    }

    @Override
    protected void draw(Canvas canvas) {
        if(mPointCollection == null)
            return;
        List<BarsModel> data = mPointCollection.getIPointSet();
        if(data == null || data.size() <= 0)
            return;

        float fXscale = mPointCollection.getmFXscale();//x缩放比例
        float fYscale = mPointCollection.getmFYscale();//y缩放比例
        float fXcoordinate = Constant.LINE_OFFSET;//当前的x方向实际坐标
        float fXstepWidth = mPointCollection.getmFXSetp()*fXscale;//实际步宽
        float fTopLimit = mPointCollection.getmFTop()+1;
        float fBottomLimit = mPointCollection.getmIHeight() - 1.0f;
        float referenceCoordinate = mPointCollection.getmFYReferenceCoordinateRaw();
        float strokeWidth = mPointCollection.getmFStrokeWidth();
        mPaint.setStrokeWidth(strokeWidth);

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
            BarsModel point = data.get(index);
            if(calculateXSelf)
                fXcoordinate += fXstepWidth * fXscale + mPointCollection.getmFStrokeWidth();
            else
                fXcoordinate = xCoordinates[index];
            if(point == null || point.getBarModels() == null)
                continue;
            BarModel[] models = point.getBarModels();
            for(int i = 0;i<models.length;i++){
                BarModel model = models[i];
                float yCoordinate = (referenceCoordinate - model.getfYcoordinateRaw())*fYscale;
                boolean isUp = model.getfYcoordinateRaw() > 0.0f;
                if(yCoordinate <= 0.0f){
                    yCoordinate = fTopLimit;
                }else if(yCoordinate >= fBottomLimit){
                    yCoordinate = fBottomLimit;
                }
                model.setfYcoordinateRaw(yCoordinate);
                model.setfXcoordinate(fXcoordinate);
                mPaint.setColor(model.getmIColor());
                if(model.isbIsStroke()){
                    if(mPaint.getStyle() == Paint.Style.FILL){
                        mPaint.setStyle(Paint.Style.STROKE);
                    }
                }else{
                    if(mPaint.getStyle() == Paint.Style.STROKE){
                        mPaint.setStyle(Paint.Style.FILL);
                    }
                }
                //柱子向上
                if(isUp) {
                    canvas.drawRect(model.getfXcoordinate(), model.getfYcoordinateRaw()
                            ,fXcoordinate+model.getfBarWidth(), referenceCoordinate, mPaint);
                }
                //柱子向下
                else{
                    canvas.drawRect(model.getfXcoordinate(),referenceCoordinate,
                            fXcoordinate+model.getfBarWidth(),model.getfYcoordinateRaw(), mPaint);
                }
                //不填充内部与填充内部实际宽度有不同
                if(calculateXSelf) {
                    if (model.isbIsStroke()) {
                        fXcoordinate += model.getfBarWidth() + strokeWidth;
                    } else {
                        fXcoordinate += model.getfBarWidth();
                    }
                }
            }
        }
    }
}

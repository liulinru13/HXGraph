package com.hxgraph.graphstrategy.special;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.graphstrategy.IGraphStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.PointCollectionImp;
import com.hxgraph.model.imp.group.special.VolumeGraphModel;
import com.hxgraph.model.imp.raw.BarModel;

import java.util.List;

/**
 * 分时量 指标图形 绘制策略
 * Created by liulinru on 2017/4/24.
 */

public class VolumeGraphStrategy extends GraphStrategyImp<VolumeGraphModel> {

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
        float referenceCoordinate = fBottomLimit;

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
                fXcoordinate += fXstepWidth * fXscale + point.getfStrokeWidth();
            else
                fXcoordinate = xCoordinates[index];
            if(point == null || point.ismBNeedSkip())
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
            mPaint.setStrokeWidth(point.getfStrokeWidth());
            if(point.isbIsStroke()){
                if(mPaint.getStyle() == Paint.Style.FILL){
                    mPaint.setStyle(Paint.Style.STROKE);
                }
            }else{
                if(mPaint.getStyle() == Paint.Style.STROKE){
                    mPaint.setStyle(Paint.Style.FILL);
                }
            }
            if(!point.isbUseLine()) {
                canvas.drawRect(point.getfXcoordinate(), point.getfYcoordinateRaw()
                        , fXcoordinate + point.getfBarWidth(), referenceCoordinate, mPaint);
            }else{
                float middleX = fXcoordinate + point.getfBarWidth() / 2.0f;
                canvas.drawLine(middleX,point.getfYcoordinateRaw(),middleX,referenceCoordinate,mPaint);
            }
            if(calculateXSelf) {
                //不填充内部与填充内部实际宽度有不同
                if (point.isbIsStroke() && !point.isbUseLine()) {
                    fXcoordinate += point.getfBarWidth() + point.getfStrokeWidth();
                } else {
                    fXcoordinate += point.getfBarWidth();
                }
            }
        }
    }
}

package com.hxgraph.graphstrategy.special;

import android.graphics.Canvas;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.special.MagicWaveGraphModel;
import com.hxgraph.model.imp.raw.MagicWavePointModel;

import java.util.List;

/**
 * 股票大盘晴雨表绘图
 * Created by liulinru on 2017/5/2.
 */

public class StockWeatherglassGraphStrategy extends MagicWaveGraphStrategy {

    @Override
    protected void initPaint(MagicWaveGraphModel pointCollection) {
        super.initPaint(pointCollection);
//        mPaint.setStrokeWidth(mPointCollection.getmFStrokeWidth());
        mPaint.setStrokeWidth(6);
    }

    @Override
    protected void draw(Canvas canvas) {
        List<MagicWavePointModel> data = mPointCollection.getIPointSet();
        if(data == null || data.size() <= 0)
            return;

        float fXscale = mPointCollection.getmFXscale();//x缩放比例
        float fYscale = mPointCollection.getmFYscale();//y缩放比例
        float fXcoordinate = Constant.LINE_OFFSET;//当前的x方向实际坐标
        float fXstepWidth = mPointCollection.getmFXSetp()*fXscale;//实际步宽
        float fTopLimit = mPointCollection.getmFTop()+1;
        float fBottomLimit = mPointCollection.getmIHeight() - 1.0f;
        float height = fBottomLimit - fTopLimit;
        float stepY = height / 3.0f;
        float startY = 0.0f;
        float stopY = 0.0f;
        float stopX = 0.0f;
        boolean calculateXSelf = false;
        float[] xCoordinates = null;

        for(int index=0;index<data.size();index++){
            MagicWavePointModel point = data.get(index);
            if(calculateXSelf) {
                fXcoordinate += fXstepWidth * fXscale;
                stopX = fXcoordinate + fXstepWidth * fXscale;
            }
            else {
                fXcoordinate = xCoordinates[index];
                if(index ==  data.size() -1)
                    stopX = fXcoordinate + fXstepWidth * fXscale;
                else
                    stopX = xCoordinates[index+1];
            }
            if(point.ismBNeedSkip())
                continue;
            mPaint.setStrokeWidth(6);
            switch (point.getIValue()){
                case 1://大盘晴
                    stopY = startY = fTopLimit + (stepY - 6)/2;
                    mPaint.setColor(point.getmIColor());
                    canvas.drawLine(fXcoordinate,startY,stopX,stopY,mPaint);
                    break;
                case 2://大盘阴
                    stopY = startY = fTopLimit + (stepY - 6)/2 + stepY;
                    mPaint.setColor(point.getmIColor());
                    canvas.drawLine(fXcoordinate,startY,stopX,stopY,mPaint);
                    break;
                case 3://大盘雨
                    stopY = startY = fTopLimit + (stepY - 6)/2 + stepY*2;
                    mPaint.setColor(point.getmIColor());
                    canvas.drawLine(fXcoordinate,startY,stopX,stopY,mPaint);
                    break;
                default:
                    break;
            }
            //不是最后一个，值不同，画连线
            if(index != data.size() -1 && data.get(index+1) != null
                    && data.get(index+1).getIValue() != point.getIValue()){
                if(mPointCollection.getmIColors() != null && mPointCollection.getmIColors().length >= 4){
                    mPaint.setColor(mPointCollection.getmIColors()[3]);
                    mPaint.setStrokeWidth(1);
                    float nextY = 0;
                    if(data.get(index+1).getIValue() == 1){
                        nextY = fTopLimit + (stepY - 6) /2 + stepY * 0;
                    }else if(data.get(index+1).getIValue() == 2){
                        nextY = fTopLimit + (stepY - 6) /2 + stepY * 1;
                    }else if(data.get(index+1).getIValue() == 3){
                        nextY = fTopLimit + (stepY - 6) /2 + stepY * 2;
                    }
                    canvas.drawLine(stopX,stopY,stopX,nextY,mPaint);
                }
            }
        }

    }
}

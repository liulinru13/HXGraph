package com.hxgraph.graphstrategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.KLineModel;
import com.hxgraph.model.imp.raw.KLinePointModel;

import java.util.List;

/**
 * Created by liulinru on 2017/4/25.
 */

public class KLineStrategy extends GraphStrategyImp<KLineModel> {

    @Override
    protected void draw(Canvas canvas) {
        List<KLinePointModel> list = mPointCollection.getIPointSet();
        if(list == null || list.size() <= 0)
            return;

        float fXscale = mPointCollection.getmFXscale();//x缩放比例
        float fYscale = mPointCollection.getmFYscale();//y缩放比例
        float fXcoordinate = Constant.LINE_OFFSET;//当前的x方向实际坐标
        float fXstepWidth = mPointCollection.getmFXSetp()*fXscale;//实际步宽
        float fTopLimit = mPointCollection.getmFTop()+1;
        float fBottomLimit = mPointCollection.getmIHeight() - 1.0f;
        float strokeWidth = mPointCollection.getmFStrokeWidth();
        float barWidth = (float) mPointCollection.getmDPointWidth()*fXscale;
        float strokeWidthBlod = mPointCollection.getmFStrokeWidthBlod();//加粗的线条宽度

        boolean calculateXSelf = false;
        float[] xCoordinates = null;
        if(mPointCollection.getmFXCoordinates() == null){
            calculateXSelf = true;
        }else{
            if(mPointCollection.getmFXCoordinates().length == list.size()) {
                calculateXSelf = false;
                xCoordinates = mPointCollection.getmFXCoordinates();
            }
        }
        if(calculateXSelf)
            fXcoordinate += barWidth * fXscale + mPointCollection.getmFStrokeWidth();
        else
            fXcoordinate = xCoordinates[0];
        for(int i=0;i<list.size();i++){
            KLinePointModel point = list.get(i);

            boolean isRise = false;//是否涨
            boolean isFill = false;//本次所使用的线条宽度
            boolean isLine = false;//本次是否使用的是线条
            float tempStrokeWidth = 0.0f;
            float middle = fXcoordinate + barWidth / 2.0f;

            if(!point.ismBNeedSkip()) {
                point.setfOpenCoordinate(getValue(point.getfOpenCoordinate(), fTopLimit, fBottomLimit));
                point.setfHighCoordinate(getValue(point.getfHighCoordinate(), fTopLimit, fBottomLimit));
                point.setfLowCoordinate(getValue(point.getfLowCoordinate(), fTopLimit, fBottomLimit));
                point.setfCloseCoordinate(getValue(point.getfCloseCoordinate(), fTopLimit, fBottomLimit));

                mPaint.setColor(point.getmIColor());

                // 涨的情况 开盘价 《 收盘价 或者 开盘价=收盘价 且 当日收盘 》= 前日收盘
                if (point.getdOpenValue() < point.getdCloseValue()
                        || (i > 0 && point.getdOpenValue() == point.getdCloseValue()
                        && list.get(i - 1) != null && point.getdCloseValue() >= list.get(i - 1).getdCloseValue())) {
                    isRise = true;
                }
                //涨
                if (isRise) {
                    //加粗线条
                    mPaint.setStrokeWidth(strokeWidthBlod);
                    tempStrokeWidth = strokeWidthBlod;
                    //使用线条
                    if (point.isbIsLine()) {
                        isLine = true;
                        canvas.drawLine(middle, point.getfLowCoordinate(), middle, point.getfHighCoordinate(), mPaint);
                    }
                    //使用柱
                    else {
                        if (point.getfOpenCoordinate() == point.getfCloseCoordinate()) {
                            //填充
                            mPaint.setStyle(Paint.Style.FILL);
                            isFill = true;
                            canvas.drawRect(fXcoordinate, point.getfCloseCoordinate(),
                                    fXcoordinate + barWidth, point.getfOpenCoordinate() + 1, mPaint);
                        } else {
                            //不填充
                            mPaint.setStyle(Paint.Style.STROKE);
                            canvas.drawRect(fXcoordinate, point.getfCloseCoordinate(),
                                    fXcoordinate + barWidth, point.getfOpenCoordinate() + 1, mPaint);
                            canvas.drawLine(middle, point.getfHighCoordinate(), middle,
                                    point.getfCloseCoordinate() + Constant.LINE_OFFSET, mPaint);
                            canvas.drawLine(middle, point.getfLowCoordinate(), middle,
                                    point.getfOpenCoordinate() - Constant.LINE_OFFSET, mPaint);
                        }
                    }
                }
                //跌
                else {
                    mPaint.setStrokeWidth(strokeWidth);
                    tempStrokeWidth = strokeWidth;
                    //使用柱
                    if (!point.isbIsLine()) {
                        mPaint.setStyle(Paint.Style.FILL);
                        isFill = true;
                        if (point.getfOpenCoordinate() == point.getfCloseCoordinate()) {
                            canvas.drawRect(fXcoordinate, point.getfOpenCoordinate(),
                                    fXcoordinate + barWidth, point.getfCloseCoordinate() + 1, mPaint);
                        } else {
                            canvas.drawRect(fXcoordinate, point.getfOpenCoordinate(),
                                    fXcoordinate + barWidth, point.getfCloseCoordinate(), mPaint);
                        }
                    }
                    canvas.drawLine(middle, point.getfLowCoordinate(), middle, point.getfHighCoordinate(), mPaint);
                }
            }//!point.ismBNeedSkip()
            if(calculateXSelf) {
                fXcoordinate += barWidth;
                if(!isLine && isFill)
                    fXcoordinate += tempStrokeWidth;
            }
            else{
                if(i+1 < xCoordinates.length){
                    fXcoordinate = xCoordinates[i+1];
                }
            }
            drawTechLine(canvas, fBottomLimit, i, middle, barWidth, point);
        }
    }

    /**
     * 绘制技术线
     * @param canvas
     * @param index
     * @param middleX
     * @param barWidth
     * @param point
     */
    protected void drawTechLine(Canvas canvas , float height,int index ,float middleX ,float barWidth, KLinePointModel point){
    }

    protected float getValue(float value,float top,float bottom){
        if(value <= 0.0f){
            value = top;
        }else if(value >= bottom){
            value = bottom;
        }
        return value;
    }
}

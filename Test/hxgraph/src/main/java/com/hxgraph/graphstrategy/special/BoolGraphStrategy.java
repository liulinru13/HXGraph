package com.hxgraph.graphstrategy.special;

import android.graphics.Canvas;

import com.hxgraph.graphstrategy.KLineStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.raw.KLinePointModel;

import java.util.List;

/**
 * 绘制bool线条
 * Created by liulinru on 2017/4/27.
 */

public class BoolGraphStrategy extends KLineStrategy {

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
        mPaint.setStrokeWidth(strokeWidth);

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
            if(!point.ismBNeedSkip()) {
//            getValue(,fTopLimit,fBottomLimit)
                point.setfOpenCoordinate(getValue(point.getfOpenCoordinate(), fTopLimit, fBottomLimit));
                point.setfHighCoordinate(getValue(point.getfHighCoordinate(), fTopLimit, fBottomLimit));
                point.setfLowCoordinate(getValue(point.getfLowCoordinate(), fTopLimit, fBottomLimit));
                point.setfCloseCoordinate(getValue(point.getfCloseCoordinate(), fTopLimit, fBottomLimit));

                mPaint.setColor(point.getmIColor());

                boolean isRise = false;//是否涨
                boolean isFill = false;//本次所使用的线条宽度
                boolean isLine = false;//本次是否使用的是线条

                float tempStrokeWidth = 0.0f;
                // 涨的情况 开盘价 《 收盘价 或者 开盘价=收盘价 且 当日收盘 》= 前日收盘
                if (point.getdOpenValue() < point.getdCloseValue()
                        || (i > 0 && point.getdOpenValue() == point.getdCloseValue()
                        && list.get(i - 1) != null && point.getdCloseValue() >= list.get(i - 1).getdCloseValue())) {
                    isRise = true;
                }
                float middle = fXcoordinate + barWidth / 2.0f;
                canvas.drawLine(fXcoordinate, point.getfOpenCoordinate(), middle,
                        point.getfOpenCoordinate(), mPaint);
                canvas.drawLine(middle, point.getfCloseCoordinate(), fXcoordinate + barWidth,
                        point.getfCloseCoordinate(), mPaint);
                canvas.drawLine(middle, point.getfLowCoordinate(), middle,
                        point.getfHighCoordinate(), mPaint);
//            涨
                if (isRise) {
                    canvas.drawLine(fXcoordinate, point.getfOpenCoordinate(), middle,
                            point.getfOpenCoordinate(), mPaint);
                    canvas.drawLine(middle, point.getfCloseCoordinate(), fXcoordinate + barWidth,
                            point.getfCloseCoordinate(), mPaint);
                    canvas.drawLine(middle, point.getfLowCoordinate(), middle,
                            point.getfHighCoordinate(), mPaint);
                }
                //跌
                else {
                    canvas.drawLine(fXcoordinate, point.getfOpenCoordinate(), middle,
                            point.getfOpenCoordinate(), mPaint);
                    canvas.drawLine(middle, point.getfCloseCoordinate(), fXcoordinate + barWidth,
                            point.getfCloseCoordinate(), mPaint);
                    canvas.drawLine(middle, point.getfLowCoordinate(), middle,
                            point.getfHighCoordinate(), mPaint);
                }
            }
            if(calculateXSelf) {
                fXcoordinate += barWidth;
            }
            else{
                if(i+1 < xCoordinates.length){
                    fXcoordinate = xCoordinates[i+1];
                }
            }
        }
    }
}

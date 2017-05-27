package com.hxgraph.graphstrategy.special;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.hxgraph.graphstrategy.DotToLineStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.special.StockBarGraphModel;
import com.hxgraph.model.imp.raw.LinePointModel;
import com.hxgraph.model.param.special.StockBarGraphParams;

import java.util.List;

/**
 * Created by liulinru on 2017/4/27.
 */

public class StockBarGraphStrategy extends DotToLineStrategy {

    @Override
    protected void draw(Canvas canvas) {
        List<LinePointModel> data = mPointCollection.getIPointSet();
        if(data == null || data.size() <= 0)
            return;

        float fXscale = mPointCollection.getmFXscale();//x缩放比例
        float fYscale = mPointCollection.getmFYscale();//y缩放比例
        float fXcoordinate = Constant.LINE_OFFSET;//当前的x方向实际坐标
        float fXstepWidth = mPointCollection.getmFXSetp()*fXscale;//实际步宽
        float fTopLimit = mPointCollection.getmFTop()+1;
        float fBottomLimit = mPointCollection.getmIHeight() - 1.0f;
        float fHeight = fBottomLimit - fTopLimit;
        float fMin = mPointCollection.getmMaxMinPoints().getMinPoint().getfValue();
        float fMax = mPointCollection.getmMaxMinPoints().getMaxPoint().getfValue();
        float diff = fMax - fMin;
        float referenceCoordinate;
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
        //bbd画线的参考线位置
        if(((StockBarGraphModel)mPointCollection).getType() == StockBarGraphParams.StockBarType.BBD){
            referenceCoordinate = fBottomLimit - (fHeight * Math.abs(fMin)/diff);
        }else{
            fHeight /= 2.0f;
            referenceCoordinate = fTopLimit + fBottomLimit / 2.0f;
            diff /= 2.0f;
        }

        referenceCoordinate += Constant.LINE_OFFSET;
        mPaint.setStyle(Paint.Style.FILL);

        for(int index = 0;index < data.size();index++){
            if(calculateXSelf)
                fXcoordinate += fXstepWidth * fXscale;
            else
                fXcoordinate = xCoordinates[index];
            LinePointModel point = data.get(index);
            float lightHeight = fHeight * point.getfValue() / diff;
            if(Math.abs(lightHeight) < 1.0f){
                lightHeight = lightHeight > 0 ? 1.0f: -1.0f;
            }
            float stopY = referenceCoordinate - lightHeight;
            if(stopY <= fTopLimit)
                stopY = fTopLimit;
            else if(stopY >= fBottomLimit)
                stopY = fBottomLimit;
            mPaint.setColor(point.getmIColor());

            if(lightHeight > 0 ){
                //向上
                canvas.drawRect(fXcoordinate,stopY,fXcoordinate + fXstepWidth,referenceCoordinate,mPaint);
            }else{
                //向下
                canvas.drawRect(fXcoordinate,referenceCoordinate,fXcoordinate + fXstepWidth,stopY,mPaint);
            }
        }
    }
}

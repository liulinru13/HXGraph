package com.hxgraph.graphstrategy.special;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.hxgraph.graphstrategy.DotToLineStrategy;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.raw.LinePointModel;

import java.util.List;

/**
 * Created by liulinru on 2017/4/27.
 */

public class StockHistroyClosedGraphStrategy extends DotToLineStrategy {

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

        Path path = new Path();
        path.reset();
        boolean moved = false;

        for(int index = 0;index < data.size();index++){

            if(calculateXSelf)
                fXcoordinate += fXstepWidth * fXscale;
            else
                fXcoordinate = xCoordinates[index];

            LinePointModel point = data.get(index);
            float lightHeight = (float)(fHeight * Math.abs(point.getfValue() - fMin) / diff);
            if(lightHeight < 1.0f)
                lightHeight = 1.5f;
            float stopY = (fBottomLimit - lightHeight) * fYscale;
            //画线
            if(moved){
                path.lineTo(fXcoordinate,stopY);
            }else{
                path.moveTo(fTopLimit,fBottomLimit);
                path.lineTo(fTopLimit,stopY);
                path.lineTo(fXcoordinate,stopY);
                moved = true;
            }
        }
        if(data.size() > 0 && moved){
            path.lineTo(fXcoordinate,fBottomLimit);
        }
        path.close();
        mPaint.setColor(mPointCollection.getmIColor());
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path,mPaint);
    }
}

package com.hxgraph.graphstrategy;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.LineModel;
import com.hxgraph.model.imp.raw.LinePointModel;

import java.util.List;

/**
 * Created by liulinru on 2017/4/20.
 */

public class DotToLineStrategy extends GraphStrategyImp<LineModel> {

    @Override
    protected void initPaint(LineModel pointCollection) {
        super.initPaint(pointCollection);
        mPaint.setStrokeWidth(mPointCollection.getmFStrokeWidth());
        mPaint.setColor(mPointCollection.getmIColor());
    }

    @Override
    protected void draw(Canvas canvas) {
        //
        LineModel.LineType lineType = mPointCollection.getmOLineType();
        //实线
        //虚线
        if(lineType == LineModel.LineType.DOTTED_LINE){
            DashPathEffect pathEffect = new DashPathEffect(mPointCollection.getmDotLineParam(),0);
            mPaint.setPathEffect(pathEffect);
        }
        List<LinePointModel> data = mPointCollection.getIPointSet();
        if(data == null || data.size() <= 0)
            return;
        LinePointModel firstPoint = data.get(0);
        if(firstPoint == null)
            return;

        drawByYCoordinate(canvas,data, firstPoint);
    }

    //需要知道绘图高度来计算y的实际坐标
    private void drawByYCoordinate(Canvas canvas,List<LinePointModel> data,
                                   LinePointModel firstPoint){
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
        if(calculateXSelf)
            calculateXY(firstPoint,fYscale,fTopLimit,fBottomLimit,fXcoordinate);
        else
            calculateXY(firstPoint,fYscale,fTopLimit,fBottomLimit,xCoordinates[0]);

        for(int index = 0;index < data.size()-1;index++){
            LinePointModel point = data.get(index);
            LinePointModel nextPoint = data.get(index+1);
            if(point == null)
                continue;
            if(calculateXSelf)
                fXcoordinate += fXstepWidth * fXscale;
            else
                fXcoordinate = xCoordinates[index];
            calculateXY(nextPoint,fYscale,fTopLimit,fBottomLimit,fXcoordinate);
            canvas.drawLine(point.getfXcoordinate(),point.getfYcoordinate()
                    ,nextPoint.getfXcoordinate(),nextPoint.getfYcoordinate(),mPaint);
        }
    }

    protected void calculateXY(LinePointModel point,float fYscale,float fTopLimit,
                             float fBottomLimit,float fXcoordinate){
        float yCoordinate = Math.min(point.getfYcoordinateRaw()*fYscale,fBottomLimit);
        yCoordinate = Math.max(yCoordinate,fTopLimit);

        point.setfXcoordinate(fXcoordinate);
        point.setfYcoordinate(yCoordinate);
    }
}

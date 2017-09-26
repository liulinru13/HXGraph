package com.hxgraph.graphstrategy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;

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

        if(data == null || data.size() == 0)
            return;

        float fXscale = mPointCollection.getmFXscale();//x缩放比例
        float fYscale = mPointCollection.getmFYscale();//y缩放比例
        float fXcoordinate = Constant.LINE_OFFSET;//当前的x方向实际坐标
        float fXstepWidth = mPointCollection.getmFXSetp()*fXscale;//实际步宽
        float fTopLimit = mPointCollection.getmFTop()+1;
        float fBottomLimit = mPointCollection.getmIHeight() - 1.0f;
        float fYMaxValue = 0.0f;
        float fYMinValue = 0.0f;

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
        //自己计算x坐标还是使用外部传进来的坐标
        if(calculateXSelf)
            calculateXY(firstPoint,fYscale,fTopLimit,fBottomLimit,fXcoordinate);
        else
            calculateXY(firstPoint,fYscale,fTopLimit,fBottomLimit,xCoordinates[0]);

        //是否需要将线条所围成的图像的背景色设置为渐变的线条颜色
        if(mPointCollection.ismBFillColor()) {
            mPath.reset();
            mPath.setFillType(Path.FillType.WINDING);
            //左下第一个点
            mPath.moveTo(data.get(0).getfXcoordinate(), fBottomLimit);
            mPath.lineTo(data.get(0).getfXcoordinate(), data.get(0).getfYcoordinate());
            fYMaxValue = data.get(0).getfYcoordinate();
            fYMinValue = data.get(0).getfYcoordinate();
        }
        LinePointModel nextPoint = null;
        for(int index = 0;index < data.size()-1;index++){
            LinePointModel point = data.get(index);
            nextPoint = data.get(index+1);

            if(calculateXSelf)
                fXcoordinate += fXstepWidth * fXscale;
            else
                fXcoordinate = xCoordinates[index+1];
            calculateXY(nextPoint,fYscale,fTopLimit,fBottomLimit,fXcoordinate);
            //两点成线，只要有一个点跳过，这条线就画不了，但是需要保留点的x坐标
            if(point == null || point.ismBNeedSkip() || nextPoint.ismBNeedSkip())
                continue;
            if(fYMaxValue < nextPoint.getfYcoordinate()){
                fYMaxValue = nextPoint.getfYcoordinate();
            }
            if(fYMinValue > nextPoint.getfYcoordinate()){
                fYMinValue = nextPoint.getfYcoordinate();
            }
            canvas.drawLine(point.getfXcoordinate(),point.getfYcoordinate()
                    ,nextPoint.getfXcoordinate(),nextPoint.getfYcoordinate(),mPaint);
            if(mPointCollection.ismBFillColor()) {
                mPath.lineTo(nextPoint.getfXcoordinate(), nextPoint.getfYcoordinate());
//                Log.e("DOT","lineTo to "+ nextPoint.getfXcoordinate() + "," + nextPoint.getfYcoordinate());
            }

        }
        if(mPointCollection.ismBFillColor()) {
            if (nextPoint != null) {
                mPath.lineTo(nextPoint.getfXcoordinate(), fBottomLimit);
//            Log.e("DOT","lineTo to "+ nextPoint.getfXcoordinate() + "," + fBottomLimit);
                mPath.close();
                int bgColor = mPointCollection.getmIBgColor();
                if(bgColor == Constant.NULLVALUE)
                    bgColor = mPointCollection.getmIColor();
                LinearGradient linearGradient = new LinearGradient(0, fYMinValue, 0,
                        fYMaxValue, bgColor, Color.TRANSPARENT, Shader.TileMode.CLAMP);
                mPaintTrans.setStyle(Paint.Style.FILL);
                mPaintTrans.setShader(linearGradient);
                canvas.drawPath(mPath, mPaintTrans);
//                if(mWRcontext != null && mWRcontext.get() != null){
//                    Context context = mWRcontext.get();
//                }

            }
        }
    }

    protected void calculateXY(LinePointModel point,float fYscale,float fTopLimit,
                             float fBottomLimit,float fXcoordinate){
        //跳过的线条不需要计算y坐标
        if(!point.ismBNeedSkip()) {
            float yCoordinate = Math.min(point.getfYcoordinateRaw() * fYscale, fBottomLimit);
            yCoordinate = Math.max(yCoordinate, fTopLimit);
            point.setfYcoordinate(yCoordinate);
        }
        point.setfXcoordinate(fXcoordinate);
    }
}

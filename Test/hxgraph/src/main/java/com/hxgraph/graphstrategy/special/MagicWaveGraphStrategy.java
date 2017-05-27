package com.hxgraph.graphstrategy.special;

import android.graphics.Canvas;

import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.group.special.MagicWaveGraphModel;
import com.hxgraph.model.imp.raw.LinePointModel;
import com.hxgraph.model.imp.raw.MagicWavePointModel;

import java.util.List;

/**
 * 神奇电波绘图策略
 * Created by liulinru on 2017/4/28.
 */

public class MagicWaveGraphStrategy extends GraphStrategyImp<MagicWaveGraphModel> {

    @Override
    protected void initPaint(MagicWaveGraphModel pointCollection) {
        super.initPaint(pointCollection);
        mPaint.setStrokeWidth(mPointCollection.getmFStrokeWidth());
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
        float margin = height * 0.08f;
        float diffHeight = height / 6;
        fTopLimit += margin;
        fBottomLimit -= margin;
        float referenceYcoordinate = height / 2 + Constant.LINE_OFFSET;
        boolean calculateXSelf = false;
        float[] xCoordinates = null;
        int left = 0;
        float right = 0.0f;
        int drawStyle = mPointCollection.getmIDrawStyle();
        if(mPointCollection.getmFXCoordinates() == null){
            calculateXSelf = true;
            right = canvas.getWidth() -left;
        }else{
            if(mPointCollection.getmFXCoordinates().length == data.size()) {
                calculateXSelf = false;
                xCoordinates = mPointCollection.getmFXCoordinates();
                right = xCoordinates[xCoordinates.length -1] + fXstepWidth;
            }
        }

        for(int index=0;index<data.size();index++){
            MagicWavePointModel point = data.get(index);
            if(calculateXSelf)
                fXcoordinate += fXstepWidth * fXscale;
            else
                fXcoordinate = xCoordinates[index];
            float stopY = 0;
            float diff = 0;
            int sign = point.getIValue();
            switch (sign){
                case Constant.MAGIC_WAVE_TOP://顶-卖点
                    mPaint.setColor(point.getmIColor());
                    stopY = fBottomLimit;
                    diff = -diffHeight;
                    break;
                case Constant.MAGIC_WAVE_BOTTOM://底-买点
                    mPaint.setColor(point.getmIColor());
                    stopY = fTopLimit;
                    diff = diffHeight;
                    break;
                default:
                    continue;
            }

            switch (drawStyle){
                case 1://一个像素为直线
                    fXcoordinate += fXstepWidth/2.0f;
                    canvas.drawLine(fXcoordinate,referenceYcoordinate,fXcoordinate,stopY,mPaint);
                    break;
                case 3://3个像素为三条竖线
                    fXcoordinate += fXstepWidth/2.0f +1;
                    //右边线
                    canvas.drawLine(fXcoordinate,referenceYcoordinate,fXcoordinate,stopY,mPaint);
                    //中间线
                    fXcoordinate--;
                    stopY += diff;
                    canvas.drawLine(fXcoordinate,referenceYcoordinate,fXcoordinate,stopY,mPaint);
                    //左边线
                    fXcoordinate--;
                    stopY += diff;
                    canvas.drawLine(fXcoordinate,referenceYcoordinate,fXcoordinate,stopY,mPaint);
                    break;
                default://其他为等腰三角形
                    float middleX = fXcoordinate + fXstepWidth / 2.0f;
                    canvas.drawLine(fXcoordinate,referenceYcoordinate,middleX,stopY,mPaint);
                    fXcoordinate += fXstepWidth;
                    canvas.drawLine(fXcoordinate,referenceYcoordinate,middleX,stopY,mPaint);
                    break;
            }
        }
        //绘制分割线
        mPaint.setColor(mPointCollection.getmIDividerLineColor());
        canvas.drawLine(left,referenceYcoordinate,right,referenceYcoordinate,mPaint);
    }
}

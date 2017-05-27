package com.hxgraph.graphstrategy.special;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.hxgraph.graphstrategy.KLineStrategy;
import com.hxgraph.model.imp.group.special.HXKLineModel;
import com.hxgraph.model.imp.raw.HXKLinePointModel;
import com.hxgraph.model.imp.raw.KLinePointModel;

/**
 * 带买入卖出点的k线图
 * Created by liulinru on 2017/4/26.
 */

public class HXKLineStrategy extends KLineStrategy {

    @Override
    protected void drawTechLine(Canvas canvas, float hight, int index, float middleX,
                                float barWidth, KLinePointModel point) {
        super.drawTechLine(canvas, hight,index, middleX, barWidth, point);
        if(point instanceof HXKLinePointModel){
            HXKLinePointModel hxPoint = (HXKLinePointModel)point;

            int distanceToKline = 3;
            int arrowHeadLength = ((HXKLineModel)mPointCollection).getmIArrowHeadLength();
            int arrowBodyLength = ((HXKLineModel)mPointCollection).getmIArrowBodyLength();
            float lowPos = point.getfLowCoordinate();
            float highPos = point.getfHighCoordinate();

            if(hxPoint.getTech() != null){
                //升
                Path path = new Path();
                if(hxPoint.getTech().getValue() == 1){
                    mPaint.setColor(mPointCollection.getmIRiseColor());
                    mPaint.setStyle(Paint.Style.FILL);
                    if( (hight - lowPos) < (distanceToKline + arrowBodyLength + arrowHeadLength) ){
                        path.moveTo(middleX - barWidth/2, highPos - distanceToKline);
                        path.lineTo(middleX + barWidth/2, highPos - distanceToKline);
                        path.lineTo(middleX + barWidth/2, highPos - distanceToKline - arrowBodyLength);
                        path.lineTo(middleX + barWidth, highPos - distanceToKline - arrowBodyLength);
                        path.lineTo(middleX, highPos - distanceToKline - arrowBodyLength - arrowHeadLength);
                        path.lineTo(middleX - barWidth, highPos - distanceToKline - arrowBodyLength);
                        path.lineTo(middleX - barWidth/2, highPos - distanceToKline - arrowBodyLength);
                        path.close();
                    }else{
                        path.moveTo(middleX,lowPos + distanceToKline);
                        path.lineTo(middleX - barWidth, lowPos + distanceToKline + arrowHeadLength);
                        path.lineTo(middleX - barWidth/2, lowPos + distanceToKline + arrowHeadLength);
                        path.lineTo(middleX - barWidth/2, lowPos + distanceToKline + arrowHeadLength + arrowBodyLength);
                        path.lineTo(middleX + barWidth/2, lowPos + distanceToKline + arrowHeadLength + arrowBodyLength);
                        path.lineTo(middleX + barWidth/2, lowPos + distanceToKline + arrowHeadLength);
                        path.lineTo(middleX + barWidth, lowPos + distanceToKline + arrowHeadLength);
                        path.close();
                    }
                    canvas.drawPath(path,mPaint);
                }
                //降
                else if(hxPoint.getTech().getValue() == -1){
                    mPaint.setColor(((HXKLineModel)mPointCollection).getmITechDownColor());
                    mPaint.setStyle(Paint.Style.FILL);
                    if( (highPos - 1) < (distanceToKline + arrowBodyLength + arrowHeadLength) ){
                        path.moveTo(middleX - barWidth/2, lowPos + distanceToKline);
                        path.lineTo(middleX + barWidth/2, lowPos + distanceToKline);
                        path.lineTo(middleX + barWidth/2, lowPos + distanceToKline + arrowBodyLength);
                        path.lineTo(middleX + barWidth, lowPos + distanceToKline + arrowBodyLength);
                        path.lineTo(middleX, lowPos + distanceToKline + arrowBodyLength + arrowHeadLength);
                        path.lineTo(middleX - barWidth, lowPos + distanceToKline + arrowBodyLength);
                        path.lineTo(middleX - barWidth/2, lowPos + distanceToKline + arrowBodyLength);
                        path.close();
                    }else{
                        path.moveTo(middleX, highPos - distanceToKline);
                        path.lineTo(middleX - barWidth, highPos - distanceToKline - arrowHeadLength);
                        path.lineTo(middleX - barWidth/2, highPos - distanceToKline - arrowHeadLength);
                        path.lineTo(middleX - barWidth/2, highPos - distanceToKline - arrowBodyLength - arrowHeadLength);
                        path.lineTo(middleX + barWidth/2, highPos - distanceToKline - arrowBodyLength - arrowHeadLength);
                        path.lineTo(middleX + barWidth/2, highPos - distanceToKline - arrowHeadLength);
                        path.lineTo(middleX + barWidth, highPos - distanceToKline - arrowHeadLength);
                        path.close();
                    }
                    canvas.drawPath(path,mPaint);
                }
            }
        }
    }
}

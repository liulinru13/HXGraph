package com.hxgraph.graphstrategy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.hxgraph.model.Constant;
import com.hxgraph.model.imp.PointCollectionImp;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 实现了 IGraphStrategy 接口的抽象类
 * Created by liulinru on 2017/4/20.
 */

public abstract class GraphStrategyImp<T extends PointCollectionImp> implements IGraphStrategy<T> {
    protected Paint mPaint;//画笔
    protected Paint mPaintTrans;//透明画笔
    protected Path mPath;
    protected T mPointCollection;
    protected WeakReference<Context> mWRcontext;
    protected int mCanvasTop = Constant.fDefaultCanvasTranslateTY;//将要绘制的图像在画布坐标系中顶点y的位置
    protected int mCanvasLeft = Constant.fDefaultCanvasTranslateTX;//将要绘制的图像在画布坐标系中顶点x的位置
    @Override
    public void draw(T pointCollection, Canvas canvas) {
        if(pointCollection == null || canvas == null)
            return;
        mPointCollection = pointCollection;
        initPaint(pointCollection);
        canvas.save();
        canvas.translate(mCanvasLeft,mCanvasTop);
        draw(canvas);
        canvas.restore();
    }

    /**
     * 根据数据结构初始化画笔
     * @param pointCollection
     */
    protected void initPaint(T pointCollection){
        if(pointCollection == null)
            return;
        if(mPaint == null) {
            mPaint = new Paint();
            mPaintTrans = new Paint();
            mPath = new Path();
        }
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setContext(Context context){
        if(context != null)
            this.mWRcontext = new WeakReference<Context>(context);
    }

    /**
     * 进行绘制
     * @param canvas
     */
    protected abstract void draw(Canvas canvas);

    /**
     * 获取画布上绘制坐标系相对于画布坐标系的原点y坐标
     * @return
     */
    public int getmCanvasTop() {
        return mCanvasTop;
    }

    /**
     * 设置画布上绘制坐标系相对于画布坐标系的原点y坐标
     * @param mCanvasTop
     */
    public void setmCanvasTop(int mCanvasTop) {
        this.mCanvasTop = mCanvasTop;
    }

    /**
     * 获取画布上绘制坐标系相对于画布坐标系的原点x坐标
     * @return
     */
    public int getmCanvasLeft() {
        return mCanvasLeft;
    }

    /**
     * 设置画布上绘制坐标系相对于画布坐标系的原点x坐标
     * @param mCanvasLeft
     */
    public void setmCanvasLeft(int mCanvasLeft) {
        this.mCanvasLeft = mCanvasLeft;
    }

    public void cleanData(){
        mCanvasTop = Constant.fDefaultCanvasTranslateTY;
        mCanvasLeft = Constant.fDefaultCanvasTranslateTX;
        mPointCollection = null;
        if(mPaint != null)
            mPaint.reset();
    }
}

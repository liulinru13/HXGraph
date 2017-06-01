package com.hxgraph.model;

import android.graphics.Color;

/**
 * 基本常量设置
 * Created by liulinru on 2017/4/19.
 */

public class Constant {

    public static final int NULLVALUE = 0x80000000;
    public static final int MINVALUE = 0x7FFFFFFF;
    /*************线条常量************/
    public static float fDefaultStrokeWidth = 2;//默认线条宽度
    public static float fDefaultStrokeWidthBlod = 6.5f;//默认线条宽度
    public static float fDefaultBarWidth = 20;//默认柱子宽度
    public static int iDefaultStrokeColor = Color.BLACK;//默认线条颜色
    public static int iDefaultRiseColor = Color.RED;//默认涨线条颜色
    public static int iDefaultDownColor = Color.GREEN;//默认跌线条颜色
    public static int iDefaultTechDownColor = Color.GREEN;//默认k线技术条跌线条颜色
    public static int iDefaultMagicWaveDividerColor = 0xFF3399FF;//默认神奇电波分割线线条颜色
    public static float[] iDefaultDotLineParam = new float[]{2,2};//默认虚线绘制参数
    public static final float LINE_OFFSET = 0.5f;

    /****************坐标系相关常量***************/
    public static float fDefaultXScale = 1.0f;//x方向上缩放比例，用于计算x方向上实际坐标
    public static float fDefaultYScale = 1.0f;//y方向上缩放比例，用于计算y方向上实际坐标
    public static float fDefaultXStep = 1.0f;//x方向上的步宽，用于计算x方向上实际坐标
    public static final float fDefaultX = 1.0f;//x方向上fXcoordinateRaw的默认值

    public static final float fGraphDefaultTop = 1.0f;//默认画布上绘图范围中y方向上最高点坐标
//    public static final float fGraphDefaultBottom = 0;//默认画布上绘图范围中y方向上最低点坐标

    public static int fDefaultCanvasTranslateTX = 0;//默认画布在canvas坐标系中顶点横坐标的位置
    public static int fDefaultCanvasTranslateTY = 0;//默认画布在canvas坐标系中顶点纵坐标的位置

    /**********************神奇电波常量*******************/
    public static final int MAGIC_WAVE_TOP = 0x00000001;
    public static final int MAGIC_WAVE_BOTTOM = 0x00000002;
    public static final int MAGIC_WAVE_MASK = 0x0000000F;
}

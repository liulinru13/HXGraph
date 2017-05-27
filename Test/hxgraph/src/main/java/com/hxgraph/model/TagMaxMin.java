package com.hxgraph.model;

/**
 * 最值存储数据结构
 * Created by liulinru on 2017/5/27.
 */

public class TagMaxMin {
    private double maxValue = -1d;
    private double minValue = -1d;
    private double centerValue = -1d;

    public TagMaxMin() {
    }

    public TagMaxMin(double maxValue, double minValue, double centerValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.centerValue = centerValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getCenterValue() {
        return centerValue;
    }

    public void setCenterValue(double centerValue) {
        this.centerValue = centerValue;
    }
}

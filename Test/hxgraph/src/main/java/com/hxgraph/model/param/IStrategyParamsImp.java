package com.hxgraph.model.param;

import com.hxgraph.model.TagMaxMin;

/**
 * Created by liulinru on 2017/4/27.
 */

public class IStrategyParamsImp implements IStrategyParams {
    private float[] xCoordinates;
    private TagMaxMin maxMin;
    public float[] getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(float[] xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public void setxCoordinates(int[] xCoordinates) {
        if(xCoordinates != null){
            this.xCoordinates = new float[xCoordinates.length];
            for(int index=0;index<xCoordinates.length;index++){
                this.xCoordinates[index] = (float)xCoordinates[index];
            }
        }
    }

    public TagMaxMin getMaxMin() {
        return maxMin;
    }

    public void setMaxMin(TagMaxMin maxMin) {
        this.maxMin = maxMin;
    }
}

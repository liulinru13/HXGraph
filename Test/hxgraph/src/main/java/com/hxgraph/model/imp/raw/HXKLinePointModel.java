package com.hxgraph.model.imp.raw;

/**
 * Created by liulinru on 2017/4/26.
 */

public class HXKLinePointModel extends KLinePointModel {
    private HXKLineTechModel tech;

    public HXKLineTechModel getTech() {
        return tech;
    }

    public void setTech(HXKLineTechModel tech) {
        this.tech = tech;
    }

    public static class HXKLineTechModel{
        int value;

        public HXKLineTechModel(int value) {
            this.value = value;
        }

        public HXKLineTechModel() {
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}

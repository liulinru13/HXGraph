package com.hxgraph;

import android.content.Context;
import android.graphics.Canvas;

import com.hxgraph.adapter.GraphAdapterImp;
import com.hxgraph.graphstrategy.GraphStrategyImp;
import com.hxgraph.model.param.IStrategyParams;
import com.hxgraph.model.param.IStrategyParamsImp;

/**
 * 控件控制类
 * Created by liulinru on 2017/4/21.
 */

public class HXGraphManager {
    private GraphStrategyImp mStrategy;//绘图类
    private GraphAdapterImp mAdapter;//数据适配器

    public HXGraphManager() {
    }

    public void setAdapter(Class adapterClass,Context context
            , HXGraphCollection.INoGraphAdapterListener listener){
        GraphAdapterImp imp = HXGraphCollection.getInstance().getAdapterByName(adapterClass, listener);
        if(imp != null){
            setAdapter(imp,context);
        }
    }

    public void setAdapter(Class adapterClass,Context context){
        GraphAdapterImp imp = HXGraphCollection.getInstance().getAdapterByName(adapterClass);
        if(imp != null){
            setAdapter(imp,context);
        }
    }

    public void setAdapter(GraphAdapterImp mAdapter, Context context) {
        if(mAdapter != null) {
            mAdapter.cleanData();
            this.mAdapter = mAdapter;
            this.mStrategy = this.mAdapter.getGraphStrategy(context);
        }
    }
    /**
     * 设入原始数据
     * @param data
     */
    public void setData(Object data){
        if(mAdapter != null)
            this.mAdapter.receiveData(data);
    }

    /**
     * 设于原始数据以及绘图参数，参数根据选择的绘图方式不同也有不同
     * @param data
     * @param params
     */
    public void setData(Object data,IStrategyParamsImp params){
        if(mAdapter != null) {
            this.mAdapter.receiveData(data);
            setStrategyParam(params);
        }
    }

    /**
     * 设入绘图参数，参数根据选择的绘图方式不同也有不同
     * @param params
     */
    public void setStrategyParam(IStrategyParamsImp params){
        if(mAdapter != null)
            this.mAdapter.wrapRawData(params);
    }

    public GraphStrategyImp getmStrategy() {
        return mStrategy;
    }

    public GraphAdapterImp getmAdapter() {
        return mAdapter;
    }

    public void draw(Canvas canvas, int height){
        if(this.mAdapter != null)
            this.mAdapter.draw(canvas,height);
    }

    public void draw(Canvas canvas,int translateLeft,int tanslateTop,int height){
        if(this.mAdapter != null)
            this.mAdapter.draw(canvas,translateLeft,tanslateTop,height);
    }
}

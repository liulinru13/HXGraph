package com.hxgraph;

import com.hxgraph.adapter.GraphAdapterImp;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于容纳不同绘图组件的单例容器，方便对象复用
 * Created by liulinru on 2017/6/1.
 */

public class HXGraphCollection {
    private Map<String,GraphAdapterImp> map;

    public static HXGraphCollection getInstance(){
        return HXGraphCollectionHolder.instance;
    }

    private HXGraphCollection(){
        map = new HashMap<String, GraphAdapterImp>();
    }

    /**
     *
     * @param impClass
     * @param listener
     * @return
     */
    public GraphAdapterImp getAdapterByName(Class<GraphAdapterImp> impClass,INoGraphAdapterListener listener){
        if(impClass != null){
            if(map.containsKey(impClass.getSimpleName())){
                return map.get(impClass.getSimpleName());
            }else{
                //从外部获取
                if(listener != null){
                    GraphAdapterImp imp = listener.getGraphAdapter();
                    if(imp != null && imp.getClass().getSimpleName().equals(impClass.getSimpleName())){
                        map.put(imp.getClass().getSimpleName(),imp);
                        return imp;
                    }
                }
            }
        }//
        return null;
    }

    private static class HXGraphCollectionHolder{
        private static final HXGraphCollection instance = new HXGraphCollection();
    }

    /**
     * 接口，在没有某种类型的数据适配器时，需要从调用者处获取
     */
    public interface INoGraphAdapterListener{
        GraphAdapterImp getGraphAdapter();
    }
}

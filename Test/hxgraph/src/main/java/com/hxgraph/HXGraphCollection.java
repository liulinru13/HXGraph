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
     *通过适配器的类名来取用已经存在的适配器或者构建新的适配器
     * @param impClass 适配器类
     * @param listener 外部构造适配器的接口
     * @return
     */
    public GraphAdapterImp getAdapterByName(Class impClass
            ,INoGraphAdapterListener listener){
        if(impClass != null && GraphAdapterImp.class.isAssignableFrom(impClass)){
            if(map.containsKey(impClass.getSimpleName())){
                return map.get(impClass.getSimpleName());
            }else{
                //从外部获取
                if(listener != null){
                    GraphAdapterImp imp = listener.getGraphAdapter();
                    if(imp != null && imp.getClass().getSimpleName()
                            .equals(impClass.getSimpleName())){
                        map.put(imp.getClass().getSimpleName(),imp);
                        return imp;
                    }
                }
            }
        }//
        return null;
    }

    /**
     *通过适配器的类名来取用已经存在的适配器或者构建新的适配器
     * @param impClass 适配器类
     * @return
     */
    public GraphAdapterImp getAdapterByName(Class impClass){
        if(impClass != null && GraphAdapterImp.class.isAssignableFrom(impClass)){
            if(map.containsKey(impClass.getSimpleName())){
                return map.get(impClass.getSimpleName());
            }else{
                try{
                    GraphAdapterImp imp = (GraphAdapterImp)impClass.newInstance();
                    if(imp != null && imp.getClass().getSimpleName()
                            .equals(impClass.getSimpleName())){
                        map.put(imp.getClass().getSimpleName(),imp);
                        return imp;
                    }
                }catch (Exception e){
                    e.printStackTrace();
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

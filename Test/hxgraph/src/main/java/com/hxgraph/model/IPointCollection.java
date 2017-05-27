package com.hxgraph.model;

import java.util.ArrayList;
import java.util.List;

/**
 * IPoint 的集合，代表一组数据，也表示一根分时线或者一根k线或更多
 * Created by liulinru on 2017/4/19.
 */

public interface IPointCollection<T extends IPoint> {

    /**
     * 获取 IPoint 为元素的 List 数组
     * @return
     */
    List<T> getIPointSet();
}

package com.dasu.ganhuo.mode.logic.category;

import java.util.List;

/**
 * Created by dasu on 2017/4/20.
 *
 * 这不是一个回调接口，而是一个规范接口，定义Category的ui必须指明是哪种类型的数据
 */

public interface ICategoryType {

    String getCategoryType();

    void updateGanHuo(List<GanHuoEntity> data);

}

package com.ballinapp.mapping;

import java.util.List;

import com.ballinapp.enum_values.MappingTypeEnum;

/**
 * User: dusan <br/> Date: 3/13/18
 */
public interface CustomMapper<MODEL, INFO> {

    INFO mapToInfo(MODEL model);

    MODEL mapToModel(INFO info, MappingTypeEnum typeEnum);

    List<INFO> mapToInfoList(List<MODEL> models);

    List<MODEL> mapToModelList(List<INFO> infos);

}

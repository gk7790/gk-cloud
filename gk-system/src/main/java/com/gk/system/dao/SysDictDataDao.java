package com.gk.system.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.system.entity.DictData;
import com.gk.system.entity.SysDictDataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 字典数据
 *
 * @author Lowen
 */
@Mapper
public interface SysDictDataDao extends BaseDao<SysDictDataEntity> {

    List<SysDictDataEntity> getList(Map<String, Object> params);

    /**
     * 字典数据列表
     */
    List<DictData> getDictDataList(@Param("language") String language);
}

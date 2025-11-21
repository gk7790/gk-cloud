package com.gk.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.BaseServiceImpl;
import com.gk.system.dao.SysLanguageDao;
import com.gk.system.entity.SysDictTypeEntity;
import com.gk.system.entity.SysLanguageEntity;
import com.gk.system.service.SysLanguageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 国际化
 *
 * @author Lowen
 */
@Service
public class SysLanguageServiceImpl extends BaseServiceImpl<SysLanguageDao, SysLanguageEntity> implements SysLanguageService {

    protected SysLanguageServiceImpl(SysLanguageDao baseDao) {
        super(baseDao);
    }

    @Override
    public void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language) {
        SysLanguageEntity entity = new SysLanguageEntity();
        entity.setTableName(tableName);
        entity.setTableId(tableId);
        entity.setFieldName(fieldName);
        entity.setFieldValue(fieldValue);
        entity.setLanguage(language);

        //判断是否有数据
        if(baseDao.getLanguage(entity) == null){
            baseDao.insert(entity);
        }else {
            baseDao.updateLanguage(entity);
        }
    }

    @Override
    public void deleteLanguage(String tableName, Long tableId) {
        baseDao.deleteLanguage(tableName, tableId);
    }


    public SysLanguageEntity getLanguage(String tableName, Long tableId, String fieldName, String language) {
        SysLanguageEntity entity = new SysLanguageEntity();
        entity.setTableName(tableName);
        entity.setTableId(tableId);
        entity.setFieldName(fieldName);
        entity.setLanguage(language);
        return baseDao.getLanguage(entity);
    }

    @Override
    public Map<Long, String> getLanguage(String tableName, String fieldName, String language) {
        QueryWrapper<SysLanguageEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("table_name", tableName);
        wrapper.eq("field_name", fieldName);
        wrapper.eq("language", language);
        List<SysLanguageEntity> list = baseDao.selectList(wrapper);
        return list.stream().collect(Collectors.toMap(SysLanguageEntity::getTableId, SysLanguageEntity::getFieldValue));
    }
}
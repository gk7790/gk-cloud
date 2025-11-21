package com.gk.system.service;


import com.gk.common.core.service.BaseService;
import com.gk.system.entity.SysLanguageEntity;

import java.util.List;
import java.util.Map;

/**
 * 国际化
 *
 * @author Lowen
 */
public interface SysLanguageService extends BaseService<SysLanguageEntity> {

    /**
     * 查询语言
     */
    SysLanguageEntity getLanguage(String tableName, Long tableId, String fieldName, String language);

    /**
     * 查询语言
     */
    Map<Long, String> getLanguage(String tableName, String fieldName, String language);

    /**
     * 保存或更新
     * @param tableName   表名
     * @param tableId     表主键
     * @param fieldName   字段名
     * @param fieldValue  字段值
     * @param language    语言
     */
    void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language);

    /**
     * 删除国际化
     * @param tableName   表名
     * @param tableId     表主键
     */
    void deleteLanguage(String tableName, Long tableId);
}

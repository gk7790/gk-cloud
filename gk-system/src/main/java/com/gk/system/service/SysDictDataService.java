package com.gk.system.service;

import com.gk.common.core.service.BaseService;
import com.gk.common.page.PageData;
import com.gk.common.tools.DataMap;
import com.gk.system.dto.SysDictDataDTO;
import com.gk.system.entity.SysDictDataEntity;

/**
 * 数据字典
 *
 * @author Lowen
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageData<SysDictDataDTO> page(DataMap params);

    PageData<SysDictDataDTO> getPage(DataMap params);

    SysDictDataDTO get(Long id);

    void save(SysDictDataDTO dto);

    void update(SysDictDataDTO dto);

    void delete(Long[] ids);

}
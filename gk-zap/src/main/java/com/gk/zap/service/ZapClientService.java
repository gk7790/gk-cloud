package com.gk.zap.service;

import com.gk.common.core.service.CrudService;
import com.gk.zap.dto.ZapClientDTO;
import com.gk.zap.entity.ZapClientEntity;

/**
 * 客户端服务
 * @author Lowen
 */
public interface ZapClientService extends CrudService<ZapClientEntity, ZapClientDTO> {

    void add(ZapClientDTO dto);
}
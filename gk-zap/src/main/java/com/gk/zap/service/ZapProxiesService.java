package com.gk.zap.service;

import com.gk.common.core.service.CrudService;
import com.gk.zap.dto.ZapClientDTO;
import com.gk.zap.dto.ZapProxiesDTO;
import com.gk.zap.entity.ZapClientEntity;
import com.gk.zap.entity.ZapProxiesEntity;

/**
 * 客户端服务
 * @author Lowen
 */
public interface ZapProxiesService extends CrudService<ZapProxiesEntity, ZapProxiesDTO> {

    void add(ZapProxiesDTO dto);
}
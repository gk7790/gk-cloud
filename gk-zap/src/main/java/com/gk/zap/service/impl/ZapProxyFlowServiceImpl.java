package com.gk.zap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.zap.dao.ZapProxyFlowDao;
import com.gk.zap.dto.ZapProxyFlowDTO;
import com.gk.zap.entity.ZapProxyFlowEntity;
import com.gk.zap.service.ZapProxyFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZapProxyFlowServiceImpl extends CrudServiceImpl<ZapProxyFlowDao, ZapProxyFlowEntity, ZapProxyFlowDTO> implements ZapProxyFlowService {

    @Override
    public QueryWrapper<ZapProxyFlowEntity> getWrapper(DataMap params) {
        QueryWrapper<ZapProxyFlowEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(params.isValueNull("proxyId"), "proxy_id", params.getLong("proxyId"));
        queryWrapper.eq(params.isValueNull("serverId"), "server_id", params.getLong("serverId"));
        queryWrapper.eq(params.isValueNull("clientId"), "client_id", params.getLong("clientId"));
        return queryWrapper;
    }
}

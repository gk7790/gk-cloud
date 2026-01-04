package com.gk.zap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.AesCTR;
import com.gk.common.tools.DataMap;
import com.gk.common.utils.CharUtils;
import com.gk.zap.dao.ZapProxiesDao;
import com.gk.zap.dto.ZapProxiesDTO;
import com.gk.zap.entity.ZapProxiesEntity;
import com.gk.zap.service.ZapProxiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZapProxiesServiceImpl extends CrudServiceImpl<ZapProxiesDao, ZapProxiesEntity, ZapProxiesDTO> implements ZapProxiesService {

    @Override
    public QueryWrapper<ZapProxiesEntity> getWrapper(DataMap params) {
        QueryWrapper<ZapProxiesEntity>  wrapper = new QueryWrapper<>();
        wrapper.like(params.isValueNull("code"), "code", params.getStr("code"));
        wrapper.like(params.isValueNull("name"), "name", params.getStr("name"));
        wrapper.eq(params.isValueNull("clientId"), "client_id", params.getStr("clientId"));
        wrapper.between(params.isValueNull("startTime") && params.isValueNull("endTime"), "created_at", params.getLocalDateTime("startTime"), params.getLocalDateTime("endTime"));
        return wrapper;
    }

    @Override
    public void add(ZapProxiesDTO dto) {
        long id = IdWorker.getId();
        ZapProxiesEntity entity = new ZapProxiesEntity();
        entity.setId(id);
        entity.setClientId(dto.getClientId());
        entity.setCode(CharUtils.numberToBase62(id));
        entity.setName(dto.getName());
        entity.setProtocol(dto.getProtocol());
        entity.setPoolCount(dto.getPoolCount());
        entity.setLocalAddr(dto.getLocalAddr());
        entity.setLocalPort(dto.getLocalPort());
        entity.setBindAddr("127.0.0.1");
        entity.setBindPort(8989);
        baseDao.insert(entity);
    }
}

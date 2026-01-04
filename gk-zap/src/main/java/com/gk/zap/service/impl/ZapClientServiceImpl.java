package com.gk.zap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.AesCTR;
import com.gk.common.tools.DataMap;
import com.gk.common.utils.CharUtils;
import com.gk.zap.dao.ZapClientDao;
import com.gk.zap.dto.ZapClientDTO;
import com.gk.zap.entity.ZapClientEntity;
import com.gk.zap.service.ZapClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZapClientServiceImpl extends CrudServiceImpl<ZapClientDao, ZapClientEntity, ZapClientDTO> implements ZapClientService {

    @Override
    public QueryWrapper<ZapClientEntity> getWrapper(DataMap params) {
        return null;
    }

    @Override
    public void add(ZapClientDTO dto) {
        AesCTR aesCTR = AesCTR.of();
        long id = IdWorker.getId();
        ZapClientEntity entity = new ZapClientEntity();
        entity.setId(id);
        entity.setCode(CharUtils.numberToBase62(id));
        entity.setSecret(aesCTR.encrypt(id + ""));
        entity.setName(dto.getName());
        entity.setProtocol(dto.getProtocol());
        entity.setPoolCount(dto.getPoolCount());
        entity.setServerAddr("127.0.0.1");
        entity.setServerPort("90");
        baseDao.insert(entity);
    }
}
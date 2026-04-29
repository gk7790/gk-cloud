package com.gk.relay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.common.tools.Result;
import com.gk.relay.dao.RelayClientDao;
import com.gk.relay.dto.RelayClientDTO;
import com.gk.relay.entity.RelayClientEntity;
import com.gk.relay.service.RelayClientService;
import com.gk.security.utils.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * Relay客户表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-28
 */
@Service
public class RelayClientServiceImpl extends CrudServiceImpl<RelayClientDao, RelayClientEntity, RelayClientDTO> implements RelayClientService {

    @Override
    public QueryWrapper<RelayClientEntity> getWrapper(DataMap params){
        QueryWrapper<RelayClientEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }


    @Override
    public Result<String> login(String username, String password) {
        RelayClientEntity clientEntity = baseDao.findByUsername(username);
        Map<String,Object> map = new HashMap<>();

        map.put("username",clientEntity.getUsername());
        map.put("email",clientEntity.getPassword());
        map.put("clientCode", clientEntity.getClientCode());
        map.put("tenant_id", clientEntity.getTenantId());

        String token = JwtUtils.generateToken("relay", map);
        return Result.success(token);
    }
}
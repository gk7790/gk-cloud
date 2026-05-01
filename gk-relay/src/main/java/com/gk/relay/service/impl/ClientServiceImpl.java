package com.gk.relay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.common.tools.Result;
import com.gk.relay.dao.ClientDao;
import com.gk.relay.dto.ClientDTO;
import com.gk.relay.entity.ClientEntity;
import com.gk.relay.service.ClientService;
import com.gk.security.utils.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * Relay客户表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Service
public class ClientServiceImpl extends CrudServiceImpl<ClientDao, ClientEntity, ClientDTO> implements ClientService {

    @Override
    public QueryWrapper<ClientEntity> getWrapper(DataMap params){
        QueryWrapper<ClientEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }

    @Override
    public Result<String> login(String username, String password) {
        ClientEntity clientEntity = baseDao.findByUsername(username);
        Map<String,Object> map = new HashMap<>();
        map.put("id", clientEntity.getId());
        map.put("tenantId", clientEntity.getTenantId());
        map.put("username",clientEntity.getUsername());
        map.put("email",clientEntity.getPassword());
        map.put("clientCode", clientEntity.getClientCode());
        String token = JwtUtils.generateToken("relay", map);
        return Result.success(token);
    }

}
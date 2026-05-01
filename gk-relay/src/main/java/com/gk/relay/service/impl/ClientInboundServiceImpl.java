package com.gk.relay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.constant.Constant;
import com.gk.common.tools.DataMap;
import com.gk.relay.dao.ClientInboundDao;
import com.gk.relay.dto.ClientInboundDTO;
import com.gk.relay.entity.ClientInboundEntity;
import com.gk.relay.service.ClientInboundService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
 * Relay客户本地入站配置表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Service
public class ClientInboundServiceImpl extends CrudServiceImpl<ClientInboundDao, ClientInboundEntity, ClientInboundDTO> implements ClientInboundService {

    @Override
    public QueryWrapper<ClientInboundEntity> getWrapper(DataMap params){
        QueryWrapper<ClientInboundEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }


}
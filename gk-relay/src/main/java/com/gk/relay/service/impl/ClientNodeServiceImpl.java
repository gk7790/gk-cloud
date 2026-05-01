package com.gk.relay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.constant.Constant;
import com.gk.common.tools.DataMap;
import com.gk.relay.dao.ClientNodeDao;
import com.gk.relay.dto.ClientNodeDTO;
import com.gk.relay.entity.ClientNodeEntity;
import com.gk.relay.service.ClientNodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
 * Relay客户节点授权表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-05-01
 */
@Service
public class ClientNodeServiceImpl extends CrudServiceImpl<ClientNodeDao, ClientNodeEntity, ClientNodeDTO> implements ClientNodeService {

    @Override
    public QueryWrapper<ClientNodeEntity> getWrapper(DataMap params){
        QueryWrapper<ClientNodeEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }


}
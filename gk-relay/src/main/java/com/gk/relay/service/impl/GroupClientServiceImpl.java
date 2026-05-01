package com.gk.relay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.relay.dao.GroupClientDao;
import com.gk.relay.dto.GroupClientDTO;
import com.gk.relay.entity.GroupClientEntity;
import com.gk.relay.service.GroupClientService;
import org.springframework.stereotype.Service;


/**
 * Relay授权组客户关系表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Service
public class GroupClientServiceImpl extends CrudServiceImpl<GroupClientDao, GroupClientEntity, GroupClientDTO> implements GroupClientService {

    @Override
    public QueryWrapper<GroupClientEntity> getWrapper(DataMap params){
        QueryWrapper<GroupClientEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }


}
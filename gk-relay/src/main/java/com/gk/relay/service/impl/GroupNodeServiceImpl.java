package com.gk.relay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.relay.dao.GroupNodeDao;
import com.gk.relay.dto.GroupNodeDTO;
import com.gk.relay.entity.GroupNodeEntity;
import com.gk.relay.service.GroupNodeService;
import org.springframework.stereotype.Service;


/**
 * Relay授权组节点关系表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Service
public class GroupNodeServiceImpl extends CrudServiceImpl<GroupNodeDao, GroupNodeEntity, GroupNodeDTO> implements GroupNodeService {

    @Override
    public QueryWrapper<GroupNodeEntity> getWrapper(DataMap params){
        QueryWrapper<GroupNodeEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }


}
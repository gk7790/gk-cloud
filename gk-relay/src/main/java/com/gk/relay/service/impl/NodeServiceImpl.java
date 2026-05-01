package com.gk.relay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.relay.dao.NodeDao;
import com.gk.relay.dto.NodeDTO;
import com.gk.relay.entity.NodeEntity;
import com.gk.relay.service.NodeService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Relay节点主表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Service
public class NodeServiceImpl extends CrudServiceImpl<NodeDao, NodeEntity, NodeDTO> implements NodeService {

    @Override
    public QueryWrapper<NodeEntity> getWrapper(DataMap params){
        QueryWrapper<NodeEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }

    @Override
    public List<NodeDTO> getNodeByClient(DataMap params) {
        return baseDao.getNodeByClient(params);
    }
}
package com.gk.relay.service;

import com.alibaba.fastjson2.JSONObject;
import com.gk.common.core.service.CrudService;
import com.gk.relay.dto.NodeInboundDTO;
import com.gk.relay.entity.NodeInboundEntity;

import java.util.List;

/**
 * Relay节点入站配置表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-05-01
 */
public interface NodeInboundService extends CrudService<NodeInboundEntity, NodeInboundDTO> {

    List<JSONObject> getClientConfigByNodeId(List<Long> nodeIdList);
}
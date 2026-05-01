package com.gk.relay.service;

import com.gk.common.core.service.CrudService;
import com.gk.common.tools.DataMap;
import com.gk.relay.dto.NodeDTO;
import com.gk.relay.entity.NodeEntity;

import java.util.List;

/**
 * Relay节点主表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
public interface NodeService extends CrudService<NodeEntity, NodeDTO> {

    List<NodeDTO> getNodeByClient(DataMap params);
}
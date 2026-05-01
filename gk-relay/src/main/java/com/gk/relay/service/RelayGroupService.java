package com.gk.relay.service;

import com.alibaba.fastjson2.JSONObject;
import com.gk.common.core.service.CrudService;
import com.gk.common.tools.Result;
import com.gk.relay.dto.RelayGroupDTO;
import com.gk.relay.entity.RelayGroupEntity;

/**
 * Relay授权组表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
public interface RelayGroupService extends CrudService<RelayGroupEntity, RelayGroupDTO> {

    RelayGroupEntity getByClientId(Long clientId);

    Result<JSONObject> getNodeConfig(Long groupId, Long nodeId, Long policyId);
}
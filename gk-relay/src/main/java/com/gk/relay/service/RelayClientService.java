package com.gk.relay.service;

import com.gk.common.core.service.CrudService;
import com.gk.common.tools.Result;
import com.gk.relay.dto.RelayClientDTO;
import com.gk.relay.entity.RelayClientEntity;

/**
 * Relay客户表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-28
 */
public interface RelayClientService extends CrudService<RelayClientEntity, RelayClientDTO> {

    Result<String> login(String username, String password);
}
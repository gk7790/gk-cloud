package com.gk.relay.service;

import com.gk.common.core.service.CrudService;
import com.gk.common.tools.Result;
import com.gk.relay.dto.ClientDTO;
import com.gk.relay.entity.ClientEntity;

/**
 * Relay客户表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
public interface ClientService extends CrudService<ClientEntity, ClientDTO> {

    Result<String> login(String username, String password);
}
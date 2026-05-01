package com.gk.relay.service;

import com.alibaba.fastjson2.JSONObject;
import com.gk.common.core.service.CrudService;
import com.gk.relay.dto.RouteRuleSetDTO;
import com.gk.relay.entity.RouteRuleSetEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * sing-box规则集表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
public interface RouteRuleSetService extends CrudService<RouteRuleSetEntity, RouteRuleSetDTO> {

    List<RouteRuleSetEntity> listActiveByPolicyId();

    Map<String, JSONObject> mapActiveByTags(Set<String> ruleSetTags);
}
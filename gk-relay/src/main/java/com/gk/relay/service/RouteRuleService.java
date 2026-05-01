package com.gk.relay.service;

import com.alibaba.fastjson2.JSONObject;
import com.gk.common.core.service.CrudService;
import com.gk.common.tools.Result;
import com.gk.relay.dto.RouteRuleDTO;
import com.gk.relay.entity.RouteRuleEntity;

import java.util.List;

/**
 * sing-box路由规则表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
public interface RouteRuleService extends CrudService<RouteRuleEntity, RouteRuleDTO> {

    /**
     * 获取策略的规则
     * @param policyId 策略ID
     * @return 返回规则列表
     */
    List<RouteRuleEntity> listActiveByPolicyId(Long policyId);

    Result<JSONObject> getRouteConfig(Long groupId, Long policyId);

}
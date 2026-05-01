package com.gk.relay.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.common.tools.Result;
import com.gk.relay.dao.RelayGroupDao;
import com.gk.relay.dto.RelayGroupDTO;
import com.gk.relay.entity.RelayGroupEntity;
import com.gk.relay.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Relay授权组表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Service
@RequiredArgsConstructor
public class RelayGroupServiceImpl extends CrudServiceImpl<RelayGroupDao, RelayGroupEntity, RelayGroupDTO> implements RelayGroupService {

    private final GroupClientService groupClientService;
    private final GroupNodeService groupNodeService;
    private final RoutePolicyService routePolicyService;
    private final RouteRuleService routeRuleService;
    private final RouteRuleSetService routeRuleSetService;
    private final RouteHttpClientService routeHttpClientService;
    private final NodeInboundService nodeInboundService;

    @Override
    public QueryWrapper<RelayGroupEntity> getWrapper(DataMap params) {
        QueryWrapper<RelayGroupEntity> wrapper = new QueryWrapper<>();

        return wrapper;
    }


    @Override
    public RelayGroupEntity getByClientId(Long clientId) {
        if (clientId <= 0) {
            return new RelayGroupEntity();
        }
        return baseDao.getByClientId(clientId);
    }

    @Override
    public Result<JSONObject> getNodeConfig(Long groupId, Long nodeId, Long policyId) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("log", Map.of("level", "info", "timestamp", true));

        jsonData.put("inbounds", List.of(Map.of("type","mixed","tag","mixed-in","listen","127.0.0.1","listen_port", 10808)));


        List<JSONObject> outbounds = nodeInboundService.getClientConfigByNodeId(List.of(nodeId));

        // 出口直连
        outbounds.add(JSONObject.of("type", "direct","tag", "direct"));
        // 出口拦截
        outbounds.add(JSONObject.of("type", "block","tag", "block"));
        outbounds.add(JSONObject.of("type", "dns","tag", "dns-out"));

        jsonData.put("outbounds", outbounds);

        Result<JSONObject> routeResult = routeRuleService.getRouteConfig(groupId, policyId);
        if (routeResult.isFail()) {
            return Result.fail(routeResult.getMsg());
        }

        jsonData.put("route", routeResult.getData());

        return Result.success(jsonData);
    }

}
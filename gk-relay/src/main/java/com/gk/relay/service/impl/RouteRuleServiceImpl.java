package com.gk.relay.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.constant.Constant;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.common.tools.Result;
import com.gk.common.tools.StringFormat;
import com.gk.relay.dao.RouteRuleDao;
import com.gk.relay.dto.RouteRuleDTO;
import com.gk.relay.entity.RoutePolicyEntity;
import com.gk.relay.entity.RouteRuleEntity;
import com.gk.relay.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * sing-box路由规则表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Service
@RequiredArgsConstructor
public class RouteRuleServiceImpl extends CrudServiceImpl<RouteRuleDao, RouteRuleEntity, RouteRuleDTO> implements RouteRuleService {

    private final RoutePolicyService routePolicyService;
    private final RouteRuleSetService routeRuleSetService;

    @Override
    public QueryWrapper<RouteRuleEntity> getWrapper(DataMap params){
        QueryWrapper<RouteRuleEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }

    @Override
    public List<RouteRuleEntity> listActiveByPolicyId(Long policyId) {
        return baseDao.listActiveByPolicyId(policyId);
    }

    @Override
    public Result<JSONObject> getRouteConfig(Long groupId, Long policyId) {
        JSONObject jsonData = new JSONObject();
        // 2. 查询路由策略
        RoutePolicyEntity policy = routePolicyService.selectById(policyId);
        if (policy == null) {
            return Result.fail("路由策略不存在或未启用: " + policyId);
        }

        // 3. 查询策略规则
        List<RouteRuleEntity> rulesList = listActiveByPolicyId(policy.getId());

        // 4. 从规则里提取所有引用的 rule_set tag
        Set<String> requestedRuleSetTags = extractRuleSetTags(rulesList);

        // 5. 查询真实存在且启用的 rule_set
        Map<String, JSONObject> ruleSetMap = routeRuleSetService.mapActiveByTags(requestedRuleSetTags);

        Collection<JSONObject> values = ruleSetMap.values();
        if (CollectionUtils.isNotEmpty(values)) {
            jsonData.put("rule_set", values);
        }

        // 6. 构建 rules，同时过滤不存在的 rule_set
        List<JSONObject> rulesResult = buildRules(rulesList, ruleSetMap.keySet());
        if (CollectionUtils.isNotEmpty(rulesResult)) {
            jsonData.put("rules", rulesResult);
        }

        jsonData.put("final", policy.getFinalOutbound());
        if (ObjUtil.isNotEmpty(policy.getAutoDetectInterface())) {
            jsonData.put("auto_detect_interface", policy.getAutoDetectInterface() == 1);
        }
        return Result.success(jsonData);
    }


    private Set<String> extractRuleSetTags(List<RouteRuleEntity> ruleEntities) {
        Set<String> tags = new LinkedHashSet<>();

        if (CollectionUtils.isEmpty(ruleEntities)) {
            return tags;
        }

        for (RouteRuleEntity rule : ruleEntities) {
            if (StringUtils.isNotBlank(rule.getRawJson())) {
                JSONObject raw = JSONObject.parseObject(rule.getRawJson());
                extractRuleSetTagsFromRule(raw, tags);
            }

            if ("rule_set".equalsIgnoreCase(rule.getMatchType()) && StringUtils.isNotBlank(rule.getMatchJson())) {
                JSONObject match = JSONObject.parseObject(rule.getMatchJson());
                extractRuleSetTagsFromRule(match, tags);
            }

            if ("logical".equalsIgnoreCase(rule.getRuleKind()) && StringUtils.isNotBlank(rule.getChildRules())) {
                JSONArray childRules = JSONArray.parseArray(rule.getChildRules());
                for (Object item : childRules) {
                    if (item instanceof JSONObject child) {
                        extractRuleSetTagsFromRule(child, tags);
                    } else if (item instanceof Map<?, ?> map) {
                        JSONObject child = new JSONObject();
                        for (Map.Entry<?, ?> entry : map.entrySet()) {
                            child.put(String.valueOf(entry.getKey()), entry.getValue());
                        }
                        extractRuleSetTagsFromRule(child, tags);
                    }
                }
            }
        }

        return tags;
    }

    private List<JSONObject> buildRules(List<RouteRuleEntity> ruleList, Set<String> ruleSetTags) {
        List<JSONObject> rules = new ArrayList<>();
        if (CollectionUtils.isEmpty(ruleList)) {
            return rules;
        }
        Set<String> finalUsedRuleSetTags = new LinkedHashSet<>();

        for (RouteRuleEntity ruleEntity : ruleList) {
            JSONObject rule = buildSingleRule(ruleEntity);

            if (rule == null || rule.isEmpty()) {
                continue;
            }

            boolean keep = cleanRuleSetReference(rule, ruleSetTags);

            if (!keep) {
                continue;
            }

            extractRuleSetTagsFromRule(rule, finalUsedRuleSetTags);

            rules.add(rule);
        }
        return rules;
    }


    private JSONObject buildSingleRule(RouteRuleEntity entity) {
        JSONObject rule = new JSONObject();

        // 1. raw_json 优先级最高
        if (StringUtils.isNotBlank(entity.getRawJson())) {
            return JSONObject.parseObject(entity.getRawJson());
        }

        String ruleKind = StrUtil.blankToDefault(entity.getRuleKind(), "default");

        // 2. logical 规则
        if ("logical".equalsIgnoreCase(ruleKind)) {
            rule.put("type", "logical");
            rule.put("mode", StrUtil.blankToDefault(entity.getLogicalMode(), "and"));

            if (StringUtils.isBlank(entity.getChildRules())) {
                throw new IllegalArgumentException("logical 规则缺少 child_rules, ruleId=" + entity.getId());
            }

            rule.put("rules", JSONArray.parseArray(entity.getChildRules()));
        } else {
            if (StringUtils.isNotBlank(entity.getMatchJson()) && JSON.isValidObject(entity.getMatchJson())) {
                rule.putAll(JSONObject.parseObject(entity.getMatchJson()));
            }
        }

        // 4. action
        String action = StrUtil.blankToDefault(entity.getActionType(), "route");
        rule.put("action", action);

        // 5. outbound
        if ("route".equalsIgnoreCase(action)) {
            if (StringUtils.isBlank(entity.getOutboundTag())) {
                throw new IllegalArgumentException("route 动作缺少 outbound_tag, ruleId=" + entity.getId());
            }
            rule.put("outbound", entity.getOutboundTag());
        }

        // 6. invert
        if (Constant.Enabled.isEnabled(entity.getInvert())) {
            rule.put("invert", true);
        }

        // 7. extra_json
        if (StringUtils.isNotBlank(entity.getExtraJson())) {
            rule.putAll(JSONObject.parseObject(entity.getExtraJson()));
        }

        return rule;
    }

    private void extractRuleSetTagsFromRule(JSONObject rule, Set<String> tags) {
        Object value = rule.get("rule_set");

        if (value == null) {
            return;
        }

        List<String> list = StringFormat.toStringList(value);
        tags.addAll(list);
    }

    private boolean cleanRuleSetReference(JSONObject rule, Set<String> validRuleSetTags) {
        Object value = rule.get("rule_set");

        if (value == null) {
            return true;
        }

        List<String> oldTags = StringFormat.toStringList(value);

        List<String> newTags = oldTags.stream()
                .filter(validRuleSetTags::contains)
                .distinct()
                .toList();

        if (!newTags.isEmpty()) {
            rule.put("rule_set", newTags);
            return true;
        }

        // rule_set 全部无效，移除
        rule.remove("rule_set");

        // 如果移除后没有任何匹配条件，整条规则不能保留
        return hasAnyMatchCondition(rule);
    }

    /**
     * 判断是否还有匹配条件
     */
    private boolean hasAnyMatchCondition(JSONObject rule) {
        Set<String> nonMatchKeys = Set.of(
                "type",
                "mode",
                "rules",
                "action",
                "outbound",
                "invert"
        );

        for (String key : rule.keySet()) {
            if (!nonMatchKeys.contains(key)) {
                return true;
            }
        }
        return false;
    }
}
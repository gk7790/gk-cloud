package com.gk.relay.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.relay.dao.RouteRuleSetDao;
import com.gk.relay.dto.RouteRuleSetDTO;
import com.gk.relay.entity.RouteRuleSetEntity;
import com.gk.relay.service.RouteRuleSetService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * sing-box规则集表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Service
public class RouteRuleSetServiceImpl extends CrudServiceImpl<RouteRuleSetDao, RouteRuleSetEntity, RouteRuleSetDTO> implements RouteRuleSetService {

    @Override
    public QueryWrapper<RouteRuleSetEntity> getWrapper(DataMap params){
        QueryWrapper<RouteRuleSetEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }

    @Override
    public List<RouteRuleSetEntity> listActiveByPolicyId() {
        return baseDao.listActiveByPolicyId();
    }

    @Override
    public Map<String, JSONObject> mapActiveByTags(Set<String> ruleSetTags) {
        List<RouteRuleSetEntity> ruleSetList = baseDao.mapActiveByTags(ruleSetTags);
        Map<String, JSONObject> map = new HashMap<>(ruleSetList.size());
        for (RouteRuleSetEntity ruleSet : ruleSetList) {
            JSONObject item = new JSONObject();
            if ("remote".equalsIgnoreCase(ruleSet.getSetType())) {
                item.put("url", ruleSet.getUrl());
                item.put("update_interval", ruleSet.getUpdateInterval());
            } else if ("local".equalsIgnoreCase(ruleSet.getSetType())) {
                item.put("url", ruleSet.getUrl());
                item.put("update_interval", ruleSet.getUpdateInterval());
            }
            if (ObjUtil.isNotEmpty(item)) {
                item.put("type", ruleSet.getSetType());
                item.put("tag", ruleSet.getRuleSetTag());
                item.put("format", ruleSet.getFormatType());
                map.put(ruleSet.getRuleSetTag(), item);
            }
        }
        return map;
    }
}
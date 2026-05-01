package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.RouteRuleSetEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
* sing-box规则集表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Mapper
public interface RouteRuleSetDao extends BaseDao<RouteRuleSetEntity> {

    List<RouteRuleSetEntity> listActiveByPolicyId();

    List<RouteRuleSetEntity> mapActiveByTags(Set<String> ruleSetTags);
}
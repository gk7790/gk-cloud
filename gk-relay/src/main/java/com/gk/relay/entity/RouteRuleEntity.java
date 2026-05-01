package com.gk.relay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gk.common.core.entity.SimpleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sing-box路由规则表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_route_rule")
public class RouteRuleEntity extends SimpleEntity {

	/**
	* 所属路由策略ID
	*/
	private Long policyId;
	/**
	* 规则名称
	*/
	private String ruleName;
	/**
	* 规则说明
	*/
	private String description;
	/**
	* 规则类型：default/logical/custom
	*/
	private String ruleKind;
	/**
	* 主要匹配类型：rule_set/domain_suffix/ip_cidr/port/port_range/process_name/custom/sniff
	*/
	private String matchType;
	/**
	* 匹配条件JSON，例如 rule_set/domain_suffix/ip_cidr/port 等
	*/
	private String matchJson;
	/**
	* 动作：route/sniff/resolve/reject/hijack-dns等
	*/
	private String actionType;
	/**
	* 出站tag，action_type=route时使用
	*/
	private String outboundTag;
	/**
	* logical规则模式：and/or
	*/
	private String logicalMode;
	/**
	* logical子规则JSON数组
	*/
	private String childRules;
	/**
	* 是否反向匹配：0否 1是
	*/
	private Integer invert;
	/**
	* 完整规则JSON；不为空时优先使用
	*/
	private String rawJson;
	/**
	* 规则级扩展字段
	*/
	private String extraJson;
	/**
	* 是否启用：0否 1是
	*/
	private Integer enabled;
	/**
	* 规则顺序，越小越靠前
	*/
	private Integer sortOrder;
	/**
	* 逻辑删除：0否 1是
	*/
	private Integer deleted;

}
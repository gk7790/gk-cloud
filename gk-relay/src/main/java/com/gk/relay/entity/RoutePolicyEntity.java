package com.gk.relay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gk.common.core.entity.SimpleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sing-box路由策略表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_route_policy")
public class RoutePolicyEntity extends SimpleEntity {

	/**
	* 策略名称
	*/
	private String policyName;
	/**
	* 策略编码
	*/
	private String policyCode;
	/**
	* 策略说明
	*/
	private String description;
	/**
	* 默认出站tag：direct/proxy/block/proxy_hk等
	*/
	private String finalOutbound;
	/**
	* 是否开启自动检测出口网卡：0否 1是
	*/
	private Integer autoDetectInterface;
	/**
	* route.default_http_client，远程规则集默认下载HTTP Client tag
	*/
	private String defaultHttpClientTag;
	/**
	* route.default_domain_resolver，可选
	*/
	private String defaultDomainResolver;
	/**
	* route.default_network_strategy，可选
	*/
	private String defaultNetworkStrategy;
	/**
	* route.default_interface，可选
	*/
	private String defaultInterface;
	/**
	* 是否启用experimental.cache_file：0否 1是
	*/
	private Integer cacheFileEnabled;
	/**
	* experimental.cache_file.path
	*/
	private String cacheFilePath;
	/**
	* experimental.cache_file额外配置
	*/
	private String cacheFileJson;
	/**
	* 是否启用：0否 1是
	*/
	private Integer enabled;
	/**
	* 是否系统内置：0否 1是
	*/
	private Integer systemBuiltin;
	/**
	* 是否允许编辑：0否 1是
	*/
	private Integer editable;
	/**
	* 策略版本号，规则变更时递增
	*/
	private Long versionNo;
	/**
	* route级别扩展字段
	*/
	private String extraJson;
	/**
	* 排序
	*/
	private Integer sortOrder;
	/**
	* 逻辑删除：0否 1是
	*/
	private Integer deleted;

}
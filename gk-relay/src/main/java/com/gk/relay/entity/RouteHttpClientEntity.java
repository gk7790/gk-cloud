package com.gk.relay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gk.common.core.entity.SimpleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sing-box远程规则集HTTP Client配置表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_route_http_client")
public class RouteHttpClientEntity extends SimpleEntity {

	/**
	* HTTP Client名称
	*/
	private String clientName;
	/**
	* HTTP Client tag，对应sing-box http_clients tag
	*/
	private String clientTag;
	/**
	* 下载规则集使用的出站tag，例如 proxy/direct
	*/
	private String detourOutbound;
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
	* 完整http_client JSON；不为空时优先使用
	*/
	private String rawJson;
	/**
	* 扩展字段
	*/
	private String extraJson;
	/**
	* 逻辑删除：0否 1是
	*/
	private Integer deleted;
}
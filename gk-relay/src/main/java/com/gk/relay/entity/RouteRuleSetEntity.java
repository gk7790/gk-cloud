package com.gk.relay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gk.common.core.entity.SimpleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sing-box规则集表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_route_rule_set")
public class RouteRuleSetEntity extends SimpleEntity {

	/**
	* 规则集名称
	*/
	private String ruleSetName;
	/**
	* 规则集tag，对应sing-box rule_set tag
	*/
	private String ruleSetTag;
	/**
	* 规则集类型：local/remote/inline
	*/
	private String setType;
	/**
	* 格式：binary/source；local/remote使用
	*/
	private String formatType;
	/**
	* 分类：geosite/geoip/custom/ad/private/reject等
	*/
	private String category;
	/**
	* 数据类型：domain/ip/mixed/process/other
	*/
	private String dataKind;
	/**
	* local规则集路径，例如 rules/geosite-google.srs
	*/
	private String path;
	/**
	* remote规则集URL，例如 https://xxx/xxx.srs
	*/
	private String url;
	/**
	* remote规则集更新间隔，例如 1d/12h/7d
	*/
	private String updateInterval;
	/**
	* remote规则集下载使用的http_client tag
	*/
	private String httpClientTag;
	/**
	* inline规则集内容，对应 rules[]
	*/
	private String inlineRules;
	/**
	* 规则数量，可选统计
	*/
	private Integer ruleCount;
	/**
	* 规则文件hash，用于校验版本
	*/
	private String fileHash;
	/**
	* 规则集版本号
	*/
	private Long versionNo;
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
	* 规则来源名称
	*/
	private String sourceName;
	/**
	* 备注
	*/
	private String remark;
	/**
	* 完整rule_set JSON；不为空时优先使用
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
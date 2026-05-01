package com.gk.relay.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;
import com.gk.common.core.entity.SimpleEntity;

/**
 * Relay节点主表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_node")
public class NodeEntity extends SimpleEntity {

	/**
	* 租户ID，单租户默认0
	*/
	private Long tenantId;
	/**
	* 节点编码，例如 HK-01、JP-01
	*/
	private String nodeCode;
	/**
	* 节点名称，例如 香港01、日本01
	*/
	private String nodeName;
	/**
	* 地区编码，例如 HK、JP、SG
	*/
	private String regionCode;
	/**
	* 地区名称
	*/
	private String regionName;
	/**
	* 国家或地区代码
	*/
	private String countryCode;
	/**
	* 城市名称
	*/
	private String cityName;
	/**
	* 业务状态：0禁用，1启用，2维护中，3异常
	*/
	private Integer status;
	/**
	* 在线状态：0未知，1在线，2离线
	*/
	private Integer onlineStatus;
	/**
	* 服务商，例如 DMIT、AWS、Vultr
	*/
	private String provider;
	/**
	* 节点访问地址，IP或域名
	*/
	private String serverHost;
	/**
	* 公网IPv4
	*/
	private String publicIp;
	/**
	* IPv6地址
	*/
	private String ipv6Addr;
	/**
	* SSH地址
	*/
	private String sshHost;
	/**
	* SSH端口
	*/
	private Integer sshPort;
	/**
	* sing-box日志级别：trace、debug、info、warn、error
	*/
	private String logLevel;
	/**
	* 默认最终出站tag
	*/
	private String routeFinalOutboundTag;
	/**
	* 节点权重，用于排序或负载
	*/
	private Integer weight;
	/**
	* 排序值
	*/
	private Integer sortOrder;
	/**
	* 最大客户数
	*/
	private Integer maxClients;
	/**
	* 最大连接数
	*/
	private Integer maxConnections;
	/**
	* 带宽限制Mbps
	*/
	private Integer bandwidthLimitMbps;
	/**
	* 当前期望配置版本
	*/
	private Long currentConfigVersion;
	/**
	* agent已应用配置版本
	*/
	private Long appliedConfigVersion;
	/**
	* 配置状态：0未知，1已应用，2待应用，3应用失败
	*/
	private Integer configApplyStatus;
	/**
	* 最近一次配置应用失败原因
	*/
	private String configApplyError;
	/**
	* 备注
	*/
	private String remark;
	/**
	* 逻辑删除：0未删除，1已删除
	*/
	private Integer deleted;
	/**
	* 乐观锁版本
	*/
	private Integer version;
}
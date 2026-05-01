package com.gk.relay.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;
import com.gk.common.core.entity.SimpleEntity;

/**
 * Relay节点入站配置表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-05-01
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_node_inbound")
public class NodeInboundEntity extends SimpleEntity {

	/**
	* 节点ID
	*/
	private Long nodeId;
	/**
	* 协议：vless、trojan、shadowsocks、hysteria2、tuic、socks、mixed、vmess
	*/
	private String protocol;
	/**
	* 是否启用
	*/
	private Integer enabled;
	/**
	* 入站名称
	*/
	private String inboundName;
	/**
	* sing-box inbound tag，例如 vless-reality-in
	*/
	private String inboundTag;
	/**
	* 监听地址，例如 ::、0.0.0.0、127.0.0.1
	*/
	private String listenAddr;
	/**
	* 监听端口
	*/
	private Integer listenPort;
	/**
	* 传输类型：tcp、ws、grpc、httpupgrade、quic
	*/
	private String transportType;
	/**
	* 网络类型
	*/
	private String network;
	/**
	* VLESS flow，例如 xtls-rprx-vision
	*/
	private String flow;
	/**
	* 是否启用TLS
	*/
	private Integer tlsEnabled;
	/**
	* TLS模式：none、tls、reality
	*/
	private String tlsMode;
	/**
	* 是否启用sniff
	*/
	private Integer sniffEnabled;
	/**
	* sniff是否覆盖目标地址
	*/
	private Integer sniffOverrideDestination;
	/**
	* 是否启用UDP
	*/
	private Integer udpEnabled;
	/**
	* 是否启用TCP Fast Open
	*/
	private Integer tcpFastOpen;
	/**
	* 协议扩展配置JSON，例如 method、password、obfs、congestion_control
	*/
	private String protocolOptions;
	/**
	* 传输扩展配置JSON，例如 ws path、headers、grpc service_name
	*/
	private String transportOptions;
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
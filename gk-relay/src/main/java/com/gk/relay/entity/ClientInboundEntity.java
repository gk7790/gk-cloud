package com.gk.relay.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;
import com.gk.common.core.entity.SimpleEntity;

/**
 * Relay客户本地入站配置表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_client_inbound")
public class ClientInboundEntity extends SimpleEntity {

	/**
	* 客户ID
	*/
	private Long clientId;
	/**
	* 客户端本地 inbound tag
	*/
	private String inboundTag;
	/**
	* 客户端本地入站名称
	*/
	private String inboundName;
	/**
	* 类型：mixed、socks、http、tun
	*/
	private String inboundType;
	/**
	* 监听地址
	*/
	private String listenAddr;
	/**
	* 监听端口
	*/
	private Integer listenPort;
	/**
	* 是否启用sniff
	*/
	private Integer sniffEnabled;
	/**
	* 是否启用UDP
	*/
	private Integer udpEnabled;
	/**
	* 是否TUN模式
	*/
	private Integer tunEnabled;
	/**
	* TUN stack：system、gvisor
	*/
	private String tunStack;
	/**
	* TUN自动路由
	*/
	private Integer tunAutoRoute;
	/**
	* TUN严格路由
	*/
	private Integer tunStrictRoute;
	/**
	* 客户端入站扩展配置JSON
	*/
	private String inboundOptions;
	/**
	* 是否启用
	*/
	private Integer enabled;
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
package com.gk.relay.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;
import com.gk.common.core.entity.SimpleEntity;

/**
 * Relay客户节点授权表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-05-01
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_client_node")
public class ClientNodeEntity extends SimpleEntity {

	/**
	* 客户ID
	*/
	private Long clientId;
	/**
	* 节点ID
	*/
	private Long nodeId;
	/**
	* 节点入站ID
	*/
	private Long inboundId;
	/**
	* 授权来源：1直接授权，2授权组生成，3套餐生成
	*/
	private Integer sourceType;
	/**
	* 来源授权组ID
	*/
	private Long groupId;
	/**
	* 凭据类型：uuid、password、token
	*/
	private String credentialType;
	/**
	* VLESS/VMess UUID
	*/
	private String uuid;
	/**
	* Trojan/Shadowsocks等密码密文
	*/
	private String passwordCipher;
	/**
	* 短ID
	*/
	private String realityShortId;
	/**
	* VLESS flow
	*/
	private String flow;
	/**
	* sing-box用户标识
	*/
	private String email;
	/**
	* 客户节点凭据扩展配置JSON
	*/
	private String credentialOptions;
	/**
	* 该节点设备限制
	*/
	private Integer deviceLimit;
	/**
	* 是否启用
	*/
	private Integer enabled;
	/**
	* 授权过期时间
	*/
	private LocalDateTime expireAt;
	/**
	* 上传限制
	*/
	private Long uploadLimitBytes;
	/**
	* 下载限制
	*/
	private Long downloadLimitBytes;
	/**
	* 总流量限制
	*/
	private Long totalLimitBytes;
	/**
	* 已用上传字节
	*/
	private Long usedUploadBytes;
	/**
	* 已用下载字节
	*/
	private Long usedDownloadBytes;
	/**
	* 已用总流量字节
	*/
	private Long usedTotalBytes;
	/**
	* 逻辑删除：0未删除，1已删除
	*/
	private Integer deleted;
	/**
	* 乐观锁版本
	*/
	private Integer version;
}
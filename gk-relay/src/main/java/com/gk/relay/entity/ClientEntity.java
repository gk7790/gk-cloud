package com.gk.relay.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;
import com.gk.common.core.entity.SimpleEntity;

/**
 * Relay客户表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_client")
public class ClientEntity extends SimpleEntity {

	/**
	* 租户ID，单租户默认0
	*/
	private Long tenantId;
	/**
	* 客户编码
	*/
	private String clientCode;
	/**
	* 客户名称
	*/
	private String clientName;
	/**
	* 登录用户名
	*/
	private String username;
	/**
	* 邮箱
	*/
	private String email;
	/**
	* 手机号
	*/
	private String phone;
	/**
	* 密码
	*/
	private String password;
	/**
	* 客户类型：1普通客户，2测试客户，3企业客户，4内部客户
	*/
	private Integer clientType;
	/**
	* 状态：0禁用，1启用，2过期，3冻结
	*/
	private Integer status;
	/**
	* 账号过期时间
	*/
	private LocalDateTime expireAt;
	/**
	* 总流量限制
	*/
	private Long totalLimitBytes;
	/**
	* 已用总流量
	*/
	private Long usedTotalBytes;
	/**
	* 最大连接数
	*/
	private Integer maxConnections;
	/**
	* 最后登录时间
	*/
	private LocalDateTime lastLoginAt;
	/**
	* 最后登录IP
	*/
	private String lastLoginIp;
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
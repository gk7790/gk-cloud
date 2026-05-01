package com.gk.relay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gk.common.core.entity.SimpleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Relay授权组客户关系表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_group_client")
public class GroupClientEntity extends SimpleEntity {

	/**
	* 授权组ID
	*/
	private Long groupId;
	/**
	* 客户ID
	*/
	private Long clientId;
	/**
	* 是否启用
	*/
	private Integer enabled;
	/**
	* 组授权过期时间
	*/
	private LocalDateTime expireAt;
	/**
	* 逻辑删除：0未删除，1已删除
	*/
	private Integer deleted;
	/**
	* 乐观锁版本
	*/
	private Integer version;
}
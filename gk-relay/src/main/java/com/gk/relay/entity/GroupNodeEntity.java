package com.gk.relay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gk.common.core.entity.SimpleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Relay授权组节点关系表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_group_node")
public class GroupNodeEntity extends SimpleEntity {

	/**
	* 授权组ID
	*/
	private Long groupId;
	/**
	* 节点ID
	*/
	private Long nodeId;
	/**
	* 是否启用
	*/
	private Integer enabled;
	/**
	* 节点在组内排序
	*/
	private Integer sortOrder;
	/**
	* 逻辑删除：0未删除，1已删除
	*/
	private Integer deleted;
	/**
	* 乐观锁版本
	*/
	private Integer version;
}
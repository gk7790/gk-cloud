package com.gk.relay.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import com.gk.common.core.entity.SimpleEntity;

/**
 * Relay授权组表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("relay_group")
public class RelayGroupEntity extends SimpleEntity {

	/**
	* 租户ID，单租户默认0
	*/
	private Long tenantId;
    /**
     * 路由策略ID
     */
    private Long routePolicyId;
    /**
	* 授权组编码，例如 VIP-HK
	*/
	private String groupCode;
    /**
	* 授权组名称，例如 VIP香港节点组
	*/
	private String groupName;
    /**
	* 组类型：1普通组，2套餐组，3企业组，4测试组
	*/
	private Integer groupType;
    /**
	* 状态：0禁用，1启用
	*/
	private Integer status;
    /**
	* 最大节点数
	*/
	private Integer maxNodes;
    /**
	* 最大客户数
	*/
	private Integer maxClients;
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
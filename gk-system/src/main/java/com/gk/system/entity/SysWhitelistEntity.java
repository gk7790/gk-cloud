package com.gk.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gk.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 白名单
 *
 * @author stark system@gmail.com
 * @since 1.0.0 2019-05-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_whitelist")
public class SysWhitelistEntity extends BaseEntity {
	/**
	 * 公司ID
	 */
	private Long deptId;
    /**
     * 平台ID
     */
	private Long tenantId;
    /**
     * 白名单ip
     */
	private String allowIp;
	/**
	 * IP归属地
	 */
	private String ipSource;
    /**
     * 备注
     */
	private String remark;
}
package com.gk.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gk.common.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 白名单
 *
 * @author stark system@gmail.com
 * @since 1.0.0 2019-05-08
 */
@Data
@Schema(title = "白名单")
public class SysWhitelistDTO extends BaseEntity implements Serializable {

	@Schema(title = "公司ID")
	private Long deptId;

	@Schema(title = "平台ID")
	private Long tenantId;

	@Schema(title = "白名单ip")
	private String allowIp;

	@Schema(title = "IP归属")
	private String ipSource;

	@Schema(title = "备注")
	private String remark;
}
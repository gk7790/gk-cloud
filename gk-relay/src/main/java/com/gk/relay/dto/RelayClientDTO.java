package com.gk.relay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* Relay客户表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-28
*/
@Data
@Schema(title = "Relay客户表")
public class RelayClientDTO implements Serializable {

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "租户ID，单租户默认0")
    private Long tenantId;
    @Schema(title = "客户编码")
    private String clientCode;
    @Schema(title = "客户名称")
    private String clientName;
    @Schema(title = "登录用户名")
    private String username;
    @Schema(title = "邮箱")
    private String email;
    @Schema(title = "手机号")
    private String phone;
    @Schema(title = "密码")
    private String password;
    @Schema(title = "客户类型：1普通客户，2测试客户，3企业客户，4内部客户")
    private Integer clientType;
    @Schema(title = "状态：0禁用，1启用，2过期，3冻结")
    private Integer status;
    @Schema(title = "账号过期时间")
    private LocalDateTime expireAt;
    @Schema(title = "总流量限制")
    private Long totalLimitBytes;
    @Schema(title = "已用总流量")
    private Long usedTotalBytes;
    @Schema(title = "最大连接数")
    private Integer maxConnections;
    @Schema(title = "最后登录时间")
    private LocalDateTime lastLoginAt;
    @Schema(title = "最后登录IP")
    private String lastLoginIp;
    @Schema(title = "备注")
    private String remark;
    @Schema(title = "创建人ID")
    private Long createdBy;
    @Schema(title = "创建时间")
    private LocalDateTime createdAt;
    @Schema(title = "更新人ID")
    private Long updatedBy;
    @Schema(title = "更新时间")
    private LocalDateTime updatedAt;
    @Schema(title = "逻辑删除：0未删除，1已删除")
    private Integer deleted;
    @Schema(title = "乐观锁版本")
    private Integer version;

}
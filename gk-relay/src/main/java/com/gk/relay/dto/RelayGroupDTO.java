package com.gk.relay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* Relay授权组表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Data
@Schema(title = "Relay授权组表")
public class RelayGroupDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "租户ID，单租户默认0")
    private Long tenantId;
    @Schema(title = "路由策略ID")
    private Long routePolicyId;
    @Schema(title = "授权组编码，例如 VIP-HK")
    private String groupCode;
    @Schema(title = "授权组名称，例如 VIP香港节点组")
    private String groupName;
    @Schema(title = "组类型：1普通组，2套餐组，3企业组，4测试组")
    private Integer groupType;
    @Schema(title = "状态：0禁用，1启用")
    private Integer status;
    @Schema(title = "最大节点数")
    private Integer maxNodes;
    @Schema(title = "最大客户数")
    private Integer maxClients;
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
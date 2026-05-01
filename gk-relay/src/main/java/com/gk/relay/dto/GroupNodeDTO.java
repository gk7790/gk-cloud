package com.gk.relay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* Relay授权组节点关系表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Data
@Schema(title = "Relay授权组节点关系表")
public class GroupNodeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "授权组ID")
    private Long groupId;
    @Schema(title = "节点ID")
    private Long nodeId;
    @Schema(title = "是否启用")
    private Integer enabled;
    @Schema(title = "节点在组内排序")
    private Integer sortOrder;
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
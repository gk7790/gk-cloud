package com.gk.relay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* sing-box路由规则表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Data
@Schema(title = "sing-box路由规则表")
public class RouteRuleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "所属路由策略ID")
    private Long policyId;
    @Schema(title = "规则名称")
    private String ruleName;
    @Schema(title = "规则说明")
    private String description;
    @Schema(title = "规则类型：default/logical/custom")
    private String ruleKind;
    @Schema(title = "主要匹配类型：rule_set/domain_suffix/ip_cidr/port/port_range/process_name/custom/sniff")
    private String matchType;
    @Schema(title = "匹配条件JSON，例如 rule_set/domain_suffix/ip_cidr/port 等")
    private String matchJson;
    @Schema(title = "动作：route/sniff/resolve/reject/hijack-dns等")
    private String actionType;
    @Schema(title = "出站tag，action_type=route时使用")
    private String outboundTag;
    @Schema(title = "logical规则模式：and/or")
    private String logicalMode;
    @Schema(title = "logical子规则JSON数组")
    private String childRules;
    @Schema(title = "是否反向匹配：0否 1是")
    private Integer invert;
    @Schema(title = "完整规则JSON；不为空时优先使用")
    private String rawJson;
    @Schema(title = "规则级扩展字段")
    private String extraJson;
    @Schema(title = "是否启用：0否 1是")
    private Integer enabled;
    @Schema(title = "规则顺序，越小越靠前")
    private Integer sortOrder;
    @Schema(title = "逻辑删除：0否 1是")
    private Integer deleted;
    @Schema(title = "创建人")
    private Long createBy;
    @Schema(title = "更新人")
    private Long updateBy;
    @Schema(title = "创建时间")
    private LocalDateTime createTime;
    @Schema(title = "更新时间")
    private LocalDateTime updateTime;

}
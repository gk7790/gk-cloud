package com.gk.relay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* sing-box远程规则集HTTP Client配置表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Data
@Schema(title = "sing-box远程规则集HTTP Client配置表")
public class RouteHttpClientDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "HTTP Client名称")
    private String clientName;
    @Schema(title = "HTTP Client tag，对应sing-box http_clients tag")
    private String clientTag;
    @Schema(title = "下载规则集使用的出站tag，例如 proxy/direct")
    private String detourOutbound;
    @Schema(title = "是否启用：0否 1是")
    private Integer enabled;
    @Schema(title = "是否系统内置：0否 1是")
    private Integer systemBuiltin;
    @Schema(title = "是否允许编辑：0否 1是")
    private Integer editable;
    @Schema(title = "完整http_client JSON；不为空时优先使用")
    private String rawJson;
    @Schema(title = "扩展字段")
    private String extraJson;
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
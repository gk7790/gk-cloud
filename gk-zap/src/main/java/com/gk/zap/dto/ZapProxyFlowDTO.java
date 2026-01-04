package com.gk.zap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(title = "渠道流量")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZapProxyFlowDTO {

    @Schema(title = "id")
    private Long id;
    @Schema(title = "代理id")
    private Long proxyId;
    @Schema(title = "客户端id")
    private Long clientId;
    @Schema(title = "服务器Id")
    private Long serverId;
    @Schema(title = "协议")
    private String protocol;
    @Schema(title = "请求流量")
    private Integer reqBytes;
    @Schema(title = "响应流量")
    private Integer respBytes;
    @Schema(title = "总流量")
    private Integer totalBytes;
    @Schema(title = "耗时")
    private Integer duration;
    @Schema(title = "创建时间")
    private LocalDateTime createdAt;

}

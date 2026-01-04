package com.gk.zap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(title = "代理管理")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZapProxiesDTO {

    @Schema(title = "id")
    private Long id;
    @Schema(title = "客户端id")
    private Long clientId;
    @Schema(title = "客户端名称")
    private String clientName;
    @Schema(title = "编码")
    private String code;
    @Schema(title = "名称")
    private String name;
    @Schema(title = "类型")
    private String type;
    @Schema(title = "协议类型")
    private String protocol;
    @Schema(title = "本地地址")
    private String localAddr;
    @Schema(title = "本地端口")
    private Integer localPort;
    @Schema(title = "绑定地址")
    private String bindAddr;
    @Schema(title = "绑定端口")
    private Integer bindPort;
    @Schema(title = "池子数量")
    private Integer poolCount;
    @Schema(title = "备注")
    private String remark;
    @Schema(title = "创建时间")
    private LocalDateTime createdAt;
}

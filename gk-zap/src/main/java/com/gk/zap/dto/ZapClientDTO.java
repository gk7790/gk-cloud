package com.gk.zap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 参数管理
 * @author Lowen
 * @since 1.0.0
 */
@Data
@Schema(title = "参数管理")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZapClientDTO implements Serializable {

    @Schema(title = "id")
    private Long id;

    @Schema(title = "编码")
    private String code;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "秘钥")
    private String secret;

    @Schema(title = "服务器地址")
    private String serverAddr;

    @Schema(title = "服务器端口")
    private String serverPort;

    @Schema(title = "池子数量")
    private Integer poolCount;

    @Schema(title = "协议类型")
    private String protocol;

    @Schema(title = "设备Id")
    private String deviceId;

    @Schema(title = "最后一次ping")
    private Long lastPing;

    @Schema(title = "创建时间")
    private LocalDateTime createdAt;
}

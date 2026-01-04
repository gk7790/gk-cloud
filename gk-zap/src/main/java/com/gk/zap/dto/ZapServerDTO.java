package com.gk.zap.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* Zap服务器
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-01-05
*/
@Data
@Schema(title = "Zap服务器")
public class ZapServerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @Schema(title = "服务编码")
    private String code;
    @Schema(title = "名称")
    private String name;
    @Schema(title = "服务ip")
    private String ip;
    @Schema(title = "服务端口")
    private String port;
    @Schema(title = "设备id")
    private String deviceId;
    @Schema(title = "根路径(需要排除)")
    private String rootPaths;
    @Schema(title = "创建者")
    private Long createdBy;
    @Schema(title = "创建时间")
    private LocalDateTime createdAt;
    @Schema(title = "修改者")
    private Long updatedBy;
    @Schema(title = "修改时间")
    private LocalDateTime updatedAt;

}
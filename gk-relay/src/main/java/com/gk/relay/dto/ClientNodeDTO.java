package com.gk.relay.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gk.common.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* Relay客户节点授权表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-05-01
*/
@Data
@Schema(title = "Relay客户节点授权表")
public class ClientNodeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "客户ID")
    private Long clientId;
    @Schema(title = "节点ID")
    private Long nodeId;
    @Schema(title = "节点入站ID")
    private Long inboundId;
    @Schema(title = "授权来源：1直接授权，2授权组生成，3套餐生成")
    private Integer sourceType;
    @Schema(title = "来源授权组ID")
    private Long groupId;
    @Schema(title = "凭据类型：uuid、password、token")
    private String credentialType;
    @Schema(title = "VLESS/VMess UUID")
    private String uuid;
    @Schema(title = "Trojan/Shadowsocks等密码密文")
    private String passwordCipher;
    @Schema(title = "短ID")
    private String realityShortId;
    @Schema(title = "VLESS flow")
    private String flow;
    @Schema(title = "sing-box用户标识")
    private String email;
    @Schema(title = "客户节点凭据扩展配置JSON")
    private String credentialOptions;
    @Schema(title = "该节点设备限制")
    private Integer deviceLimit;
    @Schema(title = "是否启用")
    private Integer enabled;
    @Schema(title = "授权过期时间")
    private LocalDateTime expireAt;
    @Schema(title = "上传限制")
    private Long uploadLimitBytes;
    @Schema(title = "下载限制")
    private Long downloadLimitBytes;
    @Schema(title = "总流量限制")
    private Long totalLimitBytes;
    @Schema(title = "已用上传字节")
    private Long usedUploadBytes;
    @Schema(title = "已用下载字节")
    private Long usedDownloadBytes;
    @Schema(title = "已用总流量字节")
    private Long usedTotalBytes;
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
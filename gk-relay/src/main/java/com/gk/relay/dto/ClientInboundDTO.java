package com.gk.relay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* Relay客户本地入站配置表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Data
@Schema(title = "Relay客户本地入站配置表")
public class ClientInboundDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "客户ID")
    private Long clientId;
    @Schema(title = "客户端本地 inbound tag")
    private String inboundTag;
    @Schema(title = "客户端本地入站名称")
    private String inboundName;
    @Schema(title = "类型：mixed、socks、http、tun")
    private String inboundType;
    @Schema(title = "监听地址")
    private String listenAddr;
    @Schema(title = "监听端口")
    private Integer listenPort;
    @Schema(title = "是否启用sniff")
    private Integer sniffEnabled;
    @Schema(title = "是否启用UDP")
    private Integer udpEnabled;
    @Schema(title = "是否TUN模式")
    private Integer tunEnabled;
    @Schema(title = "TUN stack：system、gvisor")
    private String tunStack;
    @Schema(title = "TUN自动路由")
    private Integer tunAutoRoute;
    @Schema(title = "TUN严格路由")
    private Integer tunStrictRoute;
    @Schema(title = "客户端入站扩展配置JSON")
    private String inboundOptions;
    @Schema(title = "是否启用")
    private Integer enabled;
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